package com.mapping.services;

import com.mapping.entities.EmployeeEntity;
import com.mapping.entities.ProjectEntity;
import com.mapping.repositories.EmployeeRepository;
import com.mapping.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ProjectService {
    private final ProjectRepository projectRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public List<ProjectEntity> getAllProjects() {
        return projectRepository.findAll();
    }

    public ProjectEntity getProjectById(Long id) {
        return projectRepository.findById(id).orElse(null);
    }

    public ProjectEntity createNewProject(ProjectEntity projectEntity) {
        return projectRepository.save(projectEntity);
    }

    public ProjectEntity assignProjectToResource(Long projectId, Long resourceId) {
        Optional<ProjectEntity> project = projectRepository.findById(projectId);
        Optional<EmployeeEntity> employee = employeeRepository.findById(resourceId);

        return project.flatMap(
                projectEntity -> (employee.map(employeeEntity -> {
                    projectEntity.getResources().add(employeeEntity);
                    employeeEntity.getProjects().add(projectEntity);
                    return  projectRepository.save(projectEntity);
                }))
        ).orElse(null);
    }
}
