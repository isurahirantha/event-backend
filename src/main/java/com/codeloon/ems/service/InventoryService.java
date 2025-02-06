package com.codeloon.ems.service;

import com.codeloon.ems.dto.InventoryDto;
import com.codeloon.ems.util.ResponseBean;

import java.util.List;

public interface InventoryService {
    public List<InventoryDto> getAllInventory();

    public ResponseBean getInventoryByName(String name);

    public ResponseBean getInventoryById(Long userId);

    public ResponseBean createInventory(InventoryDto inventory);

    public ResponseBean updateInventory(InventoryDto inventory);
    public ResponseBean deleteInventory(Long inventoryId);
}
