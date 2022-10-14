package com.example.baitapvietis.service.impl;

import com.example.baitapvietis.exception.NotFoundException;
import com.example.baitapvietis.model.dto.UserDto;
import com.example.baitapvietis.model.entity.UserEntity;
import com.example.baitapvietis.repository.UserRepository;
import com.example.baitapvietis.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private final ModelMapper modelMapper;

    public UserServiceImpl(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            ModelMapper modelMapper
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<UserDto> listUser() {
        List<UserEntity> userEntityList = userRepository.findAll();
        return userEntityList.stream()
                .map(userEntity -> modelMapper.map(userEntity, UserDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public UserDto create(UserDto userDto) {
        Optional<UserEntity> findByUsername = userRepository.findByUsername(userDto.getUsername());

        if (findByUsername.isPresent()) return null;

        UserEntity userEntity = modelMapper.map(userDto, UserEntity.class);

        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));

        String date = userEntity.getDateOfBrithday();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);
        LocalDate localDate = LocalDate.parse(date, dateTimeFormatter);

        userEntity.setDateOfBrithday(localDate.toString());
        System.out.println(localDate + "date");

        return modelMapper.map(userRepository.save(userEntity), UserDto.class);
    }

    @Override
    public UserDto findById(Long id) {

        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(""));


        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public boolean deleteUser(Long id) {
        Optional<UserEntity> optionalUser = Optional.ofNullable(userRepository.findById(id).
                orElseThrow(() -> new NotFoundException("User id not found" + id)));
        UserEntity userEntity = optionalUser.get();

        userRepository.deleteById(userEntity.getId());

        return true;
    }

    @Override
    public UserDto update(Long id, UserDto userDto) {
        Optional<UserEntity> findById = Optional.ofNullable(userRepository.findById(id).
                orElseThrow(() -> new NotFoundException("User id not found " + id)));

        UserEntity userOld = findById.get();
        System.out.println(userOld + "old");

        userDto.setUsername(userOld.getUsername());
        userDto.setPassword(userOld.getPassword());
        userDto.setDateOfBrithday(userOld.getDateOfBrithday());

        UserEntity userEntity = modelMapper.map(userDto, UserEntity.class);


//        userEntity.setUsername(userOld.getUsername());
//        userEntity.setPassword(userOld.getPassword());
//        userEntity.setDateOfBrithday(userOld.getDateOfBrithday());

        return modelMapper.map(userRepository.save(userEntity), UserDto.class);
    }

    @Override
    public List<UserEntity> search(String username) {
        List<UserEntity> userEntityList = null;

        if (!StringUtils.hasText(username)) {
            userEntityList = userRepository.findAll();
        } else {
            userEntityList = userRepository.searchByUsernameLike("%" + username + "%");
        }
        return userEntityList;
    }

    @Override
    public Page<UserEntity> pageNavigation(Integer pageNumber, Integer pageSize) {

        int page = 0;
        int size = 10;

        if (pageNumber != null) {
            page = pageNumber - 1;
        }
        if (pageSize != null) {
            size = pageSize;
        }

        return userRepository.findAll(PageRequest.of(page, size));
    }


    @Override
    public Page<UserEntity> serachAndPage(String username, Pageable pageable) {

        Page<UserEntity> entityPage = null;

        if (StringUtils.hasText(username)) {
            entityPage = userRepository.findByUsernameLike("%" + username + "%", pageable);
        } else {
            entityPage = userRepository.findAll(pageable);
        }
        return entityPage;
    }


}
