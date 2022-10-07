package com.example.baitapvietis.service;

import com.example.baitapvietis.model.entity.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<UserEntity> listUser();

    UserEntity create(UserEntity userEntity);

    Optional<UserEntity> findById(Long id);

    boolean deleteUser(Long id);
}
