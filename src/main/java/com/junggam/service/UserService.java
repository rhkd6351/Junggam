package com.junggam.service;

import java.util.Collections;
import java.util.Optional;

import com.junggam.domain.AuthVO;
import com.junggam.domain.UserVO;
import com.junggam.dto.UserDTO;
import com.junggam.repository.AuthRepository;
import com.junggam.repository.UserRepository;
import com.junggam.util.SecurityUtil;
import javassist.NotFoundException;
import javassist.bytecode.DuplicateMemberException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.security.auth.message.AuthException;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthRepository authRepository;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthRepository authRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authRepository = authRepository;
    }

    @Transactional
    public UserVO signup(UserDTO userDto) throws DuplicateMemberException {
        if (userRepository.findOneWithAuthoritiesByUsername(userDto.getUsername()).orElse(null) != null) {
            throw new DuplicateMemberException("이미 가입되어 있는 유저입니다.");
        }

        AuthVO auth = authRepository.findById(1L).get();

        UserVO user = UserVO.builder()
                .username(userDto.getUsername())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .auth(auth)
                .activated(true)
                .build();

        return userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public UserVO getUserWithAuthorities(String username) throws NotFoundException {
        Optional<UserVO> userVO = userRepository.findOneWithAuthoritiesByUsername(username);

        if(userVO.isEmpty())
            throw new NotFoundException("Invalid Name");

        return userVO.get();
    }

    @Transactional(readOnly = true)
    public UserVO getMyUserWithAuthorities() throws AuthException {
        Optional<UserVO> userVO = SecurityUtil.getCurrentUsername().flatMap(userRepository::findOneWithAuthoritiesByUsername);
        if(userVO.isEmpty())
            throw new AuthException("Invalid Token");

        return userVO.get();
    }
}