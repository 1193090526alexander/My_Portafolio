package com.company.inventory.Inventory.api.response.category;

import com.company.inventory.Inventory.api.response.ResponseRest;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryResposeRest extends ResponseRest {

    private CategoryResponse categoryResponse =  new CategoryResponse();
}
