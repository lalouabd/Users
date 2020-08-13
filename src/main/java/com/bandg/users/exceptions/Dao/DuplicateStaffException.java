package com.bandg.users.exceptions.Dao;

public class DuplicateStaffException extends InvalidStaffException{
    public DuplicateStaffException(String message)
    {
        super(message);
    }
}
