package com.bandg.users.exceptions.parsing;

import java.io.IOException;

public class IllegalFileException  extends IOException {
    public IllegalFileException(String message){
        super(message);
    }
}
