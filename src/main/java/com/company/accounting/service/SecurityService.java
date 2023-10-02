package com.company.accounting.service;

import com.company.accounting.dto.UserDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface SecurityService extends UserDetailsService {
    UserDTO getCurrentUser();
}
