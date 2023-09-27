package com.company.accounting.converter;


import com.company.accounting.dto.RoleDTO;
import com.company.accounting.service.RoleService;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@ConfigurationPropertiesBinding
public class RoleDtoConverter implements Converter<String, RoleDTO> {
    private final RoleService roleService;

    public RoleDtoConverter(@Lazy RoleService roleService) { // @Lazy is used to avoid circular dependency
        this.roleService = roleService;
    }

    //SneakyThrows
    @Override
    public RoleDTO convert(String id) {

        //it throws error if user selects "Select" even with @SneakyThrows
        if (id == null || id.isBlank())
            return null;
        return roleService.findById(Long.parseLong(id));
    }
}
