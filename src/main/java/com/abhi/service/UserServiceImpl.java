package com.abhi.service;

import com.abhi.model.UserDetails;
import com.abhi.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
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
//            .map(SimpleGrantedAuthority::new) -->Constructor reference
            user = new User(userEntity.getUname(), userEntity.getPwd(),
                    userEntity.getRoles().stream().map(role -> new SimpleGrantedAuthority("ROLE_" + role)).collect(Collectors.toSet()));
        }
        return user;
    }

}

/* <R> Stream<R> map(Function<? super T, ? extends R> mapper)
T → the type of the stream elements before mapping
T-> String i.c role
R → the type of the stream elements after mapping
R->SimpleGrantedAuthority
Function<T,R> → a functional interface with one method:
R apply(T t);

Input (t): "ROLE_ADMIN"
Function applies: new SimpleGrantedAuthority("ROLE_ADMIN")
Output (R): SimpleGrantedAuthority object
So after mapping, the stream type becomes Stream<SimpleGrantedAuthority>.

map() takes a Function.
That Function says: “Given an element of the stream, here’s how to turn it into something else.”
In your example, it turns a String role into a SimpleGrantedAuthority.
 * */