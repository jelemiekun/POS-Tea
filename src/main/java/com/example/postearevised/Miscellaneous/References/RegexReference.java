package com.example.postearevised.Miscellaneous.References;

public class RegexReference {
    public static final String REGEX_EMAIL = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.(com|org|net|edu|gov|mil|co\\.uk|ph)$";
    public static final String REGEX_PHONE_NUMBER = "^09\\d{9}$";
    public static final String REGEX_NAME = "^[a-zA-Z ]+$";
    public static final String REGEX_FIRST_SIX_ARE_NUMBERS = "^\\d{6}.*";
    public static final String REGEX_DIGITS_ONLY = "\\d*";
    public static final String REGEX_DIGITS_ONLY_NO_LEADING_ZERO = "^(?:[1-9][0-9]*)?$";
    public static final String REGEX_ENGLISH_ALPHABET_ONLY = "[A-Za-z ]*";
    public static final String REGEX_NAME_16_CHAR_NO_SPACE_IN_FRONT_NO_NUMBERS =  "^[A-Za-z][A-Za-z ]{0,15}$";
}
