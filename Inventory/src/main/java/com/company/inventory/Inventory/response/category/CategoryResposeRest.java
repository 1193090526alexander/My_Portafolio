package com.company.inventory.Inventory.response.category;

import com.company.inventory.Inventory.response.ResponseRest;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryResposeRest extends ResponseRest {

    private CategoryResponse categoryResponse =  new CategoryResponse();
}
