package com.restApi.security.jwt.Api.security;

import com.restApi.security.jwt.Api.entities.Rol;
import com.restApi.security.jwt.Api.entities.Users;
import com.restApi.security.jwt.Api.repository.UserRepository;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

  //need to create a UserDetails object for authentication
    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        Users user = userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"
                        + " with username o email : " + usernameOrEmail));
        
        return new User(user.getEmail(), user.getPassword(), mapRols(user.getRols()));
    }

    //map rols and return grantedAuthorities. 
    private Collection<? extends GrantedAuthority> mapRols(Set<Rol> roles) {
        return roles.stream().map(rol -> new SimpleGrantedAuthority(rol.getName())).collect(Collectors.toList());
    }
   
}
