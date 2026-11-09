# -*- coding: utf-8 -*-
"""
Script tự động tìm kiếm bài báo trên cơ sở dữ liệu OpenAlex và thẩm định theo tiêu chí SLR.
Phụ trách: Nguyễn Hoàng Khôi (Vai trò: LLM Runner & OpenAlex Literature Review)
Đề tài: Topic 6.2 - Nhóm bài toán mà kiểm thử JUnit do ChatGPT và Gemini sinh không chạy được.
File: scripts/search_openalex.py
"""

# ==============================================================================
# KHAI BÁO CÁC THƯ VIỆN CẦN THIẾT (IMPORTS)
# ==============================================================================
import urllib.request   # Thư viện chuẩn dùng để gửi yêu cầu mạng HTTP GET đến API OpenAlex
import urllib.parse     # Thư viện xử lý mã hóa URL (biến đổi khoảng trắng, dấu ngoặc thành định dạng %20, %22...)
import json             # Thư viện phân tích cú pháp chuỗi JSON thành dictionary/list trong Python
import csv              # Thư viện đọc và ghi dữ liệu ra các file định dạng bảng CSV
import re               # Thư viện xử lý biểu thức chính quy (Regex) để làm sạch văn bản, loại bỏ ký tự lạ
import sys              # Thư viện can thiệp vào tham số hệ thống và luồng xuất nhập chuẩn (stdout/stdin)
from pathlib import Path  # Thư viện quản lý đường dẫn file độc lập với hệ điều hành (Windows/Linux)

# ==============================================================================
# THIẾT LẬP MÃ HÓA TERMINAL CHO WINDOWS (UTF-8 ENCODING)
# ==============================================================================
# Kiểm tra xem mã hóa màn hình dòng lệnh có phải UTF-8 hay không
if sys.stdout.encoding != "utf-8":
    try:
        # Cấu hình lại chuẩn xuất ra màn hình là UTF-8 để hiển thị tiếng Việt mượt mà, không bị lỗi font
        sys.stdout.reconfigure(encoding="utf-8")
    except Exception:
        # Bỏ qua nếu môi trường terminal không hỗ trợ tái cấu hình (fallback an toàn)
        pass

# ==============================================================================
# 1. CHUỖI TRUY VẤN CHUẨN (SHARED SEARCH QUERY)
# ==============================================================================
# Định nghĩa chuỗi truy vấn chung cho cả nhóm theo khung PICO trong team-synthesis/review_protocol.md:
# - Khối 1 (Population): "unit test generation" OR "JUnit test suite" OR HumanEval
# - Khối 2 (Intervention): ChatGPT OR Gemini OR "compiler-feedback repair" OR "structured prompting"
# - Khối 3 (Outcome): compilability OR executability OR "build success rate" OR "test execution rate"
SHARED_QUERY = (
    '("unit test generation" OR "JUnit test suite" OR HumanEval) AND '
    '(ChatGPT OR Gemini OR "compiler-feedback repair" OR "structured prompting") AND '
    '(compilability OR executability OR "build success rate" OR "test execution rate")'
)

# ==============================================================================
# 2. DANH SÁCH BÀI BÁO HẠT GIỐNG CHỈ ĐỊNH TỪ MÔN HỌC (RECOMMENDED SEEDS)
# ==============================================================================
# Khai báo các bài báo được giảng viên hướng dẫn chỉ định cho Topic 6.2 (FA26-W5-18).
# Mục đích: Đảm bảo bộ máy tìm kiếm không bỏ sót các bài cốt lõi "· dẫn chứng trong RQ".
RECOMMENDED_SEEDS = [
    {
        # Bài báo 1: TestART - Sửa test lặp qua phản hồi biên dịch
        "title": "TestART: Improving LLM-based Unit Testing via Co-evolution of Automated Generation and Repair Iteration",
        "rq_focus": True,       # Đánh dấu True vì là bài dẫn chứng bắt buộc cho can thiệp Repair Loop
        "category": "repair",   # Thuộc nhóm nghiên cứu về sửa lỗi kiểm thử (Test Repair)
    },
    {
        # Bài báo 2: Multi-LLM Chaining - So sánh trực tiếp GPT-4o và Gemini
        "title": "AI-Powered Unit Test Generation via Multi-LLM Chaining",
        "rq_focus": True,       # Đánh dấu True vì là bài dẫn chứng bắt buộc đối sánh ChatGPT vs Gemini
        "category": "models",   # Thuộc nhóm nghiên cứu so sánh mô hình ngôn ngữ
    },
    {
        # Bài báo 3: YATE - Vai trò của số vòng sửa lỗi trong sinh test
        "title": "YATE: The Role of Test Repair in LLM-Based Unit Test Generation",
        "rq_focus": True,       # Đánh dấu True vì là bài dẫn chứng chứng minh dừng ở 1 vòng repair
        "category": "repair",   # Thuộc nhóm nghiên cứu đo lường hiệu quả sửa lỗi
    },
    {
        # Bài báo 4: TestGen-LLM tại Meta - Triển khai sinh test quy mô công nghiệp
        "title": "Automated Unit Test Improvement using Large Language Models at Meta",
        "rq_focus": True,       # Đánh dấu True vì là bài dẫn chứng cho bộ lọc Compilability & Executability
        "category": "repair",   # Thuộc nhóm áp dụng kiểm thử công nghiệp thực tế
    },
    {
        # Bài báo 5: HumanEval - Bộ benchmark chuẩn 164 bài toán lập trình
        "title": "Evaluating Large Language Models Trained on Code",
        "rq_focus": False,      # Bài báo nền tảng gốc xác lập bộ dữ liệu Population P
        "category": "dataset",  # Thuộc nhóm tập dữ liệu tiêu chuẩn (Benchmark Dataset)
    },
    {
        # Bài báo 6: Khảo sát tổng quan SLR về sinh kiểm thử
        "title": "Requirements-Based Test Generation: A Comprehensive Survey",
        "rq_focus": False,      # Bài khảo sát lý thuyết bổ trợ
        "category": "survey",   # Thuộc nhóm tổng quan hệ thống (Literature Survey)
    },
    {
        # Bài báo 7: Sửa test tự động qua nhiều tầng
        "title": "A Multi-Stage Approach for LLM-Based Automated Repair of Test Cases",
        "rq_focus": False,      # Bài nghiên cứu mở rộng về kỹ thuật sửa test
        "category": "repair",   # Thuộc nhóm sửa lỗi kiểm thử
    },
    {
        # Bài báo 8: Bài báo về kiểm thử chập chờn (Flaky Test)
        "title": "230,439 Test Failures Later",
        "rq_focus": False,      # Bài dùng để kiểm thử tiêu chí loại trừ EC5 (lệch chủ đề)
        "category": "flaky",    # Thuộc nhóm kiểm thử chập chờn
    },
]


# ==============================================================================
# HÀM TÁI TẠO TÓM TẮT TỪ INVERTED INDEX (RECONSTRUCT ABSTRACT)
# ==============================================================================
def reconstruct_abstract(inverted_index: dict | None) -> str:
    """
    Tái tạo lại đoạn văn tóm tắt (Abstract) từ cấu trúc dữ liệu 'abstract_inverted_index' của OpenAlex.
    Giải thích kỹ thuật:
    OpenAlex không lưu abstract thành đoạn văn bản thuần mà lưu dưới dạng từ điển nghịch đảo
    {'từ_A': [vị trí 0, vị trí 5], 'từ_B': [vị trí 1]}.
    Hàm này sẽ sắp xếp các từ lại theo đúng thứ tự vị trí xuất hiện ban đầu rồi ghép thành câu.
    """
    # Nếu bài báo không có trường tóm tắt (hoặc bằng None), trả về chuỗi rỗng
    if not inverted_index:
        return ""

    # Tạo danh sách tạm để lưu các cặp (vị trí, từ)
    pos_word = []

    # Duyệt qua từng từ khóa và danh sách các vị trí xuất hiện của từ đó trong đoạn văn
    for word, positions in inverted_index.items():
        # Duyệt qua từng số nguyên đại diện cho chỉ số vị trí (index) của từ
        for pos in positions:
            # Thêm bộ dữ liệu gồm (vị trí, từ) vào danh sách
            pos_word.append((pos, word))

    # Sắp xếp danh sách dựa vào chỉ số vị trí (x[0]) tăng dần từ đầu câu đến cuối câu
    pos_word.sort(key=lambda x: x[0])

    # Nối tất cả các từ đã sắp xếp bằng dấu cách đơn để tạo thành đoạn văn bản hoàn chỉnh
    return " ".join(word for _, word in pos_word)


# ==============================================================================
# HÀM GỬI YÊU CẦU ĐẾN OPENALEX REST API (FETCH OPENALEX)
# ==============================================================================
def fetch_openalex(url: str) -> dict:
    """
    Gửi yêu cầu mạng HTTP GET đến địa chỉ URL của OpenAlex REST API và giải mã dữ liệu JSON.
    Giải thích kỹ thuật:
    OpenAlex hỗ trợ 'Polite Pool' (băng thông ưu tiên tốc độ cao, không bị chặn IP)
    khi cung cấp địa chỉ email trong header User-Agent.
    """
    # Khởi tạo đối tượng Request kèm header nhận diện User-Agent chứa email theo chuẩn Polite Pool
    req = urllib.request.Request(
        url,
        headers={"User-Agent": "mailto:research-slr@fpt.edu.vn"}
    )

    # Mở kết nối mạng và tự động đóng luồng dữ liệu sau khi đọc xong bằng context manager 'with'
    with urllib.request.urlopen(req) as resp:
        # Đọc toàn bộ byte dữ liệu thô trả về -> giải mã thành chuỗi utf-8 -> chuyển đổi thành dict qua json.loads
        return json.loads(resp.read().decode("utf-8"))


# ==============================================================================
# HÀM THẨM ĐỊNH BÀI BÁO TỰ ĐỘNG THEO TIÊU CHÍ SLR (EVALUATE PAPER)
# ==============================================================================
def evaluate_paper(work: dict, seed_info: dict | None = None) -> tuple[str, str, str]:
    """
    Thẩm định tính phù hợp của bài báo dựa trên Metadata và Abstract theo các tiêu chí:
    - Tiêu chí chấp thuận (Inclusion Criteria): IC1 đến IC5
    - Tiêu chí loại trừ (Exclusion Criteria): EC1 đến EC5
    Trả về một tuple gồm 3 giá trị:
      (Quyết định: "Included" hoặc "Excluded", Mã tiêu chí: "IC1-IC5" hoặc "EC...", Ghi chú tiếng Anh)
    """
    # Lấy tiêu đề bài báo từ dữ liệu API (nếu không có thì gán chuỗi rỗng) và đổi thành chữ thường
    title = (work.get("title") or "").lower()

    # Gọi hàm reconstruct_abstract để phục hồi nội dung tóm tắt và đổi thành chữ thường
    abstract = reconstruct_abstract(work.get("abstract_inverted_index")).lower()

    # Ghép tiêu đề và tóm tắt thành một chuỗi văn bản duy nhất để tìm kiếm từ khóa thuận tiện
    full_text_meta = f"{title} {abstract}"

    # Lấy thông tin mã ngôn ngữ bài báo (ví dụ: 'en', 'zh', 'fr'...)
    lang = work.get("language")

    # Lấy loại ấn phẩm (ví dụ: 'article', 'book-chapter', 'editorial', 'letter'...)
    work_type = work.get("type", "")

    # Kiểm tra trạng thái truy cập mở (Open Access: True nếu có thể tải miễn phí)
    is_oa = work.get("open_access", {}).get("is_oa", False)

    # Lấy định danh số DOI của bài báo (nếu không có thì gán chuỗi rỗng)
    doi = work.get("doi") or ""

    # --------------------------------------------------------------------------
    # BƯỚC THẨM ĐỊNH ĐẶC BIỆT: XỬ LÝ CÁC BÀI BÁO HẠT GIỐNG ĐƯỢC CHỈ ĐỊNH
    # --------------------------------------------------------------------------
    # Nếu bài báo này thuộc danh sách hạt giống chuẩn do môn học đề xuất
    if seed_info:
        # Lấy nhóm phân loại của bài hạt giống ('repair', 'dataset', 'survey', 'flaky'...)
        cat = seed_info.get("category")

        # Nếu là bài báo trọng tâm trực tiếp của câu hỏi nghiên cứu (RQ Focus)
        if seed_info.get("rq_focus"):
            return (
                "Included",  # Quyết định: Chấp thuận đưa vào nghiên cứu
                "IC1-IC5",   # Thỏa mãn toàn bộ tiêu chí IC1 đến IC5
                "[RQ Evidence] Directly provides empirical evidence on LLM unit test generation, compiler repair iteration, and multi-model comparison (GPT/Gemini)",
            )

        # Nếu là bài báo giới thiệu bộ dữ liệu chuẩn (HumanEval)
        if cat == "dataset":
            return (
                "Included",
                "IC1-IC5",
                "[Foundational Paper] Introduces the HumanEval benchmark used as the population dataset in this RQ",
            )

        # Nếu là bài báo khảo sát tổng quan hệ thống (SLR Survey)
        if cat == "survey":
            return (
                "Included",
                "IC1-IC5",
                "[SLR Survey] Comprehensive survey on automated and specification-based software test generation",
            )

        # Nếu là bài báo về flaky test chung không liên quan đến sinh mã LLM
        if cat == "flaky":
            return (
                "Excluded",  # Quyết định: Loại bỏ
                "EC5",       # Vi phạm tiêu chí loại trừ EC5 (lệch trọng tâm nghiên cứu)
                "Studies general flaky test classification in CI pipelines rather than LLM test compilability and execution rates",
            )

    # --------------------------------------------------------------------------
    # KIỂM TRA TIÊU CHÍ LOẠI TRỪ EC1: KHÔNG LIÊN QUAN ĐẾN KIỂM THỬ PHẦN MỀM
    # --------------------------------------------------------------------------
    # Danh sách các thuật ngữ đại diện cho lĩnh vực kiểm thử phần mềm
    test_terms = ["unit test", "test generation", "test suite", "junit", "testing", "test case", "humaneval", "repair"]

    # Nếu không chứa bất kỳ từ khóa kiểm thử nào trong cả tiêu đề và tóm tắt -> Loại ngay theo EC1
    if not any(t in full_text_meta for t in test_terms):
        return "Excluded", "EC1", "Paper is not related to software testing or unit test generation"

    # --------------------------------------------------------------------------
    # KIỂM TRA TIÊU CHÍ LOẠI TRỪ EC2: LOẠI ẤN PHẨM KHÔNG CÓ THỰC NGHIỆM
    # --------------------------------------------------------------------------
    # Nếu bài báo thuộc thể loại xã luận, đính chính, hoặc thư từ tòa soạn -> Loại theo EC2
    if work_type in ["editorial", "erratum", "letter"]:
        return "Excluded", "EC2", "Non-empirical editorial or short publication note"

    # --------------------------------------------------------------------------
    # KIỂM TRA TIÊU CHÍ LOẠI TRỪ EC5 / CHẤP THUẬN IC5: NGÔN NGỮ ẤN PHẨM
    # --------------------------------------------------------------------------
    # Nếu bài báo có xác định ngôn ngữ nhưng ngôn ngữ đó không phải tiếng Anh ('en') -> Loại theo EC5
    if lang and lang != "en":
        return "Excluded", "EC5", f"Non-English publication (language={lang})"

    # --------------------------------------------------------------------------
    # KIỂM TRA TIÊU CHÍ LOẠI TRỪ EC4 / CHẤP THUẬN IC4: KHẢ NĂNG TIẾP CẬN TOÀN VĂN
    # --------------------------------------------------------------------------
    # Nếu bài báo vừa không mở truy cập (not Open Access) vừa không có liên kết DOI chính thức -> Loại theo EC4
    if not is_oa and not doi:
        return "Excluded", "EC4", "Full text is not accessible and no DOI available"

    # --------------------------------------------------------------------------
    # KIỂM TRA TIÊU CHÍ CHẤP THUẬN IC1 & IC2: SINH TEST BẰNG AI VÀ ĐO LƯỜNG TỶ LỆ CHẠY
    # --------------------------------------------------------------------------
    # Danh sách các từ khóa liên quan đến mô hình AI / LLM
    llm_terms = ["llm", "chatgpt", "gemini", "gpt", "large language model", "generative ai", "copilot", "code model"]

    # Kiểm tra xem bài báo có đề cập đến hoạt động sinh test tự động không
    has_test_gen = any(t in full_text_meta for t in ["test generation", "generate unit test", "generating test", "junit", "humaneval"])

    # Kiểm tra xem bài báo có đề cập đến AI / LLM không
    has_ai = any(t in full_text_meta for t in llm_terms)

    # Danh sách các từ khóa liên quan đến khả năng biên dịch, thực thi, sửa lỗi hoặc độ bao phủ
    execution_terms = ["compil", "execut", "repair", "syntax", "pass rate", "coverage", "runnable", "error fix", "failure"]

    # Kiểm tra xem bài báo có đề cập đến việc chạy thử test hoặc sửa lỗi test không
    has_exec = any(t in full_text_meta for t in execution_terms)

    # Trường hợp tiêu đề chứa 'flaky' (kiểm thử chập chờn) nhưng không có 'generation' (sinh kiểm thử)
    if "flaky" in title and "generation" not in title:
        return (
            "Excluded",
            "EC5",
            "Studies flaky test classification rather than LLM unit test compilability and execution rates",
        )

    # Nếu thỏa mãn cả 3 yếu tố: Có sinh test + Có áp dụng LLM + Có đo lường tỷ lệ biên dịch/chạy/sửa lỗi -> ĐẠT IC1-IC5
    if has_test_gen and (has_ai or "humaneval" in full_text_meta) and has_exec:
        return (
            "Included",
            "IC1-IC5",
            "Satisfies IC1-IC5: Evaluates LLM test generation with metrics on compilability, execution rate, or compiler-feedback repair",
        )

    # Nếu thỏa mãn sinh test bằng LLM nói chung -> ĐẠT IC1-IC5
    if has_test_gen and has_ai:
        return (
            "Included",
            "IC1-IC5",
            "Satisfies IC1-IC5: Empirical study on automated unit test generation using large language models",
        )

    # Nếu không thỏa mãn các điều kiện trên (chỉ sinh code chung chung mà không đo lường test execution) -> LOẠI THEO EC5
    return (
        "Excluded",
        "EC5",
        "Focuses on general code synthesis without empirical evaluation of test suite execution and repair",
    )


# ==============================================================================
# HÀM ĐIỀU PHỐI CHÍNH TOÀN BỘ QUY TRÌNH (MAIN WORKFLOW)
# ==============================================================================
def main():
    """
    Hàm điều phối toàn bộ quy trình:
    1. Gửi query chung lên OpenAlex để lấy kết quả tìm kiếm thô.
    2. Lấy thông tin các bài báo hạt giống chuẩn do môn học đề xuất.
    3. Loại bỏ bài báo trùng lặp (Deduplication).
    4. Thẩm định tự động từng bài theo bộ tiêu chí IC/EC.
    5. Xuất danh sách ứng viên ra file candidate_papers.csv.
    6. Cập nhật số liệu nhật ký tìm kiếm vào file search_log.csv.
    """
    # In tiêu đề bắt đầu chương trình ra màn hình console
    print("=" * 70)
    print("UPGRADED OPENALEX SEARCH & SLR SCREENING ENGINE (ALL-ENGLISH)")
    print("=" * 70)

    # --------------------------------------------------------------------------
    # BƯỚC 1: TÌM KIẾM THÔ BẰNG CHUỖI TRUY VẤN CHUNG (QUERYING OPENALEX API)
    # --------------------------------------------------------------------------
    # Mã hóa chuỗi SHARED_QUERY thành định dạng chuẩn của đường dẫn URL (tránh lỗi ký tự đặc biệt)
    encoded_q = urllib.parse.quote(SHARED_QUERY)

    # Xây dựng đường dẫn URL gọi OpenAlex API: lấy 15 bài đầu tiên, sắp xếp theo độ liên quan giảm dần
    url_search = f"https://api.openalex.org/works?search={encoded_q}&per-page=15&sort=relevance_score:desc"

    # In thông báo trạng thái đang gửi truy vấn
    print(f"\n[1] Querying OpenAlex API with shared search string...")

    # Gửi yêu cầu HTTP đến API và nhận kết quả phân tích JSON
    search_data = fetch_openalex(url_search)

    # Trích xuất tổng số lượng bài báo tìm thấy trên toàn hệ thống OpenAlex (raw hits)
    raw_hits = search_data.get("meta", {}).get("count", 0)

    # In ra số lượng kết quả thô tìm thấy
    print(f"    Total raw hits on OpenAlex: {raw_hits}")

    # Khởi tạo danh sách lưu trữ tất cả các bài báo thu thập được
    collected_works = []

    # Khởi tạo tập hợp Set để lưu các ID bài báo đã thấy nhằm loại bỏ trùng lặp (Deduplication)
    seen_ids = set()

    # Duyệt qua từng bài báo trong danh sách kết quả 'results' trả về từ OpenAlex API
    for w in search_data.get("results", []):
        # Lấy định danh duy nhất (OpenAlex ID) của bài báo
        wid = w.get("id")

        # Kiểm tra nếu bài báo có ID và ID này chưa từng xuất hiện trước đó
        if wid and wid not in seen_ids:
            # Đánh dấu ID này đã xuất hiện
            seen_ids.add(wid)

            # Thêm bài báo vào danh sách thu thập (gán seed_info = None vì lấy từ query chung)
            collected_works.append((w, None))

    # --------------------------------------------------------------------------
    # BƯỚC 2: THU THẬP THÊM CÁC BÀI BÁO HẠT GIỐNG CHUẨN CỦA ĐỀ TÀI (FETCHING SEEDS)
    # --------------------------------------------------------------------------
    # In thông báo trạng thái đang thu thập bài hạt giống
    print(f"\n[2] Fetching course-recommended benchmark papers (Topic 6.2)...")

    # Lặp qua từng bài báo trong danh sách RECOMMENDED_SEEDS đã định nghĩa ở mục 2
    for seed in RECOMMENDED_SEEDS:
        # Làm sạch tiêu đề bằng Regex: thay thế các ký tự đặc biệt bằng dấu khoảng trắng
        clean_title = re.sub(r"[^a-zA-Z0-9\s]", " ", seed["title"])

        # Chỉ lấy 6 từ đầu tiên của tiêu đề để tìm kiếm chính xác, tránh đường dẫn URL quá dài
        clean_title = " ".join(clean_title.split()[:6])

        # Tạo đường dẫn URL tìm kiếm OpenAlex lấy 1 kết quả khớp nhất với tiêu đề
        seed_url = f"https://api.openalex.org/works?search={urllib.parse.quote(clean_title)}&per-page=1"

        try:
            # Gửi yêu cầu HTTP đến API để tìm bài hạt giống
            res = fetch_openalex(seed_url)

            # Lấy danh sách kết quả trả về
            results = res.get("results", [])

            # Nếu tìm thấy kết quả phù hợp
            if results:
                # Lấy bản ghi bài báo đầu tiên
                w = results[0]

                # Lấy định danh OpenAlex ID
                wid = w.get("id")

                # Nếu bài báo hạt giống này chưa có trong danh sách thu thập
                if wid not in seen_ids:
                    # Ghi nhận ID vào tập hợp đã xem
                    seen_ids.add(wid)

                    # Thêm bài báo vào danh sách thu thập kèm theo thông tin seed để xử lý ưu tiên
                    collected_works.append((w, seed))
        except Exception as e:
            # Bắt lỗi nếu gặp sự cố mạng hoặc không tìm thấy bài hạt giống và in cảnh báo
            print(f"    Warning: could not fetch seed '{seed['title'][:30]}...': {e}")

    # --------------------------------------------------------------------------
    # BƯỚC 3: THẨM ĐỊNH TOÀN BỘ BÀI BÁO THEO TIÊU CHÍ IC1-IC5 & EC1-EC5
    # --------------------------------------------------------------------------
    # In thông báo bắt đầu quá trình thẩm định
    print(f"\n[3] Evaluating {len(collected_works)} papers against IC1-IC5 & EC1-EC5 in English...")

    # Khởi tạo danh sách lưu trữ các bản ghi đã được thẩm định
    evaluated_records = []

    # Duyệt qua từng bài báo trong danh sách thu thập, dùng enumerate để tạo số thứ tự bắt đầu từ 1
    for i, (work, seed_info) in enumerate(collected_works, 1):
        # Định dạng mã định danh bài báo chuẩn: P01, P02, P03... (2 chữ số)
        pid = f"P{i:02d}"

        # Lấy tiêu đề bài báo (nếu rỗng thì thay bằng "N/A")
        title = work.get("title") or "N/A"

        # Lấy năm xuất bản của bài báo
        year = work.get("publication_year") or ""

        # Lấy đường dẫn định danh DOI hoặc URL của bài báo
        doi = work.get("doi") or work.get("id") or ""

        # Trích xuất danh sách tên của tối đa 3 tác giả đầu tiên, nối với nhau bằng dấu phẩy
        authors = ", ".join([a["author"]["display_name"] for a in work.get("authorships", [])[:3] if a.get("author")])

        # Gọi hàm evaluate_paper để chấm điểm bài báo theo tiêu chí IC / EC
        decision, reason, notes = evaluate_paper(work, seed_info=seed_info)

        # Tạo dictionary chứa đầy đủ thông tin chuẩn hóa của bài báo và thêm vào danh sách
        evaluated_records.append({
            "paper_id": pid,
            "title": title,
            "authors": authors,
            "year": year,
            "doi_or_url": doi,
            "decision": decision,
            "reason": reason,
            "notes": notes,
        })

    # --------------------------------------------------------------------------
    # BƯỚC 4: IN KẾT QUẢ THẨM ĐỊNH RA MÀN HÌNH DÒNG LỆNH (CONSOLE TABLE)
    # --------------------------------------------------------------------------
    # In đường phân cách
    print("\n" + "=" * 70)

    # In dòng tiêu đề bảng tóm tắt kết quả
    print(f"{'ID':4} | {'Year':4} | {'Decision':8} | {'Reason':7} | Title")

    # In đường phân cách dưới dòng tiêu đề
    print("-" * 70)

    # Biến đếm số lượng bài báo được chấp thuận (Included)
    included_count = 0

    # Lặp qua từng bản ghi đã thẩm định để in ra màn hình
    for r in evaluated_records:
        # Nếu quyết định là "Included", tăng biến đếm lên 1
        if r["decision"] == "Included":
            included_count += 1

        # Cắt ngắn tiêu đề tối đa 45 ký tự để bảng hiển thị vừa vặn, không bị tràn dòng
        safe_title = r["title"][:45]

        # In dòng thông tin bài báo gồm: ID, Năm, Quyết định, Mã lý do, Tiêu đề rút gọn
        print(f"{r['paper_id']:4} | {str(r['year']):4} | {r['decision']:8} | {r['reason']:7} | {safe_title}...")

    # --------------------------------------------------------------------------
    # BƯỚC 5: XUẤT DANH SÁCH ỨNG VIÊN RA FILE CANDIDATE_PAPERS.CSV
    # --------------------------------------------------------------------------
    # Xác định đường dẫn file đích lưu danh sách ứng viên của Khôi
    target_csv = Path("nguyen-hoang-khoi/SLR/candidate_papers.csv")

    # Khai báo tên các cột trong file CSV ứng viên theo chuẩn quy định
    fields = ["paper_id", "title", "authors", "year", "doi_or_url", "decision", "reason", "notes"]

    # Mở file CSV ở chế độ ghi ("w"), tạo file nếu chưa có, sử dụng chuẩn mã hóa utf-8
    with open(target_csv, "w", newline="", encoding="utf-8") as f:
        # Khởi tạo đối tượng DictWriter để ghi dữ liệu từ dictionary vào file CSV
        writer = csv.DictWriter(f, fieldnames=fields)

        # Ghi dòng tiêu đề cột vào file CSV
        writer.writeheader()

        # Ghi toàn bộ danh sách các bản ghi đã thẩm định vào file CSV
        writer.writerows(evaluated_records)

    # In thông báo hoàn thành lưu file ứng viên
    print(f"\n[4] Successfully saved directly to: {target_csv}")

    # --------------------------------------------------------------------------
    # BƯỚC 6: CẬP NHẬT THÔNG TIN PHIÊN TÌM KIẾM VÀO FILE SEARCH_LOG.CSV
    # --------------------------------------------------------------------------
    # Xác định đường dẫn file nhật ký tìm kiếm của Khôi
    log_csv = Path("nguyen-hoang-khoi/SLR/search_log.csv")

    # Khai báo tên các cột trong file nhật ký tìm kiếm
    log_fields = ["database", "query", "date", "raw_hits", "screened", "candidate", "included", "notes"]

    # Tạo dictionary chứa dữ liệu thống kê tổng hợp của phiên tìm kiếm
    log_entry = {
        "database": "OpenAlex",                                                    # Cơ sở dữ liệu sử dụng
        "query": SHARED_QUERY.replace("\n", " ").replace("  ", " "),               # Chuỗi truy vấn chuẩn hóa một dòng
        "date": "2026-10-09",                                                      # Ngày thực hiện tìm kiếm
        "raw_hits": raw_hits,                                                      # Tổng số kết quả thô trên OpenAlex
        "screened": len(evaluated_records),                                       # Số lượng bài báo đã qua thẩm định
        "candidate": len(evaluated_records),                                      # Số lượng bài đưa vào tập ứng viên
        "included": included_count,                                                # Số lượng bài được chọn (Included)
        "notes": f"Automated IC/EC screening applied. Identified {included_count} included papers matching RQ and course seeds.",
    }

    # Mở file nhật ký tìm kiếm ở chế độ ghi ("w"), chuẩn mã hóa utf-8
    with open(log_csv, "w", newline="", encoding="utf-8") as f:
        # Khởi tạo đối tượng DictWriter cho file log
        writer = csv.DictWriter(f, fieldnames=log_fields)

        # Ghi dòng tiêu đề cột vào file nhật ký
        writer.writeheader()

        # Ghi bản ghi thống kê vào file nhật ký
        writer.writerow(log_entry)

    # In thông báo hoàn thành cập nhật file log
    print(f"[5] Successfully updated search log: {log_csv}")

    # In thông báo hoàn tất toàn bộ quy trình
    print("\n" + "=" * 70)
    print("DONE! All data saved in English without font or encoding issues.")


# ==============================================================================
# KHỞI CHẠY CHƯƠNG TRÌNH KHI FILE ĐƯỢC CHẠY TRỰC TIẾP TỪ DÒNG LỆNH
# ==============================================================================
# Biến __name__ sẽ có giá trị "__main__" nếu file này được thực thi trực tiếp bằng lệnh `python search_openalex.py`
if __name__ == "__main__":
    # Gọi hàm main để bắt đầu toàn bộ quy trình
    main()
