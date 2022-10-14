package com.example.baitapvietis.repository;

import com.example.baitapvietis.model.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long> {
    Optional<UserEntity> findByUsername(String username);

    Page<UserEntity> findByUsernameLike(String username, Pageable pageable);

    List<UserEntity> searchByUsernameLike(String usernmae);

}
