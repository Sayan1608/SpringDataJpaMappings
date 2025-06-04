package com.mapping.controllers;

import com.mapping.entities.DepartmentEntity;
import com.mapping.entities.EmployeeEntity;
import com.mapping.services.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    public EmployeeEntity createNewEmployee(@RequestBody EmployeeEntity employeeEntity){
        return this.employeeService.createNewEmployee(employeeEntity);
    }

    @GetMapping(path = "/{employeeId}")
    public EmployeeEntity getEmployeeById(@PathVariable(name = "employeeId") Long id){
        return this.employeeService.getEmployeeById(id).orElse(null);
    }

    @PutMapping(path = "/{employeeId}/{departmentId}")
    public EmployeeEntity assignDepartmentToManager(@PathVariable(name = "employeeId") Long empId,
                                                    @PathVariable(name = "departmentId") Long deptId){
        return this.employeeService.assignDepartmentToManager(empId,deptId);
    }

    @GetMapping(path = "/manager/{employeeId}")
    public DepartmentEntity getManagedDepartment(@PathVariable(name = "employeeId") Long empId){
        return this.employeeService.getManagedDepartment(empId);
    }

    @GetMapping
    public List<EmployeeEntity> getAllEmployees(){
        return this.employeeService.getAllEmployees();
    }

}
