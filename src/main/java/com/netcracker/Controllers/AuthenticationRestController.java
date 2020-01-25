//package com.netcracker.Controllers;
//
//import com.netcracker.dto.AuthenticationRequestDto;
//import com.netcracker.entities.User;
//
//import com.netcracker.services.UsersService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@RestController
//@RequestMapping(value = "/authenticate")
//public class AuthenticationRestController {
//
//    @Autowired
//    private final AuthenticationManager authenticationManager;
//
//    @Autowired
//    private final JwtTokenProvider jwtTokenProvider;
//
//    @Autowired
//    private final UsersService userService;
//
//    @Autowired
//    public AuthenticationRestController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UsersService userService) {
//        this.authenticationManager = authenticationManager;
//        this.jwtTokenProvider = jwtTokenProvider;
//        this.userService = userService;
//    }
//
//    @PostMapping("login")
//    public ResponseEntity login(@RequestBody AuthenticationRequestDto requestDto) {
//
//        String email = requestDto.getEmail();
//        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, requestDto.getPassword()));
//        User user = userService.getUserByEmail(email);
//
//        if (user == null) {
//            throw new UsernameNotFoundException("User with email: " + email + " not found");
//        }
//
//        JwtToken token = jwtTokenProvider.createToken(email);
//
//        Map<Object, Object> response = new HashMap<>();
//        response.put("token", token);
//
//        return ResponseEntity.ok(response);
//
//    }
//}
