package com.mapping.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "employees")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class EmployeeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String eName;

    @OneToOne(mappedBy = "departmentManager")
    @JsonIgnore
    private DepartmentEntity managedDepartment;

    @ManyToOne()
    @JoinColumn(name = "dept_id",referencedColumnName = "id")
    @JsonIgnore
    private DepartmentEntity workerDepartment;

    @ManyToMany(mappedBy = "resources")
    private Set<ProjectEntity> projects;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EmployeeEntity that)) return false;
        return Objects.equals(id, that.id) && Objects.equals(eName, that.eName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, eName);
    }

}
