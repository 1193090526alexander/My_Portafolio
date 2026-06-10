package com.company.inventory.Inventory.api.controller.product;

import com.company.inventory.Inventory.api.response.product.ProductoResponseRest;
import com.company.inventory.Inventory.api.services.product.IProductService;
import com.company.inventory.Inventory.api.util.product.ProductExcelExport;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
public class ExportExcelProductController {

    @Autowired
    public IProductService productService;


    @GetMapping("/products/export/excel")
    public void exportToExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=productos.xlsx";
        response.setHeader(headerKey, headerValue);

        ResponseEntity<ProductoResponseRest> productResposeRestResponseEntity = productService.serch();

        ProductExcelExport excelExport = new ProductExcelExport(
                productResposeRestResponseEntity.getBody().getProducto().getProductEntities());

        excelExport.export(response);
    }
}
