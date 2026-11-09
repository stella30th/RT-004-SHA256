# Kiểm chứng RQ — Huỳnh Gia Hào

**Tuần:** 7  
**Nguồn phụ trách:** Google Scholar Block A + IEEE Xplore  
**RQ đang kiểm chứng:** Task Categories Where ChatGPT- and Gemini-Generated JUnit Tests Fail to Run  
**Ngày cập nhật:** 2026-10-14  
**Loại đóng góp dự kiến:** **Extension** (có thành phần controlled replication, nhưng không phải replication thuần)

## 1. Gate kiểm tra evidence table

Kiểm tra `team-synthesis/evidence_table_merged.csv` theo checklist RBL-2:

| Gate | Yêu cầu | Kết quả hiện tại | Trạng thái |
|---|---:|---:|---|
| P1 | ≥ 12 paper included | 17 paper | PASS |
| P2 | Tool/LLM ≥ 90% hàng có dữ liệu | 17/17 = 100.0% | PASS |
| P3 | Kết quả ≥ 50% hàng có số | 14/17 = 82.4% | PASS |
| P4 | Hạn chế ≥ 50% hàng có dữ liệu | 17/17 = 100.0% | PASS |
| P5 | Metric cụ thể, không chỉ “accuracy” | 17/17 hàng có metric cụ thể | PASS |

Kết luận: evidence table đủ chất lượng để chuyển sang kiểm chứng RQ và proposal.

## 2. Bảng phản chứng P/I/C/O

Paper phản chứng mạnh nhất: **AI-Powered Unit Test Generation via Multi-LLM Chaining: A Case Study With GPT-4o, Gemini, and Claude-3.5** — DOI `10.1109/ACCESS.2025.3637221`.

| Thành phần | RQ của nhóm | Paper phản chứng | Match? |
|---|---|---|---|
| P — dữ liệu/task | HumanEval-Java, Java/JUnit, cùng task cho các điều kiện | HumanEval-Java, Java/JUnit | Có |
| I — can thiệp/model | ChatGPT và Gemini sinh JUnit; cùng structured prompt; tối đa 1 compiler-feedback repair | Gemini, GPT-4o, Claude; single + chained refinement | Một phần |
| C — đối chứng | Benchmark/reference JUnit baseline + paired ChatGPT vs Gemini | So sánh single LLM và chained LLM; không dùng cùng baseline/reference condition như nhóm | Không |
| O — outcome | compile success, execution success, combined success, failure category | compilation status, runtime failure, coverage, mutation | Có |

**Mức trùng:** 2.5–3/4, không phải 4/4.

Vì paper đã dùng HumanEval-Java/JUnit và có Gemini/GPT-4o nhưng **không trả lời “task category nào làm từng model fail” trong paired design của nhóm**, RQ không bị giải quyết hoàn toàn. Nhóm đang mở rộng literature theo trục **task category + benchmark/reference baseline + controlled one-repair policy + failure taxonomy**.

## 3. Nhãn tính mới

**Kết luận:** `extension`

Không chọn `new` vì đã có nhiều nghiên cứu LLM-based unit-test generation và có paper trực tiếp dùng HumanEval-Java/JUnit với Gemini/GPT-4o.

Không chọn `replication` thuần vì nhóm không lặp nguyên thiết kế của paper trước. Nhóm thay đổi:
- comparison conditions;
- task-category analysis;
- runnability outcome;
- one-repair experimental control;
- failure taxonomy.

### “So what?”

Contribution không phải “dùng model mới hơn”. Contribution là:

> Cùng một tập HumanEval-Java được đóng băng và cùng một prompt, nhóm đo riêng compile failure và execution failure của ChatGPT/Gemini, rồi phân tích các failure đó theo task category. Thiết kế paired này cho phép xác định *loại task nào* có liên hệ với runnability failure thay vì chỉ báo coverage/pass-rate tổng.

## 4. Lớp tìm kiếm bổ sung

- **L1 — targeted search:** DONE. IEEE bổ sung các paper về prompt programming và execution-based evaluation.
- **L2 — cập nhật paper mới:** DONE ở mức Week-6 source evidence (2025 papers được bổ sung).
- **L3 — survey/SLR nền:** đã có background trong tập evidence; không cần mở rộng thêm trước proposal trừ khi giảng viên yêu cầu.

Không tiếp tục snowballing vô hạn vì evidence gate đã đạt.

## 5. Rà khả thi

| Tiêu chí | Đánh giá | Ghi chú |
|---|---|---|
| Dataset | An toàn | HumanEval-Java public; repo đã audit |
| API/tool | Cần chốt trước approval | Team phải ghi **exact model IDs + access date + endpoint** |
| Tính toán | An toàn | Java/Maven + Python orchestration; không cần GPU riêng |
| Ground truth | An toàn | Correct implementation + benchmark/reference JUnit |
| Code base | An toàn | HumanEval-Java có implementation/test sẵn |
| Kỹ năng | An toàn | Java, JUnit, scripting |
| Thời gian | An toàn nếu scope 30 task | Pilot 5 trước, freeze 30 sau |

Không có tiêu chí “không làm được”. Rủi ro chính là model/API version phải được team freeze trước khi proposal được duyệt.

## 6. RQ đề xuất sau kiểm chứng

### RQ1 — primary
**For a frozen, category-stratified 30-task subset of HumanEval-Java, how do benchmark/reference JUnit tests, ChatGPT-generated tests, and Gemini-generated tests differ in compilation success, execution success, and combined runnability after at most one compiler-feedback repair?**

### RQ2 — secondary
**Which task categories and failure types are most frequently associated with ChatGPT- and Gemini-generated tests that fail to compile or execute?**

RQ2 dùng cùng dataset/pipeline với RQ1 nên vẫn khả thi trong scope.

## 7. Khoảng trống được phát biểu để đưa vào proposal

> Prior work shows that LLM-generated tests can fail syntactically, at compilation, or during execution, and that repair/context can improve test generation. However, the reviewed literature does not provide a paired task-category analysis comparing ChatGPT and Gemini against a benchmark/reference JUnit baseline on the same frozen HumanEval-Java tasks under a fixed one-repair policy. This leaves uncertainty about which task categories are most associated with runnability failures for each model.

## 8. Quyết định

- Giữ topic hiện tại.
- Không claim “chưa ai nghiên cứu LLM unit testing”.
- Định vị nghiên cứu là **extension focused on task-category runnability failures**.
- Chốt dataset/sample/protocol trong proposal.
- Exact model IDs vẫn phải được team điền trước khi proposal version 1.0 được duyệt.
