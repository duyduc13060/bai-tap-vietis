package com.example.baitapvietis.service.impl;

import com.example.baitapvietis.exception.BadRequstException;
import com.example.baitapvietis.model.entity.UserEntity;
import com.example.baitapvietis.payload.request.ChangePassword;
import com.example.baitapvietis.repository.UserRepository;
import com.example.baitapvietis.service.AuthService;
import com.example.baitapvietis.utils.CurrentUserUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthServiceImpl(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder
    ){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }



    @Override
    public void changePassword(ChangePassword changePassword){
        CustomerUserDetail userDetail = CurrentUserUtils.getCurrentUSerDetails();

        if(!passwordEncoder.matches(changePassword.getPasswordOld(),userDetail.getPassword())){
            throw new BadRequstException("Mật khẩu cũ không chính xác");
        }
        if(Objects.equals(changePassword.getPasswordOld(),changePassword.getPasswordNew())){
            throw new BadRequstException("Không thể đặt lại mật khẩu cũ");
        }
        if(!changePassword.getPasswordNew().equals(changePassword.getConfirmPassword())){
            throw new BadRequstException("Xác nhận lại mật khẩu");
        }

        UserEntity user = userRepository.getById(userDetail.getId());
        user.setPassword(passwordEncoder.encode(changePassword.getPasswordNew()));
        userRepository.save(user);

    }




}
