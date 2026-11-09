# papers/

6 PDF của các bài ACM trọng tâm (`03_final_included.csv`), đặt tên `<record_id>_<doi>.pdf`: ACM_0001, ACM_0004, ACM_0009, ACM_0024, ACM_0025, ACM_0041. Mỗi PDF có ít nhất 4 trang. Không đưa PDF lên git (bản quyền nhà xuất bản).

PDF ACM_0644 dài 3 trang được chuyển vào `superseded/` ở máy làm việc, không thuộc sáu bài trọng tâm. Các bản ghi snowballing chưa có PDF toàn văn không được tính là included.

Chạy `python tools/build_screening.py --pdf-root <thư mục PDF>` để sinh lại thư mục này.
