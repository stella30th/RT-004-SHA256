# Hồ Lê Thiên An: SLR (ACM Digital Library)

Nguồn chính: **ACM Digital Library**. Truy vấn dùng chung và tiêu chí IE ở `team-synthesis/`.

## Kết quả tuần 6 (RBL-1)

| File | Nội dung |
|---|---|
| `search-log.md`, `search_log.csv` | Bốn lượt tìm S0–S3, truy vấn, số hit, snowballing |
| `01_all_records.csv` | 823 bản ghi (747 ACM + 76 snowballing), cột `source`, `seed` |
| `02_after_screening_v1.csv` | Quyết định screening từng bản ghi (vòng 1 và vòng 2) |
| `03_final_included.csv` | 62 bài included |
| `prisma-flow.md` | Số liệu PRISMA và quy tắc screening |
| `evidence-table.md`, `evidence_table.csv` | Bảng trích xuất cho 62 bài included |
| `candidate_papers.csv` | Bản ghi qua vòng 1 và kết quả vòng 2 (mẫu của nhóm) |

## Dữ liệu và công cụ

| Thư mục/file | Vai trò |
|---|---|
| `raw/` | Kết quả gốc của tool `run-acm-scan` (không sửa) |
| `enrichment/openalex_by_doi.json` | Năm xuất bản và abstract đầy đủ theo DOI (OpenAlex) |
| `snowball/` | Dữ liệu snowballing của seed ACM_0041 |
| `records_audit.csv`, `duplicate-log.csv`, `prisma-counts*.json`, `fulltext_signals.csv`, `screening_decisions.csv` | Dữ liệu kiểm tra và quyết định screening |
| `papers/` | PDF các bài included (không đưa lên git, xem `papers/README.md`) |
| `tools/` | Script Python tái tạo toàn bộ kết quả |

## Chạy lại

```bash
python tools/acm_enrich.py
python tools/acm_process.py --from-year 2022
python tools/snowball_openalex.py --seed-doi 10.1145/3661167.3661216 --from-year 2022
python tools/fulltext_signals.py --pdf-root <thư mục chứa PDF>
python tools/build_screening.py --pdf-root <thư mục chứa PDF>
```

`screening_decisions.csv` là nơi ghi quyết định; sửa file này rồi chạy lại `build_screening.py` để cập nhật các file còn lại.
