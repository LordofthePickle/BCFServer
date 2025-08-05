package com.opensourcebim.bcfserver.services;

import com.opensourcebim.bcfserver.dtos.project.ProjectDTO;
import com.opensourcebim.bcfserver.models.Project;
import com.opensourcebim.bcfserver.repositories.ProjectRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;


    public ProjectServiceImpl(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public Page<ProjectDTO> getAllProjects(Pageable pageable) {
        Page<Project> projectsPage = projectRepository.findAll(pageable);
        return projectsPage.map(ProjectDTO::from);
    }
}
