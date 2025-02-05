package com.codeloon.ems.repository;

import com.codeloon.ems.entity.TransportCost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransportCostRepository extends JpaRepository<TransportCost, Long> {
}

