package com.mapping.controllers;

import com.mapping.entities.ProjectEntity;
import com.mapping.services.ProjectService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/projects")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping
    public List<ProjectEntity> getAllProjects(){
        return projectService.getAllProjects();
    }

    @GetMapping(path = "/{projectId}")
    public ProjectEntity getProjectById(@PathVariable(name = "projectId") Long id){
        return projectService.getProjectById(id);
    }

    @PostMapping
    public ProjectEntity createNewProject(@RequestBody ProjectEntity projectEntity){
        return projectService.createNewProject(projectEntity);
    }

    @PutMapping(path = "/{projectId}/resource/{resourceId}")
    public ProjectEntity assignProjectToResource(@PathVariable(name = "projectId") Long projectId,
                                                 @PathVariable(name = "resourceId") Long resourceId){
        return projectService.assignProjectToResource(projectId,resourceId);
    }
}
