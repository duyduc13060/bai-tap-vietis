package com.example.baitapvietis.utils;

import com.example.baitapvietis.service.impl.CustomerUserDetail;
import org.springframework.security.core.context.SecurityContextHolder;

public class CurrentUserUtils {

    public static CustomerUserDetail getCurrentUSerDetails(){
        return (CustomerUserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}
