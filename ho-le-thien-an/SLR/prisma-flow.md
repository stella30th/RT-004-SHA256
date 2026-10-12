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
| **Included từ ACM** | **55** |

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
| Vòng 2 (35 bản ghi Include/Maybe) | Include 7; loại E3 27 (chưa có toàn văn, abstract không đủ hoặc trống); loại I2 1 |
| **Included từ snowballing** | **7** |

OpenAlex chỉ ghi 25 tham chiếu cho seed (22 tài liệu truy xuất được), có thể thiếu so với danh sách tham khảo trong PDF.

## 5. Kết quả cuối

| Nguồn | Included |
|---|---|
| ACM (4 lượt tìm) | 55 |
| Snowballing | 7 |
| **Tổng** | **62** (trong đó 2 bài là tổng quan: ACM_0003, SB_0007) |

Danh sách: `03_final_included.csv`. Bảng trích xuất: `evidence-table.md`.

## Ghi chú phương pháp và hạn chế

- Tiêu chí: I1, I2, E1–E5 của sheet 05_Screening_Guide. Mã `I2` ở cột lý do loại nghĩa là bài không đạt tiêu chí bao gồm I2, ngưỡng cụ thể ở mục dưới. Mã này không có trong danh sách E1–E5, nên ghi rõ ở đây.
- Ngưỡng I2 (vòng 2): toàn văn có ít nhất 8 câu nêu biên dịch (compile) gắn với tỉ lệ/lỗi/thành công, hoặc ít nhất 4 câu biên dịch kèm 4 câu thực thi hoặc 3 câu sửa lỗi, hoặc ít nhất 12 câu về thực thi cùng ít nhất 30 lần nhắc unit test. Số đếm nằm trong `fulltext_signals.csv`; các bài sát ngưỡng đã được đọc lại câu gốc. Ngoại lệ đọc tay: ACM_0001 (prompt có cấu trúc, toàn văn nêu test không biên dịch được) và ACM_0628 (đánh giá quy mô lớn prompting có xử lý lỗi biên dịch) được thêm vào dù chưa đạt ngưỡng đếm.
- Vòng 1 và vòng 2 do Claude soạn dựa trên tiêu đề, abstract, toàn văn và số đếm trong `fulltext_signals.csv`; Hồ Lê Thiên An là người chịu trách nhiệm và cần rà soát lại trước khi nộp. Chưa có reviewer thứ hai nên `reviewer_decision` và `conflict?` để trống.
- Năm xuất bản và abstract đầy đủ lấy từ OpenAlex theo DOI; 26 DOI không có trong OpenAlex nên dùng abstract rút gọn của ACM.
- Bài ACM bị khoá (29 bài) không có toàn văn. Chưa bổ sung bài khoá do thành viên tải thêm.
