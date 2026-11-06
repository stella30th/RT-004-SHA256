"""Build An's evidence-backed reviewer response draft."""

from pathlib import Path

from docx import Document
from docx.enum.table import WD_CELL_VERTICAL_ALIGNMENT
from docx.enum.text import WD_ALIGN_PARAGRAPH
from docx.oxml import OxmlElement
from docx.oxml.ns import qn
from docx.shared import Inches, Pt, RGBColor


OUT = Path(__file__).resolve().parents[1] / "tracker_response.docx"


def shade(cell, color):
    tc_pr = cell._tc.get_or_add_tcPr()
    shd = OxmlElement("w:shd")
    shd.set(qn("w:fill"), color)
    tc_pr.append(shd)


def borders(cell):
    tc_pr = cell._tc.get_or_add_tcPr()
    elem = OxmlElement("w:tcBorders")
    for edge in ("top", "left", "bottom", "right"):
        tag = OxmlElement(f"w:{edge}")
        tag.set(qn("w:val"), "single")
        tag.set(qn("w:sz"), "4")
        tag.set(qn("w:color"), "D9D9D9")
        elem.append(tag)
    tc_pr.append(elem)


def text_cell(cell, value, bold=False):
    cell.text = ""
    paragraph = cell.paragraphs[0]
    paragraph.paragraph_format.space_after = Pt(0)
    run = paragraph.add_run(value)
    run.bold = bold
    run.font.size = Pt(9)
    cell.vertical_alignment = WD_CELL_VERTICAL_ALIGNMENT.CENTER
    borders(cell)


doc = Document()
section = doc.sections[0]
section.page_width = Inches(8.5)
section.page_height = Inches(11)
section.top_margin = Inches(0.72)
section.bottom_margin = Inches(0.7)
section.left_margin = Inches(0.72)
section.right_margin = Inches(0.72)

styles = doc.styles
styles["Normal"].font.name = "Aptos"
styles["Normal"].font.size = Pt(10)
styles["Normal"].font.color.rgb = RGBColor(0, 0, 0)
styles["Normal"].paragraph_format.space_after = Pt(6)
for name in ("Title", "Heading 1", "Heading 2"):
    styles[name].font.name = "Aptos"
    styles[name].font.color.rgb = RGBColor(0, 0, 0)
styles["Title"].font.size = Pt(17)
styles["Heading 1"].font.size = Pt(12)
styles["Heading 2"].font.size = Pt(10.5)
for owner in (styles["Title"]._element,):
    ppr = owner.get_or_add_pPr()
    border = ppr.find(qn("w:pBdr"))
    if border is not None:
        ppr.remove(border)

doc.add_paragraph("Phản hồi tracker OAPR của Hồ Lê Thiên An", style="Title")
doc.add_paragraph("Bản nháp bằng chứng ngày 06/11/2026. Giữ nguyên ID của reviewer tracker; mọi dòng dưới đây vẫn ở trạng thái Đang làm cho đến khi nhóm kiểm tra artifact và xác nhận hoàn tất.")

doc.add_paragraph("Kết quả đã đối soát", style="Heading 1")
doc.add_paragraph(
    "Script analysis.py dựng đủ 188 cặp, kiểm tra log và tính Clopper–Pearson exact 95% CI: "
    "15/18 = 83,33% [58,58%; 96,42%]; ChatGPT 10/11 [58,72%; 99,77%]; "
    "Gemini 5/7 [29,04%; 96,33%]. P0 raw là 167/188; P0 sau phân loại hồi tố của nhóm là 170/188; "
    "carry-forward tương ứng là 182/188 và 185/188. CE023 P0 FAIL ở bước biên dịch; P1 PASS 4/4 test, "
    "nhưng P1 dùng test và TARGET_SOURCE khác P0. Chưa có rerun thành công của bộ test P0 gốc."
)

doc.add_paragraph("Nguồn bằng chứng trong dự án", style="Heading 2")
for item in (
    "ho-le-thien-an/analysis.py; ho-le-thien-an/oapr-analysis/{pair_transitions.csv,summary.json,analysis_notes.md}",
    "ho-le-thien-an/oapr-analysis/ce023-repro-p0/rerun.log và ce023-repro-p1/rerun.log",
    "ho-le-thien-an/paper_v3.tex (bản thảo; cần nhóm rà và biên dịch trước khi nộp)",
):
    doc.add_paragraph(item, style="List Bullet")

doc.add_paragraph("Phản hồi theo ID", style="Heading 1")
doc.add_paragraph("Cột bằng chứng ghi phần đã làm và việc còn thiếu. Đây là phản hồi dự thảo để nhập lại vào tracker gốc, không phải xác nhận hoàn thành.")

rows = [
    ("B1", "Đang làm", "Đã sửa một số câu khẳng định vượt dữ liệu trong paper_v3.tex. Cần nhóm rà toàn bộ Phụ lục B; chưa có kết quả kiểm tra lại theo yêu cầu môn học."),
    ("H5", "Đang làm", "analysis.py tính exact 95% CI cho 15/18, 10/11, 5/7; paper_v3.tex đã nêu khoảng. Cần đối chiếu bản PDF cuối."),
    ("H9", "Đang làm", "Đã đổi tiêu đề sang Focused Recovery và giới hạn abstract vào compile-and-pass. Chưa có mutation score; cần rà toàn văn để loại claim về oracle accuracy."),
    ("H12", "Đang làm", "Bài ghi nghiên cứu thăm dò, đơn vị model-task pair và đại lượng cho RQ1–RQ3. Cần nhóm chốt protocol đối chứng trước khi khẳng định hiệu quả nhân quả."),
    ("H14", "Đang làm", "Có CI exact, bảng chuyển trạng thái 15 PASS/3 FAIL và so sánh mô tả. CE023 được gắn nhãn provisional; cần xác minh baseline P0."),
    ("H15", "Đang làm", "Internal validity đã nêu focus statements do người viết và thiếu nhánh đối chứng. Cần Hào cung cấp người phân tích 18 lỗi, quy tắc viết focus và trạng thái làm mù."),
    ("H16", "Đang làm", "Conclusion đã dùng focused recovery và giới hạn single-run; cần biên dịch bản cuối và kiểm tra không còn claim quá mức."),
    ("H17", "Chờ xác minh", "Bản thảo có mục AI use, Artifacts, Funding/COI ở dạng chờ. Cần từng tác giả xác nhận công cụ, công đoạn, URL dữ liệu, tài trợ và xung đột lợi ích."),
    ("H18", "Đang làm", "Statistical Analysis có Clopper–Pearson CI và lý do không dùng McNemar để suy ra nhân quả trên cohort chọn theo P0 FAIL. Cần dữ liệu paired có đối chứng nếu muốn báo test/effect size."),
    ("M2", "Đang làm", "Discussion mô tả hai cohort riêng, không kết luận model nào tốt hơn. Cần rà lại bảng/hình cuối để tránh diễn giải superiority."),
    ("M6", "Đang làm", "Abstract bản paper_v3.tex có 197 từ theo phép đếm tách từ, nằm trong khoảng 150–200. Cần kiểm tra bằng mẫu đếm của nơi nộp."),
    ("M13", "Đang làm", "Results có 11/7 failures, 15/18 recovery và CI. Chưa có run registry đối chứng để báo p-value/effect size hợp lệ; không bịa số."),
    ("M14", "Đang làm", "Discussion đã đối chiếu ChatTester/TestART/YATE và nêu hướng dẫn chỉ dùng cho triage có kiểm chứng. Cần Khôi xác minh trích dẫn và bản PDF cuối."),
    ("M15", "Đang làm", "Threats đã chia Internal, External, Construct, Conclusion. Cần rà từng mitigation với artifact, nhất là CE023 và việc lưu log."),
    ("M16", "Đang làm", "Conclusion trả lời RQ1–RQ3; RQ1 có 15 assertion, 2 runtime, 1 compilation trong 18 cặp; RQ2 15/18; RQ3 170/188→185/188 tạm thời. Cần rà bản PDF cuối."),
    ("M17", "Chờ xác minh", "AI-use disclosure hiện chưa có phiên bản/công cụ và vai trò soạn bài đã xác nhận. Cần tác giả cung cấp thông tin thực tế."),
    ("M18", "Chờ Khôi/Hào", "Data availability mới nêu artifact local; cần URL hoặc lý do hạn chế. Tracker gốc ghi owner Khôi + Hào; An theo dõi phần declaration trong bản phân công vòng 2."),
    ("M19", "Chờ cả nhóm", "Chưa thể khai 'không có tài trợ/xung đột' khi chưa nhận xác nhận của từng tác giả. Tracker gốc ghi owner cả nhóm."),
    ("M20", "Đang làm", "Đã đổi các kết luận nhân quả thành mô tả trong paper_v3.tex. Cần đối chiếu từng câu ở Phụ lục A và rà toàn văn cuối."),
    ("M11 (phối hợp)", "Đang làm", "Bài quy định PASS = biên dịch và 0 test fail/error; script kiểm tra log. Cần Hào chốt bảng metric và protocol làm bằng chứng."),
]

table = doc.add_table(rows=1, cols=3)
table.autofit = False
widths = (Inches(0.62), Inches(1.12), Inches(5.28))
for column, width in zip(table.columns, widths):
    column.width = width
for j, (cell, label) in enumerate(zip(table.rows[0].cells, ("ID", "Trạng thái", "Bằng chứng và phần còn thiếu"))):
    cell.width = widths[j]
    text_cell(cell, label, bold=True)
    shade(cell, "DCE6F1")
table.rows[0]._tr.get_or_add_trPr().append(OxmlElement("w:tblHeader"))

for i, row in enumerate(rows):
    cells = table.add_row().cells
    table.rows[-1]._tr.get_or_add_trPr().append(OxmlElement("w:cantSplit"))
    for j, (cell, value) in enumerate(zip(cells, row)):
        cell.width = widths[j]
        text_cell(cell, value)
        if i % 2:
            shade(cell, "F7F9FB")

doc.add_paragraph("Điều kiện trước khi đánh dấu Xong", style="Heading 1")
doc.add_paragraph(
    "Hào/Khoa xác minh prompt, run ID và các bảng số liệu; Khôi rà trích dẫn và nguồn dataset; "
    "cả nhóm xác nhận disclosures. CE023 cần bằng chứng chạy lại P0 gốc sau sửa đầu vào hoặc phải được giữ rõ trong phân tích độ nhạy. "
    "Sau đó An cập nhật tracker gốc bằng vị trí trong paper và đường dẫn artifact, rồi gửi giảng viên kiểm tra."
)

footer = section.footer.paragraphs[0]
footer.alignment = WD_ALIGN_PARAGRAPH.CENTER
footer.add_run("OAPR • phản hồi dự thảo của An")

OUT.parent.mkdir(parents=True, exist_ok=True)
doc.save(OUT)
print(OUT)
