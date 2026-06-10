package com.company.inventory.Inventory.api.controller.category;

import com.company.inventory.Inventory.api.response.category.CategoryResposeRest;
import com.company.inventory.Inventory.api.services.category.ICategoryService;
import com.company.inventory.Inventory.api.util.category.CategoryExcelExport;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = {"http://localhost:4200"})
public class ExporExcelCategoryController {

    @Autowired
    public ICategoryService categoryService;

    @GetMapping("/categories/export/excel")
    public void exportToExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=category.xlsx";
        response.setHeader(headerKey, headerValue);

        ResponseEntity<CategoryResposeRest> categoryResposeRestResponseEntity = categoryService.serch();

        CategoryExcelExport excelExport = new CategoryExcelExport(
                categoryResposeRestResponseEntity.getBody().getCategoryResponse().getCategory());

        excelExport.export(response);
    }

}
