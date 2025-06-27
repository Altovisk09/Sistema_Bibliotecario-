package com.library;

import com.library.model.User;
import com.library.model.UserType;
import com.library.service.LibraryService;
import com.library.model.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        LibraryService service = new LibraryService();
        // Criar usuários
        service.createUser("João da Silva", UserType.STUDENT_GRADUATION);
        service.createUser("Maria Oliveira", UserType.TEACHER);
        service.createUser("Carlos Souza", UserType.STUDENT_POSTGRADUATION);

        // Criar livros
        service.RegisterBook("O Senhor dos Anéis", "J.R.R. Tolkien", BookCategory.FICTION, 3);
        service.RegisterBook("Dom Casmurro", "Machado de Assis", BookCategory.LITERATURE, 2);
        service.RegisterBook("Clean Code", "Robert C. Martin", BookCategory.TECHNOLOGY, 5);
        service.RegisterBook("1984", "George Orwell", BookCategory.FICTION, 4);

        // Pegar instâncias dos objetos criados
        User joao = service.getUsersList().get(0);
        User maria = service.getUsersList().get(1);
        User carlos = service.getUsersList().get(2);

        Book lotr = service.getBooksList().get(0);       // O Senhor dos Anéis
        Book domCasmurro = service.getBooksList().get(1);
        Book cleanCode = service.getBooksList().get(2);
        Book book1984 = service.getBooksList().get(3);

        // Criar alguns empréstimos
        service.RegisterLoan(joao, lotr);
        service.RegisterLoan(joao, domCasmurro);
        service.RegisterLoan(maria, cleanCode);
        service.RegisterLoan(carlos, lotr);
        service.RegisterLoan(carlos, book1984);

        // TESTAR DEVOLUÇÃO
        service.returnBooksByUserInteractive();
        //Funcionou :D
    }
}
