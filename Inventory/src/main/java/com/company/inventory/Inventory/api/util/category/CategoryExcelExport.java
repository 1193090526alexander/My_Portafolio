package com.company.inventory.Inventory.api.util.category;

import com.company.inventory.Inventory.api.model.CategoryEntity;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.util.List;

public class CategoryExcelExport {

    private XSSFWorkbook workBook;
    private XSSFSheet sheet;
    private List<CategoryEntity> categories;

    public CategoryExcelExport(List<CategoryEntity> categories) {
        this.categories = categories;
        workBook = new XSSFWorkbook();
    }

    private void writeHeaderLine(){
        sheet = workBook.createSheet("Categories");
        Row row = sheet.createRow(0);
        CellStyle style = workBook.createCellStyle();

        XSSFFont font = workBook.createFont();
        font.setBold(true);
        font.setFontHeightInPoints((short) 12);
        style.setFont(font);


        createCell(row, 0, "ID", style);
        createCell(row, 1, "Nombre", style);
        createCell(row, 2, "Descripcción", style);
    }

    private void createCell(Row row, int columnCount, Object value, CellStyle style){
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);

        if(value instanceof Integer){
            cell.setCellValue((Integer) value);
        } else if (value instanceof  Boolean) {
            cell.setCellValue((Boolean) value);
        }else {
            cell.setCellValue((String) value);
        }
        cell.setCellStyle(style);
    }

    private void writeDataLines(){
        int rowCount = 1;
        CellStyle style = workBook.createCellStyle();
        XSSFFont font = workBook.createFont();
        font.setFontHeightInPoints((short) 12);
        style.setFont(font);

        for(CategoryEntity result : categories){
            Row row = sheet.createRow(rowCount++);
            int  columnCount = 0;
            createCell(row, columnCount++, String.valueOf(result.getIdcategory()), style);
            createCell(row, columnCount++, result.getName(), style);
            createCell(row, columnCount++, String.valueOf(result.getDescription()), style);
        }
    }


    public void export(HttpServletResponse response) throws IOException {
        writeHeaderLine();
        writeDataLines();
        ServletOutputStream outputStream = response.getOutputStream();
        workBook.write(outputStream);
        workBook.close();

        outputStream.close();
    }

}
