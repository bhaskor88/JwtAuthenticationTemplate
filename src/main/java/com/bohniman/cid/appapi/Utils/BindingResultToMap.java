package com.bohniman.cid.appapi.Utils;

import java.util.HashMap;
import java.util.Map;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

/**
 * BindingResultToMap
 */
public class BindingResultToMap {

    public static Map<String, String> convertBindingResultToMap(BindingResult bindingResult) {
        Map<String, String> errors = new HashMap<String, String>();
        for (Object object : bindingResult.getAllErrors()) {
            if (object instanceof FieldError) {
                FieldError fieldError = (FieldError) object;
                errors.put(fieldError.getField(), fieldError.getDefaultMessage());
            }
        }
        return errors;
    }

}
