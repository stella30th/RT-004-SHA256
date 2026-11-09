# Biên Bản Bàn Giao Tiến Độ RBL-1 (Tuần 6)
**Thành viên:** Nguyễn Hoàng Khôi  
**Vai trò chính:** LLM Runner (LR) & OpenAlex Literature Review  
**Nhánh Git:** `rbl/khoi`  
**Đề tài (Topic 6.2 · FA26-W5-18):** Task Categories Where ChatGPT- and Gemini-Generated JUnit Tests Fail to Run  
**Ngày bàn giao:** 11/10/2026  

---

## 1. Xác Nhận Phân Công Nhiệm Vụ
- **Cơ sở dữ liệu đảm nhận:** **OpenAlex** (Khẳng định nhận đúng nguồn theo `team-synthesis/review_protocol.md` và `team-synthesis/authorship.md`).
- **Người kiểm tra chéo (Peer Reviewer):** Nguyễn Nam Khoa.
- **Tiêu chuẩn tuân thủ:** Bộ tiêu chí chọn/loại `IC1–IC5` và `EC1–EC5` tại `docs/ie_criteria.md`.

---

## 2. Các Sản Phẩm Bàn Giao (Delivered Artifacts)

| STT | Tệp tin / Đường dẫn | Mô tả nội dung | Trạng thái |
|:---:|---|---|:---:|
| 1 | `scripts/search_openalex.py` | Tool tự động hóa kết nối OpenAlex REST API, bóc tách Abstract Inverted Index, tự động thẩm định tiêu chí IC/EC, có chú thích tiếng Việt chi tiết từng dòng. | Hoàn thành |
| 2 | `nguyen-hoang-khoi/SLR/search_log.csv` | Nhật ký tìm kiếm chuẩn hóa bằng tiếng Anh ghi nhận phiên tìm kiếm `OA-Q1`. | Hoàn thành |
| 3 | `nguyen-hoang-khoi/SLR/candidate_papers.csv` | Danh sách 23 bài báo đã sàng lọc với quyết định `Included`/`Excluded`, mã tiêu chí và link DOI kiểm chứng. | Hoàn thành |
| 4 | `nguyen-hoang-khoi/HANDOVER.md` | Tài liệu bàn giao tiến độ và hướng dẫn review chéo. | Hoàn thành |

---

## 3. Số Liệu Báo Cáo Tiến Độ (Dành Cho PRISMA và Progress Report)

Số liệu chính thức từ phiên tìm kiếm trên OpenAlex:
- **Chuỗi truy vấn (Shared Query):** `("unit test generation" OR "JUnit test suite" OR HumanEval) AND (ChatGPT OR Gemini OR "compiler-feedback repair" OR "structured prompting") AND (compilability OR executability OR "build success rate" OR "test execution rate")`
- **Tổng số kết quả thô (Raw hits):** **3.441 bài**
- **Số bài đã sàng lọc (Screened):** **23 bài**
- **Số bài ứng viên (Candidate papers):** **23 bài**
- **Số bài được chọn (Included - IC1–IC5):** **18 bài**
- **Số bài bị loại (Excluded - EC1, EC5):** **5 bài**

---

## 4. Các Bài Báo Trọng Tâm Đóng Góp Cho Nhóm (`rq-evidence-map.md`)

Khôi đã thu thập đầy đủ các bài báo cốt lõi của đề tài môn học để nhóm đưa vào `evidence-table-merged.md`:
1. **TestART (2024)** (DOI: `10.48550/arxiv.2408.03095`) — Dẫn chứng trực tiếp cho cơ chế lặp sửa lỗi (`compiler-feedback repair`).
2. **AI-Powered Unit Test Generation via Multi-LLM Chaining (2025)** (DOI: `10.1109/access.2025.3637221`) — Dẫn chứng so sánh năng lực của cả **ChatGPT (GPT-4o)** và **Gemini**.
3. **YATE (2025)** (DOI: `10.48550/arxiv.2507.18316`) — Dẫn chứng về vai trò và hiệu quả của việc sửa test (`test repair`).
4. **Automated Unit Test Improvement at Meta (2024)** (DOI: `10.1145/3663529.3663839`) — Dẫn chứng công cụ TestGen-LLM dùng bộ lọc biên dịch và chạy test.
5. **Evaluating Large Language Models Trained on Code (2021)** — Bài báo gốc giới thiệu benchmark **HumanEval**.
6. **Requirements-Based Test Generation: A Comprehensive Survey (2025)** — Khảo sát tổng quan SLR về sinh test tự động.

---

## 5. Hướng Dẫn Dành Cho Reviewer (Nguyễn Nam Khoa)

Bạn Khoa có thể kiểm chứng tính minh bạch và tái lập kết quả của Khôi qua 2 cách:
1. **Kiểm tra trực tiếp code**:
   Mở terminal tại thư mục gốc dự án và chạy:
   ```bash
   python scripts/search_openalex.py
   ```
   Script sẽ tự động kết nối OpenAlex API, chạy lại và ghi đè kết quả nhất quán 100%.
2. **Kiểm tra DOI/Link**:
   Mở file `nguyen-hoang-khoi/SLR/candidate_papers.csv`, bấm vào các đường link DOI ở cột `doi_or_url` để xác thực toàn văn bài báo.
3. **Xác nhận**:
   Sau khi kiểm tra đạt yêu cầu, Khoa tạo file xác nhận `REVIEWED.md` hoặc thông báo để nhóm trưởng (Hào) tiến hành gộp vào bảng chung của nhóm.

---

## 6. Kế Hoạch Tiếp Theo (Tuần 7 - RBL-2 & RBL-3)
- Phối hợp với nhóm trưởng và các bạn hoàn thiện bảng gộp `evidence-table-merged.md` ($\ge$ 12 papers) và vẽ sơ đồ `prisma-team.md`.
- Chuẩn bị khung sườn kỹ thuật cho vai trò **LLM Runner**: cấu hình API key (đảm bảo không đưa lên Git qua `.gitignore`) và chạy thử nghiệm mini-pilot 5–10 bài toán HumanEval.
