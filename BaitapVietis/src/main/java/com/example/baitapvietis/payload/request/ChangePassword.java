package com.example.baitapvietis.payload.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChangePassword {

    private String passwordOld;
    private String passwordNew;
    private String confirmPassword;

}
