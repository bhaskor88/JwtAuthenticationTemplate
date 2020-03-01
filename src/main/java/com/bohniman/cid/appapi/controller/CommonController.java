package com.bohniman.cid.appapi.controller;

import javax.validation.Valid;

import com.bohniman.cid.appapi.Utils.JwtUtil;
import com.bohniman.cid.appapi.Utils.UserValidator;
import com.bohniman.cid.appapi.model.User;
import com.bohniman.cid.appapi.payload.AuthenticationRequest;
import com.bohniman.cid.appapi.payload.AuthenticationResponse;
import com.bohniman.cid.appapi.services.MapValidationErrorService;
import com.bohniman.cid.appapi.services.MyUserDetailsService;
import com.bohniman.cid.appapi.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * CommonController
 */
@RestController
@RequestMapping("/public")
public class CommonController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private MapValidationErrorService mapValidationErrorService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserValidator userValidator;

    @RequestMapping(value="/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> registerUser(@Valid @RequestBody AuthenticationRequest authenticationRequest, BindingResult result) throws Exception{
        try {
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
            );
        } catch (Exception e) {
            throw new Exception("Incorrect Username and Password", e);
        }
        
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());

        final String jwtToken = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwtToken));
    }

    @RequestMapping(value="/register", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@Valid @RequestBody User user, BindingResult result) throws Exception{
        // Expecting an User Model at authenticationRequest
        userValidator.validate(user, result);

        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if (errorMap != null)
            return errorMap;

        User newUser = userService.saveUser(user);
        // for (Role r : newUser.getRoles()) {
        // System.out.println(r.getRolename());
        // }
        // newUser.setConfirmPassword("");
        // newUser.setRoles(null);
        return new ResponseEntity<User>(newUser, HttpStatus.CREATED);
    }

}