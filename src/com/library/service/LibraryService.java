package com.library.service;

import com.library.exceptions.*;
import com.library.model.*;
import com.library.util.IdGenerator;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LibraryService {
    private List<Loan> loanList;
    private List<User> usersList;
    private List<Book> booksList;

    public LibraryService() {
        this.usersList = new ArrayList<>();
        this.booksList = new ArrayList<>();
        this.loanList = new ArrayList<>();
    }

    public List<Loan> getLoanList() {
        return loanList;
    }

    public void setLoanList(List<Loan> loanList) {
        this.loanList = loanList;
    }

    public List<User> getUsersList() {
        return usersList;
    }

    public void setUsersList(List<User> usersList) {
        this.usersList = usersList;
    }

    public List<Book> getBooksList() {
        return booksList;
    }

    public void setBooksList(List<Book> booksList) {
        this.booksList = booksList;
    }

    public void createUser(String name, UserType userType) {
        try {
            if (name == null || name.isBlank()) {
                throw new InvalidFieldException("Nome de usuário não pode estar vazio.");
            }
            if (userType == null) {
                throw new InvalidFieldException("Tipo de usuário não pode estar vazio.");
            }
            int newId = IdGenerator.getNextId(usersList, User::getId);
            User newUser = new User(newId, name, userType);
            usersList.add(newUser);
            System.out.println("Usuário criado com sucesso: " + newUser);
        } catch (InvalidFieldException e) {
            System.out.println("[ERROR] " + e.getMessage());
        }
    }

    public void RegisterBook(String name, String autorName, BookCategory category, int totalCopies) {
        try {
            if (name == null || name.isBlank() || autorName == null || autorName.isBlank() || category == null) {
                throw new InvalidFieldException("Informações básicas do livro não podem estar vazias.");
            }
            if (totalCopies <= 0) {
                throw new InvalidFieldException("É necessário ao menos uma unidade do livro para realizar o cadastro");
            }
            //metodo para validar se o livro já existe

            int newId = IdGenerator.getNextId(booksList, Book::getId);
            Book newBook = new Book(newId, name, autorName, category, totalCopies);
            booksList.add(newBook);
            System.out.println("Livro \"" + newBook.getName() + "\" cadastrado com sucesso.");

        } catch (InvalidFieldException e) {
            System.out.println("[ERROR] " + e.getMessage());
        }
    }

    public void RegisterLoan(User user, Book book) {
        try {
            if (user == null) {
                throw new InvalidFieldException("Usuário não pode ser nulo.");
            }

            if (user.getUserType() == null) {
                throw new InvalidFieldException("Tipo de usuário não definido.");
            }

            if (book == null) {
                throw new InvalidFieldException("Livro não pode ser nulo.");
            }
            //Verifica se o limite de livros emprestados foi atingido e se a alguma devolução pendente
            List<Loan> userLoanList = activeLoansByUser(user);
            overdueBooksValidation(user);
            checkLoanLimit(userLoanList, user);

            int newId = IdGenerator.getNextId(loanList, Loan::getId);
            int borrowedDays = user.getUserType().getBorrowedDays();
            LocalDate startDate = LocalDate.now();
            LocalDate finalDate = startDate.plusDays(borrowedDays);
            LoanStatus status = LoanStatus.ACTIVE;

            book.lendBook();

            Loan newLoan = new Loan(newId, user, book, startDate, finalDate, status);
            loanList.add(newLoan);

            System.out.println("Empréstimo registrado com sucesso para: " + user.getName());

        } catch (InvalidFieldException | BookUnavailableException | UserNotEligibleForLoanException |
                 UserLoanLimitException e) {
            System.out.println("[ERRO] " + e.getMessage());
        }
    }

    public void listUsers() {
        if (!usersList.isEmpty()) {
            for (User user : usersList) {
                System.out.println(user);
            }
        }
        System.out.println("Sem usuários cadastrados");
    }

    public User findUserById(Integer id) throws NotFoundException, InvalidFieldException {
        if (id == null) {
            throw new InvalidFieldException("Campo Id deve ser preenchido");
        }

        return usersList.stream().filter(user -> user.getId() == id).findFirst().orElseThrow(() -> new NotFoundException("Usuário não encontrado."));

    }

    public User findUserByIdSafe(Integer id) {
        try {
            if (id == null) {
                throw new InvalidFieldException("Campo Id deve ser preenchido");
            }

            return usersList.stream().filter(user -> user.getId() == id).findFirst().orElseThrow(() -> new NotFoundException("Usuário não encontrado."));
        } catch (InvalidFieldException | NotFoundException e) {
            System.out.println("[ERROR]: " + e.getMessage());
            return null;
        }
    }

    private List<Loan> activeLoansByUser(User user) {
        return loanList.stream().filter(loan -> loan.getUser().equals(user) && loan.getStatus() == LoanStatus.ACTIVE).toList();
    }

    private List<Loan> userLateReturns(User user) {
        List<Loan> userLoanList = activeLoansByUser(user);
        return userLoanList.stream().filter(loan -> loan.getFinalDate().isBefore(LocalDate.now())).toList();
    }

    private void overdueBooksValidation(User user) throws UserNotEligibleForLoanException {
        List<Loan> lateReturnsList = userLateReturns(user);
        if (lateReturnsList.isEmpty()) {
            return;
        }
        String message = lateReturnsList.stream().map(loan -> String.format("%s (previsto: %s)", loan.getBook().getName(), loan.getFinalDate())).collect(Collectors.joining("\n"));

        throw new UserNotEligibleForLoanException("Usuário: " + user.getName() + "possui" + lateReturnsList.size() + "\n" + message);
    }

    private void checkLoanLimit(List<Loan> list, User user) throws UserLoanLimitException {
        int loanLimit = user.getUserType().getLoanLimit();
        int currentLoanActive = list.size();

        if (loanLimit >= currentLoanActive) {
            throw new UserLoanLimitException("Limite de %d empréstimos simultaneos foi atingido, devolva algum dos livros em sua posse para pegar outro.");
        }
    }

    public void updateUser(int userId, String userName, UserType type) {
        try {
            User user = findUserById(userId);
            boolean noNameProvided = (userName == null || userName.isBlank());
            boolean noTypeProvided = (type == null);
            if (noNameProvided && noTypeProvided) {
                throw new InvalidFieldException("Novos dados para atualização não informados.");
            }

            if (!noNameProvided) {
                user.setName(userName);
                System.out.println("Nome de usuário alterado para: " + userName);
            }
            if (!noTypeProvided) {
                user.setUserType(type);
                System.out.println("Tipo da conta alterada para: " + type.getDisplayName());
            }
        } catch (NotFoundException | InvalidFieldException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteUser(int userId) {
        try {
            User findUser = findUserById(userId);
            List<Loan> activeLoans = activeLoansByUser(findUser);
            if (!activeLoans.isEmpty()) {
                String loanListMessage = activeLoans.stream().map(loan -> String.format("- \"%s\" (previsão de devolução: %s)", loan.getBook().getName(), loan.getFinalDate())).collect(Collectors.joining("\n"));
                throw new IllegalDeletionException("Usuário possui devoluções pendentes: \n" + loanListMessage);
            }

            usersList.remove(findUser);
            System.out.println("Usuário \"" + findUser.getName() + "\" removido com sucesso.");
        } catch (NotFoundException | InvalidFieldException | IllegalDeletionException e) {
            System.out.println("[ERRO]: " + e.getMessage());
        }
    }
}



