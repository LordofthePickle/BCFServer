package com.opensourcebim.bcfserver.controllers;

import com.opensourcebim.bcfserver.dtos.PageRequestDTO;
import com.opensourcebim.bcfserver.dtos.project.ProjectDTO;
import com.opensourcebim.bcfserver.services.ProjectService;
import com.opensourcebim.bcfserver.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    private final ProjectService projectService;
    private final UserService userService;

    @Autowired
    public ProjectController(ProjectService projectService, UserService userService) {
        this.projectService = projectService;
        this.userService = userService;
    }

    //Getters
    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Page<ProjectDTO>> getAllProjects(@ModelAttribute PageRequestDTO request) {
        Pageable pageable = PageRequest.of(request.getPage(), request.getSize());
        return ResponseEntity.ok(projectService.getAllProjects(pageable));
    }

    @GetMapping("/myProjects")
    public ResponseEntity<List<ProjectDTO>> getProjectsForCurrentUser() {
        return ResponseEntity.ok(userService.getAllProjectsForCurrentUser());
    }
}
