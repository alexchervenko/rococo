package io.student.rococo.test_data;

public class RegisterErrors {
    public static final String USERNAME_EXISTS = "Username `%s` already exists";
    public static final String USERNAME_IS_BLANK = "Username can not be blank";
    public static final String USERNAME_HAS_WHITESPACES = "Username must not contain whitespaces";
    public static final String USERNAME_OUT_OF_BOUNDS = "Allowed username length should be from 3 to 50 characters";
    public static final String PASSWORD_IS_BLANK = "Password can not be blank";
    public static final String PASSWORD_CONTAINS_WHITESPACES = "Password must not contain whitespaces";
    public static final String PASSWORD_OUT_OF_BOUNDS = "Allowed password length should be from 3 to 12 characters";
    public static final String PASSWORDS_ARE_NOT_EQUAL = "Passwords should be equal";
}
