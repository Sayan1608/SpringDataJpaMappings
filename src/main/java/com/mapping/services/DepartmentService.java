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
public class DepartmentService {
    private final DepartmentRepository departmentRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public DepartmentEntity createNewDepartment(DepartmentEntity departmentEntity) {
       return this.departmentRepository.save(departmentEntity);
    }

    public Optional<DepartmentEntity> getDepartmentById(Long id) {
        return this.departmentRepository.findById(id);
    }

    public DepartmentEntity assignDepartmentManager(Long deptId, Long empId) {
        Optional<DepartmentEntity> departmentEntity = this.departmentRepository.findById(deptId);
        Optional<EmployeeEntity> employeeEntity = this.employeeRepository.findById(empId);

        return departmentEntity.flatMap(department->
                employeeEntity.map(employee->{
                  department.setDepartmentManager(employee);
                  employee.setManagedDepartment(department);
                  employeeRepository.save(employee);
                  return departmentRepository.save(department);
                })
                ).orElse(null);

    }

    public List<DepartmentEntity> getAllDepartments() {
        return this.departmentRepository.findAll();
    }

    public DepartmentEntity assignDepartmentToWorker(Long deptId, Long empId) {
        Optional<DepartmentEntity> dept = departmentRepository.findById(deptId);
        Optional<EmployeeEntity> emp = employeeRepository.findById(empId);
       return dept.flatMap(department->
                emp.map(employeeEntity -> {
                    employeeEntity.setWorkerDepartment(department);
                    employeeRepository.save(employeeEntity);
                    department.getWorkers().add(employeeEntity);
                    return department;
                })).orElse(null);
    }
}
