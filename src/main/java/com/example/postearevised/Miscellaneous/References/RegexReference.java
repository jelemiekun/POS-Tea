package com.example.postearevised.Miscellaneous.References;

public class RegexReference {
    public static final String REGEX_EMAIL = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.(com|org|net|edu|gov|mil|co\\.uk|ph)$";
    public static final String REGEX_PHONE_NUMBER = "^09\\d{9}$";
    public static final String REGEX_NAME = "^[a-zA-Z ]+$";
    public static final String REGEX_FIRST_SIX_ARE_NUMBERS = "^\\d{6}.*";
    public static final String REGEX_DIGITS_ONLY = "\\d*";
    public static final String REGEX_ENGLISH_ALPHABET_ONLY = "[A-Za-z ]*";
}
