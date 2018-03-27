package com.chat.server.Translation;

/**
 *
 * @author kari
 */
public class InputTrimmer {

    public String trim(String input) {
        if (input.substring(input.length() - 1, input.length()).equals("\n")) {
            input = input.substring(0, input.length() - 1);
        }
        return input;
    }
}
