# ClassEval-T OAPR: kiểm toán số liệu lịch sử

Chạy lại bằng `python ho-le-thien-an/analysis.py` từ gốc dự án. Script đọc hai bản sao cố định `source_data.json` và `regression_targets.json` trong chính thư mục này (sao từ `outputs/classeval-p0-p1-20261029/`, cùng SHA-256), dựng đủ 94 task cho mỗi model, kiểm tra trạng thái trong log, rồi ghi `pair_transitions.csv` và `summary.json`. Hai JSON đầu vào là bản tổng hợp đã được phân loại trước; script không thay thế việc kiểm tra provenance của lần chạy gốc.

| Nhóm | P0 raw PASS | P0 sau chuẩn hóa PASS | P0 FAIL hợp lệ | P1 phục hồi | Exact 95% CI cho recovery | Carry-forward PASS |
|---|---:|---:|---:|---:|---:|---:|
| ChatGPT | 81/94 | 83/94 | 11 | 10/11 | 58.72–99.77% | 93/94 |
| Gemini | 86/94 | 87/94 | 7 | 5/7 | 29.04–96.33% | 92/94 |
| Gộp | 167/188 | 170/188 | 18 | 15/18 | 58.58–96.42% | 185/188 |

Khoảng tin cậy dùng Clopper–Pearson hai phía, α = 0.05. `185/188` là phép **carry-forward**: giữ 170 cặp P0 PASS sau chuẩn hóa và cộng 15 cặp P1 phục hồi trong tập thất bại. Nó không phải tỷ lệ P1 trên cả 188 cặp. Ba cặp còn FAIL là ChatGPT CE066, Gemini CE033 và Gemini CE037.

Trong 18 cặp P0 FAIL hợp lệ, nhãn giai đoạn lỗi của bản tổng hợp gồm 15 bộ test có assertion failure, 2 runtime error và 1 compilation failure. Đây là số **bộ test**, không phải tổng số assertion bị sai.

## Phân tích độ nhạy của ba điều chỉnh hậu nghiệm

CE029 và CE087 của ChatGPT có lỗi dependency SQLite trong lần đầu và log P0 lưu trong kho là log PASS sau khi bổ sung driver. CE023 của Gemini có đầu vào không khớp với source thực thi: log P0 là FAIL. Prompt P1 ghi rõ thay `TARGET_SOURCE` của CE023 bằng source dự án và yêu cầu sinh test mới với API đúng; log P1 là PASS. **Hai file `src/tests-gemini-p0/java/org/GeneratedTest_CE023.java` và `src/tests-gemini-p1/java/org/GeneratedTest_CE023.java` chứa bộ test khác nhau.** Vì vậy log P1 PASS không chứng minh bộ test P0 cũ sẽ PASS sau khi sửa input, và cũng không tách được tác động của prompt refinement khỏi tác động của sửa đầu vào. Cần tìm một run độc lập chạy lại chính bộ test P0 dưới đầu vào đã sửa, hoặc trình bày CE023 là ca chưa giải quyết trong baseline raw.

Nếu giữ cả ba ca là FAIL thay vì chuẩn hóa thành PASS, P0 raw là `167/188`. Cộng *chỉ* 15 ca phục hồi đã quan sát thì được `182/188`; số còn FAIL là 6. Viết `15/21` chỉ như kịch bản số học của 21 ca raw FAIL, **không** như tỷ lệ phục hồi P1 đã quan sát trên cả 21 ca, vì ba ca bổ sung không thuộc tập P1 hợp lệ.

## Phần chưa thể tính từ kho hiện tại

Chưa thấy run registry cho ba nhánh đối chứng A = P0-resample, B = checklist-only, C = checklist+focus trên 18 ca với các lần lặp. Vì vậy chưa thể tính tỷ lệ PASS theo arm, dao động giữa lần chạy, paired effect hay p-value của thí nghiệm đối chứng. Không áp McNemar để suy ra tác động nhân quả từ 18 cặp được chọn vì P0 FAIL. Cần CSV có model, task, arm, replicate, trạng thái compile và số test fail/error, cùng log/prompt/response cho từng run.

## Chạy lại CE023 trong môi trường cô lập

Đã sao chép nguyên trạng `Classroom.java` và lần lượt hai file test CE023 do Gemini tạo ở P0/P1 vào hai Maven project nhỏ trong `ce023-repro-p0/` và `ce023-repro-p1/`. Chạy Maven 3.9.16 với JDK 21, `maven.compiler.release=17`, JUnit 4.13.2, compiler plugin 3.13.0 và Surefire 3.2.5:

- P0: exit code 1; `testCompile` báo `package ClassroomManagementTest does not exist`. JUnit chưa chạy. Xem `ce023-repro-p0/rerun.log`.
- P1: exit code 0; 4 tests, 0 failures, 0 errors; `BUILD SUCCESS`. Xem `ce023-repro-p1/rerun.log`.

Đây là chạy lại **các file test đã lưu**, không phải gọi lại mô hình Gemini để sinh test mới. Hai file test khác nhau và prompt P1 cũng thay `TARGET_SOURCE` của CE023; phép chạy này xác nhận trạng thái build của từng artifact, không tách được nguyên nhân P1 PASS.
# Update 2026-11-09

The strict historical analysis now excludes Gemini CE023 because its P0 input did not match the project source. The valid cohort is 187 pairs: 169 P0 passes, 18 eligible failures, and 15 recoveries for 184 carry-forward passes. The former 188-pair adjusted counts are retained only as a sensitivity scenario pending a successful rerun of the original CE023 P0 suite. See round2_analysis_notes.md and round2_summary.json for the separate ChatGPT round-2 handover analysis.
