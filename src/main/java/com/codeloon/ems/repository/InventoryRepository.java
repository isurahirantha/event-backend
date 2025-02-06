package com.codeloon.ems.repository;

import com.codeloon.ems.entity.Inventory;
import com.codeloon.ems.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InventoryRepository extends JpaRepository<Inventory, Long>{
    //Optional<Inventory> getByInventoryByName(String name);
    //Optional<Inventory> getInventoryById(Long id);
}
