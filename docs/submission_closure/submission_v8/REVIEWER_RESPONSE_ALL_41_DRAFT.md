# Response to reviewer/action tracker (draft; author review required)

This covers all 41 action IDs from the supplied November 2026 tracker. The `Assessment` field is evidence triage, not reviewer acceptance or a claim that manuscript changes are merged. For every row, verify the cited source and change in the FINAL submitted PDF before marking closed.

Project facts: the historical 187-pair matched-source bookkeeping and the separate 99 ChatGPT/63 Gemini Round-2 suites are not pooled. CE023 was revised at API level. No historic session provenance or preregistration is attested.

## B1 — Blocking
**Assessment:** `OPEN`.

**Reviewer concern:** AI-detect đo được 45.2 % (chưa đạt ngưỡng <= 20 %). 2 câu được gắn cờ văn phong AI rập khuôn. Minh chứng: Các câu cần rà soát được trích ở Phụ lục B;

**Evidence / remaining work:** AI-detector target cannot be substantiated or used as evidence of authorship; perform human editorial review and venue-compliant disclosure.

**Draft response to reviewer:** We revised the scope of our claims and documented the available evidence: AI-detector target cannot be substantiated or used as evidence of authorship; perform human editorial review and venue-compliant disclosure. We do not interpret this as satisfying the request until the corresponding final-manuscript passage, artifact or author attestation is checked.

**Closure evidence:** final PDF section/page, repository path/revision, reviewer confirmation: `[TO BE VERIFIED]`

---

## H1 — High
**Assessment:** `PARTIAL`.

**Reviewer concern:** Chỉ một họ mô hình: Chỉ đánh giá 1 họ mô hình (Gemini 3.8 Flash) mà không nêu giới hạn khái quát sang mô hình khác ở Threats/Limitations/Conclusion -

**Evidence / remaining work:** Two model families reported, but settings and version identity unverified.

**Draft response to reviewer:** We revised the scope of our claims and documented the available evidence: Two model families reported, but settings and version identity unverified. We do not interpret this as satisfying the request until the corresponding final-manuscript passage, artifact or author attestation is checked.

**Closure evidence:** final PDF section/page, repository path/revision, reviewer confirmation: `[TO BE VERIFIED]`

---

## H2 — High
**Assessment:** `OPEN`.

**Reviewer concern:** Đường thu thập mù: Mục III.B nói hai mô hình nhận cùng yêu cầu cấu trúc nhưng không cho biết chúng được gọi qua API hay giao diện web, system prompt n

**Evidence / remaining work:** Session metadata and exact model access configurations are not independently verified.

**Draft response to reviewer:** We revised the scope of our claims and documented the available evidence: Session metadata and exact model access configurations are not independently verified. We do not interpret this as satisfying the request until the corresponding final-manuscript passage, artifact or author attestation is checked.

**Closure evidence:** final PDF section/page, repository path/revision, reviewer confirmation: `[TO BE VERIFIED]`

---

## H3 — High
**Assessment:** `PARTIAL`.

**Reviewer concern:** Chuẩn hóa hậu nghiệm: CE029, CE087 và CE023 được sửa môi trường hoặc đầu vào rồi gán thành P0 PASS, nhưng bài không đưa quy tắc tiền định để phân biệt

**Evidence / remaining work:** CE023 history and valid-cohort sensitivity documented; upstream identity and matched input provenance unverified.

**Draft response to reviewer:** We revised the scope of our claims and documented the available evidence: CE023 history and valid-cohort sensitivity documented; upstream identity and matched input provenance unverified. We do not interpret this as satisfying the request until the corresponding final-manuscript passage, artifact or author attestation is checked.

**Closure evidence:** final PDF section/page, repository path/revision, reviewer confirmation: `[TO BE VERIFIED]`

---

## H4 — High
**Assessment:** `PARTIAL`.

**Reviewer concern:** Không chạy lặp, không đo dao động: Bài dùng bộ sinh ngẫu nhiên (LLM/temperature/EvoSuite/Randoop...) nhưng không nêu số lần chạy lặp (N runs/seeds, tr

**Evidence / remaining work:** Round-2 3 runs per condition archived; provenance and per-task clustering limit inference.

**Draft response to reviewer:** We revised the scope of our claims and documented the available evidence: Round-2 3 runs per condition archived; provenance and per-task clustering limit inference. We do not interpret this as satisfying the request until the corresponding final-manuscript passage, artifact or author attestation is checked.

**Closure evidence:** final PDF section/page, repository path/revision, reviewer confirmation: `[TO BE VERIFIED]`

---

## H5 — High
**Assessment:** `EVIDENCED_IN_DRAFT`.

**Reviewer concern:** Độ bất định bị giấu: 15/18 được báo thành 83.33% với hai chữ số thập phân nhưng không có khoảng tin cậy. Với n=18, độ bất định rất lớn; độ chính xác h

**Evidence / remaining work:** Exact 95% Clopper-Pearson intervals included in manuscript.

**Draft response to reviewer:** The available draft contains a treatment of this concern: Exact 95% Clopper-Pearson intervals included in manuscript. We will verify its location and accuracy against the final PDF before requesting closure.

**Closure evidence:** final PDF section/page, repository path/revision, reviewer confirmation: `[TO BE VERIFIED]`

---

## H6 — High
**Assessment:** `PARTIAL`.

**Reviewer concern:** Không đo biến thiên: Mỗi model-task-condition dường như chỉ được sinh một lần, nên một đầu ra ngẫu nhiên quyết định PASS/FAIL. Ngay cả temperature 0 c

**Evidence / remaining work:** 11 ChatGPT and 7 Gemini repeated tasks; not a repeat of all 18 historical P0/P1 pairs.

**Draft response to reviewer:** We revised the scope of our claims and documented the available evidence: 11 ChatGPT and 7 Gemini repeated tasks; not a repeat of all 18 historical P0/P1 pairs. We do not interpret this as satisfying the request until the corresponding final-manuscript passage, artifact or author attestation is checked.

**Closure evidence:** final PDF section/page, repository path/revision, reviewer confirmation: `[TO BE VERIFIED]`

---

## H7 — High
**Assessment:** `PARTIAL`.

**Reviewer concern:** Thiếu cách đặt yêu cầu cho mô hình nguyên văn: Bài nhắc cách đặt yêu cầu cho mô hình 33 lần nhưng không trình bày nguyên văn cách đặt yêu cầu cho mô h

**Evidence / remaining work:** Prompts present in repository; exhaustive verbatim prompt inventory not fully attested.

**Draft response to reviewer:** We revised the scope of our claims and documented the available evidence: Prompts present in repository; exhaustive verbatim prompt inventory not fully attested. We do not interpret this as satisfying the request until the corresponding final-manuscript passage, artifact or author attestation is checked.

**Closure evidence:** final PDF section/page, repository path/revision, reviewer confirmation: `[TO BE VERIFIED]`

---

## H8 — High
**Assessment:** `PARTIAL`.

**Reviewer concern:** Thiếu baseline ngang ngân sách: P1 được hưởng một lần sinh bổ sung và chẩn đoán từ lỗi P0, nhưng bài không so với mốc đối chiếu đơn giản có cùng ngân

**Evidence / remaining work:** P0-resample comparator exists in separate 11-task round 2, but not full historical 18-case matched budget.

**Draft response to reviewer:** We revised the scope of our claims and documented the available evidence: P0-resample comparator exists in separate 11-task round 2, but not full historical 18-case matched budget. We do not interpret this as satisfying the request until the corresponding final-manuscript passage, artifact or author attestation is checked.

**Closure evidence:** final PDF section/page, repository path/revision, reviewer confirmation: `[TO BE VERIFIED]`

---

## H9 — High
**Assessment:** `EVIDENCED_IN_DRAFT`.

**Reviewer concern:** Nhận xét 1 (HĐ) Tiêu đề và Abstract gọi OAPR là một phương pháp cải thiện độ chính xác, nhưng phép đo thực tế chỉ là khả năng biên dịch và chạy qua tr

**Evidence / remaining work:** NIER draft title and claims restrict conclusion to compile-and-pass compatibility.

**Draft response to reviewer:** The available draft contains a treatment of this concern: NIER draft title and claims restrict conclusion to compile-and-pass compatibility. We will verify its location and accuracy against the final PDF before requesting closure.

**Closure evidence:** final PDF section/page, repository path/revision, reviewer confirmation: `[TO BE VERIFIED]`

---

## H10 — High
**Assessment:** `PARTIAL`.

**Reviewer concern:** Nhận xét 1 (HĐ) Khoảng trống nghiên cứu không được chứng minh so với ChatTester [7] và TestART [9], vốn đã dùng phản hồi và vòng sửa test. Bốn đóng gó

**Evidence / remaining work:** Related work and positioning exist; full reviewer comparison matrix still requires human validation.

**Draft response to reviewer:** We revised the scope of our claims and documented the available evidence: Related work and positioning exist; full reviewer comparison matrix still requires human validation. We do not interpret this as satisfying the request until the corresponding final-manuscript passage, artifact or author attestation is checked.

**Closure evidence:** final PDF section/page, repository path/revision, reviewer confirmation: `[TO BE VERIFIED]`

---

## H11 — High
**Assessment:** `PARTIAL`.

**Reviewer concern:** Nhận xét 1 (HĐ) Related Work liệt kê hệ thống nhưng không thực hiện so sánh cơ chế và không giải thích vì sao OAPR khác một biến thể prompting thông t

**Evidence / remaining work:** Need verify relevant cited works and final comparison matrix in submitted paper.

**Draft response to reviewer:** We revised the scope of our claims and documented the available evidence: Need verify relevant cited works and final comparison matrix in submitted paper. We do not interpret this as satisfying the request until the corresponding final-manuscript passage, artifact or author attestation is checked.

**Closure evidence:** final PDF section/page, repository path/revision, reviewer confirmation: `[TO BE VERIFIED]`

---

## H12 — High
**Assessment:** `EVIDENCED_IN_DRAFT`.

**Reviewer concern:** Ba RQ là câu hỏi mô tả, không có H0/H1. Điều đó chấp nhận được cho nghiên cứu thăm dò, nhưng RQ2 và RQ3 lại được trình bày như đánh giá hiệu quả trong

**Evidence / remaining work:** Exploratory/descriptive language and explicit denominators in NIER draft.

**Draft response to reviewer:** The available draft contains a treatment of this concern: Exploratory/descriptive language and explicit denominators in NIER draft. We will verify its location and accuracy against the final PDF before requesting closure.

**Closure evidence:** final PDF section/page, repository path/revision, reviewer confirmation: `[TO BE VERIFIED]`

---

## H13 — High
**Assessment:** `PARTIAL`.

**Reviewer concern:** Nhận xét 1 (HĐ) P1 không chỉ thay checklist chung mà còn nhận “failure-specific focus statements” được tạo sau khi xem từng lỗi P0. Vì vậy, 15/18 đo h

**Evidence / remaining work:** Three-condition ablation in two different task cohorts; no isolated historical causal effect.

**Draft response to reviewer:** We revised the scope of our claims and documented the available evidence: Three-condition ablation in two different task cohorts; no isolated historical causal effect. We do not interpret this as satisfying the request until the corresponding final-manuscript passage, artifact or author attestation is checked.

**Closure evidence:** final PDF section/page, repository path/revision, reviewer confirmation: `[TO BE VERIFIED]`

---

## H14 — High
**Assessment:** `EVIDENCED_IN_DRAFT`.

**Reviewer concern:** Kết quả chỉ báo tỷ lệ điểm từ 18 ca được chọn và không có khoảng tin cậy hoặc phân tích độ nhạy. Chênh lệch 10/11 so với 5/7 quá nhỏ để hỗ trợ so sánh

**Evidence / remaining work:** Exact intervals and carry-forward distinction in full manuscript; paired transition table needs final signoff.

**Draft response to reviewer:** The available draft contains a treatment of this concern: Exact intervals and carry-forward distinction in full manuscript; paired transition table needs final signoff. We will verify its location and accuracy against the final PDF before requesting closure.

**Closure evidence:** final PDF section/page, repository path/revision, reviewer confirmation: `[TO BE VERIFIED]`

---

## H15 — High
**Assessment:** `EVIDENCED_IN_DRAFT`.

**Reviewer concern:** Mục Threats có thừa nhận chọn lọc tập thất bại, prompt overfitting và biến thiên mô hình, nhưng chưa xử lý nguy cơ chẩn đoán viên đã nhìn source, test

**Evidence / remaining work:** Threats discuss human diagnosis, focus confounding, and study limitations.

**Draft response to reviewer:** The available draft contains a treatment of this concern: Threats discuss human diagnosis, focus confounding, and study limitations. We will verify its location and accuracy against the final PDF before requesting closure.

**Closure evidence:** final PDF section/page, repository path/revision, reviewer confirmation: `[TO BE VERIFIED]`

---

## H16 — High
**Assessment:** `EVIDENCED_IN_DRAFT`.

**Reviewer concern:** Nhận xét 1 (HĐ) Kết luận “practical targeted repair strategy” đi xa hơn thiết kế một lần chạy trên 18 lỗi đã biết. Dữ liệu hiện chỉ cho thấy 15 suite

**Evidence / remaining work:** NIER conclusion restricts claim to observed recovery.

**Draft response to reviewer:** The available draft contains a treatment of this concern: NIER conclusion restricts claim to observed recovery. We will verify its location and accuracy against the final PDF before requesting closure.

**Closure evidence:** final PDF section/page, repository path/revision, reviewer confirmation: `[TO BE VERIFIED]`

---

## H17 — High
**Assessment:** `BLOCKED_AUTHOR`.

**Reviewer concern:** Nhận xét 1 (HĐ) Bài không có tuyên bố sử dụng AI/LLM trong quá trình nghiên cứu hoặc soạn bài, không có tuyên bố về khả năng truy cập dữ liệu, và khôn

**Evidence / remaining work:** AI, funding, COI, artifact location require confirmed declarations.

**Draft response to reviewer:** We revised the scope of our claims and documented the available evidence: AI, funding, COI, artifact location require confirmed declarations. We do not interpret this as satisfying the request until the corresponding final-manuscript passage, artifact or author attestation is checked.

**Closure evidence:** final PDF section/page, repository path/revision, reviewer confirmation: `[TO BE VERIFIED]`

---

## H18 — High
**Assessment:** `PARTIAL`.

**Reviewer concern:** Methodology - Statistical Analysis: Không có kế hoạch kiểm định thống kê: chưa nêu phép kiểm định và lý do chọn, mức alpha, mức độ ảnh hưởng hay ngưỡn

**Evidence / remaining work:** No valid confirmatory paired test; manuscript adopts descriptive inference; reviewer must accept scope.

**Draft response to reviewer:** We revised the scope of our claims and documented the available evidence: No valid confirmatory paired test; manuscript adopts descriptive inference; reviewer must accept scope. We do not interpret this as satisfying the request until the corresponding final-manuscript passage, artifact or author attestation is checked.

**Closure evidence:** final PDF section/page, repository path/revision, reviewer confirmation: `[TO BE VERIFIED]`

---

## M1 — Medium
**Assessment:** `BLOCKED_AUTHOR`.

**Reviewer concern:** Định danh model sai/thiếu: Cần đối chiếu tên mô hình trong bài với danh mục bản phát hành thật. Chưa ghi phiên bản mô hình cụ thể chính xác (base/Inst

**Evidence / remaining work:** True model versions/snapshots not recoverable from archived metadata.

**Draft response to reviewer:** We revised the scope of our claims and documented the available evidence: True model versions/snapshots not recoverable from archived metadata. We do not interpret this as satisfying the request until the corresponding final-manuscript passage, artifact or author attestation is checked.

**Closure evidence:** final PDF section/page, repository path/revision, reviewer confirmation: `[TO BE VERIFIED]`

---

## M2 — Medium
**Assessment:** `EVIDENCED_IN_DRAFT`.

**Reviewer concern:** So mô hình không đủ lực: Phần Discussion đặt 90.91% cạnh 71.43%, nhưng đây chỉ là 10/11 so với 5/7 trên hai tập bài toán khác nhau. Không có thiết kế

**Evidence / remaining work:** Avoids unpaired model-superiority claims.

**Draft response to reviewer:** The available draft contains a treatment of this concern: Avoids unpaired model-superiority claims. We will verify its location and accuracy against the final PDF before requesting closure.

**Closure evidence:** final PDF section/page, repository path/revision, reviewer confirmation: `[TO BE VERIFIED]`

---

## M3 — Medium
**Assessment:** `OPEN`.

**Reviewer concern:** Thiếu hình tổng quan phương pháp: Thiếu sơ đồ tổng quan quy trình nghiên cứu cho mục III DATASET AND EXPERIMENTAL SETUP (bài không có hình nào). Revie

**Evidence / remaining work:** Full manuscript methodology diagram not found/verified.

**Draft response to reviewer:** We revised the scope of our claims and documented the available evidence: Full manuscript methodology diagram not found/verified. We do not interpret this as satisfying the request until the corresponding final-manuscript passage, artifact or author attestation is checked.

**Closure evidence:** final PDF section/page, repository path/revision, reviewer confirmation: `[TO BE VERIFIED]`

---

## M4 — Medium
**Assessment:** `BLOCKED_PUBLICATION`.

**Reviewer concern:** Thiếu gói tái lập công khai: Bài thực nghiệm nhưng không có đường dẫn gói tái lập (mã, cách đặt yêu cầu cho mô hình, dữ liệu, log) - reviewer không ki

**Evidence / remaining work:** No verified anonymized immutable reviewer artifact URL.

**Draft response to reviewer:** We revised the scope of our claims and documented the available evidence: No verified anonymized immutable reviewer artifact URL. We do not interpret this as satisfying the request until the corresponding final-manuscript passage, artifact or author attestation is checked.

**Closure evidence:** final PDF section/page, repository path/revision, reviewer confirmation: `[TO BE VERIFIED]`

---

## M5 — Medium
**Assessment:** `PARTIAL`.

**Reviewer concern:** Công cụ/chỉ số đánh giá/nhận định thiếu trích dẫn: 1 câu nhận định chung về lĩnh vực ở Introduction/Related Work không có trích dẫn. Vì sao cần sửa: C

**Evidence / remaining work:** Some citations present; source-by-source reference audit pending.

**Draft response to reviewer:** We revised the scope of our claims and documented the available evidence: Some citations present; source-by-source reference audit pending. We do not interpret this as satisfying the request until the corresponding final-manuscript passage, artifact or author attestation is checked.

**Closure evidence:** final PDF section/page, repository path/revision, reviewer confirmation: `[TO BE VERIFIED]`

---

## M6 — Medium
**Assessment:** `EVIDENCED_IN_DRAFT`.

**Reviewer concern:** abstract: Abstract có 211 từ (yêu cầu 150-200) - lệch độ dài. Có đủ bối cảnh, khoảng trống, phương pháp, kết quả định lượng cụ thể và hàm ý được giới

**Evidence / remaining work:** NIER abbreviated abstract; final wordcount and editorial check still needed.

**Draft response to reviewer:** The available draft contains a treatment of this concern: NIER abbreviated abstract; final wordcount and editorial check still needed. We will verify its location and accuracy against the final PDF before requesting closure.

**Closure evidence:** final PDF section/page, repository path/revision, reviewer confirmation: `[TO BE VERIFIED]`

---

## M7 — Medium
**Assessment:** `OPEN`.

**Reviewer concern:** introduction: Đã nêu vấn đề thực tế, hiện trạng, khoảng trống cụ thể và bốn đóng góp rõ ràng; tuy nhiên không có đoạn giới thiệu cấu trúc các phần còn

**Evidence / remaining work:** Roadmap paragraph in full paper not independently verified.

**Draft response to reviewer:** We revised the scope of our claims and documented the available evidence: Roadmap paragraph in full paper not independently verified. We do not interpret this as satisfying the request until the corresponding final-manuscript passage, artifact or author attestation is checked.

**Closure evidence:** final PDF section/page, repository path/revision, reviewer confirmation: `[TO BE VERIFIED]`

---

## M8 — Medium
**Assessment:** `PARTIAL`.

**Reviewer concern:** Related Work: Nội dung được tổ chức theo chủ đề và có câu định vị OAPR, nhưng phần tổng hợp chủ yếu mô tả công trình trước; phê bình, đối chiếu điểm m

**Evidence / remaining work:** Related work text exists; full comparative critique not independently checked.

**Draft response to reviewer:** We revised the scope of our claims and documented the available evidence: Related work text exists; full comparative critique not independently checked. We do not interpret this as satisfying the request until the corresponding final-manuscript passage, artifact or author attestation is checked.

**Closure evidence:** final PDF section/page, repository path/revision, reviewer confirmation: `[TO BE VERIFIED]`

---

## M9 — Medium
**Assessment:** `PARTIAL`.

**Reviewer concern:** Methodology - Dataset: Có tên bộ dữ liệu, quy mô mẫu sử dụng, mô tả nhóm nhiệm vụ và trích dẫn nguồn gốc; thiếu URL nguồn, quy trình lọc từ toàn bộ dữ

**Evidence / remaining work:** 94-task dataset manifest and domain distribution exist; benchmark upstream provenance pending.

**Draft response to reviewer:** We revised the scope of our claims and documented the available evidence: 94-task dataset manifest and domain distribution exist; benchmark upstream provenance pending. We do not interpret this as satisfying the request until the corresponding final-manuscript passage, artifact or author attestation is checked.

**Closure evidence:** final PDF section/page, repository path/revision, reviewer confirmation: `[TO BE VERIFIED]`

---

## M10 — Medium
**Assessment:** `BLOCKED_AUTHOR`.

**Reviewer concern:** Methodology - Experimental Setup: thiếu cách đặt yêu cầu cho mô hình nguyên văn, thiếu gói tái lập công khai. Có cấu hình mô hình được gọi tên, phiên

**Evidence / remaining work:** Model ID, access date and session settings unavailable or incomplete.

**Draft response to reviewer:** We revised the scope of our claims and documented the available evidence: Model ID, access date and session settings unavailable or incomplete. We do not interpret this as satisfying the request until the corresponding final-manuscript passage, artifact or author attestation is checked.

**Closure evidence:** final PDF section/page, repository path/revision, reviewer confirmation: `[TO BE VERIFIED]`

---

## M11 — Medium
**Assessment:** `PARTIAL`.

**Reviewer concern:** Methodology - Metrics: Đã định nghĩa combined success, recovery count/rate và công thức cho các thước đo riêng; thiếu mô tả tập trung về công cụ/thư v

**Evidence / remaining work:** Metrics defined in methodology; tool thresholds and citation need final review.

**Draft response to reviewer:** We revised the scope of our claims and documented the available evidence: Metrics defined in methodology; tool thresholds and citation need final review. We do not interpret this as satisfying the request until the corresponding final-manuscript passage, artifact or author attestation is checked.

**Closure evidence:** final PDF section/page, repository path/revision, reviewer confirmation: `[TO BE VERIFIED]`

---

## M12 — Medium
**Assessment:** `PARTIAL`.

**Reviewer concern:** Trích dẫn cho nhận định về công trình trước: 1 câu nhận định chung về lĩnh vực ở Introduction/Related Work không có trích dẫn. Minh chứng được dẫn: "R

**Evidence / remaining work:** Claim-to-source citation audit not complete.

**Draft response to reviewer:** We revised the scope of our claims and documented the available evidence: Claim-to-source citation audit not complete. We do not interpret this as satisfying the request until the corresponding final-manuscript passage, artifact or author attestation is checked.

**Closure evidence:** final PDF section/page, repository path/revision, reviewer confirmation: `[TO BE VERIFIED]`

---

## M13 — Medium
**Assessment:** `PARTIAL`.

**Reviewer concern:** results: Kết quả trả lời các RQ bằng số lượng sau chuẩn hóa và trình bày lỗi còn lại, nhưng không báo exact p-value, mức độ ảnh hưởng, hoặc invalid/er

**Evidence / remaining work:** Results present; confirmatory p-value not applicable to purely descriptive design, reviewer disposition needed.

**Draft response to reviewer:** We revised the scope of our claims and documented the available evidence: Results present; confirmatory p-value not applicable to purely descriptive design, reviewer disposition needed. We do not interpret this as satisfying the request until the corresponding final-manuscript passage, artifact or author attestation is checked.

**Closure evidence:** final PDF section/page, repository path/revision, reviewer confirmation: `[TO BE VERIFIED]`

---

## M14 — Medium
**Assessment:** `PARTIAL`.

**Reviewer concern:** discussion: Có giải thích lỗi, giới hạn và khác biệt mô tả giữa hai mô hình; tuy nhiên chưa đối chiếu kết quả trực tiếp với số liệu hoặc kết luận của

**Evidence / remaining work:** Discussion/implications present; cross-study synthesis review pending.

**Draft response to reviewer:** We revised the scope of our claims and documented the available evidence: Discussion/implications present; cross-study synthesis review pending. We do not interpret this as satisfying the request until the corresponding final-manuscript passage, artifact or author attestation is checked.

**Closure evidence:** final PDF section/page, repository path/revision, reviewer confirmation: `[TO BE VERIFIED]`

---

## M15 — Medium
**Assessment:** `EVIDENCED_IN_DRAFT`.

**Reviewer concern:** Threats to Validity: Đã đề cập các nguy cơ gần với internal, external và construct validity, nhưng chưa tổ chức đầy đủ theo bốn nhóm internal/external

**Evidence / remaining work:** Four validity types appear in main manuscript.

**Draft response to reviewer:** The available draft contains a treatment of this concern: Four validity types appear in main manuscript. We will verify its location and accuracy against the final PDF before requesting closure.

**Closure evidence:** final PDF section/page, repository path/revision, reviewer confirmation: `[TO BE VERIFIED]`

---

## M16 — Medium
**Assessment:** `EVIDENCED_IN_DRAFT`.

**Reviewer concern:** conclusion: Đã tóm tắt số liệu recovery và combined success, nêu đóng góp chính và các hướng tương lai cụ thể; tuy nhiên chưa tóm tắt định lượng hoặc

**Evidence / remaining work:** Numerical results included in conclusion.

**Draft response to reviewer:** The available draft contains a treatment of this concern: Numerical results included in conclusion. We will verify its location and accuracy against the final PDF before requesting closure.

**Closure evidence:** final PDF section/page, repository path/revision, reviewer confirmation: `[TO BE VERIFIED]`

---

## M17 — Medium
**Assessment:** `BLOCKED_AUTHOR`.

**Reviewer concern:** Công bố sử dụng AI: Bài dùng LLM nhưng Chưa thấy câu nêu tên mô hình/cách dùng. Minh chứng: Chưa có trích đoạn cụ thể.

**Evidence / remaining work:** Authors must identify and declare AI-generated manuscript material.

**Draft response to reviewer:** We revised the scope of our claims and documented the available evidence: Authors must identify and declare AI-generated manuscript material. We do not interpret this as satisfying the request until the corresponding final-manuscript passage, artifact or author attestation is checked.

**Closure evidence:** final PDF section/page, repository path/revision, reviewer confirmation: `[TO BE VERIFIED]`

---

## M18 — Medium
**Assessment:** `BLOCKED_PUBLICATION`.

**Reviewer concern:** Khả năng truy cập dữ liệu: Chưa thấy URL dữ liệu/mã của bài hay câu nêu dữ liệu có công khai không. Minh chứng: Chưa có trích đoạn cụ thể.

**Evidence / remaining work:** Real artifact access or documented limitations required.

**Draft response to reviewer:** We revised the scope of our claims and documented the available evidence: Real artifact access or documented limitations required. We do not interpret this as satisfying the request until the corresponding final-manuscript passage, artifact or author attestation is checked.

**Closure evidence:** final PDF section/page, repository path/revision, reviewer confirmation: `[TO BE VERIFIED]`

---

## M19 — Medium
**Assessment:** `BLOCKED_AUTHOR`.

**Reviewer concern:** Công bố xung đột lợi ích: Không có câu xung đột lợi ích/tài trợ (có thể vắng hợp lệ với bài môn học, nhưng nhiều venue bắt buộc). Minh chứng: Chưa có

**Evidence / remaining work:** Individual-author funding and competing-interest confirmation required.

**Draft response to reviewer:** We revised the scope of our claims and documented the available evidence: Individual-author funding and competing-interest confirmation required. We do not interpret this as satisfying the request until the corresponding final-manuscript passage, artifact or author attestation is checked.

**Closure evidence:** final PDF section/page, repository path/revision, reviewer confirmation: `[TO BE VERIFIED]`

---

## M20 — Medium
**Assessment:** `PARTIAL`.

**Reviewer concern:** Nhận xét 1 (Văn phong) 2 câu bị gắn cờ loại "Khẳng định thiếu căn cứ". Minh chứng: Chưa có trích đoạn cụ thể.  Nhận xét 2 (Văn phong, câu 1) Kết luận

**Evidence / remaining work:** Overstated causal claims reduced in NIER draft; whole paper still requires check.

**Draft response to reviewer:** We revised the scope of our claims and documented the available evidence: Overstated causal claims reduced in NIER draft; whole paper still requires check. We do not interpret this as satisfying the request until the corresponding final-manuscript passage, artifact or author attestation is checked.

**Closure evidence:** final PDF section/page, repository path/revision, reviewer confirmation: `[TO BE VERIFIED]`

---

## M21 — Medium
**Assessment:** `PARTIAL`.

**Reviewer concern:** Nhận xét 1 (Văn phong) 1 câu bị gắn cờ loại "Khoảng trống cấu trúc". Minh chứng: Chưa có trích đoạn cụ thể.  Nhận xét 2 (Văn phong, câu 3) Đoạn Relate

**Evidence / remaining work:** Related work synthesis not fully verified against primary literature.

**Draft response to reviewer:** We revised the scope of our claims and documented the available evidence: Related work synthesis not fully verified against primary literature. We do not interpret this as satisfying the request until the corresponding final-manuscript passage, artifact or author attestation is checked.

**Closure evidence:** final PDF section/page, repository path/revision, reviewer confirmation: `[TO BE VERIFIED]`

---

## M22 — Medium
**Assessment:** `PARTIAL`.

**Reviewer concern:** Chưa xác định nơi nộp: Bài chưa nêu sẽ nộp hội nghị hay tạp chí nào và theo template nào. Giới hạn trang, định dạng, các mục bắt buộc (Data Availabili

**Evidence / remaining work:** Venue NIER identified and CFP verified; final submission formatting/anonymity gate remains.

**Draft response to reviewer:** We revised the scope of our claims and documented the available evidence: Venue NIER identified and CFP verified; final submission formatting/anonymity gate remains. We do not interpret this as satisfying the request until the corresponding final-manuscript passage, artifact or author attestation is checked.

**Closure evidence:** final PDF section/page, repository path/revision, reviewer confirmation: `[TO BE VERIFIED]`

---
