package com.server.ecommerceapp.service;

import com.server.ecommerceapp.dto.AppUserDTO;
import com.server.ecommerceapp.exception.DuplicateUserException;
import com.server.ecommerceapp.model.AppUser;
import com.server.ecommerceapp.dto.AuthRequestDTO;
import com.server.ecommerceapp.repository.IUserRepository;
import com.server.ecommerceapp.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Service("Authentication Service Implementation")
@RequiredArgsConstructor
public class AuthServiceImpl implements IAuthService{
    private final IUserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public AppUserDTO registerUser(AuthRequestDTO authRequestDTO) throws DuplicateUserException {
        Optional<AppUser> appUser = userRepository.getByUsername(authRequestDTO.getUsername());

        if (appUser.isPresent()) {
            throw new DuplicateUserException();
        }

        ModelMapper modelMapper = new ModelMapper();

        AppUserDTO appUserDTO = new AppUserDTO();
        appUserDTO.setUsername(authRequestDTO.getUsername());
        appUserDTO.setEmail(authRequestDTO.getEmail());
        appUserDTO.setPassword(passwordEncoder.encode(authRequestDTO.getPassword()));

        AppUser createdAppUser = modelMapper.map(appUserDTO, AppUser.class);
        createdAppUser = userRepository.save(createdAppUser);

        return appUserDTO;
    }

    @Override
    public String loginUser(AuthRequestDTO authRequestDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authRequestDTO.getUsername(),
                        authRequestDTO.getPassword()
                )
        );
        return jwtTokenProvider.generateToken(authentication);
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        AppUser user = userRepository.findByUsername(userName);
        if (user == null) {
            System.out.println("User doesnt exist!");
            throw new UsernameNotFoundException("User doesnt exist!");
        }
        System.out.println("User found!");
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
        });
        return new User(user.getUsername(), user.getPassword(), authorities);
    }
}
