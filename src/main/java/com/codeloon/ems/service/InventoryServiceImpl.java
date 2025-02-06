package com.codeloon.ems.service;

import com.codeloon.ems.dto.InventoryDto;
import com.codeloon.ems.dto.UserDto;
import com.codeloon.ems.entity.Inventory;
import com.codeloon.ems.entity.Role;
import com.codeloon.ems.entity.User;
import com.codeloon.ems.entity.UserPersonalData;
import com.codeloon.ems.repository.InventoryRepository;
import com.codeloon.ems.util.DataVarList;
import com.codeloon.ems.util.ResponseBean;
import com.codeloon.ems.util.ResponseCode;
import com.codeloon.ems.util.UserUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {
    @Override
    public List<InventoryDto> getAllInventory() {
        return List.of();
    }

    @Override
    public ResponseBean getInventoryByName(String name) {
        return null;
    }

    @Override
    public ResponseBean getInventoryById(Long userId) {
        return null;
    }

    @Override
    public ResponseBean createInventory(InventoryDto inventory) {
        return null;
    }

    @Override
    public ResponseBean updateInventory(InventoryDto inventory) {
        return null;
    }

    @Override
    public ResponseBean deleteInventory(Long inventoryId) {
        return null;
    }
}
