package com.opensourcebim.bcfserver.repositories;

import com.opensourcebim.bcfserver.models.Project;
import com.opensourcebim.bcfserver.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

public interface ProjectRepository extends JpaRepository<Project, Long> {

    @Override
    @NonNull
    Page<Project> findAll(Pageable pageable);
}
