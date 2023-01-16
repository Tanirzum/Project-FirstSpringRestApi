package com.tanirbergen.FirstProjectRestApi.Util;

public class PersonNotFoundValidator extends RuntimeException {
    public PersonNotFoundValidator(String message) {
        super(message);
    }
}
