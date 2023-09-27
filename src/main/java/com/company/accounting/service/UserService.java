package com.company.accounting.service;

import com.company.accounting.dto.UserDTO;
import com.company.accounting.service.common.CrudService;

public interface UserService extends CrudService<UserDTO, Long> {
    UserDTO findByUsername(String name);

    UserDTO getCurrentUser();

}