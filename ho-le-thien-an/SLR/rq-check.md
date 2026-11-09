# Kiểm chứng RQ tuần 7 — nguồn ACM Digital Library

**Người phụ trách nguồn:** Hồ Lê Thiên An · **Ngày rà soát:** 12/10/2026

**RQ đã chốt của nhóm:** Trên 30 bài HumanEval cố định, cùng prompt có cấu trúc và một vòng sửa theo lỗi compiler, nhóm bài toán nào khiến test JUnit do ChatGPT hoặc Gemini tạo có tỉ lệ biên dịch và chạy thành công thấp hơn baseline do người viết trước khi xem đầu ra AI? (Nguyên văn: `docs/rq_definition.md`.)

## Bài liên quan gần nhất và mức chồng lấn

| Bài ACM đã kiểm toàn văn | Đã làm gì liên quan đến RQ? | Chưa trả lời trực tiếp điểm nào của RQ? |
|---|---|---|
| [ACM_0041 — Siddiq et al. (2024)](https://doi.org/10.1145/3661167.3661216) | HumanEval chuyển sang Java/JUnit5; có test viết tay; báo tỉ lệ biên dịch và test correctness của test do LLM tạo. **Đây là tiền lệ gần nhất, chồng lấn mạnh về P/C/O.** | Không báo phép so sánh tỉ lệ **biên dịch và chạy thành công theo nhóm bài toán** với baseline người; H1–H7 là sửa theo heuristic, không phải một lần gửi lỗi compiler cho LLM sửa. |
| [ACM_0009 — Yuan et al. (2024)](https://doi.org/10.1145/3660783) | ChatGPT/ChatTester tạo và sửa test Java; đo compile và execution. | Không dùng HumanEval trong thực nghiệm chính; không có baseline người và phân tích theo nhóm bài toán của RQ. |
| [ACM_0024 — Gu et al. (2026)](https://doi.org/10.1145/3844945) | TestART dùng vòng lặp tạo–sửa test và báo lỗi compile/run, pass rate. | Không phải thiết kế HumanEval/ChatGPT–Gemini/một vòng sửa của RQ; không có baseline người theo nhóm bài toán. |
| [ACM_0004 — Chen et al. (2026)](https://doi.org/10.1145/3832242) | CATGen đo compilation success và passing rate, có sửa dựa trên phân tích tĩnh. | Không dùng HumanEval hay baseline người; phân tích **loại lỗi**, không phải nhóm bài toán. |
| [ACM_0001 — Diop et al. (2025)](https://doi.org/10.1145/3779657.3779658) | Dùng prompt có cấu trúc để sinh test Java. | Dùng SBST 2020/Mistral 7B, không phải phép so sánh trong RQ. |
| [ACM_0025 — Chudic & Çalıklı (2026)](https://doi.org/10.1145/3794763.3794828) | Dùng HumanEval và ví dụ test do người viết trong few-shot prompting; đo compile/pass sau sửa theo luật. | Dùng Python/pytest; test người viết là **ví dụ trong prompt**, không phải baseline kết quả của RQ. |

**Bằng chứng từ ACM_0041:** §4.1/Table 1 (trang PDF 5) báo 160 test classes viết tay biên dịch thành công 100%; GPT-3.5-Turbo đạt 43,1% trước và 81,3% sau các bước sửa H1–H7. §3.1.3 (trang PDF 4) mô tả H1–H7 là heuristic; Table 2 báo test correctness. Các bảng này không cung cấp tỉ lệ compile-and-execute theo nhóm bài toán so với baseline người.

## Kết luận kiểm chứng

Các nghiên cứu đã có tiền lệ về HumanEval/JUnit, test viết tay, đo biên dịch và sửa test. Điểm RQ nhóm tập trung làm rõ là **so sánh kết quả biên dịch và chạy thành công theo nhóm bài toán**, trên cùng tập bài, prompt và quy trình sửa, đối chiếu ChatGPT/Gemini với baseline người. Trong sáu công trình ACM được đối chiếu toàn văn ở trên, chưa có công trình nào báo trực tiếp phép so sánh này. Vì vậy **giữ nguyên RQ đã chốt** và dùng ACM_0041 làm nghiên cứu nền gần nhất, không trình bày các thành tố riêng lẻ như đóng góp mới.

**Phạm vi kết luận:** Nhận định chỉ dựa trên sáu công trình ACM đã đối chiếu toàn văn, không khẳng định đã bao quát mọi nghiên cứu liên quan. Bảng trích xuất và phương pháp tìm kiếm được lưu trong `evidence-table.md` và `rq-search-log-week7.md`. Tài liệu này kiểm chứng RQ bằng nghiên cứu trước, **không báo cáo kết quả thực nghiệm của nhóm**.
