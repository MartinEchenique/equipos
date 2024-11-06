package com.echenique.equipos.security;

import com.echenique.equipos.model.AppUser;
import com.echenique.equipos.repository.AppUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final AppUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = userRepository.findById(username).orElseThrow(()-> new UsernameNotFoundException("Admin Username "+ username+ "not found"));
        return new User(appUser.getUsername(), appUser.getPassword(), List.of());
    }

}