package com.library;

import com.library.model.UserType;
import com.library.service.LibraryService;

public class Main {
    public static void main(String[] args){
        LibraryService service = new LibraryService();
        service.createUser("Eric", UserType.TEACHER);
        service.createUser("Eric", UserType.STUDENT_GRADUATION);
        service.createUser("Eric", UserType.STUDENT_POSTGRADUATION);
        service.listUsers();
        service.findUserByIdSafe(1);
    }
}
