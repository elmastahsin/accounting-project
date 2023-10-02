package com.company.accounting.service.impl;

import com.company.accounting.dto.UserDTO;
import com.company.accounting.entity.Company;
import com.company.accounting.entity.User;
import com.company.accounting.repository.UserRepository;
import com.company.accounting.mapper.MapperUtil;
import com.company.accounting.service.SecurityService;
import com.company.accounting.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final MapperUtil mapperUtil;
    private final SecurityService securityService;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, MapperUtil mapperUtil, SecurityService securitService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.mapperUtil = mapperUtil;
        this.securityService = securitService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDTO findByUsername(String username) {
        return mapperUtil.convert(userRepository.findUserByUsername(username), new UserDTO());

    }


    @Override
    public UserDTO getCurrentUser() {
        return securityService.getCurrentUser();
    }

    @Override
    public UserDTO findById(Long userId) {
        return mapperUtil.convert(userRepository.findById(userId).get(), new UserDTO());
    }

    @Override
    public List<UserDTO> findAll() {

        List<User> userList;
        if (getCurrentUser().getRole().getDescription().equals("Root User")) {
            userList = userRepository.findAllByRole_Description("Admin");
        } else {
            userList = userRepository.findAllByCompany(mapperUtil.convert(securityService.getCurrentUser().getCompany(), new Company()));
        }
        return userList.stream()
                .sorted(Comparator.comparing((User u) -> u.getCompany().getTitle()).thenComparing(u -> u.getRole().getDescription()))
                .map(user -> mapperUtil.convert(user, new UserDTO()))
                .map(userDto -> {
                    isOnlyAdmin(userDto);
                    return userDto;
                })
                .collect(Collectors.toList());


    }


    @Override
    public void save(UserDTO userDTO) {

        User user = mapperUtil.convert(userDTO, new User());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEnabled(true);
        userRepository.save(user);

    }

    @Override
    public void delete(UserDTO userDTO) {

        User user= mapperUtil.convert(userDTO, new User());
        user.setUsername(user.getUsername() + "-" + user.getId() + " DELETED");

        user.setIsDeleted(true);
        userRepository.save(user);

    }


    @Override
    public void update(UserDTO userDTO, Long userId) {
        User user =  userRepository.findById(userId).get();
        userDTO.setId(userId);
        save(userDTO);

    }


    private int adminCount(UserDTO userDto) {
        return (int) userRepository.findAllByCompany(mapperUtil.convert(userDto.getCompany(), new Company())).stream()
                .filter(user -> user.getRole().getDescription().equals("Admin"))
                .count();
    }

    @Override
    public boolean isExist(UserDTO userDTO) {
        return findAll().stream().filter(userDto1 -> userDto1.getUsername().equals(userDTO.getUsername())).count() > 0;
    }

    private void isOnlyAdmin(UserDTO userDto) {
        if (userDto.getRole().getDescription().equalsIgnoreCase("Admin") && adminCount(userDto) == 1)
            userDto.setIsOnlyAdmin(true);
        else
            userDto.setIsOnlyAdmin(false);
    }
}
