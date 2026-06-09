package com.company.inventory.Inventory.api.services;

public interface UseCasePatch <D, R>{

    R execute(Integer id, D data);
}
