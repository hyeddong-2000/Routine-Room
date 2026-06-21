package com.routineroom.controller.user;

import com.routineroom.common.common.ResponseWrapper;
import com.routineroom.dto.users.UserRequestDTO;
import com.routineroom.dto.users.UserResponseDTO;
import com.routineroom.service.user.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<ResponseWrapper> registerUser(@Valid @RequestBody UserRequestDTO.SignUp request) {
        userService.registerUser(request);
        return ResponseEntity.ok(ResponseWrapper.success());
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseWrapper> loginUser(@Valid @RequestBody UserRequestDTO.Login request) {
        UserResponseDTO.LoginInfo loginInfo = userService.loginUser(request);
        return ResponseEntity.ok(ResponseWrapper.success(loginInfo));
    }

    @GetMapping("/me")
    public ResponseEntity<ResponseWrapper> getMyInfo() {
        UserResponseDTO.Profile profile = userService.getMyInfo();
        return ResponseEntity.ok(ResponseWrapper.success(profile));
    }
}
