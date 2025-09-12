package com.abhi.service;

import com.abhi.model.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface IUserService extends UserDetailsService {

    String userRegistration(UserDetails userDetails);
}
