package com.junggam.controller;

import com.junggam.domain.UserVO;
import com.junggam.dto.UserDTO;
import com.junggam.service.UserService;
import com.junggam.util.ObjectConverter;
import javassist.bytecode.DuplicateMemberException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api")
public class UserController {

    final private static ObjectConverter oc = new ObjectConverter();

    @Autowired
    UserService userService;


    @PostMapping(path = "/signUp")
    public ResponseEntity<UserDTO> signUp(
            @RequestBody UserDTO userDTO) throws DuplicateMemberException {

        UserVO user = userService.signup(userDTO);
        return ResponseEntity.ok(oc.userVOToDTO(user));

    }


}
