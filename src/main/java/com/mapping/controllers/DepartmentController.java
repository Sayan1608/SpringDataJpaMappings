package com.mapping.controllers;

import com.mapping.entities.DepartmentEntity;
import com.mapping.services.DepartmentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @PostMapping
    public DepartmentEntity createNewDepartment(@RequestBody DepartmentEntity departmentEntity){
        return this.departmentService.createNewDepartment(departmentEntity);
    }

    @GetMapping(path = "/{departmentId}")
    public DepartmentEntity getDepartmentById(@PathVariable(name = "departmentId") Long id){
        return this.departmentService.getDepartmentById(id).orElse(null);
    }

    @PutMapping(path = "/{departmentId}/{employeeId}")
    public DepartmentEntity assignDepartmentManager(@PathVariable(name = "departmentId") Long deptId,
                                                    @PathVariable(name = "employeeId") Long empId){
        return this.departmentService.assignDepartmentManager(deptId,empId);
    }

    @GetMapping
    public List<DepartmentEntity> getAllDepartments(){
        return this.departmentService.getAllDepartments();
    }

    @PutMapping(path = "/{departmentId}/worker/{employeeId}")
    public DepartmentEntity assignDepartmentToWorker(@PathVariable(name = "departmentId") Long deptId,
                                                    @PathVariable(name = "employeeId") Long empId){
        return this.departmentService.assignDepartmentToWorker(deptId,empId);
    }


}
