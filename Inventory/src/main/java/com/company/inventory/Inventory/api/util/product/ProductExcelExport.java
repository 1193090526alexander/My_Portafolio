package com.company.inventory.Inventory.api.util.product;

import com.company.inventory.Inventory.api.model.CategoryEntity;
import com.company.inventory.Inventory.api.model.ProductEntity;
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

public class ProductExcelExport {

    private XSSFWorkbook workBook;
    private XSSFSheet sheet;
    private List<ProductEntity> productEntities;

    public ProductExcelExport(List<ProductEntity> product) {
        this.productEntities = product;
        workBook = new XSSFWorkbook();
    }

    private void writeHeaderLine(){
        sheet = workBook.createSheet("product");
        Row row = sheet.createRow(0);
        CellStyle style = workBook.createCellStyle();

        XSSFFont font = workBook.createFont();
        font.setBold(true);
        font.setFontHeightInPoints((short) 12);
        style.setFont(font);


        createCell(row, 0, "ID", style);
        createCell(row, 1, "Nombre", style);
        createCell(row, 2, "Precio", style);
        createCell(row, 3, "Categoria", style);
        createCell(row, 4, "cantidad", style);
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

        for(ProductEntity result : productEntities){
            Row row = sheet.createRow(rowCount++);
            int  columnCount = 0;
            createCell(row, columnCount++, String.valueOf(result.getId()), style);
            createCell(row, columnCount++, result.getName(), style);
            createCell(row, columnCount++, String.valueOf(result.getPrice()), style);
            createCell(row, columnCount++, String.valueOf(result.getCategory().getName()), style);
            createCell(row, columnCount++, String.valueOf(result.getQuantity()), style);

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
