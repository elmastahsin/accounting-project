package com.company.accounting.service.impl;

import com.company.accounting.entity.User;
import com.company.accounting.entity.common.UserPrincipal;
import com.company.accounting.repository.UserRepository;
import com.company.accounting.service.SecurityService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service // This annotation is used to mark the class as a service provider.
public class SecurityServiceImpl implements SecurityService {
    private final UserRepository userRepository;

    public SecurityServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);

        }
        return new UserPrincipal(user);
    }
}
