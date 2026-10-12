# Search log: ACM Digital Library (Hồ Lê Thiên An)

- Nguồn: ACM Digital Library, bộ sưu tập "ACM Full-Text Collection" (852,927 bản ghi), Basic Edition, không đăng nhập khi tìm.
- Thành viên phụ trách: Hồ Lê Thiên An (reviewer: Huỳnh Gia Hào).
- S0 là search string gốc của cô; S1–S3 rút gọn dần. Cả bốn lượt đều chạy trên giao diện Advanced Search của ACM.
- Không đặt bộ lọc năm trên ACM (giao diện không cho chỉnh). Quy tắc từ 2022 trở đi của cô được áp dụng sau khi thu, bằng cờ `PRE_2022` ở `records_audit.csv`.
- Thu metadata và PDF bằng tool `run-acm-scan` (Node.js + Edge), không vượt CAPTCHA, do Hồ Lê Thiên An chạy. PDF được tải bằng tài khoản email của một bạn cùng lớp có quyền truy cập ACM; tài khoản của An (Basic Edition) không tải được các bài này.
- Năm xuất bản và abstract đầy đủ bổ sung theo DOI từ OpenAlex bằng `tools/acm_enrich.py` (raw của ACM không có năm, abstract bị rút gọn). Raw không bị sửa.

## Bảng lượt tìm (khớp sheet 08_Search_Log)

| Run ID | Member | Source | Date (giờ VN) | String Version | Fields searched | Filters | Reported hits | Records collected | Export file |
|---|---|---|---|---|---|---|---|---|---|
| ACM-S0 | An | ACM DL | 2026-10-11 | S0 (string gốc của cô) | All fields (có full text) | không | 612 | 612 | raw/acm_ACM-S0_results.json (+ .csv, _locked.txt) |
| ACM-S1 | An | ACM DL | 2026-10-11 | S1 | Title + Abstract | không | 196 | 196 | raw/acm_ACM-S1_results.json (+ .csv, _locked.txt) |
| ACM-S2 | An | ACM DL | 2026-10-11 | S2 | Abstract | không | 28 | 28 | raw/acm_ACM-S2_results.json (+ .csv, _locked.txt) |
| ACM-S3 | An | ACM DL | 2026-10-11 | S3 | Title + Abstract | không | 4 | 4 | raw/acm_ACM-S3_results.json (+ .csv, _locked.txt) |

Giờ quét theo `metadata.started_at` trong từng `results.json` (UTC): S0 2026-10-11T11:23, S1 2026-10-10T18:40, S2 2026-10-11T04:37, S3 2026-10-10T19:25. Quy đổi sang giờ VN (UTC+7) đều là ngày 2026-10-11.

## Truy vấn đầy đủ (khớp `metadata.search_url`)

**ACM-S0** (shared, `team-synthesis/review_protocol.md`), tìm ở All fields:
```
("unit test generation" OR "JUnit test suite" OR HumanEval)
AND (ChatGPT OR Gemini OR "compiler-feedback repair" OR "structured prompting")
AND (compilability OR executability OR "build success rate" OR "test execution rate")
```

**ACM-S1**:
```
(Title:("unit test*" OR "test generation" OR "test case generation" OR JUnit)
 OR Abstract:("unit test*" OR "test generation" OR JUnit))
AND
(Title:("large language model*" OR LLM OR ChatGPT OR Gemini OR GPT)
 OR Abstract:("large language model*" OR LLM OR ChatGPT OR Gemini OR GPT))
```

**ACM-S2**:
```
Abstract:("LLM" OR "ChatGPT" OR "large language model*")
AND Abstract:("unit test*" OR JUnit)
AND Abstract:(compil* OR executab* OR "repair" OR "compiler feedback" OR "build failure")
```

**ACM-S3**:
```
(Title:HumanEval OR Abstract:HumanEval)
AND (Abstract:("test generation" OR "unit test*" OR JUnit))
```

## Lý do đổi S0 → S1 → S2 → S3

| Bước | Thay đổi so với bước trước | Hệ quả |
|---|---|---|
| S0 → S1 | Từ All fields (có full text) sang Title/Abstract; bỏ khối kết quả (compilability, executability...); mở rộng khối công cụ (LLM, GPT) | 612 → 196; S1 bổ sung 135 bài không có trong S0 |
| S1 → S2 | Chỉ tìm ở Abstract; thêm lại khối kết quả (compil*, executab*, repair, compiler feedback, build failure); bỏ Gemini | 196 → 28; cả 28 bài đã có trong S0 hoặc S1 |
| S2 → S3 | Neo vào HumanEval ở Title/Abstract | 28 → 4; cả 4 bài đã có trong S0 hoặc S1 |

## Truy cập toàn văn (theo `access` và `download_status` của tool)

| Run | ACM báo public | ACM báo locked | Chưa rõ | PDF đã có | Ghi chú |
|---|---|---|---|---|---|
| ACM-S0 | 590 | 22 | 0 | 422 | 164 bài public nhưng chưa tải được, 4 lần tải lỗi |
| ACM-S1 | 185 | 10 | 1 | 185 | |
| ACM-S2 | 26 | 2 | 0 | 26 | |
| ACM-S3 | 4 | 0 | 0 | 4 | |

Danh sách bài bị khoá nằm ở `raw/acm_ACM-S*_locked.txt`. Chưa bổ sung bài khoá nào tải thêm thủ công; các bài khoá nằm trong vòng 2 bị loại E3.

## Snowballing (1 seed)

Công cụ: `tools/snowball_openalex.py`, chạy 2026-10-11, lọc năm từ 2022.

| Seed | Backward (tài liệu seed trích dẫn) | Forward (bài trích dẫn seed) | Record mới | Kết quả screening |
|---|---|---|---|---|
| ACM_0041, *Using Large Language Models to Generate JUnit Tests: An Empirical Study* (10.1145/3661167.3661216) | 22 tìm được; 17 trước 2022; 5 mới | 93 tìm được; 22 đã có trong bản ghi ACM; 71 mới | 76 (SB_0001 đến SB_0076) | 7 đạt tiêu chí, 1 được chọn; xem `prisma-flow.md` |

Lý do chọn seed: bài dùng HumanEval và JUnit, trùng bối cảnh RQ; là một trong 4 bài của lượt S3. Dữ liệu trích dẫn lấy từ OpenAlex (chỉ ghi 25 tham chiếu cho seed).

## Kết quả screening (tóm tắt)

| Nguồn | Bản ghi duy nhất | Vòng 1 còn lại | Kết quả |
|---|---|---|---|
| ACM (S0–S3 gộp) | 747 | 164 | 55 đạt tiêu chí, 5 được chọn |
| Snowballing | 76 | 35 | 7 đạt tiêu chí, 1 được chọn |
| **Tổng** | 823 | 199 | 62 đạt tiêu chí, **6 được chọn** |

Chi tiết và quy tắc: `prisma-flow.md`; quyết định từng bài: `02_after_screening_v1.csv`, `screening_decisions.csv`.
