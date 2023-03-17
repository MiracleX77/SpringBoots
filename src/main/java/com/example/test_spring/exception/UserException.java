package com.example.test_spring.exception;

public class UserException extends BaseException {
    public UserException(String code) {
        super("user." + code);
    }

    public static UserException requestNull() {
        return new UserException("register.request.null");
    }

    public static UserException emailNull() {
        return new UserException("register.email.null");
    }

    public static UserException notFound() {
        return new UserException("not.found");
    }

    public static UserException unAuthorized() {
        return new UserException("unauthorized");
    }




// CREATE

    public static UserException createEmailNull() {
        return new UserException("create.email.null");
    }

    public static UserException createPasswordNull() {
        return new UserException("create.password.null");
    }

    public static UserException createNameNull() {
        return new UserException("create.name.null");
    }

    // VERIFY

    public static  UserException verifyEmail(){
        return  new UserException("verify.email");
    }

    //LOGIN

    public static UserException loginEmailNotFound() {
        return new UserException("login.email.not.found1");
    }

    public static UserException loginPasswordIncorrect() {
        return new UserException("login.password.incorrect");
    }

}
