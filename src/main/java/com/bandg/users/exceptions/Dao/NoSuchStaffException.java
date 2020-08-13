package com.bandg.users.exceptions.Dao;

import java.util.NoSuchElementException;

public class NoSuchStaffException extends NoSuchElementException {
    public NoSuchStaffException(String message)
    {
        super(message);
    }
}
