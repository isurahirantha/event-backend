package com.codeloon.ems.repository;

import com.codeloon.ems.entity.Role;
import com.codeloon.ems.entity.UserPersonalData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserPersonalDataRepository extends JpaRepository<UserPersonalData,String> {
}
