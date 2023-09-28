package com.company.accounting.repository;

import com.company.accounting.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {

    User findUserById(Long id);
    User findByUsername(String username);


}
