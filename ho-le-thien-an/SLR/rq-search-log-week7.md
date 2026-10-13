# Nhật ký kiểm tra bổ sung tuần 7 — An / ACM

Ngày 2026-10-12, kiểm tra trong tập đã thu: 6 PDF selected và metadata/abstract của 47 reserve. Đây không phải lượt quét ACM DL mới.

## Rà hồi cứu 47 reserve trong tập ACM đã thu

Nguồn đối chiếu: ID từ `03_reserve_eligible.csv`, title + abstract tương ứng trong `02_after_screening_v1.csv`. Tìm chuỗi không phân biệt hoa/thường; một bản ghi có thể nằm ở nhiều dòng. Đây là tín hiệu ưu tiên đọc, **không** phải bộ lọc loại trừ:

| Cụm tìm trong title + abstract | Số bản ghi | ID |
|---|---:|---|
| `HumanEval` | 0 | — |
| `Gemini` | 1 | ACM_0068 |
| `ChatGPT` | 1 | ACM_0011 |
| `human-written`, `manually-written`, `manually created` | 1 | ACM_0121 |
| `repair` | 7 | ACM_0008, ACM_0071, ACM_0614, ACM_0626, ACM_0635, ACM_0648, ACM_0655 |
| `categor` (category/categories) | 3 | ACM_0003, ACM_0013, ACM_0071 |

`HumanEval = 0` nghĩa là **không thấy chuỗi này trong title/abstract được lưu của 47 reserve**, không có nghĩa 47 PDF không nhắc HumanEval. Abstract có thể thiếu/rút gọn. ACM_0068 và ACM_0121 cũng là ứng viên phải kiểm nếu tranh luận sâu về Gemini hoặc human baseline.

## Tra cứu mở để tìm paper bỏ sót

Các truy vấn web giới hạn `site:dl.acm.org/doi`: `HumanEval JUnit Gemini unit test`; `HumanEval human-written unit test compilation`; `ChatGPT Gemini JUnit test generation`; `task categories unit test LLM`. Công cụ trả không có kết quả. Điều này **không** đồng nghĩa ACM DL có 0 bài.

Truy vấn rộng hơn tìm lại Siddiq et al. (DOI 10.1145/3661167.3661216), và phát hiện ứng viên ACM_0071 (DOI 10.1145/3798251) về sửa test và phân standalone/non-standalone; bài này đã nằm trong 47 reserve. Không có số lượng hit ACM chính thức cho các truy vấn bổ sung; không nhập chúng vào PRISMA của tuần 6.

Đây là lượt **kiểm bổ sung tự chọn**, không phải ba lớp L1/L2/L3 bắt buộc: chưa có yêu cầu RBL-2 chi tiết nào quy định chúng. Nếu nhóm muốn mở rộng kết luận ra ngoài tập PDF đã chọn, cần lượt tìm trực tiếp trên ACM DL có log search string, ngày, bộ lọc, số hit, số bản ghi và quyết định screening. Không suy diễn từ kết quả web search.
