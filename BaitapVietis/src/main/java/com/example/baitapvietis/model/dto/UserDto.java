package com.example.baitapvietis.model.dto;

import com.example.baitapvietis.contants.RoleEnum;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class UserDto {

    @Id
    private Long id;

    @NotBlank(message = "Usernmae is required")
    @Size(min = 6, max = 15)
    private String username;

    @NotBlank(message = "Fullname is required !!")
    @Size(min = 6, max = 40)
    private String fullname;

    @NotBlank(message = "Password is required !!")
    @Size(min = 5,max = 20)
    private String password;

    @NotBlank(message = "Date of brithday is required !!")
    private String dateOfBrithday;

    private RoleEnum role;
}
