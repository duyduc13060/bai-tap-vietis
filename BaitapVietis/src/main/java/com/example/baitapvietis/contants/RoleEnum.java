package com.example.baitapvietis.contants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RoleEnum {
    USER(0),
    ADMIN(1);

    private final int value;
}
