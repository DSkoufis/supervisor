package com.supervisor.repository.project;

import com.supervisor.domain.project.Project;
import org.springframework.data.repository.CrudRepository;

public interface ProjectRepository extends CrudRepository<Project, Long> {
}
