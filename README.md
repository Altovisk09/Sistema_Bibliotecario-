
# Library Management System

Este é um projeto Java que simula o funcionamento básico de uma **biblioteca virtual**, com foco em **boas práticas de
programação**, **tratamento de exceções**, **organização de pacotes**, uso de **Enums**, e princípios frequentemente
adotados no mercado de trabalho.

---

## 🧠 Motivação

Criado com o intuito de praticar lógica, estruturação de projetos, e conceitos fundamentais como:

- Exceptions personalizadas
- Boas práticas e separação de responsabilidades (principais princípios SOLID)
- Organização de pacotes por domínio (Model, View, Service, Util, etc.)
- CRUD 
- Uso de Enums e controle de estado
- Interação via menu CLI
- Geração e simulação de dados

---

## 🚀 Como Executar

Este projeto foi desenvolvido usando Java **versão 23**.

- Clone o repositório ou baixe os arquivos.
- Compile o projeto com `javac`.
  - Execute a classe `Main.java` que chama o menu interativo via terminal.

```bash
javac com/library/Main.java
java com.library.Main
```

---

## 📁 Estrutura do Projeto

```
com.library
├── Main.java
├── exceptions/
├── model/
├── service/
├── util/
└── view/
```

---

## 📦 Pacotes e Componentes

### 🔹 `model`

Contém as **entidades principais** que representam os objetos de domínio do sistema:

- **Book**: Representa um livro (id, título, autor, categoria, disponibilidade).
- **Loan**: Registro de empréstimos (livro, usuário, status, datas).
- **User**: Representa um usuário (nome, tipo, livros emprestados).
- **Enums**:
- `BookCategory`: Ficção, Não Ficção, História, etc.
- `LoanStatus`: ATIVO, CONCLUIDO, ATRASADO.
- `UserType`: ESTUDANTE, PROFESSOR — com diferentes regras de empréstimo.

### 🔹 `exceptions`

Custom exceptions para lidar com cenários específicos:

- `BookUnavailableException`
- `IllegalDeletionException`
- `InvalidFieldException`
- `NotFoundException`
- `UserLoanLimitException`
- `UserNotEligibleForLoanException`

Essas exceções reforçam o uso de `try-catch` e promovem uma **lógica robusta e clara**.

### 🔹 `service`

Contém as **regras de negócio**:

- `LibraryService`: Métodos para empréstimos, devoluções, adição, busca e remoção de livros/usuários.
- `ReportService`: Gera relatórios de livros emprestados, usuários e status.

### 🔹 `util`

- `IdGenerator`: Gera identificadores únicos para livros e usuários.
- `DataSeeder`: Cria dados mockados para facilitar os testes.

### 🔹 `view`

- `RunMenu`: Interface interativa baseada em texto para interação com o sistema.

---

## 🧩 Funcionamento

### 1. Adicionar Livros/Usuários

- Pode ser feito automaticamente com `DataSeeder` ou manualmente via menu.
- Cada livro possui título, autor, categoria e status.
- Cada usuário possui nome e tipo (estudante/professor).

### 2. Empréstimos

- Usuários podem pegar livros emprestados com limites definidos por `UserType`.
- Livros devem estar disponíveis.
- O sistema registra datas e status do empréstimo.

### 3. Devoluções

- Ao devolver, o status do empréstimo muda para `CONCLUIDO`.
- Se estiver em atraso, pode ser tratado futuramente com regras adicionais.

### 4. Relatórios

- O `ReportService` imprime listas de livros por status, usuários e estatísticas básicas.

---

## 🧪 Exemplos de Uso

```java
User aluno = new User("João", UserType.ESTUDANTE);
Book livro = new Book("Java Essencial", "Autor Exemplo", BookCategory.PROGRAMACAO);
libraryService.borrowBook(aluno.getId(),livro.getId());
```

```java
reportService.printLoanedBooks();
reportService.printUsers();
```

---

---

### 🔄 Atualizações de Usuários e Livros

O projeto conta com os métodos `updateUser` e `updateBook` na classe `LibraryService`, permitindo alterações seletivas
de dados dos objetos. Esses métodos:

- São acessíveis tanto via **menu interativo** quanto por chamada direta em código.
- Aceitam **valores `null`** para preservar atributos que não serão modificados.
- Representam uma abordagem flexível e reutilizável para edição de entidades.

Exemplo de uso direto:

```java
libraryService.updateUser("user123","Novo Nome",null); // Apenas o nome será alterado
libraryService.updateBook("book456",null,"Novo Autor",null,3); // Atualiza autor e quantidade
```
--- 

## 🛠️ Melhorias Futuras

- Implementar persistência de dados (arquivo ou banco de dados).
- Adicionar interface gráfica (GUI) usando JavaFX.
- Implementar testes unitários com JUnit.
- Adicionar sistema de autenticação.
- Tratar datas de devolução e multas por atraso.
- Aplicação de testes unitários com JUnit.
- Adição de autenticação (login e permissão).
- Cálculo de multas e alertas de atraso.
- API REST com Spring Boot.

---

## 👤 Autor

Projeto desenvolvido para fins de estudo e prática de lógica e estrutura de software em Java.

---

## 📝 Licença

Este projeto é de uso livre para fins educacionais.

### Technologies

- Java 23
- Command-line interface
- Object-oriented structure
- Designed for educational purposes

