package com.library.view;

import com.library.model.BookCategory;
import com.library.model.UserType;
import com.library.service.LibraryService;
import com.library.service.ReportService;

import java.util.Scanner;

public class RunMenu {
    private final Scanner scanner = new Scanner(System.in);
    private final LibraryService libraryService;
    private final ReportService reportService;

    public RunMenu(LibraryService libraryService, ReportService reportService) {
        this.libraryService = libraryService;
        this.reportService = reportService;
    }

    public void start() {
        int option;

        do {
            printMainMenu();
            option = readInt("Escolha uma opção: ");

            switch (option) {
                case 1 -> cadastrarUsuario();
                case 2 -> cadastrarLivro();
                case 3 -> libraryService.listUsers();
                case 4 -> libraryService.listBooks();
                case 5 -> libraryService.borrowBooksInteractive();
                case 6 -> libraryService.returnBooksByUserInteractive();
                case 7 -> atualizarUsuario();
                case 8 -> atualizarLivro();
                case 9 -> excluirUsuario();
                case 10 -> excluirLivro();
                case 11 -> buscarLivro();
                case 12 -> mostrarRelatorios();
                case 0 -> System.out.println("Saindo...");
                default -> System.out.println("Opção inválida. Tente novamente.");
            }

        } while (option != 0);
    }

    private void printMainMenu() {
        System.out.println("\n===== MENU BIBLIOTECA =====");
        System.out.println("1. Cadastrar usuário");
        System.out.println("2. Cadastrar livro");
        System.out.println("3. Listar usuários");
        System.out.println("4. Listar livros");
        System.out.println("5. Realizar empréstimo");
        System.out.println("6. Devolver livro");
        System.out.println("7. Atualizar usuário");
        System.out.println("8. Atualizar livro");
        System.out.println("9. Excluir usuário");
        System.out.println("10. Excluir livro");
        System.out.println("11. Buscar livro por nome");
        System.out.println("12. Ver relatórios e estatísticas 📊");
        System.out.println("0. Sair");
    }

    private void cadastrarUsuario() {
        System.out.print("Nome do usuário: ");
        String nome = scanner.nextLine().trim();

        UserType[] tipos = UserType.values();
        System.out.println("Tipos disponíveis:");
        for (int i = 0; i < tipos.length; i++) {
            System.out.printf("%d - %s (%s)%n", i + 1, tipos[i].name(), tipos[i].getDisplayName());
        }

        int escolha = readInt("Escolha o número correspondente ao tipo: ");
        if (escolha < 1 || escolha > tipos.length) {
            System.out.println("Tipo inválido. Cadastro cancelado.");
            return;
        }

        UserType type = tipos[escolha - 1];
        libraryService.createUser(nome, type);
    }

    private void cadastrarLivro() {
        System.out.print("Nome do livro: ");
        String nome = scanner.nextLine().trim();
        System.out.print("Nome do autor: ");
        String autor = scanner.nextLine().trim();

        BookCategory[] categorias = BookCategory.values();
        System.out.println("Categorias disponíveis:");
        for (int i = 0; i < categorias.length; i++) {
            System.out.printf("%d - %s (%s)%n", i + 1, categorias[i].name(), categorias[i].getName());
        }

        int escolha = readInt("Escolha o número correspondente à categoria: ");
        if (escolha < 1 || escolha > categorias.length) {
            System.out.println("Categoria inválida. Cadastro cancelado.");
            return;
        }

        BookCategory categoria = categorias[escolha - 1];
        int copias = readInt("Número total de cópias: ");
        libraryService.registerBook(nome, autor, categoria, copias);
    }

    private void atualizarUsuario() {
        int id = readInt("Digite o ID do usuário: ");
        System.out.print("Novo nome (deixe em branco para não alterar): ");
        String nome = scanner.nextLine().trim();

        UserType[] tipos = UserType.values();
        System.out.println("Tipos disponíveis:");
        for (int i = 0; i < tipos.length; i++) {
            System.out.printf("%d - %s (%s)%n", i + 1, tipos[i].name(), tipos[i].getDisplayName());
        }

        System.out.print("Escolha o número correspondente ao novo tipo (ou ENTER para manter): ");
        String tipoInput = scanner.nextLine().trim();
        UserType type = null;

        if (!tipoInput.isBlank()) {
            try {
                int escolha = Integer.parseInt(tipoInput);
                if (escolha >= 1 && escolha <= tipos.length) {
                    type = tipos[escolha - 1];
                } else {
                    System.out.println("Tipo inválido. Nenhuma mudança feita.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Nenhuma mudança feita.");
            }
        }

        libraryService.updateUser(id, nome.isBlank() ? null : nome, type);
    }

    private void atualizarLivro() {
        int id = readInt("Digite o ID do livro: ");
        System.out.print("Novo nome (ou ENTER para manter): ");
        String nome = scanner.nextLine().trim();
        System.out.print("Novo autor (ou ENTER para manter): ");
        String autor = scanner.nextLine().trim();

        BookCategory[] categorias = BookCategory.values();
        System.out.println("Categorias disponíveis:");
        for (int i = 0; i < categorias.length; i++) {
            System.out.printf("%d - %s (%s)%n", i + 1, categorias[i].name(), categorias[i].getName());
        }

        System.out.print("Escolha o número da nova categoria (ou ENTER para manter): ");
        String catInput = scanner.nextLine().trim();
        BookCategory cat = null;

        if (!catInput.isBlank()) {
            try {
                int escolha = Integer.parseInt(catInput);
                if (escolha >= 1 && escolha <= categorias.length) {
                    cat = categorias[escolha - 1];
                } else {
                    System.out.println("Categoria inválida.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida.");
            }
        }

        int copias = readInt("Novo total de cópias (0 para manter): ");
        libraryService.updateBook(id, nome.isBlank() ? null : nome, autor.isBlank() ? null : autor, cat, copias);
    }

    private void excluirUsuario() {
        int id = readInt("Digite o ID do usuário: ");
        libraryService.deleteUser(id);
    }

    private void excluirLivro() {
        int id = readInt("Digite o ID do livro: ");
        libraryService.deleteBook(id);
    }

    private void buscarLivro() {
        libraryService.searchBookByNameInteractive();
    }

    private void mostrarRelatorios() {
        int op;
        do {
            System.out.println("\n--- RELATÓRIOS E ESTATÍSTICAS ---");
            System.out.println("1. Estatísticas gerais 📈");
            System.out.println("2. Usuários com empréstimos ativos 👤");
            System.out.println("3. Empréstimos atrasados 🚨");
            System.out.println("4. Top 5 livros mais emprestados 🔝");
            System.out.println("0. Voltar");

            op = readInt("Escolha uma opção: ");
            switch (op) {
                case 1 -> reportService.printStatistics();
                case 2 -> reportService.listUsersWithActiveLoans();
                case 3 -> reportService.listOverdueLoans();
                case 4 -> reportService.listMostBorrowedBooks();
                case 0 -> System.out.println("Voltando ao menu principal...");
                default -> System.out.println("Opção inválida");
            }
        } while (op != 0);
    }

    private int readInt(String msg) {
        while (true) {
            try {
                System.out.print(msg);
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Digite um número válido.");
            }
        }
    }
}
