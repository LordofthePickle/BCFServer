package com.opensourcebim.bcfserver.services;

import com.opensourcebim.bcfserver.dtos.ProjectDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProjectService {

    Page<ProjectDTO> getAllProjects(Pageable pageable);

}
