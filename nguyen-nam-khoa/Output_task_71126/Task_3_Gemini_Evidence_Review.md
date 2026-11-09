# Kiểm tra bằng chứng Gemini cho CE023 và 7 ca lỗi

Người phụ trách Nguyễn Nam Khoa. Ngày đối soát 07/11/2026.

Đối soát hồ sơ P0/P1 đã lưu. Cả 8 ca P0 đều FAIL, gồm 2 lỗi biên dịch và 6 ca có lỗi khi chạy test. P1 có 6 PASS và 2 FAIL. Tách CE023 do đầu vào P0 khác source project, 7 ca còn lại có 5 PASS và 2 FAIL ở P1.

Phương pháp gồm đọc mã nguồn và test, đối chiếu log Maven với Surefire TXT/XML, kiểm tra prompt source và capture P0. Đường dẫn cùng mã SHA-256 trong bản sao lưu được đối chiếu với file đang có. Kết quả thực thi được trích từ log cũ. Các bảng trace ký tự được tính theo source và khớp lỗi trong Surefire.

Bản sao lưu có prompt tổng P0/P1 và 8 file Java cho mỗi pha trong nhóm kiểm tra. Capture P0 khớp các test P0 đã lưu. Chưa tìm thấy capture phản hồi thô P1 hoặc bằng chứng phiên gửi prompt/model ID trong hồ sơ đã kiểm kê. Hai ca lỗi biên dịch P0 không có Surefire vì dừng trước bước chạy test.

| Task | P0 | P1 | Tests P1 | Assertion fail P1 | Lỗi chạy P1 |
|---|---|---|---:|---:|---:|
| CE023 | FAIL | PASS | 4 | 0 | 0 |
| CE033 | FAIL | FAIL | 4 | 1 | 0 |
| CE037 | FAIL | FAIL | 4 | 1 | 0 |
| CE039 | FAIL | PASS | 5 | 0 | 0 |
| CE062 | FAIL | PASS | 5 | 0 | 0 |
| CE073 | FAIL | PASS | 4 | 0 | 0 |
| CE079 | FAIL | PASS | 3 | 0 | 0 |
| CE094 | FAIL | PASS | 4 | 0 | 0 |

## CE023

P0 dùng ClassroomManagementTest.Course, còn project định nghĩa Classroom.Course. P1 dùng source project có nested Course và bổ sung equals/hashCode. Test P1 chạy 4/4 PASS. CE023 tách riêng khỏi 7 ca recovery. Chưa thấy một lần P0 riêng với đầu vào đã sửa.

Prompt P0 khác source project. Prompt P1 khớp source project.

| Bằng chứng | P0 | P1 |
|---|---|---|
| Prompt tổng và dòng bắt đầu task | [p0/prompts.txt](<C:/Users/ADMIN/OneDrive/Desktop/study/ky 5/SWT/RBL/src code/RT-SWT302-004-SHA256/workspace-template/behavioral-output-P0-gemini/ClassEvalT_P0_Behavioral_All_94_Prompts.txt:3058>) | [p1/prompts.txt](<C:/Users/ADMIN/OneDrive/Desktop/study/ky 5/SWT/RBL/src code/RT-SWT302-004-SHA256/workspace-template/behavioral-p1-resultOAPR-gemini/ClassEvalT_Gemini_3_8_Flash_P1_OAPR.txt:488>) |
| Test Java | [p0/tests/GeneratedTest_CE023.java](<C:/Users/ADMIN/OneDrive/Desktop/study/ky 5/SWT/RBL/src code/RT-SWT302-004-SHA256/workspace-template/src/tests-gemini-p0/java/org/GeneratedTest_CE023.java>) | [p1/tests/GeneratedTest_CE023.java](<C:/Users/ADMIN/OneDrive/Desktop/study/ky 5/SWT/RBL/src code/RT-SWT302-004-SHA256/workspace-template/src/tests-gemini-p1/java/org/GeneratedTest_CE023.java>) |
| Log Maven | [p0/logs/CE023.log](<C:/Users/ADMIN/OneDrive/Desktop/study/ky 5/SWT/RBL/src code/RT-SWT302-004-SHA256/workspace-template/behavioral-output-P0-gemini/per-task/CE023.log>) | [p1/logs/CE023.log](<C:/Users/ADMIN/OneDrive/Desktop/study/ky 5/SWT/RBL/src code/RT-SWT302-004-SHA256/workspace-template/behavioral-p1-resultOAPR-gemini/per-task/CE023.log>) |
| Surefire TXT | Không áp dụng do lỗi biên dịch | [p1/surefire/CE023.txt](<C:/Users/ADMIN/OneDrive/Desktop/study/ky 5/SWT/RBL/src code/RT-SWT302-004-SHA256/workspace-template/behavioral-p1-resultOAPR-gemini/per-task/CE023.txt>) |
| Surefire XML | Không áp dụng do lỗi biên dịch | [p1/xml/20261029-082426-f2f8e4bb/CE023.xml](<C:/Users/ADMIN/OneDrive/Desktop/study/ky 5/SWT/RBL/src code/RT-SWT302-004-SHA256/workspace-template/target/gemini-p1/20261029-082426-f2f8e4bb/CE023/surefire-reports/TEST-org.example.GeneratedTest_CE023.xml>) |

Source project được đối chiếu tại [source/Classroom.java](<C:/Users/ADMIN/OneDrive/Desktop/study/ky 5/SWT/RBL/src code/RT-SWT302-004-SHA256/workspace-template/src/main/java/org/example/Classroom.java:18>).

SHA-256 test P0 `8cd64e77345317ec6669921b6e6cc2625403282c47c234a25990f862d1e26407`.

SHA-256 test P1 `c7fb400d351fdacfdf035400ed89911aae4ad5da685ad0d0e72c33e3a3e932e1`.

Khác biệt đầu vào gồm thay tham chiếu ClassroomManagementTest.Course bằng nested Course, thêm định nghĩa Course cùng constructor, equals/hashCode và đổi các chữ ký addCourse/removeCourse/checkCourseConflict. Source P1 trùng source project hiện có.

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

P0 kỳ vọng rails=1 trả nguyên chuỗi. Source tăng row lên 1 rồi truy cập ngoài mảng một hàng. P1 đổi rails=1 sang expected exception. Assertion Vigenere dòng 34 vẫn sai. Đối chiếu plaintext Helln, Xpsme! với expected Hello, World! bằng trace từng ký tự.

Prompt P0 khớp source project. Prompt P1 khớp source project.

| Bằng chứng | P0 | P1 |
|---|---|---|
| Prompt tổng và dòng bắt đầu task | [p0/prompts.txt](<C:/Users/ADMIN/OneDrive/Desktop/study/ky 5/SWT/RBL/src code/RT-SWT302-004-SHA256/workspace-template/behavioral-output-P0-gemini/ClassEvalT_P0_Behavioral_All_94_Prompts.txt:4124>) | [p1/prompts.txt](<C:/Users/ADMIN/OneDrive/Desktop/study/ky 5/SWT/RBL/src code/RT-SWT302-004-SHA256/workspace-template/behavioral-p1-resultOAPR-gemini/ClassEvalT_Gemini_3_8_Flash_P1_OAPR.txt:598>) |
| Test Java | [p0/tests/GeneratedTest_CE033.java](<C:/Users/ADMIN/OneDrive/Desktop/study/ky 5/SWT/RBL/src code/RT-SWT302-004-SHA256/workspace-template/src/tests-gemini-p0/java/org/GeneratedTest_CE033.java>) | [p1/tests/GeneratedTest_CE033.java](<C:/Users/ADMIN/OneDrive/Desktop/study/ky 5/SWT/RBL/src code/RT-SWT302-004-SHA256/workspace-template/src/tests-gemini-p1/java/org/GeneratedTest_CE033.java>) |
| Log Maven | [p0/logs/CE033.log](<C:/Users/ADMIN/OneDrive/Desktop/study/ky 5/SWT/RBL/src code/RT-SWT302-004-SHA256/workspace-template/behavioral-output-P0-gemini/per-task/CE033.log>) | [p1/logs/CE033.log](<C:/Users/ADMIN/OneDrive/Desktop/study/ky 5/SWT/RBL/src code/RT-SWT302-004-SHA256/workspace-template/behavioral-p1-resultOAPR-gemini/per-task/CE033.log>) |
| Surefire TXT | [p0/surefire/CE033.txt](<C:/Users/ADMIN/OneDrive/Desktop/study/ky 5/SWT/RBL/src code/RT-SWT302-004-SHA256/workspace-template/behavioral-output-P0-gemini/all-result/org.example.GeneratedTest_CE033.txt>) | [p1/surefire/CE033.txt](<C:/Users/ADMIN/OneDrive/Desktop/study/ky 5/SWT/RBL/src code/RT-SWT302-004-SHA256/workspace-template/behavioral-p1-resultOAPR-gemini/per-task/CE033.txt>) |
| Surefire XML | [p0/xml/20261028-204355-ae2bdc2c/CE033.xml](<C:/Users/ADMIN/OneDrive/Desktop/study/ky 5/SWT/RBL/src code/RT-SWT302-004-SHA256/workspace-template/target/gemini-p0/20261028-204355-ae2bdc2c/CE033/surefire-reports/TEST-org.example.GeneratedTest_CE033.xml>) | [p1/xml/20261029-082426-f2f8e4bb/CE033.xml](<C:/Users/ADMIN/OneDrive/Desktop/study/ky 5/SWT/RBL/src code/RT-SWT302-004-SHA256/workspace-template/target/gemini-p1/20261029-082426-f2f8e4bb/CE033/surefire-reports/TEST-org.example.GeneratedTest_CE033.xml>) |

Source project được đối chiếu tại [source/DecryptionUtils.java](<C:/Users/ADMIN/OneDrive/Desktop/study/ky 5/SWT/RBL/src code/RT-SWT302-004-SHA256/workspace-template/src/main/java/org/example/DecryptionUtils.java:26>).

SHA-256 test P0 `c19a30bf3cf1ac120022d348b782672c227073410b17d5b1e66676f2443fe59b`.

SHA-256 test P1 `58054b5fe1ce70b8e812fc34ccd81368f90c83c428cfa5cb89364a01396f7633`.

| Lỗi P0 theo Surefire | Vị trí test | Thông báo |
|---|---|---|
| testRailFenceDecipher | [Dòng 28](<C:/Users/ADMIN/OneDrive/Desktop/study/ky 5/SWT/RBL/src code/RT-SWT302-004-SHA256/workspace-template/src/tests-gemini-p0/java/org/GeneratedTest_CE033.java:28>) | Index 1 out of bounds for length 1 |

| Lỗi P1 theo Surefire | Vị trí test | Thông báo |
|---|---|---|
| testVigenereDecipher | [Dòng 34](<C:/Users/ADMIN/OneDrive/Desktop/study/ky 5/SWT/RBL/src code/RT-SWT302-004-SHA256/workspace-template/src/tests-gemini-p1/java/org/GeneratedTest_CE033.java:34>) | expected:<Hell[o, World]!> but was:<Hell[n, Xpsme]!> |

### Đối chiếu assertion còn lỗi ở P1

Test dùng key `abc`, input `Hfnlo, Zptoe!` và expected `Hello, World!` tại dòng 34. Source tăng keyIndex khi gặp chữ cái và giữ nguyên dấu câu/khoảng trắng. Dịch ngược từng chữ cho `Helln, Xpsme!`, khớp actual trong Surefire.

P0 lỗi ở railFenceDecipher với rails=1. Sau khi ghi fence[0][0], row thành 1 và lần kế tiếp truy cập fence[1] gây ArrayIndexOutOfBoundsException. P1 có test expected exception cho trường hợp này, còn Vigenere xuất hiện assertion sai với fixture mới.

| Vị trí | Input | Key | Độ dịch | Kết quả theo source | Expected trong test | Đối chiếu |
|---:|---|---|---:|---|---|---|
| 1 | H | a | 0 | H | H | Khớp |
| 2 | f | b | 1 | e | e | Khớp |
| 3 | n | c | 2 | l | l | Khớp |
| 4 | l | a | 0 | l | l | Khớp |
| 5 | o | b | 1 | n | o | Lệch |
| 6 | , | Không dùng key |  | , | , | Khớp |
| 7 | [space] | Không dùng key |  | [space] | [space] | Khớp |
| 8 | Z | c | 2 | X | W | Lệch |
| 9 | p | a | 0 | p | o | Lệch |
| 10 | t | b | 1 | s | r | Lệch |
| 11 | o | c | 2 | m | l | Lệch |
| 12 | e | a | 0 | e | d | Lệch |
| 13 | ! | Không dùng key |  | ! | ! | Khớp |

## CE037

P0 rail-fence bỏ sót dấu cách và sai thứ tự. Expected HLOOLELWRD, actual HLOWRDEL OL. P1 có rail-fence PASS. Vigenere với key abc, input bba cho bcc, còn test kỳ vọng bbb. Đối chiếu ba bước dịch 0, 1, 2 trong trace oracle.

Prompt P0 khớp source project. Prompt P1 khớp source project.

| Bằng chứng | P0 | P1 |
|---|---|---|
| Prompt tổng và dòng bắt đầu task | [p0/prompts.txt](<C:/Users/ADMIN/OneDrive/Desktop/study/ky 5/SWT/RBL/src code/RT-SWT302-004-SHA256/workspace-template/behavioral-output-P0-gemini/ClassEvalT_P0_Behavioral_All_94_Prompts.txt:4742>) | [p1/prompts.txt](<C:/Users/ADMIN/OneDrive/Desktop/study/ky 5/SWT/RBL/src code/RT-SWT302-004-SHA256/workspace-template/behavioral-p1-resultOAPR-gemini/ClassEvalT_Gemini_3_8_Flash_P1_OAPR.txt:706>) |
| Test Java | [p0/tests/GeneratedTest_CE037.java](<C:/Users/ADMIN/OneDrive/Desktop/study/ky 5/SWT/RBL/src code/RT-SWT302-004-SHA256/workspace-template/src/tests-gemini-p0/java/org/GeneratedTest_CE037.java>) | [p1/tests/GeneratedTest_CE037.java](<C:/Users/ADMIN/OneDrive/Desktop/study/ky 5/SWT/RBL/src code/RT-SWT302-004-SHA256/workspace-template/src/tests-gemini-p1/java/org/GeneratedTest_CE037.java>) |
| Log Maven | [p0/logs/CE037.log](<C:/Users/ADMIN/OneDrive/Desktop/study/ky 5/SWT/RBL/src code/RT-SWT302-004-SHA256/workspace-template/behavioral-output-P0-gemini/per-task/CE037.log>) | [p1/logs/CE037.log](<C:/Users/ADMIN/OneDrive/Desktop/study/ky 5/SWT/RBL/src code/RT-SWT302-004-SHA256/workspace-template/behavioral-p1-resultOAPR-gemini/per-task/CE037.log>) |
| Surefire TXT | [p0/surefire/CE037.txt](<C:/Users/ADMIN/OneDrive/Desktop/study/ky 5/SWT/RBL/src code/RT-SWT302-004-SHA256/workspace-template/behavioral-output-P0-gemini/all-result/org.example.GeneratedTest_CE037.txt>) | [p1/surefire/CE037.txt](<C:/Users/ADMIN/OneDrive/Desktop/study/ky 5/SWT/RBL/src code/RT-SWT302-004-SHA256/workspace-template/behavioral-p1-resultOAPR-gemini/per-task/CE037.txt>) |
| Surefire XML | [p0/xml/20261028-204355-ae2bdc2c/CE037.xml](<C:/Users/ADMIN/OneDrive/Desktop/study/ky 5/SWT/RBL/src code/RT-SWT302-004-SHA256/workspace-template/target/gemini-p0/20261028-204355-ae2bdc2c/CE037/surefire-reports/TEST-org.example.GeneratedTest_CE037.xml>) | [p1/xml/20261029-082426-f2f8e4bb/CE037.xml](<C:/Users/ADMIN/OneDrive/Desktop/study/ky 5/SWT/RBL/src code/RT-SWT302-004-SHA256/workspace-template/target/gemini-p1/20261029-082426-f2f8e4bb/CE037/surefire-reports/TEST-org.example.GeneratedTest_CE037.xml>) |

Source project được đối chiếu tại [source/EncryptionUtils.java](<C:/Users/ADMIN/OneDrive/Desktop/study/ky 5/SWT/RBL/src code/RT-SWT302-004-SHA256/workspace-template/src/main/java/org/example/EncryptionUtils.java:25>).

SHA-256 test P0 `44176cb009a1161c8c1d3d92feff17afe71b2105cd223f421b51f008b11e31d9`.

SHA-256 test P1 `619e8588179dadbe6d7770275fb1c5e29a6b74f018ed2019e53d99b644383b9a`.

| Lỗi P0 theo Surefire | Vị trí test | Thông báo |
|---|---|---|
| testRailFenceCipher | [Dòng 30](<C:/Users/ADMIN/OneDrive/Desktop/study/ky 5/SWT/RBL/src code/RT-SWT302-004-SHA256/workspace-template/src/tests-gemini-p0/java/org/GeneratedTest_CE037.java:30>) | expected:<HLO[OLELWRD]> but was:<HLO[WRDEL OL]> |

| Lỗi P1 theo Surefire | Vị trí test | Thông báo |
|---|---|---|
| testVigenereCipher | [Dòng 34](<C:/Users/ADMIN/OneDrive/Desktop/study/ky 5/SWT/RBL/src code/RT-SWT302-004-SHA256/workspace-template/src/tests-gemini-p1/java/org/GeneratedTest_CE037.java:34>) | expected:<b[bb]> but was:<b[cc]> |

### Đối chiếu assertion còn lỗi ở P1

Test dùng key `abc`, input `bba` và expected `bbb` tại dòng 34. Ba độ dịch là 0, 1, 2, nên source trả lần lượt `b`, `c`, `c`. Actual `bcc` khớp Surefire.

P0 lỗi rail-fence ở input HELLO WORLD, rails=2. Hàng 0 là HLOWRD, hàng 1 là EL OL, nên kết quả HLOWRDEL OL. P1 rail-fence PASS với fixtures mới, nhưng Vigenere vẫn có assertion sai.

| Vị trí | Input | Key | Độ dịch | Kết quả theo source | Expected trong test | Đối chiếu |
|---:|---|---|---:|---|---|---|
| 1 | b | a | 0 | b | b | Khớp |
| 2 | b | b | 1 | c | b | Lệch |
| 3 | a | c | 2 | c | b | Lệch |

## CE039

P0 kỳ vọng 10-6/2 bằng 7 nhưng actual là 3. compare(*,+) trả false thay vì true. P1 kiểm tra compare(*,+)=false và dùng 2 - 3 cho actual 3. Cả 5 test PASS. Fixture phép trừ đã đổi từ 10-6/2 sang 2 - 3. Source đổi dấu trừ thành ~ và bỏ token này khi parse.

Prompt P0 khớp source project. Prompt P1 khớp source project.

| Bằng chứng | P0 | P1 |
|---|---|---|
| Prompt tổng và dòng bắt đầu task | [p0/prompts.txt](<C:/Users/ADMIN/OneDrive/Desktop/study/ky 5/SWT/RBL/src code/RT-SWT302-004-SHA256/workspace-template/behavioral-output-P0-gemini/ClassEvalT_P0_Behavioral_All_94_Prompts.txt:5001>) | [p1/prompts.txt](<C:/Users/ADMIN/OneDrive/Desktop/study/ky 5/SWT/RBL/src code/RT-SWT302-004-SHA256/workspace-template/behavioral-p1-resultOAPR-gemini/ClassEvalT_Gemini_3_8_Flash_P1_OAPR.txt:798>) |
| Test Java | [p0/tests/GeneratedTest_CE039.java](<C:/Users/ADMIN/OneDrive/Desktop/study/ky 5/SWT/RBL/src code/RT-SWT302-004-SHA256/workspace-template/src/tests-gemini-p0/java/org/GeneratedTest_CE039.java>) | [p1/tests/GeneratedTest_CE039.java](<C:/Users/ADMIN/OneDrive/Desktop/study/ky 5/SWT/RBL/src code/RT-SWT302-004-SHA256/workspace-template/src/tests-gemini-p1/java/org/GeneratedTest_CE039.java>) |
| Log Maven | [p0/logs/CE039.log](<C:/Users/ADMIN/OneDrive/Desktop/study/ky 5/SWT/RBL/src code/RT-SWT302-004-SHA256/workspace-template/behavioral-output-P0-gemini/per-task/CE039.log>) | [p1/logs/CE039.log](<C:/Users/ADMIN/OneDrive/Desktop/study/ky 5/SWT/RBL/src code/RT-SWT302-004-SHA256/workspace-template/behavioral-p1-resultOAPR-gemini/per-task/CE039.log>) |
| Surefire TXT | [p0/surefire/CE039.txt](<C:/Users/ADMIN/OneDrive/Desktop/study/ky 5/SWT/RBL/src code/RT-SWT302-004-SHA256/workspace-template/behavioral-output-P0-gemini/all-result/org.example.GeneratedTest_CE039.txt>) | [p1/surefire/CE039.txt](<C:/Users/ADMIN/OneDrive/Desktop/study/ky 5/SWT/RBL/src code/RT-SWT302-004-SHA256/workspace-template/behavioral-p1-resultOAPR-gemini/per-task/CE039.txt>) |
| Surefire XML | [p0/xml/20261028-204355-ae2bdc2c/CE039.xml](<C:/Users/ADMIN/OneDrive/Desktop/study/ky 5/SWT/RBL/src code/RT-SWT302-004-SHA256/workspace-template/target/gemini-p0/20261028-204355-ae2bdc2c/CE039/surefire-reports/TEST-org.example.GeneratedTest_CE039.xml>) | [p1/xml/20261029-082426-f2f8e4bb/CE039.xml](<C:/Users/ADMIN/OneDrive/Desktop/study/ky 5/SWT/RBL/src code/RT-SWT302-004-SHA256/workspace-template/target/gemini-p1/20261029-082426-f2f8e4bb/CE039/surefire-reports/TEST-org.example.GeneratedTest_CE039.xml>) |

Source project được đối chiếu tại [source/ExpressionCalculator.java](<C:/Users/ADMIN/OneDrive/Desktop/study/ky 5/SWT/RBL/src code/RT-SWT302-004-SHA256/workspace-template/src/main/java/org/example/ExpressionCalculator.java:54>).

SHA-256 test P0 `c2917063cf74d865a3a2cf67fe7a32722d413d5b87211ba990e150252d25717f`.

SHA-256 test P1 `697536830245eac3fd97b7e0b5bbf4f4259fc45fe50f2a2eeccaae806a7f6358`.

| Lỗi P0 theo Surefire | Vị trí test | Thông báo |
|---|---|---|
| testCalculateBasicArithmetic | [Dòng 15](<C:/Users/ADMIN/OneDrive/Desktop/study/ky 5/SWT/RBL/src code/RT-SWT302-004-SHA256/workspace-template/src/tests-gemini-p0/java/org/GeneratedTest_CE039.java:15>) | expected:<7.0> but was:<3.0> |
| testIsOperatorAndCompare | [Dòng 47](<C:/Users/ADMIN/OneDrive/Desktop/study/ky 5/SWT/RBL/src code/RT-SWT302-004-SHA256/workspace-template/src/tests-gemini-p0/java/org/GeneratedTest_CE039.java:47>) | java.lang.AssertionError |

## CE062

P0 kỳ vọng giữ đủ hậu tố. Vòng lặp pos < length - 2 bỏ hai ký tự cuối của phần text thường. P1 kiểm tra abc thành a và Hello thành Hel. Cả 5 test PASS. Các fixture gây lỗi P0 được thay bằng fixture mới. PASS được ghi cho bộ P1 đã lưu.

Prompt P0 khớp source project. Prompt P1 khớp source project.

| Bằng chứng | P0 | P1 |
|---|---|---|
| Prompt tổng và dòng bắt đầu task | [p0/prompts.txt](<C:/Users/ADMIN/OneDrive/Desktop/study/ky 5/SWT/RBL/src code/RT-SWT302-004-SHA256/workspace-template/behavioral-output-P0-gemini/ClassEvalT_P0_Behavioral_All_94_Prompts.txt:7860>) | [p1/prompts.txt](<C:/Users/ADMIN/OneDrive/Desktop/study/ky 5/SWT/RBL/src code/RT-SWT302-004-SHA256/workspace-template/behavioral-p1-resultOAPR-gemini/ClassEvalT_Gemini_3_8_Flash_P1_OAPR.txt:926>) |
| Test Java | [p0/tests/GeneratedTest_CE062.java](<C:/Users/ADMIN/OneDrive/Desktop/study/ky 5/SWT/RBL/src code/RT-SWT302-004-SHA256/workspace-template/src/tests-gemini-p0/java/org/GeneratedTest_CE062.java>) | [p1/tests/GeneratedTest_CE062.java](<C:/Users/ADMIN/OneDrive/Desktop/study/ky 5/SWT/RBL/src code/RT-SWT302-004-SHA256/workspace-template/src/tests-gemini-p1/java/org/GeneratedTest_CE062.java>) |
| Log Maven | [p0/logs/CE062.log](<C:/Users/ADMIN/OneDrive/Desktop/study/ky 5/SWT/RBL/src code/RT-SWT302-004-SHA256/workspace-template/behavioral-output-P0-gemini/per-task/CE062.log>) | [p1/logs/CE062.log](<C:/Users/ADMIN/OneDrive/Desktop/study/ky 5/SWT/RBL/src code/RT-SWT302-004-SHA256/workspace-template/behavioral-p1-resultOAPR-gemini/per-task/CE062.log>) |
| Surefire TXT | [p0/surefire/CE062.txt](<C:/Users/ADMIN/OneDrive/Desktop/study/ky 5/SWT/RBL/src code/RT-SWT302-004-SHA256/workspace-template/behavioral-output-P0-gemini/all-result/org.example.GeneratedTest_CE062.txt>) | [p1/surefire/CE062.txt](<C:/Users/ADMIN/OneDrive/Desktop/study/ky 5/SWT/RBL/src code/RT-SWT302-004-SHA256/workspace-template/behavioral-p1-resultOAPR-gemini/per-task/CE062.txt>) |
| Surefire XML | [p0/xml/20261028-204355-ae2bdc2c/CE062.xml](<C:/Users/ADMIN/OneDrive/Desktop/study/ky 5/SWT/RBL/src code/RT-SWT302-004-SHA256/workspace-template/target/gemini-p0/20261028-204355-ae2bdc2c/CE062/surefire-reports/TEST-org.example.GeneratedTest_CE062.xml>) | [p1/xml/20261029-082426-f2f8e4bb/CE062.xml](<C:/Users/ADMIN/OneDrive/Desktop/study/ky 5/SWT/RBL/src code/RT-SWT302-004-SHA256/workspace-template/target/gemini-p1/20261029-082426-f2f8e4bb/CE062/surefire-reports/TEST-org.example.GeneratedTest_CE062.xml>) |

Source project được đối chiếu tại [source/NumericEntityUnescaper.java](<C:/Users/ADMIN/OneDrive/Desktop/study/ky 5/SWT/RBL/src code/RT-SWT302-004-SHA256/workspace-template/src/main/java/org/example/NumericEntityUnescaper.java:10>).

SHA-256 test P0 `99e0f4509530eec34004b2a6fe447ef86db50c07469947ea52c3017d2bebc965`.

SHA-256 test P1 `cea4b0f21c0a641d60c59a1bef8b05a79cf4cd75e50bd6c848a84c2cc8c4206e`.

| Lỗi P0 theo Surefire | Vị trí test | Thông báo |
|---|---|---|
| testReplaceHexEntities | [Dòng 22](<C:/Users/ADMIN/OneDrive/Desktop/study/ky 5/SWT/RBL/src code/RT-SWT302-004-SHA256/workspace-template/src/tests-gemini-p0/java/org/GeneratedTest_CE062.java:22>) | expected:<Test & Mo[re]> but was:<Test & Mo[]> |
| testReplaceDecimalEntities | [Dòng 14](<C:/Users/ADMIN/OneDrive/Desktop/study/ky 5/SWT/RBL/src code/RT-SWT302-004-SHA256/workspace-template/src/tests-gemini-p0/java/org/GeneratedTest_CE062.java:14>) | expected:<Hello Worl[d!]> but was:<Hello Worl[]> |
| testNoEntitiesOrIncompleteEntities | [Dòng 28](<C:/Users/ADMIN/OneDrive/Desktop/study/ky 5/SWT/RBL/src code/RT-SWT302-004-SHA256/workspace-template/src/tests-gemini-p0/java/org/GeneratedTest_CE062.java:28>) | expected:<Just plain te[xt]> but was:<Just plain te[]> |

## CE073

P0 countWords có khoảng trắng đầu trả 5 thay vì 4. Câu dài nhất có 9 từ, test kỳ vọng 10. P1 có 4 test PASS với fixture mới, gồm câu dài nhất 7 từ. P1 bỏ fixture khoảng trắng đầu và thay câu dài 9 từ. Chưa có kết quả P1 trên hai fixture P0 này.

Prompt P0 khớp source project. Prompt P1 khớp source project.

| Bằng chứng | P0 | P1 |
|---|---|---|
| Prompt tổng và dòng bắt đầu task | [p0/prompts.txt](<C:/Users/ADMIN/OneDrive/Desktop/study/ky 5/SWT/RBL/src code/RT-SWT302-004-SHA256/workspace-template/behavioral-output-P0-gemini/ClassEvalT_P0_Behavioral_All_94_Prompts.txt:9355>) | [p1/prompts.txt](<C:/Users/ADMIN/OneDrive/Desktop/study/ky 5/SWT/RBL/src code/RT-SWT302-004-SHA256/workspace-template/behavioral-p1-resultOAPR-gemini/ClassEvalT_Gemini_3_8_Flash_P1_OAPR.txt:997>) |
| Test Java | [p0/tests/GeneratedTest_CE073.java](<C:/Users/ADMIN/OneDrive/Desktop/study/ky 5/SWT/RBL/src code/RT-SWT302-004-SHA256/workspace-template/src/tests-gemini-p0/java/org/GeneratedTest_CE073.java>) | [p1/tests/GeneratedTest_CE073.java](<C:/Users/ADMIN/OneDrive/Desktop/study/ky 5/SWT/RBL/src code/RT-SWT302-004-SHA256/workspace-template/src/tests-gemini-p1/java/org/GeneratedTest_CE073.java>) |
| Log Maven | [p0/logs/CE073.log](<C:/Users/ADMIN/OneDrive/Desktop/study/ky 5/SWT/RBL/src code/RT-SWT302-004-SHA256/workspace-template/behavioral-output-P0-gemini/per-task/CE073.log>) | [p1/logs/CE073.log](<C:/Users/ADMIN/OneDrive/Desktop/study/ky 5/SWT/RBL/src code/RT-SWT302-004-SHA256/workspace-template/behavioral-p1-resultOAPR-gemini/per-task/CE073.log>) |
| Surefire TXT | [p0/surefire/CE073.txt](<C:/Users/ADMIN/OneDrive/Desktop/study/ky 5/SWT/RBL/src code/RT-SWT302-004-SHA256/workspace-template/behavioral-output-P0-gemini/all-result/org.example.GeneratedTest_CE073.txt>) | [p1/surefire/CE073.txt](<C:/Users/ADMIN/OneDrive/Desktop/study/ky 5/SWT/RBL/src code/RT-SWT302-004-SHA256/workspace-template/behavioral-p1-resultOAPR-gemini/per-task/CE073.txt>) |
| Surefire XML | [p0/xml/20261028-204355-ae2bdc2c/CE073.xml](<C:/Users/ADMIN/OneDrive/Desktop/study/ky 5/SWT/RBL/src code/RT-SWT302-004-SHA256/workspace-template/target/gemini-p0/20261028-204355-ae2bdc2c/CE073/surefire-reports/TEST-org.example.GeneratedTest_CE073.xml>) | [p1/xml/20261029-082426-f2f8e4bb/CE073.xml](<C:/Users/ADMIN/OneDrive/Desktop/study/ky 5/SWT/RBL/src code/RT-SWT302-004-SHA256/workspace-template/target/gemini-p1/20261029-082426-f2f8e4bb/CE073/surefire-reports/TEST-org.example.GeneratedTest_CE073.xml>) |

Source project được đối chiếu tại [source/SplitSentence.java](<C:/Users/ADMIN/OneDrive/Desktop/study/ky 5/SWT/RBL/src code/RT-SWT302-004-SHA256/workspace-template/src/main/java/org/example/SplitSentence.java:25>).

SHA-256 test P0 `8e9bf533435854edecd9133074d6b9de2cc784c19a00d724cd8b5cd056c465c3`.

SHA-256 test P1 `7efe97adef5bd16f541d1a765cb5248fa073cfb59a429350d771b3214c69df34`.

| Lỗi P0 theo Surefire | Vị trí test | Thông báo |
|---|---|---|
| testCountWords | [Dòng 27](<C:/Users/ADMIN/OneDrive/Desktop/study/ky 5/SWT/RBL/src code/RT-SWT302-004-SHA256/workspace-template/src/tests-gemini-p0/java/org/GeneratedTest_CE073.java:27>) | expected:<4> but was:<5> |
| testProcessTextFileReturnsMaxWordCount | [Dòng 35](<C:/Users/ADMIN/OneDrive/Desktop/study/ky 5/SWT/RBL/src code/RT-SWT302-004-SHA256/workspace-template/src/tests-gemini-p0/java/org/GeneratedTest_CE073.java:35>) | expected:<10> but was:<9> |

## CE079

P0 gọi assertTrue ở dòng 54 nhưng chỉ static-import assertEquals và assertNotNull. P1 import org.junit.Assert.* và chạy 3 test PASS. P0 dừng ở testCompile nên Surefire TXT/XML không được tạo.

Prompt P0 khớp source project. Prompt P1 khớp source project.

| Bằng chứng | P0 | P1 |
|---|---|---|
| Prompt tổng và dòng bắt đầu task | [p0/prompts.txt](<C:/Users/ADMIN/OneDrive/Desktop/study/ky 5/SWT/RBL/src code/RT-SWT302-004-SHA256/workspace-template/behavioral-output-P0-gemini/ClassEvalT_P0_Behavioral_All_94_Prompts.txt:10203>) | [p1/prompts.txt](<C:/Users/ADMIN/OneDrive/Desktop/study/ky 5/SWT/RBL/src code/RT-SWT302-004-SHA256/workspace-template/behavioral-p1-resultOAPR-gemini/ClassEvalT_Gemini_3_8_Flash_P1_OAPR.txt:1060>) |
| Test Java | [p0/tests/GeneratedTest_CE079.java](<C:/Users/ADMIN/OneDrive/Desktop/study/ky 5/SWT/RBL/src code/RT-SWT302-004-SHA256/workspace-template/src/tests-gemini-p0/java/org/GeneratedTest_CE079.java>) | [p1/tests/GeneratedTest_CE079.java](<C:/Users/ADMIN/OneDrive/Desktop/study/ky 5/SWT/RBL/src code/RT-SWT302-004-SHA256/workspace-template/src/tests-gemini-p1/java/org/GeneratedTest_CE079.java>) |
| Log Maven | [p0/logs/CE079.log](<C:/Users/ADMIN/OneDrive/Desktop/study/ky 5/SWT/RBL/src code/RT-SWT302-004-SHA256/workspace-template/behavioral-output-P0-gemini/per-task/CE079.log>) | [p1/logs/CE079.log](<C:/Users/ADMIN/OneDrive/Desktop/study/ky 5/SWT/RBL/src code/RT-SWT302-004-SHA256/workspace-template/behavioral-p1-resultOAPR-gemini/per-task/CE079.log>) |
| Surefire TXT | Không áp dụng do lỗi biên dịch | [p1/surefire/CE079.txt](<C:/Users/ADMIN/OneDrive/Desktop/study/ky 5/SWT/RBL/src code/RT-SWT302-004-SHA256/workspace-template/behavioral-p1-resultOAPR-gemini/per-task/CE079.txt>) |
| Surefire XML | Không áp dụng do lỗi biên dịch | [p1/xml/20261029-082426-f2f8e4bb/CE079.xml](<C:/Users/ADMIN/OneDrive/Desktop/study/ky 5/SWT/RBL/src code/RT-SWT302-004-SHA256/workspace-template/target/gemini-p1/20261029-082426-f2f8e4bb/CE079/surefire-reports/TEST-org.example.GeneratedTest_CE079.xml>) |

Source project được đối chiếu tại [source/TextFileProcessor.java](<C:/Users/ADMIN/OneDrive/Desktop/study/ky 5/SWT/RBL/src code/RT-SWT302-004-SHA256/workspace-template/src/main/java/org/example/TextFileProcessor.java:15>).

SHA-256 test P0 `74ef39d7ed47e1c6fd42590c7941615661dc50bdce8e89fb246c5201b299ac89`.

SHA-256 test P1 `11f04d97a64bffbb8eacf5e2678ef621df466492370d90dcbc18349ab56a9b75`.

## CE094

P0 ZIP có subdir/file2.txt nhưng thiếu directory entry. extractAll không tạo parent cho file con. P1 extractAll dùng hai file ở root. Nested-file được kiểm tra bằng extractFile. Cả 4 test PASS. P1 thay fixture và đường gọi. Source extractFile tạo parent, còn extractAll chỉ log IOException và tiếp tục.

Prompt P0 khớp source project. Prompt P1 khớp source project.

| Bằng chứng | P0 | P1 |
|---|---|---|
| Prompt tổng và dòng bắt đầu task | [p0/prompts.txt](<C:/Users/ADMIN/OneDrive/Desktop/study/ky 5/SWT/RBL/src code/RT-SWT302-004-SHA256/workspace-template/behavioral-output-P0-gemini/ClassEvalT_P0_Behavioral_All_94_Prompts.txt:12131>) | [p1/prompts.txt](<C:/Users/ADMIN/OneDrive/Desktop/study/ky 5/SWT/RBL/src code/RT-SWT302-004-SHA256/workspace-template/behavioral-p1-resultOAPR-gemini/ClassEvalT_Gemini_3_8_Flash_P1_OAPR.txt:1116>) |
| Test Java | [p0/tests/GeneratedTest_CE094.java](<C:/Users/ADMIN/OneDrive/Desktop/study/ky 5/SWT/RBL/src code/RT-SWT302-004-SHA256/workspace-template/src/tests-gemini-p0/java/org/GeneratedTest_CE094.java>) | [p1/tests/GeneratedTest_CE094.java](<C:/Users/ADMIN/OneDrive/Desktop/study/ky 5/SWT/RBL/src code/RT-SWT302-004-SHA256/workspace-template/src/tests-gemini-p1/java/org/GeneratedTest_CE094.java>) |
| Log Maven | [p0/logs/CE094.log](<C:/Users/ADMIN/OneDrive/Desktop/study/ky 5/SWT/RBL/src code/RT-SWT302-004-SHA256/workspace-template/behavioral-output-P0-gemini/per-task/CE094.log>) | [p1/logs/CE094.log](<C:/Users/ADMIN/OneDrive/Desktop/study/ky 5/SWT/RBL/src code/RT-SWT302-004-SHA256/workspace-template/behavioral-p1-resultOAPR-gemini/per-task/CE094.log>) |
| Surefire TXT | [p0/surefire/CE094.txt](<C:/Users/ADMIN/OneDrive/Desktop/study/ky 5/SWT/RBL/src code/RT-SWT302-004-SHA256/workspace-template/behavioral-output-P0-gemini/all-result/org.example.GeneratedTest_CE094.txt>) | [p1/surefire/CE094.txt](<C:/Users/ADMIN/OneDrive/Desktop/study/ky 5/SWT/RBL/src code/RT-SWT302-004-SHA256/workspace-template/behavioral-p1-resultOAPR-gemini/per-task/CE094.txt>) |
| Surefire XML | [p0/xml/20261028-204355-ae2bdc2c/CE094.xml](<C:/Users/ADMIN/OneDrive/Desktop/study/ky 5/SWT/RBL/src code/RT-SWT302-004-SHA256/workspace-template/target/gemini-p0/20261028-204355-ae2bdc2c/CE094/surefire-reports/TEST-org.example.GeneratedTest_CE094.xml>) | [p1/xml/20261029-082426-f2f8e4bb/CE094.xml](<C:/Users/ADMIN/OneDrive/Desktop/study/ky 5/SWT/RBL/src code/RT-SWT302-004-SHA256/workspace-template/target/gemini-p1/20261029-082426-f2f8e4bb/CE094/surefire-reports/TEST-org.example.GeneratedTest_CE094.xml>) |

Source project được đối chiếu tại [source/ZipFileProcessor.java](<C:/Users/ADMIN/OneDrive/Desktop/study/ky 5/SWT/RBL/src code/RT-SWT302-004-SHA256/workspace-template/src/main/java/org/example/ZipFileProcessor.java:45>).

SHA-256 test P0 `5433d9dfc2406e132e3a91df8c158dfef3ae68d89bd71af2eaf987650de433e6`.

SHA-256 test P1 `d8a8b0cc4817cd201a3439358d9df4b13f101491f61aefae3a98d1a2196c4963`.

| Lỗi P0 theo Surefire | Vị trí test | Thông báo |
|---|---|---|
| testExtractAll | [Dòng 66](<C:/Users/ADMIN/OneDrive/Desktop/study/ky 5/SWT/RBL/src code/RT-SWT302-004-SHA256/workspace-template/src/tests-gemini-p0/java/org/GeneratedTest_CE094.java:66>) | java.lang.AssertionError |

## Bằng chứng cần bổ sung

- Bản xuất hoặc capture phản hồi P1 nguyên gốc để đối chiếu với 8 file test Java đã lưu.
- Bằng chứng phiên Gemini và ngày gửi prompt để xác minh model/version và prompt thực sự đã dùng.
- Nếu nhóm có lần chạy P0 riêng cho CE023 sau sửa đầu vào, bổ sung prompt/test/log tương ứng. Hồ sơ hiện đối chiếu ghi PASS ở P1.

Các thay đổi fixture cần ghi trong paper gồm CE039, CE062, CE073 và CE094. Kết quả P1 trong bảng áp dụng cho bộ test P1 đã lưu.
