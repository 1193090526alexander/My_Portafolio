package com.company.inventory.Inventory.api.services;

public interface UseCase <D,R>{
    R execute(D data);
}
