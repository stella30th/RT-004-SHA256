"""Build An's evidence-backed reviewer response draft."""

from pathlib import Path

from docx import Document
from docx.enum.table import WD_CELL_VERTICAL_ALIGNMENT
from docx.enum.text import WD_ALIGN_PARAGRAPH
from docx.oxml import OxmlElement
from docx.oxml.ns import qn
from docx.shared import Inches, Pt, RGBColor


OUT = Path(__file__).resolve().parents[1] / "tracker_response_round2.docx"


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
doc.add_paragraph("Bản cập nhật ngày 10/11/2026 sau khi đối chiếu develop mới nhất của Hào, Khoa và Khôi. Giữ nguyên ID của reviewer tracker. Trạng thái Xong chỉ được dùng sau khi nhóm kiểm tra artifact và bản paper cuối.")

doc.add_paragraph("Kết quả đã đối soát", style="Heading 1")
doc.add_paragraph(
    "Script analysis.py dựng đủ 188 cặp, kiểm tra log và tính Clopper–Pearson exact 95% CI: "
    "15/18 = 83,33% [58,58%; 96,42%]; ChatGPT 10/11 [58,72%; 99,77%]; "
    "Gemini 5/7 [29,04%; 96,33%]. Khi loại CE023 khỏi cohort hợp lệ: P0 169/187, carry-forward 184/187. "
    "Kịch bản cũ 170/188 và 185/188 cần một lần P0 CE023 chạy lại có bằng chứng. "
    "Vòng 2 ChatGPT có 99 bản ghi: P0-resample PASS 23/33, checklist-only và checklist+focus cùng PASS 33/33; "
    "hai checklist cùng ghi 924/1.164 mutant bị diệt. Gemini vòng 2 có 63 lượt: A 17/21, B 18/21, C 15/21 PASS; chưa có mutation Gemini."
)

doc.add_paragraph("Nguồn bằng chứng trong dự án", style="Heading 2")
for item in (
    "ho-le-thien-an/analysis.py và round2_analysis.py; oapr-analysis/summary.json và round2_summary.json",
    "huynh-gia-hao/experiment/round2/outputs/ và SWT_Hao_Round2_Final_Handover/; nguyen-nam-khoa/Round 2/Evidence/",
    "paper/main.tex và paper/sections/ (bản tổng hợp; cần biên dịch và rà trước khi nộp)",
):
    doc.add_paragraph(item, style="List Bullet")

doc.add_paragraph("Phản hồi theo ID", style="Heading 1")
doc.add_paragraph("Cột bằng chứng ghi phần đã làm và việc còn thiếu. Đây là phản hồi dự thảo để nhập lại vào tracker gốc, không phải xác nhận hoàn thành.")

rows = [
    ("B1", "Đang làm", "Đã sửa câu khẳng định vượt dữ liệu trong paper/sections/. Cần nhóm rà toàn bộ Phụ lục B; chưa có kết quả kiểm tra lại theo yêu cầu môn học."),
    ("H5", "Đang làm", "analysis.py tính exact 95% CI cho 15/18, 10/11, 5/7; paper/sections/05c_results_combined.tex đã nêu khoảng. Cần kiểm tra PDF cuối."),
    ("H9", "Đang làm", "paper/main.tex đã đổi title sang Focused Recovery; abstract chỉ nói executable compatibility. Có PIT vòng 2 nhưng không chứng minh focus tăng mutant kill."),
    ("H12", "Đang làm", "Paper nêu RQ1–RQ3 và phân biệt historical với vòng 2. PROTOCOL_FROZEN_DRAFT.md còn nhãn DRAFT; cần xác nhận thời điểm khóa trước khi diễn giải đối chứng."),
    ("H14", "Đang làm", "Có CI exact, bảng 15 PASS/3 FAIL và kịch bản 187 so với 188. CE023 P0 vẫn FAIL; cần rerun P0 gốc nếu muốn dùng baseline 188."),
    ("H15", "Đang làm", "Threats nêu focus do người viết, replacement outputs và thiếu provenance độc lập. Cần xác nhận người phân tích 18 lỗi, quy tắc viết focus và việc làm mù."),
    ("H16", "Đang làm", "Conclusion dùng focused recovery; tách mutation khỏi JUnit PASS và nêu 187 valid pairs. Cần biên dịch, rà toàn văn và nhóm duyệt."),
    ("H17", "Chờ xác minh", "Bản thảo có mục AI use, Artifacts, Funding/COI ở dạng chờ. Cần từng tác giả xác nhận công cụ, công đoạn, URL dữ liệu, tài trợ và xung đột lợi ích."),
    ("H18", "Đang làm", "ChatGPT có 33 cặp task-run trên 11 task, Gemini có 21 cặp trên 7 task. Hai cohort khác thiết kế; có 2 P0 replacement ChatGPT và provenance chưa đủ. Không dùng p-value như bằng chứng nhân quả."),
    ("M2", "Đang làm", "Discussion tách cohort lịch sử, ChatGPT vòng 2 và Gemini vòng 2; không kết luận model nào tốt hơn. Cần rà bảng/hình cuối để tránh diễn giải superiority."),
    ("M6", "Đang làm", "Abstract mới có 160 từ theo phép đếm tách từ, trong khoảng 150–200. Cần kiểm tra bằng bộ đếm của nơi nộp và bản PDF cuối."),
    ("M13", "Đang làm", "Results có 187 valid pairs, 15/18 recovery, CI, bảng 99 run ChatGPT và 63 run Gemini riêng. Không báo p-value vì task-run phụ thuộc và provenance chưa đủ."),
    ("M14", "Đang làm", "Discussion đối chiếu ChatTester/TestART/YATE; phân biệt JUnit PASS với mutation. Cần Khôi xác minh các trích dẫn và lời khuyên sau khi render."),
    ("M15", "Đang làm", "Threats ghi CE023, 2 replacement, task lặp, khác biệt baseline Gemini và provenance. Repo có raw JUnit/PIT Hào và raw Gemini; cần rà phát biểu với artifact."),
    ("M16", "Đang làm", "Conclusion: RQ1 15 assertion/2 runtime/1 compile; RQ2 15/18; RQ3 169/187→184/187. Hai cohort vòng 2 tách riêng. Cần rà PDF cuối."),
    ("M17", "Chờ xác minh", "AI-use disclosure hiện chưa có phiên bản/công cụ và vai trò soạn bài đã xác nhận. Cần tác giả cung cấp thông tin thực tế."),
    ("M18", "Chờ Khôi/Hào", "Repo đã có raw evidence giải nén của Hào và Gemini Official_Run.zip; 5 ZIP đóng gói của Hào chưa ở repo. Cần URL/DOI hoặc lý do hạn chế; owner gốc Khôi + Hào."),
    ("M19", "Chờ cả nhóm", "Chưa thể khai 'không có tài trợ/xung đột' khi chưa nhận xác nhận của từng tác giả. Tracker gốc ghi owner cả nhóm."),
    ("M20", "Đang làm", "Đã bỏ các claim nhân quả và superiority trong paper/sections/; cần đối chiếu từng câu Phụ lục A và rà toàn văn cuối."),
    ("M11 (phối hợp)", "Đang làm", "Methodology quy định PASS = biên dịch + ít nhất 1 test + 0 fail/error; script kiểm tra 99 ChatGPT và 63 Gemini run cùng sự hiện diện raw evidence. Cần rà phiên sinh."),
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
    "Hào và Khoa xác nhận provenance phiên sinh, cấu hình model và trạng thái khóa protocol; Khôi rà trích dẫn và venue; "
    "cả nhóm xác nhận disclosures và nơi lưu artifact. CE023 cần một P0 baseline mới với source đúng nếu muốn dùng mẫu số 188. "
    "Sau khi biên dịch và rà bản paper, An cập nhật tracker gốc theo vị trí và artifact rồi gửi giảng viên kiểm tra."
)

footer = section.footer.paragraphs[0]
footer.alignment = WD_ALIGN_PARAGRAPH.CENTER
footer.add_run("OAPR • phản hồi dự thảo của An")

OUT.parent.mkdir(parents=True, exist_ok=True)
doc.save(OUT)
print(OUT)
