package view;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import fi.haagahelia.makeupstore.domain.Makeup;

public class ExcelView extends AbstractXlsView{

    @Override
    protected void buildExcelDocument(Map<String, Object> model,
                                      Workbook workbook,
                                      HttpServletRequest request,
                                      HttpServletResponse response) throws Exception {

        // change the file name
        response.setHeader("Content-Disposition", "attachment; filename=\"my-xls-file.xls\"");

        @SuppressWarnings("unchecked")
        List<Makeup> makeups = (List<Makeup>) model.get("makeups");

        // create excel xls sheet
        Sheet sheet = workbook.createSheet("Makeupstore");
        sheet.setDefaultColumnWidth(30);

        // create style for header cells
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setFontName("Arial");
        style.setFillForegroundColor(HSSFColor.BLUE.index);
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        font.setBold(true);
        font.setColor(HSSFColor.WHITE.index);
        style.setFont(font);


        // create header row
        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("Brand");
        header.getCell(0).setCellStyle(style);
        header.createCell(1).setCellValue("Name");
        header.getCell(1).setCellStyle(style);
        header.createCell(2).setCellValue("Size");
        header.getCell(2).setCellStyle(style);
        header.createCell(3).setCellValue("Price");
        header.getCell(3).setCellStyle(style);
       



        int rowCount = 1;

        for(Makeup makeup : makeups){
            Row makeupRow =  sheet.createRow(rowCount++);
            makeupRow.createCell(0).setCellValue(makeup.getBrand());
            makeupRow.createCell(1).setCellValue(makeup.getName());
            makeupRow.createCell(2).setCellValue(makeup.getSize());
            makeupRow.createCell(3).setCellValue(makeup.getPrice());
         
           

            }

    }

}
