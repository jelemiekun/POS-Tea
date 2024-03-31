package com.example.postearevised.Miscellaneous.References;

public class RegexReference {
    public static final String REGEX_EMAIL = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.(com|org|net|edu|gov|mil|co\\.uk|ph)$";
    public static final String REGEX_PHONE_NUMBER = "^09\\d{9}$";
    public static final String REGEX_FIRST_SIX_ARE_NUMBERS = "^\\d{6}.*";
    public static final String REGEX_DIGITS_ONLY = "\\d*";
    public static final String REGEX_DIGITS_ONLY_NO_LEADING_ZERO = "^(?:[1-9][0-9]*)?$";
    public static final String REGEX_ENGLISH_ALPHABET_ONLY = "[A-Za-z ]*";
    public static final String REGEX_ENGLISH_ALPHABET_ONLY_NO_SPACE_IN_FRONT = "^(?!\\s)[A-Za-z\\s]*$";
    public static final String REGEX_NAME_16_CHAR_NO_SPACE_IN_FRONT_NO_NUMBERS =  "^[A-Za-z][A-Za-z ]{0,15}$";
    public static final String REGEX_NAME_200_CHAR_NO_SPACE_IN_FRONT_NO_NUMBERS =  "^[A-Za-z][A-Za-z ]{0,199}$";
    public static final String REGEX_ORDER_HISTORY_SEARCH_256_LIMIT_NO_SPACE_IN_FRONT_NO_SPECIAL_CHARACTERS = "^(?!\\s)[a-zA-Z ]{1,256}$";
    public static final String REGEX_CAN_CONTAIN_ANYTHING_EXCEPT_SPACE_FIRST_CHARACTER_IS_NOT_ALLOWED = "^(?!\\s).*";

}
