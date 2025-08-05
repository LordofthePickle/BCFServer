package com.opensourcebim.bcfserver.repositories;

import com.opensourcebim.bcfserver.models.User;
import com.opensourcebim.bcfserver.models.enums.UserType;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

   Optional<User> findByUsername(String username);

   boolean existsByUsername(String username);

   Optional<User> findByEmail(String email);

   boolean existsByEmail(String email);

   Optional<User> findByUoid(Long uoid);

   List<User> findByUserType(UserType userType);

   @NonNull
   Page<User> findByUserType(UserType userType, Pageable pageable);

   @NonNull
   Page<User> findByCreationTimeAfter(@NotNull LocalDateTime creationTime, Pageable pageable);

   @NonNull
   Page<User> findByCreationTimeBefore(@NotNull LocalDateTime creationTime, Pageable pageable);

   @NonNull
   Page<User> findByCreationTimeBetween(@NotNull LocalDateTime creationTimeBefore, @NotNull LocalDateTime creationTimeAfter, Pageable pageable);

   @Override
   @NonNull
   Page<User> findAll(Pageable pageable);
}
