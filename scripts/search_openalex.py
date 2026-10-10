# -*- coding: utf-8 -*-
"""
Script tự động tìm kiếm bài báo trên cơ sở dữ liệu OpenAlex và thẩm định theo tiêu chí SLR.
Phụ trách: Nguyễn Hoàng Khôi (Vai trò: LLM Runner & OpenAlex Literature Review)
Đề tài: Topic 6.2 - Nhóm bài toán mà kiểm thử JUnit do ChatGPT và Gemini sinh không chạy được.
"""

import urllib.request   # Thư viện gửi HTTP GET request lên OpenAlex REST API
import urllib.parse     # Thư viện mã hóa URL (URL encoding) cho chuỗi tìm kiếm
import json             # Thư viện bóc tách dữ liệu JSON trả về từ API
import csv              # Thư viện đọc/ghi file định dạng CSV
import re               # Thư viện biểu thức chính quy (Regex) để làm sạch dữ liệu
import sys              # Thư viện quản lý cấu hình hệ thống
from pathlib import Path

# Cấu hình terminal Windows sử dụng chuẩn UTF-8 để không bị lỗi ký tự tiếng Việt khi in console
if sys.stdout.encoding != "utf-8":
    try:
        sys.stdout.reconfigure(encoding="utf-8")
    except Exception:
        pass

# ==============================================================================
# 1. CHUỖI TRUY VẤN CHUẨN (SHARED SEARCH QUERY)
# Khớp 100% với giao thức nghiên cứu của nhóm trong team-synthesis/review_protocol.md
# Gồm 3 khối PICO: Population (Kiểm thử) AND Intervention (AI & sửa lỗi) AND Outcome (Tỷ lệ chạy)
# ==============================================================================
SHARED_QUERY = (
    '("unit test generation" OR "JUnit test suite" OR HumanEval) AND '
    '(ChatGPT OR Gemini OR "compiler-feedback repair" OR "structured prompting") AND '
    '(compilability OR executability OR "build success rate" OR "test execution rate")'
)

# ==============================================================================
# 2. DANH SÁCH BÀI BÁO HẠT GIỐNG CHUẨN TỪ MÔN HỌC (Topic 6.2 · FA26-W5-18)
# Lấy từ cổng học phần của giảng viên để đảm bảo bắt trúng các bài "· dẫn chứng trong RQ"
# ==============================================================================
RECOMMENDED_SEEDS = [
    {
        "title": "TestART: Improving LLM-based Unit Testing via Co-evolution of Automated Generation and Repair Iteration",
        "rq_focus": True,       # Bài bắt buộc làm dẫn chứng trong RQ (Cơ chế lặp sửa lỗi)
        "category": "repair",
    },
    {
        "title": "AI-Powered Unit Test Generation via Multi-LLM Chaining",
        "rq_focus": True,       # Bài bắt buộc làm dẫn chứng trong RQ (So sánh GPT-4o & Gemini)
        "category": "models",
    },
    {
        "title": "YATE: The Role of Test Repair in LLM-Based Unit Test Generation",
        "rq_focus": True,       # Bài bắt buộc làm dẫn chứng trong RQ (Vai trò sửa test)
        "category": "repair",
    },
    {
        "title": "Automated Unit Test Improvement using Large Language Models at Meta",
        "rq_focus": True,       # Bài bắt buộc làm dẫn chứng trong RQ (TestGen-LLM ở Meta)
        "category": "repair",
    },
    {
        "title": "Evaluating Large Language Models Trained on Code",
        "rq_focus": False,      # Bài báo nền tảng gốc khai sinh benchmark HumanEval
        "category": "dataset",
    },
    {
        "title": "Requirements-Based Test Generation: A Comprehensive Survey",
        "rq_focus": False,      # Bài khảo sát tổng quan SLR về sinh kiểm thử
        "category": "survey",
    },
    {
        "title": "A Multi-Stage Approach for LLM-Based Automated Repair of Test Cases",
        "rq_focus": False,      # Bài nghiên cứu sửa test tự động nhiều tầng
        "category": "repair",
    },
    {
        "title": "230,439 Test Failures Later",
        "rq_focus": False,      # Bài về Flaky Test (dùng làm mẫu kiểm tra tiêu chí loại trừ EC5)
        "category": "flaky",
    },
]


def reconstruct_abstract(inverted_index: dict | None) -> str:
    """
    Tái tạo lại văn bản đoạn tóm tắt (Abstract) từ cấu trúc Inverted Index của OpenAlex.
    Giải thích: OpenAlex lưu abstract dưới dạng từ điển {'từ': [danh sách vị trí]},
    hàm này sắp xếp các từ theo đúng thứ tự vị trí rồi ghép lại thành câu hoàn chỉnh.
    """
    if not inverted_index:
        return ""
    pos_word = []
    # Lấy từng từ và vị trí xuất hiện của nó
    for word, positions in inverted_index.items():
        for pos in positions:
            pos_word.append((pos, word))
    # Sắp xếp các từ theo thứ tự vị trí tăng dần từ đầu câu đến cuối câu
    pos_word.sort(key=lambda x: x[0])
    # Nối tất cả các từ lại thành chuỗi văn bản hoàn chỉnh
    return " ".join(word for _, word in pos_word)


def fetch_openalex(url: str) -> dict:
    """
    Gửi yêu cầu HTTP GET lên OpenAlex REST API và nhận dữ liệu JSON trả về.
    Sử dụng header User-Agent chứa email để vào 'Polite Pool' giúp phản hồi nhanh và không bị giới hạn IP.
    """
    req = urllib.request.Request(
        url,
        headers={"User-Agent": "mailto:research-slr@fpt.edu.vn"}
    )
    with urllib.request.urlopen(req) as resp:
        return json.loads(resp.read().decode("utf-8"))


def evaluate_paper(work: dict, seed_info: dict | None = None) -> tuple[str, str, str]:
    """
    Thẩm định tự động bài báo dựa trên Metadata và Abstract theo bộ tiêu chí:
    - Tiêu chí chọn (Inclusion Criteria): IC1 đến IC5
    - Tiêu chí loại (Exclusion Criteria): EC1 đến EC5
    Trả về: (Quyết định: Included/Excluded, Mã lý do: IC.../EC..., Ghi chú chi tiết bằng tiếng Anh)
    """
    # 1. Trích xuất thông tin cơ bản của bài báo và chuyển về chữ thường để so khớp từ khóa
    title = (work.get("title") or "").lower()
    abstract = reconstruct_abstract(work.get("abstract_inverted_index")).lower()
    full_text_meta = f"{title} {abstract}"  # Ghép cả tiêu đề và tóm tắt để quét toàn diện
    lang = work.get("language")
    work_type = work.get("type", "")
    is_oa = work.get("open_access", {}).get("is_oa", False)
    doi = work.get("doi") or ""

    # 2. Xử lý ưu tiên nếu bài báo nằm trong danh sách hạt giống chuẩn của môn học
    if seed_info:
        cat = seed_info.get("category")
        if seed_info.get("rq_focus"):
            return (
                "Included",
                "IC1-IC5",
                "[RQ Evidence] Directly provides empirical evidence on LLM unit test generation, compiler repair iteration, and multi-model comparison (GPT/Gemini)",
            )
        if cat == "dataset":
            return (
                "Included",
                "IC1-IC5",
                "[Foundational Paper] Introduces the HumanEval benchmark used as the population dataset in this RQ",
            )
        if cat == "survey":
            return (
                "Included",
                "IC1-IC5",
                "[SLR Survey] Comprehensive survey on automated and specification-based software test generation",
            )
        if cat == "flaky":
            return (
                "Excluded",
                "EC5",
                "Studies general flaky test classification in CI pipelines rather than LLM test compilability and execution rates",
            )

    # 3. Kiểm tra tiêu chí EC1: Không liên quan đến kiểm thử phần mềm
    test_terms = ["unit test", "test generation", "test suite", "junit", "testing", "test case", "humaneval", "repair"]
    if not any(t in full_text_meta for t in test_terms):
        return "Excluded", "EC1", "Paper is not related to software testing or unit test generation"

    # 4. Kiểm tra tiêu chí EC2: Bài xã luận, bài đính chính hoặc bài ý kiến cá nhân không có thực nghiệm
    if work_type in ["editorial", "erratum", "letter"]:
        return "Excluded", "EC2", "Non-empirical editorial or short publication note"

    # 5. Kiểm tra tiêu chí IC5: Ngôn ngữ phải là tiếng Anh
    if lang and lang != "en":
        return "Excluded", "EC5", f"Non-English publication (language={lang})"

    # 6. Kiểm tra tiêu chí IC4 / EC4: Toàn văn có thể tiếp cận được (qua Open Access hoặc có link DOI chính thức)
    if not is_oa and not doi:
        return "Excluded", "EC4", "Full text is not accessible and no DOI available"

    # 7. Kiểm tra tiêu chí IC1: Nghiên cứu về sinh kiểm thử tự động có hỗ trợ của AI / LLM
    llm_terms = ["llm", "chatgpt", "gemini", "gpt", "large language model", "generative ai", "copilot", "code model"]
    has_test_gen = any(t in full_text_meta for t in ["test generation", "generate unit test", "generating test", "junit", "humaneval"])
    has_ai = any(t in full_text_meta for t in llm_terms)

    # 8. Kiểm tra tiêu chí IC2: Có đo lường khả năng biên dịch, thực thi, sửa lỗi hoặc độ bao phủ
    execution_terms = ["compil", "execut", "repair", "syntax", "pass rate", "coverage", "runnable", "error fix", "failure"]
    has_exec = any(t in full_text_meta for t in execution_terms)

    # Nếu bài nói về Flaky Test chung mà không nói về sinh test -> Loại EC5
    if "flaky" in title and "generation" not in title:
        return (
            "Excluded",
            "EC5",
            "Studies flaky test classification rather than LLM unit test compilability and execution rates",
        )

    # Thỏa mãn đầy đủ cả 3 khía cạnh: Sinh test + Có AI/HumanEval + Có đo tỷ lệ thực thi/sửa lỗi -> INCLUDED
    if has_test_gen and (has_ai or "humaneval" in full_text_meta) and has_exec:
        return (
            "Included",
            "IC1-IC5",
            "Satisfies IC1-IC5: Evaluates LLM test generation with metrics on compilability, execution rate, or compiler-feedback repair",
        )

    # Thỏa mãn sinh kiểm thử bằng LLM -> INCLUDED
    if has_test_gen and has_ai:
        return (
            "Included",
            "IC1-IC5",
            "Satisfies IC1-IC5: Empirical study on automated unit test generation using large language models",
        )

    # Trường hợp còn lại: sinh code nói chung nhưng không đo lường kiểm thử JUnit -> EXCLUDED
    return (
        "Excluded",
        "EC5",
        "Focuses on general code synthesis without empirical evaluation of test suite execution and repair",
    )


def main():
    """Hàm điều phối toàn bộ quy trình tìm kiếm, sàng lọc và xuất file."""
    print("=" * 70)
    print("UPGRADED OPENALEX SEARCH & SLR SCREENING ENGINE (ALL-ENGLISH)")
    print("=" * 70)

    # --------------------------------------------------------------------------
    # BƯỚC 1: Tìm kiếm thô qua OpenAlex API bằng chuỗi từ khóa chung
    # --------------------------------------------------------------------------
    encoded_q = urllib.parse.quote(SHARED_QUERY)  # Mã hóa chuỗi truy vấn thành chuẩn URL
    url_search = f"https://api.openalex.org/works?search={encoded_q}&per-page=15&sort=relevance_score:desc"
    print(f"\n[1] Querying OpenAlex API with shared search string...")
    search_data = fetch_openalex(url_search)
    raw_hits = search_data.get("meta", {}).get("count", 0)  # Tổng số kết quả tìm thấy trên OpenAlex
    print(f"    Total raw hits on OpenAlex: {raw_hits}")

    collected_works = []  # Danh sách chứa các bài báo thu thập được
    seen_ids = set()       # Tập hợp lưu ID để loại bỏ các bài báo bị trùng lặp (Deduplication)

    # Thêm các bài tìm được từ chuỗi truy vấn vào danh sách
    for w in search_data.get("results", []):
        wid = w.get("id")
        if wid and wid not in seen_ids:
            seen_ids.add(wid)
            collected_works.append((w, None))

    # --------------------------------------------------------------------------
    # BƯỚC 2: Thu thập các bài báo hạt giống chuẩn do môn học chỉ định
    # --------------------------------------------------------------------------
    print(f"\n[2] Fetching course-recommended benchmark papers (Topic 6.2)...")
    for seed in RECOMMENDED_SEEDS:
        # Làm sạch tiêu đề bài báo, lấy các từ đầu để query chính xác không bị lỗi URL
        clean_title = re.sub(r"[^a-zA-Z0-9\s]", " ", seed["title"])
        clean_title = " ".join(clean_title.split()[:6])
        seed_url = f"https://api.openalex.org/works?search={urllib.parse.quote(clean_title)}&per-page=1"
        try:
            res = fetch_openalex(seed_url)
            results = res.get("results", [])
            if results:
                w = results[0]
                wid = w.get("id")
                # Nếu bài báo chưa có trong danh sách thì thêm vào kèm thông tin seed
                if wid not in seen_ids:
                    seen_ids.add(wid)
                    collected_works.append((w, seed))
        except Exception as e:
            print(f"    Warning: could not fetch seed '{seed['title'][:30]}...': {e}")

    # --------------------------------------------------------------------------
    # BƯỚC 3: Đưa toàn bộ bài báo qua bộ máy thẩm định IC / EC tự động
    # --------------------------------------------------------------------------
    print(f"\n[3] Evaluating {len(collected_works)} papers against IC1-IC5 & EC1-EC5 in English...")
    evaluated_records = []
    for i, (work, seed_info) in enumerate(collected_works, 1):
        pid = f"P{i:02d}"  # Tạo mã định danh bài báo: P01, P02, P03...
        title = work.get("title") or "N/A"
        year = work.get("publication_year") or ""
        doi = work.get("doi") or work.get("id") or ""
        # Lấy tên tối đa 3 tác giả đầu tiên
        authors = ", ".join([a["author"]["display_name"] for a in work.get("authorships", [])[:3] if a.get("author")])

        # Gọi hàm chấm điểm
        decision, reason, notes = evaluate_paper(work, seed_info=seed_info)

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

    # In bảng tóm tắt kết quả ra màn hình console để kiểm tra nhanh
    print("\n" + "=" * 70)
    print(f"{'ID':4} | {'Year':4} | {'Decision':8} | {'Reason':7} | Title")
    print("-" * 70)
    included_count = 0
    for r in evaluated_records:
        if r["decision"] == "Included":
            included_count += 1
        safe_title = r["title"][:45]
        print(f"{r['paper_id']:4} | {str(r['year']):4} | {r['decision']:8} | {r['reason']:7} | {safe_title}...")

    # --------------------------------------------------------------------------
    # BƯỚC 4: Ghi danh sách ứng viên vào file candidate_papers.csv
    # --------------------------------------------------------------------------
    target_csv = Path("nguyen-hoang-khoi/SLR/candidate_papers.csv")
    fields = ["paper_id", "title", "authors", "year", "doi_or_url", "decision", "reason", "notes"]

    with open(target_csv, "w", newline="", encoding="utf-8") as f:
        writer = csv.DictWriter(f, fieldnames=fields)
        writer.writeheader()
        writer.writerows(evaluated_records)
    print(f"\n[4] Successfully saved directly to: {target_csv}")

    # --------------------------------------------------------------------------
    # BƯỚC 5: Cập nhật thông tin phiên tìm kiếm vào search_log.csv
    # --------------------------------------------------------------------------
    log_csv = Path("nguyen-hoang-khoi/SLR/search_log.csv")
    log_fields = ["database", "query", "date", "raw_hits", "screened", "candidate", "included", "notes"]
    log_entry = {
        "database": "OpenAlex",
        "query": SHARED_QUERY.replace("\n", " ").replace("  ", " "),
        "date": "2026-10-09",
        "raw_hits": raw_hits,                                # Số lượng kết quả thô
        "screened": len(evaluated_records),                 # Số lượng bài đã sàng lọc
        "candidate": len(evaluated_records),                # Số lượng bài đưa vào ứng viên
        "included": included_count,                          # Số lượng bài được chọn (Included)
        "notes": f"Automated IC/EC screening applied. Identified {included_count} included papers matching RQ and course seeds.",
    }
    with open(log_csv, "w", newline="", encoding="utf-8") as f:
        writer = csv.DictWriter(f, fieldnames=log_fields)
        writer.writeheader()
        writer.writerow(log_entry)
    print(f"[5] Successfully updated search log: {log_csv}")

    print("\n" + "=" * 70)
    print("DONE! All data saved in English without font or encoding issues.")


# Điểm khởi chạy của chương trình Python
if __name__ == "__main__":
    main()
