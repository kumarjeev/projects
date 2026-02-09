package com.securityexample.controller;

import com.securityexample.dto.APIResponse;
import com.securityexample.dto.LoginDto;
import com.securityexample.dto.UserDto;
import com.securityexample.entity.User;
import com.securityexample.repository.UserRepository;
import com.securityexample.service.AuthService;
import com.securityexample.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    @Autowired
    private AuthService authService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private UserRepository userRepository;

    //http://localhost:8080/api/v1/auth/signup
    @PostMapping("/signup")
    public ResponseEntity<APIResponse<String>> createUser(@RequestBody UserDto userDto){
        APIResponse<String> response = authService.register(userDto);
        return new ResponseEntity<>(response, HttpStatusCode.valueOf(response.getStatus()));

    }
    @PostMapping("/login")
    public ResponseEntity<APIResponse<String>> verifyLogin(@RequestBody LoginDto loginDto){
        APIResponse<String> response=new APIResponse<>();
        try {
            UsernamePasswordAuthenticationToken token=new UsernamePasswordAuthenticationToken(loginDto.getUsername(),loginDto.getPassword());
            Authentication authenticate = authenticationManager.authenticate(token);
            if (authenticate.isAuthenticated()){
                String jwtToken = jwtService.generateToken(loginDto.getUsername(), authenticate.getAuthorities().iterator().next().getAuthority());
                response.setMessage("Login Sucessful");
                response.setStatus(200);
                response.setData(jwtToken);
                return new ResponseEntity<>(response, HttpStatusCode.valueOf(response.getStatus()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        response.setMessage("Failed");
        response.setStatus(401);
        response.setData("Un-Authorized Access");
        return new ResponseEntity<>(response, HttpStatusCode.valueOf(response.getStatus()));

    }
    @GetMapping("/profile")
    public  ResponseEntity<String> profile(@AuthenticationPrincipal UserDetails userDetails){
        return new ResponseEntity<>(userDetails.getUsername(), HttpStatus.OK);
    }

    public User geUserByUsername(@RequestParam("username") String username){
        return  userRepository.findByUsername(username);
    }
}
