package com.bandg.users.exceptions.parsing;

import java.util.IllegalFormatException;

public class IllegalRowException extends IllegalArgumentException {
    public IllegalRowException(String message)
    {
            super(message);
    }
}
