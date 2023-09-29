package com.company.accounting.converter;

import com.company.accounting.dto.UserDTO;
import com.company.accounting.entity.User;
import com.company.accounting.service.UserService;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@ConfigurationPropertiesBinding
public class UserDtoConverter implements Converter<String, UserDTO>{
    private final UserService userService;

    public UserDtoConverter(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDTO convert(String id) {
        if (id == null || id.isBlank())
            return null;
        return userService.findById(Long.parseLong(id));
    }
}
