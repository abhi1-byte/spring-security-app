package com.abhi.service;

import com.abhi.model.UserDetails;
import com.abhi.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements IUserService {

    private IUserRepository repo;
    private BCryptPasswordEncoder encoder;

    @Autowired
    public UserServiceImpl(IUserRepository repo, BCryptPasswordEncoder encoder) {
        this.repo = repo;
        this.encoder = encoder;
    }

    @Override
    public String userRegistration(UserDetails userDetails) {
        userDetails.setPwd(encoder.encode(userDetails.getPwd()));
        return repo.save(userDetails).getUid() + "user is registered";
    }

    @Override
    public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = null;
        Optional<UserDetails> optional = repo.findUserDetailsByUname(username);
        if (optional.isEmpty()) {
            throw new IllegalArgumentException("User not found");
        } else {
            UserDetails userEntity = optional.get();
            user = new User(userEntity.getUname(), userEntity.getPwd(),
                    userEntity.getRoles().stream().map(role -> new SimpleGrantedAuthority(role)).collect(Collectors.toSet()));
        }
        return user;
    }

}
