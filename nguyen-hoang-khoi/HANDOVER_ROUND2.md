# BIÊN BẢN BÀN GIAO KẾT QUẢ VÒNG 2 — NGHIỆM THU REVIEWER ACTION TRACKER
**Thành viên:** Nguyễn Hoàng Khôi  
**Vai trò:** Lead Researcher — Data Provenance, Literature Gap & Venue Strategy  
**Nhánh Git:** `rbl/khoi`  
**Giai đoạn:** Vòng 2 (Post-Review Revision)  
**Ngày bàn giao:** 07/11/2026  

---

## 1. Danh Sách Các Sản Phẩm Đã Hoàn Thành (Mandatory Deliverables)

Theo đúng phân công tại **Mục 2 & Mục 4** của tài liệu *Phân công nghiên cứu vòng 2*, Khôi đã hoàn thành và lưu trữ đầy đủ 4 sản phẩm đầu ra bắt buộc tại hai vị trí: thư mục cá nhân `nguyen-hoang-khoi/` và thư mục dùng chung của nhóm `docs/`:

| STT | Tên file bàn giao | Vị trí lưu trữ | Mã Tracker phụ trách | Cổng nghiệm thu (Gate) | Tình trạng |
|:---:|---|---|:---:|:---:|:---:|
| 1 | `dataset_manifest.csv` | `docs/dataset_manifest.csv`<br/>`nguyen-hoang-khoi/dataset_manifest.csv` | `H3`, `M9` | **Gate G0** (Data Provenance & Audit) | **HOÀN THÀNH 100%** (Đủ 94 class, mã SHA256 thật, ghi chú CE023) |
| 2 | `reference_audit.md` | `docs/reference_audit.md`<br/>`nguyen-hoang-khoi/reference_audit.md` | `M5`, `M8`, `M9`, `M12` | **Gate G0 / G5** (Citation & Paper Claims) | **HOÀN THÀNH 100%** (Xác thực 100% claim qua DOI, Page, Table) |
| 3 | `related_work_matrix.csv` | `docs/related_work_matrix.csv`<br/>`nguyen-hoang-khoi/related_work_matrix.csv` | `H10`, `H11`, `M8`, `M21` | **Gate G5** (Related Work & Novelty Delta) | **HOÀN THÀNH 100%** (7 công trình: ChatTester, TestART, YATE, ChatUniTest, IntUT, EvoSuite, OAPR) |
| 4 | `venue_decision.md` | `docs/venue_decision.md`<br/>`nguyen-hoang-khoi/venue_decision.md` | `M22` | **Gate G5** (Venue & Format Strategy) | **HOÀN THÀNH 100%** (Chốt ICST 2027 NIER, backup ISSTA/STVR từ CFP thật) |

---

## 2. Chi Tiết Từng Sản Phẩm & Ý Nghĩa Nghiệm Thu

### 2.1. `dataset_manifest.csv` (Vượt Gate G0)
- Kiểm kê chính xác toàn bộ **94 bài toán ClassEval-T Java** (`CE001` – `CE094`).
- Tính toán trực tiếp mã băm **SHA-256** từ file nguồn `data/ClassEval-T/solution_human/*.java` để chứng minh không can thiệp vào mã nguồn gốc (*anti-tampering*).
- Ghi chú minh bạch ca đặc biệt **`CE023`** (`Classroom.java` có inner class `Course`), khẳng định giữ nguyên mã nguồn SUT gốc mà không tự ý sửa đổi code để làm đẹp số liệu.

### 2.2. `reference_audit.md` (Bảo chứng học thuật 100%)
- Rà soát từng số liệu trong bài báo (94 lớp, 7 nhóm nghiệp vụ, tỷ lệ bao phủ 99.7% câu lệnh / 98.2% nhánh, độ dài 66.7 LOC, 76.2% phụ thuộc trạng thái nội bộ).
- Dẫn chứng số trang, số bảng, mã DOI từ bài báo gốc ClassEval (ICSE 2024) và ClassEval-T (ISSTA 2025).
- Bổ sung cụm trích dẫn chuẩn hóa cho nhận định tổng quan về các lỗi biên dịch, thực thi, ảo giác oracle của LLM (giải quyết triệt để phản biện `M5` và `M12`).
- Rà soát nguyên tắc loại bỏ tuyên bố không có bằng chứng (*zero-tolerance unverified claims*).

### 2.3. `related_work_matrix.csv` (Bảo vệ điểm mới của OAPR)
- Đối sánh trực diện OAPR với 6 công trình đại diện: ChatTester, TestART, YATE, ChatUniTest, IntUT (Test Intention Guided), và EvoSuite baseline.
- Làm rõ đóng góp mới của OAPR: là nghiên cứu thực nghiệm định hướng thất bại (*exploratory failure-informed workflow*) trên cấp độ toàn bộ Lớp (Class-level) với 94 lớp Java có tính phụ thuộc trạng thái cao (76.2%), khác biệt với cơ chế vòng lặp đóng phương thức đơn lẻ (*method-level loop*) của ChatTester hay đào sâu ý định (*intention mining*) của IntUT.
- Cung cấp nguồn dữ liệu để cập nhật và bảo vệ **Bảng I (`\label{tab:prior}`)** trong bài báo.

### 2.4. `venue_decision.md` (Chốt nơi nộp bài)
- **Nơi nộp ưu tiên số 1:** **IEEE ICST 2027 — NIER Track** (*New Ideas and Emerging Results*).
  - Định dạng: IEEE 2 cột (`IEEEtran`), tối đa 4 trang nội dung + 2 trang tài liệu tham khảo. Phù hợp 100% với dung lượng 4–5 trang hiện tại của bài báo.
  - Hạn nộp: ~13/01/2027.
- **Dự phòng:** **ACM ISSTA 2027 (Tool/Emerging Track)** (hạn 11/01/2027) hoặc **STVR (Wiley Journal)** (nhận bài liên tục).
- Xác lập 5 tiêu chuẩn tuân thủ: Thể thức IEEE, ngân sách trang, ẩn danh kép (*Double-blind*), gói dữ liệu mở (*Artifact Availability*), và tuyên bố sử dụng AI (*AI Disclosure*).

---

## 3. Hướng Dẫn Dành Cho Nhóm Trưởng (Hào), Người Viết Tổng Hợp (An) và Người Kiểm Tra Chéo (Khoa)

1. **Người kiểm tra chéo (Khoa):**
   - Mở file `docs/dataset_manifest.csv`, chọn ngẫu nhiên một bài toán (ví dụ `CE001` hoặc `CE023`), chạy `Get-FileHash data/ClassEval-T/solution_human/AccessGatewayFilter.java -Algorithm SHA256` trên PowerShell để đối soát mã băm.
   - Kiểm tra các link DOI trong `docs/reference_audit.md`.
2. **Người tổng hợp bài báo (An):**
   - Sử dụng các số liệu đã được kiểm toán trong `docs/reference_audit.md` và ma trận `docs/related_work_matrix.csv` để đưa vào phần *Related Work* và *Table I* của bài báo `paper/main.tex`.
   - Giữ nguyên cấu hình định dạng `IEEEtran` 4+2 trang theo hướng dẫn tại `docs/venue_decision.md`.
3. **Nhóm trưởng (Hào):**
   - Nghiệm thu Gate G0 và Gate G5 cho phần việc của Khôi theo biên bản này.
