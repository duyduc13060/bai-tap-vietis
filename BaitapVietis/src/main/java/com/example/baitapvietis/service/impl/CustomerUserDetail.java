package com.example.baitapvietis.service.impl;

import com.example.baitapvietis.contants.RoleEnum;
import com.example.baitapvietis.model.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class CustomerUserDetail implements UserDetails {

    private Long id;
    private String username;
    private String fullname;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    public static CustomerUserDetail buid(UserEntity userEntity){
        List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(userEntity.getRole().toString()));
        return CustomerUserDetail.builder()
                .id(userEntity.getId())
                .username(userEntity.getUsername())
                .fullname(userEntity.getFullname())
                .password(userEntity.getPassword())
                .authorities(authorities)
                .build();
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
