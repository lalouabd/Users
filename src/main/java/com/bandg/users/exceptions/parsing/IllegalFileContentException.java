package com.bandg.users.exceptions.parsing;

import java.io.IOException;

public class IllegalFileContentException  extends IOException {
    public IllegalFileContentException(String message)
    {
        super(message);
    }
}
