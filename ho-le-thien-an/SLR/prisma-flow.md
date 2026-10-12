# PRISMA flow: ACM Digital Library + snowballing (Hồ Lê Thiên An)

Số liệu lấy từ `prisma-counts.json`, `prisma-counts-final.json` và `screening_decisions.csv`.

## 1. Identification

| Bước | Số bản ghi |
|---|---|
| ACM-S0 (string gốc, All fields) | 612 |
| ACM-S1 (Title + Abstract) | 196 |
| ACM-S2 (Abstract) | 28 |
| ACM-S3 (Title + Abstract, HumanEval) | 4 |
| Tổng dòng thu từ ACM | 840 |
| Trùng bị loại (DOI, rồi tiêu đề + năm) | 93 |
| **Bản ghi ACM duy nhất** | **747** |

Phân bổ bản ghi duy nhất: S0 612, S1 thêm 135 (61 trùng S0), S2 và S3 trùng hoàn toàn với S0/S1.

## 2. Screening vòng 1 (tiêu đề và abstract), bản ghi ACM

| Kết quả | Số |
|---|---|
| Đưa vào vòng 1 | 747 |
| Loại E5 (mục lục kỷ yếu, không phải bài báo) | 167 |
| Loại E1 (không về sinh test/unit test) | 409 |
| Loại E2 (về test nhưng không dùng LLM) | 7 |
| **Còn lại cho vòng 2** (105 Include + 59 Maybe) | **164** |

Bộ lọc năm của cô (từ 2022): chỉ 1 bản ghi trước 2022 (ACM_0747, năm 2014), đã bị loại ở E1.

## 3. Screening vòng 2 (toàn văn), bản ghi ACM

| Kết quả | Số |
|---|---|
| Xét vòng 2 | 164 |
| Loại E3 (không có toàn văn: ACM khoá hoặc không tải được) | 13 |
| Loại E1 (nhìn toàn văn thấy không phải sinh test: ACM_0045, 0057, 0242, 0606, 0725) | 5 |
| Loại E4 (bản hội nghị của cùng công trình, giữ bản đầy đủ hơn: ACM_0699) | 1 |
| Loại I2 (toàn văn không có bằng chứng định lượng về biên dịch/chạy/sửa lỗi của test sinh ra) | 90 |
| **Đạt tiêu chí vòng 2 (ACM)** | **55** |

## 4. Snowballing (seed ACM_0041, một lượt lùi + một lượt tiến)

Seed: *Using Large Language Models to Generate JUnit Tests: An Empirical Study* (2024), chọn vì dùng HumanEval và JUnit, trùng bối cảnh RQ. Công cụ: `tools/snowball_openalex.py` (OpenAlex).

| Hướng | Tài liệu tìm được | Đã có trong bản ghi ACM | Trước 2022 (loại theo quy tắc năm) | Mới |
|---|---|---|---|---|
| Lùi (seed trích dẫn) | 22 | 0 | 17 | 5 |
| Tiến (trích dẫn seed) | 93 | 22 | 0 | 71 |
| **Tổng bản ghi mới** | | | | **76** |

| Vòng | Kết quả |
|---|---|
| Vòng 1 (tiêu đề/abstract), 76 bản ghi | Include 27, Maybe 8, loại E1 34, E2 5, E4 2 (trùng tiêu đề) |
| Vòng 2 (35 bản ghi Include/Maybe) | Đạt tiêu chí 7; loại E3 27 (chưa có toàn văn, abstract không đủ hoặc trống); loại I2 1 |
| **Đạt tiêu chí vòng 2 (snowballing)** | **7** |

OpenAlex chỉ ghi 25 tham chiếu cho seed (22 tài liệu truy xuất được), có thể thiếu so với danh sách tham khảo trong PDF.

## 5. Chọn lọc cho bảng tổng hợp (6 bài)

Nhóm cần từ 12 bài included trở lên cho cả nhóm, nên mỗi thành viên đóng góp khoảng 6 bài. Từ 62 bài đạt tiêu chí, 6 bài được chọn theo mức liên quan với RQ: có HumanEval, có đo tỉ lệ biên dịch/chạy của test do LLM sinh, có vòng sửa theo phản hồi, có kiểu prompt, có ChatGPT/Gemini hoặc baseline người, và đọc được toàn văn.

| Bài | Lý do chọn |
|---|---|
| ACM_0041 | HumanEval + JUnit5, 3 LLM, đo tỉ lệ biên dịch trực tiếp; bài seed |
| ACM_0009 | ChatGPT: 57,9% test lỗi biên dịch; có vòng refine sửa lỗi biên dịch |
| ACM_0644 | Vòng sửa theo phản hồi compiler, so zero-shot, few-shot, CoT trên 3 dự án Java |
| ACM_0025 | HumanEval, GPT-4o, few-shot; đo compilation correctness và pass rate |
| ACM_0001 | Prompt có cấu trúc hai bước (khớp RQ), Java; ~64% test không dùng được |
| SB_0070 | Có Gemini và baseline người; chỉ xét theo abstract |

56 bài còn lại đạt tiêu chí nhưng không chọn, nằm ở `03_reserve_eligible.csv` (dự phòng nếu nhóm cần thêm bài). Hai bài dự phòng liên quan nhất: SB_0030 (đo lỗi biên dịch theo kiểu prompt, tới 86%) và SB_0061 (Gemini 2.5 Pro, pass rate).

## 6. Kết quả cuối

| Nguồn | Được chọn |
|---|---|
| ACM (4 lượt tìm) | 5 |
| Snowballing | 1 |
| **Tổng được chọn** | **6** |
| Đạt tiêu chí nhưng dự phòng | 56 (gồm 2 bài tổng quan: ACM_0003, SB_0007) |

Danh sách chọn: `03_final_included.csv`. Dự phòng: `03_reserve_eligible.csv`. Bảng trích xuất: `evidence-table.md`.

## Ghi chú phương pháp và hạn chế

- Tiêu chí: I1, I2, E1–E5 của sheet 05_Screening_Guide. Mã `I2` ở cột lý do loại nghĩa là bài không đạt tiêu chí bao gồm I2, ngưỡng cụ thể ở mục dưới. Mã này không có trong danh sách E1–E5, nên ghi rõ ở đây.
- Ngưỡng I2 (vòng 2): toàn văn có ít nhất 8 câu nêu biên dịch (compile) gắn với tỉ lệ/lỗi/thành công, hoặc ít nhất 4 câu biên dịch kèm 4 câu thực thi hoặc 3 câu sửa lỗi, hoặc ít nhất 12 câu về thực thi cùng ít nhất 30 lần nhắc unit test. Số đếm nằm trong `fulltext_signals.csv`; các bài sát ngưỡng đã được đọc lại câu gốc. Ngoại lệ đọc tay: ACM_0001 (prompt có cấu trúc, toàn văn nêu test không biên dịch được) và ACM_0628 (đánh giá quy mô lớn prompting có xử lý lỗi biên dịch) được thêm vào dù chưa đạt ngưỡng đếm.
- Screening vòng 1 và vòng 2 có hỗ trợ của công cụ AI (đọc tiêu đề, abstract, toàn văn và đếm câu trong `fulltext_signals.csv`). Hồ Lê Thiên An rà soát và chịu trách nhiệm về các quyết định. Chưa có reviewer thứ hai nên `reviewer_decision` và `conflict?` để trống.
- Năm xuất bản và abstract đầy đủ lấy từ OpenAlex theo DOI; 26 DOI không có trong OpenAlex nên dùng abstract rút gọn của ACM.
- Bài ACM bị khoá (29 bài) không có toàn văn. Chưa bổ sung bài khoá do thành viên tải thêm.
