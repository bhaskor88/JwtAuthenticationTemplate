package com.bohniman.cid.appapi.Utils;

import com.bohniman.cid.appapi.model.User;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * UserValidator
 */
@Component
public class UserValidator implements Validator {

    private static final String PASSWORD_PATTERN = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%&*()_-+=\"\']).{6,20})";
    /*
     * Atleast one number, lowercase, uppercase, special[@#$%&*()_-+="']
     */

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        // User user = (User) target;
        // if (!user.getPassword().matches(PASSWORD_PATTERN)) {
        //     errors.rejectValue("password", "Match", "Password should contain one number, one lowecase & uppercase alphabet, one special character");
        // }
        // if (!user.getPassword().equals(user.getConfirmPassword())) {
        //     errors.rejectValue("confirmPassword", "Match", "Password and confirm password doesn't match");
        // }
    }
}