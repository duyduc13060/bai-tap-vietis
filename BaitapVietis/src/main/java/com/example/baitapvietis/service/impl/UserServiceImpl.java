package com.example.baitapvietis.service.impl;

import com.example.baitapvietis.model.entity.UserEntity;
import com.example.baitapvietis.repository.UserRepository;
import com.example.baitapvietis.service.UserService;
import com.example.baitapvietis.utils.CurrentUserUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
        Optional<UserEntity> optionalUser = userRepository.findById(id);
        if(!optionalUser.isPresent()) return false;

        UserEntity userEntity = optionalUser.get();
        userRepository.deleteById(userEntity.getId());

        return true;
    }

    public UserEntity update(Long id,UserEntity userEntity){


        Optional<UserEntity> findById = userRepository.findById(id);
        if(!findById.isPresent()) return null;

        UserEntity user = findById.get();


        return userRepository.save(user);
    }



}
