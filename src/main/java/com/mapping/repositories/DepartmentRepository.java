package com.mapping.repositories;

import com.mapping.entities.DepartmentEntity;
import com.mapping.entities.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DepartmentRepository extends JpaRepository<DepartmentEntity,Long> {
    Optional<DepartmentEntity> findByDepartmentManager(EmployeeEntity employeeEntity);
}
