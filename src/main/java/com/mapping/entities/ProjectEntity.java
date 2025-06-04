package com.mapping.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "projects")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class ProjectEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long projectId;

    private String projectCode;

    @ManyToMany
    @JoinTable(name = "project_resource_mapping"
    ,joinColumns = @JoinColumn(name = "project_id")
    ,inverseJoinColumns = @JoinColumn(name = "project_code"))
    @JsonIgnore
    private Set<EmployeeEntity> resources;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProjectEntity that)) return false;
        return Objects.equals(projectId, that.projectId) && Objects.equals(projectCode, that.projectCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectId, projectCode);
    }
}
