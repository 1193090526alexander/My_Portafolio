package com.company.inventory.Inventory.api.response.product;

import com.company.inventory.Inventory.api.response.ResponseRest;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductoResponseRest extends ResponseRest {

    private ProductoResponse  producto = new ProductoResponse();

}
