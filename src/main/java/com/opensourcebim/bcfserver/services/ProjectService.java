package com.opensourcebim.bcfserver.services;

import com.opensourcebim.bcfserver.models.Project;
import com.opensourcebim.bcfserver.repositories.ProjectRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProjectService {

    Page<Project> getAllProjects(Pageable pageable);

}
