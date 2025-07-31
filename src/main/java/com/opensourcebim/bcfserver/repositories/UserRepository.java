package com.opensourcebim.bcfserver.repositories;

import com.opensourcebim.bcfserver.models.User;
import com.opensourcebim.bcfserver.models.enums.UserType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

   Optional<User> findByUsername(String username);

   boolean existsByUsername(String username);

   Optional<User> findByEmail(String email);

   boolean existsByEmail(String email);

   Optional<User> findByUoid(Long uoid);

   List<User> findByUserType(UserType userType);

   @Override
   @NonNull
   Page<User> findAll(Pageable pageable);
}
