package com.abhi.repository;

import com.abhi.model.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUserRepository extends JpaRepository<UserDetails, Integer> {
    Optional<UserDetails> findUserDetailsByUname(String uName);
}
