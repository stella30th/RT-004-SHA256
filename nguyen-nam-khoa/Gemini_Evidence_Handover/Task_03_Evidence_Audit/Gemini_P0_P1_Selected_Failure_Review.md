# Gemini Evidence Review: CE023 and Seven Selected Failure Cases

Reviewer: Nguyen Nam Khoa. Audit date: 7 November 2026.

The archived P0/P1 records were reviewed. All eight selected P0 cases failed: two compilation failures and six execution-stage test failures. P1 yielded six PASS and two FAIL results. CE023 is handled separately because its P0 prompt source differed from the project source. Of the seven remaining eligible recovery cases, five passed and two failed in P1.

The audit examined production code and generated tests, reconciled Maven logs with Surefire TXT/XML, and compared prompt source blocks with P0 captures. Archived paths and SHA-256 hashes were cross-checked against available files. Execution outcomes come from historical logs. Character-level traces were calculated from the source and checked against Surefire failures.

The archive contains aggregate P0/P1 prompts and eight Java test files for each phase of the selected set. P0 captures match the stored P0 tests. At audit time, the inventory did not locate raw P1 response captures or submission-session/model-ID evidence. The two P0 compilation failures have no Surefire reports because execution stopped before the test phase.

| Task | P0 | P1 | Tests P1 | Assertion fail P1 | P1 runtime errors |
|---|---|---|---:|---:|---:|
| CE023 | FAIL | PASS | 4 | 0 | 0 |
| CE033 | FAIL | FAIL | 4 | 1 | 0 |
| CE037 | FAIL | FAIL | 4 | 1 | 0 |
| CE039 | FAIL | PASS | 5 | 0 | 0 |
| CE062 | FAIL | PASS | 5 | 0 | 0 |
| CE073 | FAIL | PASS | 4 | 0 | 0 |
| CE079 | FAIL | PASS | 3 | 0 | 0 |
| CE094 | FAIL | PASS | 4 | 0 | 0 |

> **Source-path note:** Links pointing to `C:/Users/...` are preserved from the original audit as historical local references. They may not open on another computer. Use the archive-relative file names and the Task 01 evidence ZIP for portable access.

## CE023

P0 used `ClassroomManagementTest.Course`, whereas the project defines `Classroom.Course`. P1 used the project source with the nested `Course` type and added `equals/hashCode`. All four P1 tests passed. CE023 is excluded from the seven-case recovery set. No independent P0 run with corrected input was found.

The P0 prompt differs from the project source; the P1 prompt matches it.

| Evidence | P0 | P1 |
|---|---|---|
| Aggregate prompt and task starting line | [p0/prompts.txt](<C:/Users/ADMIN/OneDrive/Desktop/study/ky 5/SWT/RBL/src code/RT-SWT302-004-SHA256/workspace-template/behavioral-output-P0-gemini/ClassEvalT_P0_Behavioral_All_94_Prompts.txt:3058>) | [p1/prompts.txt](<C:/Users/ADMIN/OneDrive/Desktop/study/ky 5/SWT/RBL/src code/RT-SWT302-004-SHA256/workspace-template/behavioral-p1-resultOAPR-gemini/ClassEvalT_Gemini_3_8_Flash_P1_OAPR.txt:488>) |
| Test Java | [p0/tests/GeneratedTest_CE023.java](<C:/Users/ADMIN/OneDrive/Desktop/study/ky 5/SWT/RBL/src code/RT-SWT302-004-SHA256/workspace-template/src/tests-gemini-p0/java/org/GeneratedTest_CE023.java>) | [p1/tests/GeneratedTest_CE023.java](<C:/Users/ADMIN/OneDrive/Desktop/study/ky 5/SWT/RBL/src code/RT-SWT302-004-SHA256/workspace-template/src/tests-gemini-p1/java/org/GeneratedTest_CE023.java>) |
| Log Maven | [p0/logs/CE023.log](<C:/Users/ADMIN/OneDrive/Desktop/study/ky 5/SWT/RBL/src code/RT-SWT302-004-SHA256/workspace-template/behavioral-output-P0-gemini/per-task/CE023.log>) | [p1/logs/CE023.log](<C:/Users/ADMIN/OneDrive/Desktop/study/ky 5/SWT/RBL/src code/RT-SWT302-004-SHA256/workspace-template/behavioral-p1-resultOAPR-gemini/per-task/CE023.log>) |
| Surefire TXT | Not applicable (compilation failure) | [p1/surefire/CE023.txt](<C:/Users/ADMIN/OneDrive/Desktop/study/ky 5/SWT/RBL/src code/RT-SWT302-004-SHA256/workspace-template/behavioral-p1-resultOAPR-gemini/per-task/CE023.txt>) |
| Surefire XML | Not applicable (compilation failure) | [p1/xml/20261029-082426-f2f8e4bb/CE023.xml](<C:/Users/ADMIN/OneDrive/Desktop/study/ky 5/SWT/RBL/src code/RT-SWT302-004-SHA256/workspace-template/target/gemini-p1/20261029-082426-f2f8e4bb/CE023/surefire-reports/TEST-org.example.GeneratedTest_CE023.xml>) |

Project source checked at [source/Classroom.java](<C:/Users/ADMIN/OneDrive/Desktop/study/ky 5/SWT/RBL/src code/RT-SWT302-004-SHA256/workspace-template/src/main/java/org/example/Classroom.java:18>).

SHA-256 test P0 `8cd64e77345317ec6669921b6e6cc2625403282c47c234a25990f862d1e26407`.

SHA-256 test P1 `c7fb400d351fdacfdf035400ed89911aae4ad5da685ad0d0e72c33e3a3e932e1`.

Input changes replaced `ClassroomManagementTest.Course` with a nested `Course`, added its definition, constructor, and `equals/hashCode`, and changed the `addCourse`, `removeCourse`, and `checkCourseConflict` signatures. The P1 source matches the available project source.

```diff
--- TARGET_SOURCE P0
+++ TARGET_SOURCE P1
@@ -3,47 +3,86 @@
 import java.time.LocalTime;
 import java.util.ArrayList;
 import java.util.List;
-
-
+import java.util.Objects;
 
 public class Classroom {
+
     private int id;
-    List<ClassroomManagementTest.Course> courses;
+    List<Course> courses;
 
     public Classroom(int id) {
         this.id = id;
         this.courses = new ArrayList<>();
     }
 
-    public void addCourse(ClassroomManagementTest.Course course) {
+    public static class Course {
+        String name;
+        LocalTime startTime;
+        LocalTime endTime;
+
+        public Course(String name, String startTime, String endTime) {
+            this.name = name;
+            this.startTime = LocalTime.parse(startTime);
+            this.endTime = LocalTime.parse(endTime);
+        }
+
+        @Override
+        public boolean equals(Object obj) {
+            if (this == obj) {
+                return true;
+            }
+
+            if (obj == null || getClass() != obj.getClass()) {
+                return false;
+            }
+
+            Course course = (Course) obj;
+
+            return name.equals(course.name)
+                    && startTime.equals(course.startTime)
+                    && endTime.equals(course.endTime);
+        }
+
+        @Override
+        public int hashCode() {
+            return Objects.hash(name, startTime, endTime);
+        }
+    }
+
+    public void addCourse(Course course) {
         if (!courses.contains(course)) {
             courses.add(course);
         }
     }
 
-    public void removeCourse(ClassroomManagementTest.Course course) {
+    public void removeCourse(Course course) {
         courses.remove(course);
     }
 
     public boolean isFreeAt(String checkTime) {
         LocalTime time = LocalTime.parse(checkTime);
-        for (ClassroomManagementTest.Course course : courses) {
-            if (!time.isBefore(course.startTime) && !time.isAfter(course.endTime)) {
+
+        for (Course course : courses) {
+            if (!time.isBefore(course.startTime)
+                    && !time.isAfter(course.endTime)) {
                 return false;
             }
         }
+
         return true;
     }
 
-    public boolean checkCourseConflict(ClassroomManagementTest.Course newCourse) {
+    public boolean checkCourseConflict(Course newCourse) {
         LocalTime newStartTime = newCourse.startTime;
         LocalTime newEndTime = newCourse.endTime;
 
-        for (ClassroomManagementTest.Course course : courses) {
-            if (!(newEndTime.isBefore(course.startTime) || newStartTime.isAfter(course.endTime))) {
+        for (Course course : courses) {
+            if (!(newEndTime.isBefore(course.startTime)
+                    || newStartTime.isAfter(course.endTime))) {
                 return false;
             }
         }
+
         return true;
     }
 }
```

## CE033

P0 expected `rails=1` to return the input unchanged, but the source increments `row` to 1 and accesses beyond a single-row array. P1 changed the `rails=1` case to expect an exception. Its Vigenere assertion at line 34 remains incorrect: a character-level trace yields `Helln, Xpsme!`, not the expected `Hello, World!`.

Both P0 and P1 prompts match the project source.

| Evidence | P0 | P1 |
|---|---|---|
| Aggregate prompt and task starting line | [p0/prompts.txt](<C:/Users/ADMIN/OneDrive/Desktop/study/ky 5/SWT/RBL/src code/RT-SWT302-004-SHA256/workspace-template/behavioral-output-P0-gemini/ClassEvalT_P0_Behavioral_All_94_Prompts.txt:4124>) | [p1/prompts.txt](<C:/Users/ADMIN/OneDrive/Desktop/study/ky 5/SWT/RBL/src code/RT-SWT302-004-SHA256/workspace-template/behavioral-p1-resultOAPR-gemini/ClassEvalT_Gemini_3_8_Flash_P1_OAPR.txt:598>) |
| Test Java | [p0/tests/GeneratedTest_CE033.java](<C:/Users/ADMIN/OneDrive/Desktop/study/ky 5/SWT/RBL/src code/RT-SWT302-004-SHA256/workspace-template/src/tests-gemini-p0/java/org/GeneratedTest_CE033.java>) | [p1/tests/GeneratedTest_CE033.java](<C:/Users/ADMIN/OneDrive/Desktop/study/ky 5/SWT/RBL/src code/RT-SWT302-004-SHA256/workspace-template/src/tests-gemini-p1/java/org/GeneratedTest_CE033.java>) |
| Log Maven | [p0/logs/CE033.log](<C:/Users/ADMIN/OneDrive/Desktop/study/ky 5/SWT/RBL/src code/RT-SWT302-004-SHA256/workspace-template/behavioral-output-P0-gemini/per-task/CE033.log>) | [p1/logs/CE033.log](<C:/Users/ADMIN/OneDrive/Desktop/study/ky 5/SWT/RBL/src code/RT-SWT302-004-SHA256/workspace-template/behavioral-p1-resultOAPR-gemini/per-task/CE033.log>) |
| Surefire TXT | [p0/surefire/CE033.txt](<C:/Users/ADMIN/OneDrive/Desktop/study/ky 5/SWT/RBL/src code/RT-SWT302-004-SHA256/workspace-template/behavioral-output-P0-gemini/all-result/org.example.GeneratedTest_CE033.txt>) | [p1/surefire/CE033.txt](<C:/Users/ADMIN/OneDrive/Desktop/study/ky 5/SWT/RBL/src code/RT-SWT302-004-SHA256/workspace-template/behavioral-p1-resultOAPR-gemini/per-task/CE033.txt>) |
| Surefire XML | [p0/xml/20261028-204355-ae2bdc2c/CE033.xml](<C:/Users/ADMIN/OneDrive/Desktop/study/ky 5/SWT/RBL/src code/RT-SWT302-004-SHA256/workspace-template/target/gemini-p0/20261028-204355-ae2bdc2c/CE033/surefire-reports/TEST-org.example.GeneratedTest_CE033.xml>) | [p1/xml/20261029-082426-f2f8e4bb/CE033.xml](<C:/Users/ADMIN/OneDrive/Desktop/study/ky 5/SWT/RBL/src code/RT-SWT302-004-SHA256/workspace-template/target/gemini-p1/20261029-082426-f2f8e4bb/CE033/surefire-reports/TEST-org.example.GeneratedTest_CE033.xml>) |

Project source checked at [source/DecryptionUtils.java](<C:/Users/ADMIN/OneDrive/Desktop/study/ky 5/SWT/RBL/src code/RT-SWT302-004-SHA256/workspace-template/src/main/java/org/example/DecryptionUtils.java:26>).

SHA-256 test P0 `c19a30bf3cf1ac120022d348b782672c227073410b17d5b1e66676f2443fe59b`.

SHA-256 test P1 `58054b5fe1ce70b8e812fc34ccd81368f90c83c428cfa5cb89364a01396f7633`.

| P0 failure from Surefire | Test location | Message |
|---|---|---|
| testRailFenceDecipher | [Line 28](<C:/Users/ADMIN/OneDrive/Desktop/study/ky 5/SWT/RBL/src code/RT-SWT302-004-SHA256/workspace-template/src/tests-gemini-p0/java/org/GeneratedTest_CE033.java:28>) | Index 1 out of bounds for length 1 |

| P1 failure from Surefire | Test location | Message |
|---|---|---|
| testVigenereDecipher | [Line 34](<C:/Users/ADMIN/OneDrive/Desktop/study/ky 5/SWT/RBL/src code/RT-SWT302-004-SHA256/workspace-template/src/tests-gemini-p1/java/org/GeneratedTest_CE033.java:34>) | expected:<Hell[o, World]!> but was:<Hell[n, Xpsme]!> |

### Review of Remaining P1 Assertion Failure

The test uses key `abc`, input `Hfnlo, Zptoe!`, and expected `Hello, World!` at line 34. The source advances `keyIndex` on letters and leaves punctuation and spaces unchanged. Character-by-character decryption produces `Helln, Xpsme!`, matching the Surefire actual value.

P0 fails in `railFenceDecipher` for `rails=1`: after writing `fence[0][0]`, `row` becomes 1, and the next access to `fence[1]` throws `ArrayIndexOutOfBoundsException`. P1 expects the exception in that case, but its revised Vigenere fixture contains an incorrect assertion.

| Position | Input | Key | Shift | Result from source | Expected in test | Comparison |
|---:|---|---|---:|---|---|---|
| 1 | H | a | 0 | H | H | Match |
| 2 | f | b | 1 | e | e | Match |
| 3 | n | c | 2 | l | l | Match |
| 4 | l | a | 0 | l | l | Match |
| 5 | o | b | 1 | n | o | Mismatch |
| 6 | , | Key not consumed |  | , | , | Match |
| 7 | [space] | Key not consumed |  | [space] | [space] | Match |
| 8 | Z | c | 2 | X | W | Mismatch |
| 9 | p | a | 0 | p | o | Mismatch |
| 10 | t | b | 1 | s | r | Mismatch |
| 11 | o | c | 2 | m | l | Mismatch |
| 12 | e | a | 0 | e | d | Mismatch |
| 13 | ! | Key not consumed |  | ! | ! | Match |

## CE037

The P0 rail-fence oracle omitted a space and had an incorrect ordering: expected `HLOOLELWRD`, actual `HLOWRDEL OL`. The P1 rail-fence test passed, but its Vigenere fixture with key `abc` and input `bba` yields `bcc`, while the test expects `bbb`. The three shifts (0, 1, 2) are documented in the oracle trace.

Both P0 and P1 prompts match the project source.

| Evidence | P0 | P1 |
|---|---|---|
| Aggregate prompt and task starting line | [p0/prompts.txt](<C:/Users/ADMIN/OneDrive/Desktop/study/ky 5/SWT/RBL/src code/RT-SWT302-004-SHA256/workspace-template/behavioral-output-P0-gemini/ClassEvalT_P0_Behavioral_All_94_Prompts.txt:4742>) | [p1/prompts.txt](<C:/Users/ADMIN/OneDrive/Desktop/study/ky 5/SWT/RBL/src code/RT-SWT302-004-SHA256/workspace-template/behavioral-p1-resultOAPR-gemini/ClassEvalT_Gemini_3_8_Flash_P1_OAPR.txt:706>) |
| Test Java | [p0/tests/GeneratedTest_CE037.java](<C:/Users/ADMIN/OneDrive/Desktop/study/ky 5/SWT/RBL/src code/RT-SWT302-004-SHA256/workspace-template/src/tests-gemini-p0/java/org/GeneratedTest_CE037.java>) | [p1/tests/GeneratedTest_CE037.java](<C:/Users/ADMIN/OneDrive/Desktop/study/ky 5/SWT/RBL/src code/RT-SWT302-004-SHA256/workspace-template/src/tests-gemini-p1/java/org/GeneratedTest_CE037.java>) |
| Log Maven | [p0/logs/CE037.log](<C:/Users/ADMIN/OneDrive/Desktop/study/ky 5/SWT/RBL/src code/RT-SWT302-004-SHA256/workspace-template/behavioral-output-P0-gemini/per-task/CE037.log>) | [p1/logs/CE037.log](<C:/Users/ADMIN/OneDrive/Desktop/study/ky 5/SWT/RBL/src code/RT-SWT302-004-SHA256/workspace-template/behavioral-p1-resultOAPR-gemini/per-task/CE037.log>) |
| Surefire TXT | [p0/surefire/CE037.txt](<C:/Users/ADMIN/OneDrive/Desktop/study/ky 5/SWT/RBL/src code/RT-SWT302-004-SHA256/workspace-template/behavioral-output-P0-gemini/all-result/org.example.GeneratedTest_CE037.txt>) | [p1/surefire/CE037.txt](<C:/Users/ADMIN/OneDrive/Desktop/study/ky 5/SWT/RBL/src code/RT-SWT302-004-SHA256/workspace-template/behavioral-p1-resultOAPR-gemini/per-task/CE037.txt>) |
| Surefire XML | [p0/xml/20261028-204355-ae2bdc2c/CE037.xml](<C:/Users/ADMIN/OneDrive/Desktop/study/ky 5/SWT/RBL/src code/RT-SWT302-004-SHA256/workspace-template/target/gemini-p0/20261028-204355-ae2bdc2c/CE037/surefire-reports/TEST-org.example.GeneratedTest_CE037.xml>) | [p1/xml/20261029-082426-f2f8e4bb/CE037.xml](<C:/Users/ADMIN/OneDrive/Desktop/study/ky 5/SWT/RBL/src code/RT-SWT302-004-SHA256/workspace-template/target/gemini-p1/20261029-082426-f2f8e4bb/CE037/surefire-reports/TEST-org.example.GeneratedTest_CE037.xml>) |

Project source checked at [source/EncryptionUtils.java](<C:/Users/ADMIN/OneDrive/Desktop/study/ky 5/SWT/RBL/src code/RT-SWT302-004-SHA256/workspace-template/src/main/java/org/example/EncryptionUtils.java:25>).

SHA-256 test P0 `44176cb009a1161c8c1d3d92feff17afe71b2105cd223f421b51f008b11e31d9`.

SHA-256 test P1 `619e8588179dadbe6d7770275fb1c5e29a6b74f018ed2019e53d99b644383b9a`.

| P0 failure from Surefire | Test location | Message |
|---|---|---|
| testRailFenceCipher | [Line 30](<C:/Users/ADMIN/OneDrive/Desktop/study/ky 5/SWT/RBL/src code/RT-SWT302-004-SHA256/workspace-template/src/tests-gemini-p0/java/org/GeneratedTest_CE037.java:30>) | expected:<HLO[OLELWRD]> but was:<HLO[WRDEL OL]> |

| P1 failure from Surefire | Test location | Message |
|---|---|---|
| testVigenereCipher | [Line 34](<C:/Users/ADMIN/OneDrive/Desktop/study/ky 5/SWT/RBL/src code/RT-SWT302-004-SHA256/workspace-template/src/tests-gemini-p1/java/org/GeneratedTest_CE037.java:34>) | expected:<b[bb]> but was:<b[cc]> |

### Review of Remaining P1 Assertion Failure

The test uses key `abc`, input `bba`, and expected `bbb` at line 34. Shifts 0, 1, and 2 produce `b`, `c`, and `c`, so the source returns `bcc`, consistent with Surefire.

P0 rail-fence fails for input `HELLO WORLD` with two rails. Row 0 contains `HLOWRD`; row 1 contains `EL OL`; the concatenated result is `HLOWRDEL OL`. The P1 rail-fence test passes with new fixtures, but the Vigenere assertion remains incorrect.

| Position | Input | Key | Shift | Result from source | Expected in test | Comparison |
|---:|---|---|---:|---|---|---|
| 1 | b | a | 0 | b | b | Match |
| 2 | b | b | 1 | c | b | Mismatch |
| 3 | a | c | 2 | c | b | Mismatch |

## CE039

P0 expected `10-6/2` to evaluate to 7, whereas the actual value was 3; `compare(*,+)` returned false rather than the expected true. P1 tested `compare(*,+)=false` and used `2 - 3`, whose actual result was 3. All five P1 tests passed. The subtraction fixture was changed; the source converts minus to `~` and omits that token when parsing.

Both P0 and P1 prompts match the project source.

| Evidence | P0 | P1 |
|---|---|---|
| Aggregate prompt and task starting line | [p0/prompts.txt](<C:/Users/ADMIN/OneDrive/Desktop/study/ky 5/SWT/RBL/src code/RT-SWT302-004-SHA256/workspace-template/behavioral-output-P0-gemini/ClassEvalT_P0_Behavioral_All_94_Prompts.txt:5001>) | [p1/prompts.txt](<C:/Users/ADMIN/OneDrive/Desktop/study/ky 5/SWT/RBL/src code/RT-SWT302-004-SHA256/workspace-template/behavioral-p1-resultOAPR-gemini/ClassEvalT_Gemini_3_8_Flash_P1_OAPR.txt:798>) |
| Test Java | [p0/tests/GeneratedTest_CE039.java](<C:/Users/ADMIN/OneDrive/Desktop/study/ky 5/SWT/RBL/src code/RT-SWT302-004-SHA256/workspace-template/src/tests-gemini-p0/java/org/GeneratedTest_CE039.java>) | [p1/tests/GeneratedTest_CE039.java](<C:/Users/ADMIN/OneDrive/Desktop/study/ky 5/SWT/RBL/src code/RT-SWT302-004-SHA256/workspace-template/src/tests-gemini-p1/java/org/GeneratedTest_CE039.java>) |
| Log Maven | [p0/logs/CE039.log](<C:/Users/ADMIN/OneDrive/Desktop/study/ky 5/SWT/RBL/src code/RT-SWT302-004-SHA256/workspace-template/behavioral-output-P0-gemini/per-task/CE039.log>) | [p1/logs/CE039.log](<C:/Users/ADMIN/OneDrive/Desktop/study/ky 5/SWT/RBL/src code/RT-SWT302-004-SHA256/workspace-template/behavioral-p1-resultOAPR-gemini/per-task/CE039.log>) |
| Surefire TXT | [p0/surefire/CE039.txt](<C:/Users/ADMIN/OneDrive/Desktop/study/ky 5/SWT/RBL/src code/RT-SWT302-004-SHA256/workspace-template/behavioral-output-P0-gemini/all-result/org.example.GeneratedTest_CE039.txt>) | [p1/surefire/CE039.txt](<C:/Users/ADMIN/OneDrive/Desktop/study/ky 5/SWT/RBL/src code/RT-SWT302-004-SHA256/workspace-template/behavioral-p1-resultOAPR-gemini/per-task/CE039.txt>) |
| Surefire XML | [p0/xml/20261028-204355-ae2bdc2c/CE039.xml](<C:/Users/ADMIN/OneDrive/Desktop/study/ky 5/SWT/RBL/src code/RT-SWT302-004-SHA256/workspace-template/target/gemini-p0/20261028-204355-ae2bdc2c/CE039/surefire-reports/TEST-org.example.GeneratedTest_CE039.xml>) | [p1/xml/20261029-082426-f2f8e4bb/CE039.xml](<C:/Users/ADMIN/OneDrive/Desktop/study/ky 5/SWT/RBL/src code/RT-SWT302-004-SHA256/workspace-template/target/gemini-p1/20261029-082426-f2f8e4bb/CE039/surefire-reports/TEST-org.example.GeneratedTest_CE039.xml>) |

Project source checked at [source/ExpressionCalculator.java](<C:/Users/ADMIN/OneDrive/Desktop/study/ky 5/SWT/RBL/src code/RT-SWT302-004-SHA256/workspace-template/src/main/java/org/example/ExpressionCalculator.java:54>).

SHA-256 test P0 `c2917063cf74d865a3a2cf67fe7a32722d413d5b87211ba990e150252d25717f`.

SHA-256 test P1 `697536830245eac3fd97b7e0b5bbf4f4259fc45fe50f2a2eeccaae806a7f6358`.

| P0 failure from Surefire | Test location | Message |
|---|---|---|
| testCalculateBasicArithmetic | [Line 15](<C:/Users/ADMIN/OneDrive/Desktop/study/ky 5/SWT/RBL/src code/RT-SWT302-004-SHA256/workspace-template/src/tests-gemini-p0/java/org/GeneratedTest_CE039.java:15>) | expected:<7.0> but was:<3.0> |
| testIsOperatorAndCompare | [Line 47](<C:/Users/ADMIN/OneDrive/Desktop/study/ky 5/SWT/RBL/src code/RT-SWT302-004-SHA256/workspace-template/src/tests-gemini-p0/java/org/GeneratedTest_CE039.java:47>) | java.lang.AssertionError |

## CE062

P0 expected the complete trailing text to be preserved, but the loop condition `pos < length - 2` discards the last two ordinary characters. P1 checks that `abc` becomes `a` and `Hello` becomes `Hel`. All five stored P1 tests passed; the P0 failing fixtures were replaced. PASS applies to the stored P1 suite.

Both P0 and P1 prompts match the project source.

| Evidence | P0 | P1 |
|---|---|---|
| Aggregate prompt and task starting line | [p0/prompts.txt](<C:/Users/ADMIN/OneDrive/Desktop/study/ky 5/SWT/RBL/src code/RT-SWT302-004-SHA256/workspace-template/behavioral-output-P0-gemini/ClassEvalT_P0_Behavioral_All_94_Prompts.txt:7860>) | [p1/prompts.txt](<C:/Users/ADMIN/OneDrive/Desktop/study/ky 5/SWT/RBL/src code/RT-SWT302-004-SHA256/workspace-template/behavioral-p1-resultOAPR-gemini/ClassEvalT_Gemini_3_8_Flash_P1_OAPR.txt:926>) |
| Test Java | [p0/tests/GeneratedTest_CE062.java](<C:/Users/ADMIN/OneDrive/Desktop/study/ky 5/SWT/RBL/src code/RT-SWT302-004-SHA256/workspace-template/src/tests-gemini-p0/java/org/GeneratedTest_CE062.java>) | [p1/tests/GeneratedTest_CE062.java](<C:/Users/ADMIN/OneDrive/Desktop/study/ky 5/SWT/RBL/src code/RT-SWT302-004-SHA256/workspace-template/src/tests-gemini-p1/java/org/GeneratedTest_CE062.java>) |
| Log Maven | [p0/logs/CE062.log](<C:/Users/ADMIN/OneDrive/Desktop/study/ky 5/SWT/RBL/src code/RT-SWT302-004-SHA256/workspace-template/behavioral-output-P0-gemini/per-task/CE062.log>) | [p1/logs/CE062.log](<C:/Users/ADMIN/OneDrive/Desktop/study/ky 5/SWT/RBL/src code/RT-SWT302-004-SHA256/workspace-template/behavioral-p1-resultOAPR-gemini/per-task/CE062.log>) |
| Surefire TXT | [p0/surefire/CE062.txt](<C:/Users/ADMIN/OneDrive/Desktop/study/ky 5/SWT/RBL/src code/RT-SWT302-004-SHA256/workspace-template/behavioral-output-P0-gemini/all-result/org.example.GeneratedTest_CE062.txt>) | [p1/surefire/CE062.txt](<C:/Users/ADMIN/OneDrive/Desktop/study/ky 5/SWT/RBL/src code/RT-SWT302-004-SHA256/workspace-template/behavioral-p1-resultOAPR-gemini/per-task/CE062.txt>) |
| Surefire XML | [p0/xml/20261028-204355-ae2bdc2c/CE062.xml](<C:/Users/ADMIN/OneDrive/Desktop/study/ky 5/SWT/RBL/src code/RT-SWT302-004-SHA256/workspace-template/target/gemini-p0/20261028-204355-ae2bdc2c/CE062/surefire-reports/TEST-org.example.GeneratedTest_CE062.xml>) | [p1/xml/20261029-082426-f2f8e4bb/CE062.xml](<C:/Users/ADMIN/OneDrive/Desktop/study/ky 5/SWT/RBL/src code/RT-SWT302-004-SHA256/workspace-template/target/gemini-p1/20261029-082426-f2f8e4bb/CE062/surefire-reports/TEST-org.example.GeneratedTest_CE062.xml>) |

Project source checked at [source/NumericEntityUnescaper.java](<C:/Users/ADMIN/OneDrive/Desktop/study/ky 5/SWT/RBL/src code/RT-SWT302-004-SHA256/workspace-template/src/main/java/org/example/NumericEntityUnescaper.java:10>).

SHA-256 test P0 `99e0f4509530eec34004b2a6fe447ef86db50c07469947ea52c3017d2bebc965`.

SHA-256 test P1 `cea4b0f21c0a641d60c59a1bef8b05a79cf4cd75e50bd6c848a84c2cc8c4206e`.

| P0 failure from Surefire | Test location | Message |
|---|---|---|
| testReplaceHexEntities | [Line 22](<C:/Users/ADMIN/OneDrive/Desktop/study/ky 5/SWT/RBL/src code/RT-SWT302-004-SHA256/workspace-template/src/tests-gemini-p0/java/org/GeneratedTest_CE062.java:22>) | expected:<Test & Mo[re]> but was:<Test & Mo[]> |
| testReplaceDecimalEntities | [Line 14](<C:/Users/ADMIN/OneDrive/Desktop/study/ky 5/SWT/RBL/src code/RT-SWT302-004-SHA256/workspace-template/src/tests-gemini-p0/java/org/GeneratedTest_CE062.java:14>) | expected:<Hello Worl[d!]> but was:<Hello Worl[]> |
| testNoEntitiesOrIncompleteEntities | [Line 28](<C:/Users/ADMIN/OneDrive/Desktop/study/ky 5/SWT/RBL/src code/RT-SWT302-004-SHA256/workspace-template/src/tests-gemini-p0/java/org/GeneratedTest_CE062.java:28>) | expected:<Just plain te[xt]> but was:<Just plain te[]> |

## CE073

P0 `countWords` returned 5 rather than the expected 4 for leading whitespace; the longest sentence contained 9 words rather than the expected 10. All four P1 tests passed with new fixtures, including a longest sentence of 7 words. P1 omitted the leading-whitespace fixture and replaced the nine-word example; no P1 result is available for those two original P0 fixtures.

Both P0 and P1 prompts match the project source.

| Evidence | P0 | P1 |
|---|---|---|
| Aggregate prompt and task starting line | [p0/prompts.txt](<C:/Users/ADMIN/OneDrive/Desktop/study/ky 5/SWT/RBL/src code/RT-SWT302-004-SHA256/workspace-template/behavioral-output-P0-gemini/ClassEvalT_P0_Behavioral_All_94_Prompts.txt:9355>) | [p1/prompts.txt](<C:/Users/ADMIN/OneDrive/Desktop/study/ky 5/SWT/RBL/src code/RT-SWT302-004-SHA256/workspace-template/behavioral-p1-resultOAPR-gemini/ClassEvalT_Gemini_3_8_Flash_P1_OAPR.txt:997>) |
| Test Java | [p0/tests/GeneratedTest_CE073.java](<C:/Users/ADMIN/OneDrive/Desktop/study/ky 5/SWT/RBL/src code/RT-SWT302-004-SHA256/workspace-template/src/tests-gemini-p0/java/org/GeneratedTest_CE073.java>) | [p1/tests/GeneratedTest_CE073.java](<C:/Users/ADMIN/OneDrive/Desktop/study/ky 5/SWT/RBL/src code/RT-SWT302-004-SHA256/workspace-template/src/tests-gemini-p1/java/org/GeneratedTest_CE073.java>) |
| Log Maven | [p0/logs/CE073.log](<C:/Users/ADMIN/OneDrive/Desktop/study/ky 5/SWT/RBL/src code/RT-SWT302-004-SHA256/workspace-template/behavioral-output-P0-gemini/per-task/CE073.log>) | [p1/logs/CE073.log](<C:/Users/ADMIN/OneDrive/Desktop/study/ky 5/SWT/RBL/src code/RT-SWT302-004-SHA256/workspace-template/behavioral-p1-resultOAPR-gemini/per-task/CE073.log>) |
| Surefire TXT | [p0/surefire/CE073.txt](<C:/Users/ADMIN/OneDrive/Desktop/study/ky 5/SWT/RBL/src code/RT-SWT302-004-SHA256/workspace-template/behavioral-output-P0-gemini/all-result/org.example.GeneratedTest_CE073.txt>) | [p1/surefire/CE073.txt](<C:/Users/ADMIN/OneDrive/Desktop/study/ky 5/SWT/RBL/src code/RT-SWT302-004-SHA256/workspace-template/behavioral-p1-resultOAPR-gemini/per-task/CE073.txt>) |
| Surefire XML | [p0/xml/20261028-204355-ae2bdc2c/CE073.xml](<C:/Users/ADMIN/OneDrive/Desktop/study/ky 5/SWT/RBL/src code/RT-SWT302-004-SHA256/workspace-template/target/gemini-p0/20261028-204355-ae2bdc2c/CE073/surefire-reports/TEST-org.example.GeneratedTest_CE073.xml>) | [p1/xml/20261029-082426-f2f8e4bb/CE073.xml](<C:/Users/ADMIN/OneDrive/Desktop/study/ky 5/SWT/RBL/src code/RT-SWT302-004-SHA256/workspace-template/target/gemini-p1/20261029-082426-f2f8e4bb/CE073/surefire-reports/TEST-org.example.GeneratedTest_CE073.xml>) |

Project source checked at [source/SplitSentence.java](<C:/Users/ADMIN/OneDrive/Desktop/study/ky 5/SWT/RBL/src code/RT-SWT302-004-SHA256/workspace-template/src/main/java/org/example/SplitSentence.java:25>).

SHA-256 test P0 `8e9bf533435854edecd9133074d6b9de2cc784c19a00d724cd8b5cd056c465c3`.

SHA-256 test P1 `7efe97adef5bd16f541d1a765cb5248fa073cfb59a429350d771b3214c69df34`.

| P0 failure from Surefire | Test location | Message |
|---|---|---|
| testCountWords | [Line 27](<C:/Users/ADMIN/OneDrive/Desktop/study/ky 5/SWT/RBL/src code/RT-SWT302-004-SHA256/workspace-template/src/tests-gemini-p0/java/org/GeneratedTest_CE073.java:27>) | expected:<4> but was:<5> |
| testProcessTextFileReturnsMaxWordCount | [Line 35](<C:/Users/ADMIN/OneDrive/Desktop/study/ky 5/SWT/RBL/src code/RT-SWT302-004-SHA256/workspace-template/src/tests-gemini-p0/java/org/GeneratedTest_CE073.java:35>) | expected:<10> but was:<9> |

## CE079

P0 called `assertTrue` at line 54 but statically imported only `assertEquals` and `assertNotNull`. P1 imported `org.junit.Assert.*` and all three tests passed. P0 stopped during `testCompile`, so no Surefire TXT/XML was produced.

Both P0 and P1 prompts match the project source.

| Evidence | P0 | P1 |
|---|---|---|
| Aggregate prompt and task starting line | [p0/prompts.txt](<C:/Users/ADMIN/OneDrive/Desktop/study/ky 5/SWT/RBL/src code/RT-SWT302-004-SHA256/workspace-template/behavioral-output-P0-gemini/ClassEvalT_P0_Behavioral_All_94_Prompts.txt:10203>) | [p1/prompts.txt](<C:/Users/ADMIN/OneDrive/Desktop/study/ky 5/SWT/RBL/src code/RT-SWT302-004-SHA256/workspace-template/behavioral-p1-resultOAPR-gemini/ClassEvalT_Gemini_3_8_Flash_P1_OAPR.txt:1060>) |
| Test Java | [p0/tests/GeneratedTest_CE079.java](<C:/Users/ADMIN/OneDrive/Desktop/study/ky 5/SWT/RBL/src code/RT-SWT302-004-SHA256/workspace-template/src/tests-gemini-p0/java/org/GeneratedTest_CE079.java>) | [p1/tests/GeneratedTest_CE079.java](<C:/Users/ADMIN/OneDrive/Desktop/study/ky 5/SWT/RBL/src code/RT-SWT302-004-SHA256/workspace-template/src/tests-gemini-p1/java/org/GeneratedTest_CE079.java>) |
| Log Maven | [p0/logs/CE079.log](<C:/Users/ADMIN/OneDrive/Desktop/study/ky 5/SWT/RBL/src code/RT-SWT302-004-SHA256/workspace-template/behavioral-output-P0-gemini/per-task/CE079.log>) | [p1/logs/CE079.log](<C:/Users/ADMIN/OneDrive/Desktop/study/ky 5/SWT/RBL/src code/RT-SWT302-004-SHA256/workspace-template/behavioral-p1-resultOAPR-gemini/per-task/CE079.log>) |
| Surefire TXT | Not applicable (compilation failure) | [p1/surefire/CE079.txt](<C:/Users/ADMIN/OneDrive/Desktop/study/ky 5/SWT/RBL/src code/RT-SWT302-004-SHA256/workspace-template/behavioral-p1-resultOAPR-gemini/per-task/CE079.txt>) |
| Surefire XML | Not applicable (compilation failure) | [p1/xml/20261029-082426-f2f8e4bb/CE079.xml](<C:/Users/ADMIN/OneDrive/Desktop/study/ky 5/SWT/RBL/src code/RT-SWT302-004-SHA256/workspace-template/target/gemini-p1/20261029-082426-f2f8e4bb/CE079/surefire-reports/TEST-org.example.GeneratedTest_CE079.xml>) |

Project source checked at [source/TextFileProcessor.java](<C:/Users/ADMIN/OneDrive/Desktop/study/ky 5/SWT/RBL/src code/RT-SWT302-004-SHA256/workspace-template/src/main/java/org/example/TextFileProcessor.java:15>).

SHA-256 test P0 `74ef39d7ed47e1c6fd42590c7941615661dc50bdce8e89fb246c5201b299ac89`.

SHA-256 test P1 `11f04d97a64bffbb8eacf5e2678ef621df466492370d90dcbc18349ab56a9b75`.

## CE094

The P0 ZIP contained `subdir/file2.txt` but no directory entry; `extractAll` did not create the child file’s parent directory. P1 called `extractAll` with two root-level files and tested the nested file through `extractFile`. All four P1 tests passed. P1 changed both fixture and call path. The source creates parent directories for `extractFile`, while `extractAll` logs the `IOException` and continues.

Both P0 and P1 prompts match the project source.

| Evidence | P0 | P1 |
|---|---|---|
| Aggregate prompt and task starting line | [p0/prompts.txt](<C:/Users/ADMIN/OneDrive/Desktop/study/ky 5/SWT/RBL/src code/RT-SWT302-004-SHA256/workspace-template/behavioral-output-P0-gemini/ClassEvalT_P0_Behavioral_All_94_Prompts.txt:12131>) | [p1/prompts.txt](<C:/Users/ADMIN/OneDrive/Desktop/study/ky 5/SWT/RBL/src code/RT-SWT302-004-SHA256/workspace-template/behavioral-p1-resultOAPR-gemini/ClassEvalT_Gemini_3_8_Flash_P1_OAPR.txt:1116>) |
| Test Java | [p0/tests/GeneratedTest_CE094.java](<C:/Users/ADMIN/OneDrive/Desktop/study/ky 5/SWT/RBL/src code/RT-SWT302-004-SHA256/workspace-template/src/tests-gemini-p0/java/org/GeneratedTest_CE094.java>) | [p1/tests/GeneratedTest_CE094.java](<C:/Users/ADMIN/OneDrive/Desktop/study/ky 5/SWT/RBL/src code/RT-SWT302-004-SHA256/workspace-template/src/tests-gemini-p1/java/org/GeneratedTest_CE094.java>) |
| Log Maven | [p0/logs/CE094.log](<C:/Users/ADMIN/OneDrive/Desktop/study/ky 5/SWT/RBL/src code/RT-SWT302-004-SHA256/workspace-template/behavioral-output-P0-gemini/per-task/CE094.log>) | [p1/logs/CE094.log](<C:/Users/ADMIN/OneDrive/Desktop/study/ky 5/SWT/RBL/src code/RT-SWT302-004-SHA256/workspace-template/behavioral-p1-resultOAPR-gemini/per-task/CE094.log>) |
| Surefire TXT | [p0/surefire/CE094.txt](<C:/Users/ADMIN/OneDrive/Desktop/study/ky 5/SWT/RBL/src code/RT-SWT302-004-SHA256/workspace-template/behavioral-output-P0-gemini/all-result/org.example.GeneratedTest_CE094.txt>) | [p1/surefire/CE094.txt](<C:/Users/ADMIN/OneDrive/Desktop/study/ky 5/SWT/RBL/src code/RT-SWT302-004-SHA256/workspace-template/behavioral-p1-resultOAPR-gemini/per-task/CE094.txt>) |
| Surefire XML | [p0/xml/20261028-204355-ae2bdc2c/CE094.xml](<C:/Users/ADMIN/OneDrive/Desktop/study/ky 5/SWT/RBL/src code/RT-SWT302-004-SHA256/workspace-template/target/gemini-p0/20261028-204355-ae2bdc2c/CE094/surefire-reports/TEST-org.example.GeneratedTest_CE094.xml>) | [p1/xml/20261029-082426-f2f8e4bb/CE094.xml](<C:/Users/ADMIN/OneDrive/Desktop/study/ky 5/SWT/RBL/src code/RT-SWT302-004-SHA256/workspace-template/target/gemini-p1/20261029-082426-f2f8e4bb/CE094/surefire-reports/TEST-org.example.GeneratedTest_CE094.xml>) |

Project source checked at [source/ZipFileProcessor.java](<C:/Users/ADMIN/OneDrive/Desktop/study/ky 5/SWT/RBL/src code/RT-SWT302-004-SHA256/workspace-template/src/main/java/org/example/ZipFileProcessor.java:45>).

SHA-256 test P0 `5433d9dfc2406e132e3a91df8c158dfef3ae68d89bd71af2eaf987650de433e6`.

SHA-256 test P1 `d8a8b0cc4817cd201a3439358d9df4b13f101491f61aefae3a98d1a2196c4963`.

| P0 failure from Surefire | Test location | Message |
|---|---|---|
| testExtractAll | [Line 66](<C:/Users/ADMIN/OneDrive/Desktop/study/ky 5/SWT/RBL/src code/RT-SWT302-004-SHA256/workspace-template/src/tests-gemini-p0/java/org/GeneratedTest_CE094.java:66>) | java.lang.AssertionError |

## Additional Evidence Needed

- Obtain original P1 response exports or captures to compare with the eight stored Java tests.
- Obtain Gemini session evidence and prompt submission dates to verify the model/version and actual prompt.
- If a separate corrected-input P0 run for CE023 exists, add its prompt, test, and log. Current records establish PASS for P1 only.

Fixture changes for CE039, CE062, CE073, and CE094 should be disclosed in the paper. Reported P1 outcomes apply to the archived P1 suites.
