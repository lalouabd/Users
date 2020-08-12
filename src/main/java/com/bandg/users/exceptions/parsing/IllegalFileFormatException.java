package com.bandg.users.exceptions.parsing;

import java.io.IOException;

public class IllegalFileFormatException extends IllegalStateException{
    public IllegalFileFormatException(String message){
        super(message);
    }
}
