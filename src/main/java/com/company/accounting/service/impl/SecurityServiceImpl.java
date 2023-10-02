package com.company.accounting.service.impl;

import com.company.accounting.dto.UserDTO;
import com.company.accounting.entity.User;
import com.company.accounting.entity.common.UserPrincipal;
import com.company.accounting.repository.UserRepository;
import com.company.accounting.service.SecurityService;
import com.company.accounting.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service // This annotation is used to mark the class as a service provider.
public class SecurityServiceImpl implements SecurityService {
    private final UserRepository userRepository;
    private final UserService userService;
    public SecurityServiceImpl(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userRepository.findUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);

        }
        return new UserPrincipal(user);
    }
    @Override
    public UserDTO getCurrentUser() {


        return userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
    }
}
