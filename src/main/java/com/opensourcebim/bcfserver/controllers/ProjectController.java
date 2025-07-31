package com.opensourcebim.bcfserver.controllers;

import com.opensourcebim.bcfserver.dtos.ProjectDTO;
import com.opensourcebim.bcfserver.models.Project;
import com.opensourcebim.bcfserver.services.ProjectService;
import com.opensourcebim.bcfserver.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<Page<ProjectDTO>> getAllProjects(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(projectService.getAllProjects(pageable));
    }

    @GetMapping("/myProjects")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<ProjectDTO>> getProjectsForCurrentUser() {
        return ResponseEntity.ok(userService.getAllProjectsForCurrentUser());
    }
}
