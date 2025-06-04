package com.mapping.services;

import com.mapping.entities.DepartmentEntity;
import com.mapping.entities.EmployeeEntity;
import com.mapping.repositories.DepartmentRepository;
import com.mapping.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public EmployeeEntity createNewEmployee(EmployeeEntity employeeEntity) {
        return this.employeeRepository.save(employeeEntity);
    }

    public Optional<EmployeeEntity> getEmployeeById(Long id) {
        return this.employeeRepository.findById(id);
    }

    public EmployeeEntity assignDepartmentToManager(Long empId, Long deptId) {
        Optional<DepartmentEntity> departmentEntity = this.departmentRepository.findById(deptId);
        Optional<EmployeeEntity> employeeEntity = this.employeeRepository.findById(empId);

        return employeeEntity.flatMap(employee->
                departmentEntity.map(department->{
                    department.setDepartmentManager(employee);
                    employee.setManagedDepartment(department);
                    departmentRepository.save(department);
                    return employeeRepository.save(employee);
                })
                ).orElse(null);
    }


    public DepartmentEntity getManagedDepartment(Long empId) {
//        Optional<EmployeeEntity> employeeEntity = this.employeeRepository.findById(empId);
//        return employeeEntity.map(EmployeeEntity::getManagedDepartment).orElse(null);

        EmployeeEntity employeeEntity = EmployeeEntity.builder()
                .id(empId)
                .build();

        return departmentRepository.findByDepartmentManager(employeeEntity).orElse(null);
    }

    public List<EmployeeEntity> getAllEmployees() {
        return this.employeeRepository.findAll();
    }
}
