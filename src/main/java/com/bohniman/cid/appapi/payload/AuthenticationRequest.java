package com.bohniman.cid.appapi.payload;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * AuthenticationRequest
 */
@Data
@NoArgsConstructor
public class AuthenticationRequest {

    private String username;
    private String password;

}