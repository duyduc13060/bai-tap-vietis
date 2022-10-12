package com.example.baitapvietis.service;

import com.example.baitapvietis.model.dto.UserDto;
import com.example.baitapvietis.model.entity.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<UserDto> listUser();

    UserDto create(UserDto userDto);

    UserDto findById(Long id);

    boolean deleteUser(Long id);

    UserDto update(Long id, UserDto userDto);

    List<UserEntity> search(String username);
}
