package com.example.baitapvietis.service.impl;

import com.example.baitapvietis.contants.RoleEnum;
import com.example.baitapvietis.exception.NotFoundException;
import com.example.baitapvietis.model.entity.UserEntity;
import com.example.baitapvietis.repository.UserRepository;
import com.example.baitapvietis.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder
    ){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<UserEntity> listUser(){
        return userRepository.findAll();
    }

    @Override
    public UserEntity create(UserEntity userEntity){
        Optional<UserEntity> findByUsername = userRepository.findByUsername(userEntity.getUsername());
        if(findByUsername.isPresent()) return null;

        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        userEntity.setDateOfBrithday(LocalDateTime.now());
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//        LocalDateTime date = LocalDateTime.parse(userEntity.getDateOfBrithday().toString(), formatter);
//        userEntity.setDateOfBrithday(date);
//
//        System.out.println(date + "fffffffff");


        return userRepository.save(userEntity);
    }

    @Override
    public Optional<UserEntity> findById(Long id){
        return userRepository.findById(id);
    }

    @Override
    public boolean deleteUser(Long id){
        Optional<UserEntity> optionalUser = Optional.ofNullable(userRepository.findById(id).
                orElseThrow(() -> new NotFoundException("User id not found" + id)));
        UserEntity userEntity = optionalUser.get();

        userRepository.deleteById(userEntity.getId());

        return true;
    }

    @Override
    public UserEntity update(Long id, UserEntity userEntity){
        Optional<UserEntity> findById = Optional.ofNullable(userRepository.findById(id).
                orElseThrow(() -> new NotFoundException("User id not found " + id)));

        UserEntity userOld = findById.get();

        userEntity.setUsername(userOld.getUsername());
        userEntity.setPassword(userOld.getPassword());
        userEntity.setDateOfBrithday(userOld.getDateOfBrithday());

        return userRepository.save(userEntity);
    }

    @Override
    public List<UserEntity> search(String username){
        List<UserEntity> userEntityList = null;

        if(!StringUtils.hasText(username)){
            userEntityList = userRepository.findAll();
            return userEntityList;
        }else{
            userEntityList = userRepository.searchByUsername(username);
            return userEntityList;
        }

    }


}
