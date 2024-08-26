package com.board.api.jwt.controller;


import com.board.api.jwt.config.JwtUtil;
import com.board.api.jwt.dto.JwtReq;
import com.board.api.jwt.dto.JwtRes;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class JwtAuthController {

  private final AuthenticationManager authenticationManager;

  private final JwtUtil jwtUtil;

  @PostMapping("/login")
  public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtReq req) throws Exception {
    try {
      authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(req.getUsername(), req.getPassword())
      );
    } catch (BadCredentialsException e) {
      throw new Exception("ID 또는 PW 틀림", e);
    }

    final UserDetails userDetails = new User(req.getUsername(), req.getPassword(), new ArrayList<>());
    final String jwt = jwtUtil.generateToken(userDetails.getUsername());

    return ResponseEntity.ok(new JwtRes(jwt));
  }

}
