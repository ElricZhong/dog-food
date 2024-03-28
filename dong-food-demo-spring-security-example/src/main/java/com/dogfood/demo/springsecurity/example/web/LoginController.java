package com.dogfood.demo.springsecurity.example.web;

import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhongfupeng
 */
@RestController
@AllArgsConstructor
public class LoginController {

    private final AuthenticationManager authenticationManager;

    // 前后端分离登录
    //@RequestMapping("/login")
    //public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
    //    Authentication authenticationRequest = new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword());
    //    Authentication authenticationResponse = this.authenticationManager.authenticate(authenticationRequest);
    //    return ResponseEntity.ok("login success");
    //}
}
