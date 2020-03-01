package com.bohniman.cid.appapi.payload;

/**
 * AuthenticationResponse
 */
public class AuthenticationResponse {

    private final String jwt;


    public AuthenticationResponse(String jwt) {
        this.jwt = jwt;
    }

    public String getJwt() {
        return this.jwt;
    }

}