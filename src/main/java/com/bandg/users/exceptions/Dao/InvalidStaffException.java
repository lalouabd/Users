package com.bandg.users.exceptions.Dao;

public class InvalidStaffException extends IllegalArgumentException {
    public InvalidStaffException(String message)
    {
        super(message);
    }
}
