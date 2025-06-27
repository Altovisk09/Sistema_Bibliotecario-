package com.library.util;

import com.library.model.*;
import com.library.service.*;
import java.util.List;
import java.util.Random;

public class DataSeeder {

    public static void seed(LibraryService service) {
        service.createUser("Alice Martins", UserType.STUDENT_GRADUATION);
        service.createUser("Bruno Oliveira", UserType.STUDENT_GRADUATION);
        service.createUser("Carla Fernandes", UserType.STUDENT_POSTGRADUATION);
        service.createUser("Diego Souza", UserType.TEACHER);
        service.createUser("Eduarda Lima", UserType.STUDENT_GRADUATION);
        service.createUser("Felipe Alves", UserType.STUDENT_POSTGRADUATION);
        service.createUser("Giovana Pereira", UserType.TEACHER);
        service.createUser("Henrique Rocha", UserType.STUDENT_GRADUATION);
        service.createUser("Isabela Costa", UserType.TEACHER);
        service.createUser("João Silva", UserType.STUDENT_POSTGRADUATION);
        service.createUser("Cinthia Araújo", UserType.STUDENT_GRADUATION);
        service.createUser("Lidiane Cardoso", UserType.STUDENT_GRADUATION);
        service.createUser("Carlos Nascimento", UserType.STUDENT_POSTGRADUATION);
        service.createUser("Mariana Alves", UserType.STUDENT_GRADUATION);
        service.createUser("Felipe Santos", UserType.STUDENT_POSTGRADUATION);
        service.createUser("Giovana Rocha", UserType.TEACHER);
        service.createUser("Henrique Dias", UserType.STUDENT_GRADUATION);
        service.createUser("Isabela Martins", UserType.TEACHER);
        service.createUser("João Fernandes", UserType.STUDENT_POSTGRADUATION);
        service.createUser("Larissa Gomes", UserType.STUDENT_GRADUATION);
        service.createUser("Alice Martins", UserType.STUDENT_GRADUATION);
        service.createUser("Bruno Oliveira", UserType.STUDENT_GRADUATION);
        service.createUser("Carla Fernandes", UserType.STUDENT_POSTGRADUATION);
        service.createUser("Diego Souza", UserType.TEACHER);
        service.createUser("Eduarda Lima", UserType.STUDENT_GRADUATION);
        service.createUser("Felipe Alves", UserType.STUDENT_POSTGRADUATION);
        service.createUser("Giovana Pereira", UserType.TEACHER);
        service.createUser("Henrique Rocha", UserType.STUDENT_GRADUATION);
        service.createUser("Isabela Costa", UserType.TEACHER);
        service.createUser("João Silva", UserType.STUDENT_POSTGRADUATION);
        service.createUser("Cinthia Araújo", UserType.STUDENT_GRADUATION);
        service.createUser("Lidiane Cardoso", UserType.STUDENT_GRADUATION);
        service.createUser("Carlos Nascimento", UserType.STUDENT_POSTGRADUATION);
        service.createUser("Mariana Alves", UserType.STUDENT_GRADUATION);
        service.createUser("Elias Eric", UserType.STUDENT_POSTGRADUATION);
        service.createUser("Giovana Rocha", UserType.TEACHER);
        service.createUser("Henrique Dias", UserType.STUDENT_GRADUATION);
        service.createUser("Isabela Martins", UserType.TEACHER);
        service.createUser("João Fernandes", UserType.STUDENT_POSTGRADUATION);
        service.createUser("Eric Elias", UserType.STUDENT_GRADUATION);

        String[] autores = {"Martin Fowler", "Robert C. Martin", "J.K. Rowling", "Isaac Asimov", "Stephen King", "Carl Sagan", "Machado de Assis", "Sun Tzu", "Platão", "Clarice Lispector"};
        service.registerBook("Clean Code", autores[1], BookCategory.TECHNOLOGY, 3);
        service.registerBook("Código Limpo", autores[1], BookCategory.TECHNOLOGY, 3);
        service.registerBook("Refactoring", autores[0], BookCategory.TECHNOLOGY, 3);
        service.registerBook("Design Patterns", "GoF", BookCategory.TECHNOLOGY, 3);
        service.registerBook("The Pragmatic Programmer", "Andrew Hunt", BookCategory.TECHNOLOGY, 3);
        service.registerBook("Structure and Interpretation of Computer Programs", "Sussman", BookCategory.TECHNOLOGY, 2);
        service.registerBook("Artificial Intelligence", "Stuart Russell", BookCategory.TECHNOLOGY, 2);
        service.registerBook("Clean Architecture", autores[1], BookCategory.TECHNOLOGY, 3);
        service.registerBook("Domain-Driven Design", "Eric Evans", BookCategory.TECHNOLOGY, 2);
        service.registerBook("You Don’t Know JS", "Kyle Simpson", BookCategory.TECHNOLOGY, 3);
        service.registerBook("Java Concurrency in Practice", "Brian Goetz", BookCategory.TECHNOLOGY, 2);
        service.registerBook("Effective Java", "Joshua Bloch", BookCategory.TECHNOLOGY, 3);
        service.registerBook("The Mythical Man-Month", "Fred Brooks", BookCategory.TECHNOLOGY, 2);
        service.registerBook("The Art of Computer Programming", "Donald Knuth", BookCategory.TECHNOLOGY, 1);
        service.registerBook("Introduction to Algorithms", "CLRS", BookCategory.TECHNOLOGY, 2);
        service.registerBook("Python Crash Course", "Eric Matthes", BookCategory.TECHNOLOGY, 2);
        service.registerBook("Eloquent JavaScript", "Marijn Haverbeke", BookCategory.TECHNOLOGY, 2);
        service.registerBook("Head First Java", "Kathy Sierra", BookCategory.TECHNOLOGY, 2);
        service.registerBook("Linux Bible", "Christopher Negus", BookCategory.TECHNOLOGY, 2);
        service.registerBook("Clean Agile", autores[1], BookCategory.TECHNOLOGY, 2);
        service.registerBook("Game Programming Patterns", "Robert Nystrom", BookCategory.TECHNOLOGY, 2);
        service.registerBook("Homo Deus", "Yuval Noah Harari", BookCategory.TECHNOLOGY, 1);
        service.registerBook("Neuromancer", "William Gibson", BookCategory.FICTION, 2);
        service.registerBook("Snow Crash", "Neal Stephenson", BookCategory.FICTION, 2);
        service.registerBook("The Matrix and Philosophy", "William Irwin", BookCategory.PHILOSOPHY, 1);
        service.registerBook("Hackers: Heroes of the Computer Revolution", "Steven Levy", BookCategory.BIOGRAPHY, 2);
        service.registerBook("Steve Jobs", "Walter Isaacson", BookCategory.BIOGRAPHY, 2);
        service.registerBook("The Phoenix Project", "Gene Kim", BookCategory.TECHNOLOGY, 2);
        service.registerBook("Soft Skills", "John Sonmez", BookCategory.SELF_HELP, 2);
        service.registerBook("How Linux Works", "Brian Ward", BookCategory.TECHNOLOGY, 2);
        service.registerBook("Automate the Boring Stuff with Python", "Al Sweigart", BookCategory.TECHNOLOGY, 3);
        service.registerBook("The DevOps Handbook", "Gene Kim", BookCategory.TECHNOLOGY, 2);
        service.registerBook("Continuous Delivery", "Jez Humble", BookCategory.TECHNOLOGY, 2);
        service.registerBook("Lean Startup", "Eric Ries", BookCategory.SELF_HELP, 2);
        service.registerBook("Cracking the Coding Interview", "Gayle Laakmann", BookCategory.EDUCATION, 3);
        service.registerBook("Compilers: Principles, Techniques, and Tools", "Aho", BookCategory.TECHNOLOGY, 1);
        service.registerBook("Agile Software Development", autores[1], BookCategory.TECHNOLOGY, 2);
        service.registerBook("The Effective Engineer", "Edmond Lau", BookCategory.TECHNOLOGY, 2);
        service.registerBook("Site Reliability Engineering", "Google", BookCategory.TECHNOLOGY, 2);
        service.registerBook("The Unicorn Project", "Gene Kim", BookCategory.FICTION, 1);
        service.registerBook("The Lean Programmer", "Bob Martin", BookCategory.SELF_HELP, 2);
        service.registerBook("Designing Data-Intensive Applications", "Martin Kleppmann", BookCategory.TECHNOLOGY, 2);
        service.registerBook("Programming Pearls", "Jon Bentley", BookCategory.TECHNOLOGY, 1);
        service.registerBook("Test-Driven Development", "Kent Beck", BookCategory.TECHNOLOGY, 2);
        service.registerBook("Extreme Programming Explained", "Kent Beck", BookCategory.TECHNOLOGY, 2);
        service.registerBook("JavaScript: The Good Parts", "Douglas Crockford", BookCategory.TECHNOLOGY, 2);
        service.registerBook("Linux Command Line", "William Shotts", BookCategory.TECHNOLOGY, 2);
        service.registerBook("The Pragmatic Programmer (2ª edição)", "Hunt & Thomas", BookCategory.TECHNOLOGY, 2);
        service.registerBook("A República", "Platão", BookCategory.PHILOSOPHY, 2);
        service.registerBook("Arte da Guerra", "Sun Tzu", BookCategory.PHILOSOPHY, 2);
        service.registerBook("Cosmos", "Carl Sagan", BookCategory.SCIENCE, 2);
        service.registerBook("Uma Breve História do Tempo", "Stephen Hawking", BookCategory.SCIENCE, 2);
        service.registerBook("O Mundo de Sofia", "Jostein Gaarder", BookCategory.EDUCATION, 2);
        service.registerBook("Meditações", "Marco Aurélio", BookCategory.PHILOSOPHY, 2);
        service.registerBook("Dom Casmurro", "Machado de Assis", BookCategory.LITERATURE, 2);
        service.registerBook("Quincas Borba", "Machado de Assis", BookCategory.LITERATURE, 2);
        service.registerBook("Grande Sertão: Veredas", "Guimarães Rosa", BookCategory.LITERATURE, 2);
        service.registerBook("Vidas Secas", "Graciliano Ramos", BookCategory.LITERATURE, 2);
        service.registerBook("Steve Jobs", "Walter Isaacson", BookCategory.BIOGRAPHY, 2);
        service.registerBook("Elon Musk", "Ashlee Vance", BookCategory.BIOGRAPHY, 2);
        service.registerBook("O Diário de Anne Frank", "Anne Frank", BookCategory.HISTORY, 2);
        service.registerBook("Os Sertões", "Euclides da Cunha", BookCategory.HISTORY, 2);
        service.registerBook("Pai Rico, Pai Pobre", "Robert Kiyosaki", BookCategory.SELF_HELP, 2);
        service.registerBook("Mindset", "Carol Dweck", BookCategory.SELF_HELP, 2);
        service.registerBook("A Cabana", "William Young", BookCategory.FICTION, 2);
        service.registerBook("O Pequeno Príncipe", "Antoine de Saint-Exupéry", BookCategory.FICTION, 2);
        service.registerBook("A Arte da Felicidade", "Dalai Lama", BookCategory.SELF_HELP, 2);
        service.registerBook("História da Filosofia", "Bertrand Russell", BookCategory.PHILOSOPHY, 2);
        service.registerBook("O Gene Egoísta", "Richard Dawkins", BookCategory.SCIENCE, 2);
        service.registerBook("Como as Democracias Morrem", "Levitsky", BookCategory.HISTORY, 2);
        service.registerBook("O Livro dos Espíritos", "Allan Kardec", BookCategory.EDUCATION, 2);
        service.registerBook("A Revolução dos Bichos", "George Orwell", BookCategory.FICTION, 2);
        service.registerBook("1984", "George Orwell", BookCategory.FICTION, 2);

        List<User> users = service.getUsersList();
        List<Book> books = service.getBooksList();
        Random random = new Random();

        for (User user : users) {
            Book bookToLoan = null;
            // tenta encontrar um livro com estoque disponível
            for (int i = 0; i < books.size(); i++) {
                Book candidate = books.get(random.nextInt(books.size()));
                if (candidate.getAvailableCopies() > 0) {
                    bookToLoan = candidate;
                    break;
                }
            }
            if (bookToLoan != null) {
                service.registerLoan(user, bookToLoan);
            } else {
                System.out.println("Nenhum livro disponível para empréstimo para o usuário: " + user.getName());
            }
        }

        System.out.println("✅ Dados de exemplo inseridos com sucesso!");
    }
}
