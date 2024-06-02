import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Sistema {
    private Biblioteca biblioteca;
    private List<Usuario> usuarios;
    private Usuario usuarioLogado;

    public Sistema() {
        biblioteca = new Biblioteca();
        usuarios = new ArrayList<>();
        // Adicionando alguns livros ao catálogo para testes
        biblioteca.adicionarLivro(new Livro("O Senhor dos Anéis", "J.R.R. Tolkien"));
        biblioteca.adicionarLivro(new Livro("Harry Potter", "J.K. Rowling"));

        // Adicionando um gerente padrão
        usuarios.add(new Gerente("1", "gerente", "1234"));
    }

    public void iniciar() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Login");
            System.out.println("2. Cadastrar novo usuário");
            System.out.println("3. Sair");
            int escolha = scanner.nextInt();
            scanner.nextLine(); // Consumir nova linha

            if (escolha == 1) {
                if (autenticarUsuario(scanner)) {
                    menuPrincipal(scanner);
                }
            } else if (escolha == 2) {
                cadastrarUsuario(scanner);
            } else if (escolha == 3) {
                System.out.println("Saindo...");
                return;
            } else {
                System.out.println("Opção inválida.");
            }
        }
    }

    private boolean autenticarUsuario(Scanner scanner) {
        System.out.println("Login: ");
        String login = scanner.nextLine();
        System.out.println("Senha: ");
        String senha = scanner.nextLine();

        usuarioLogado = null;
        for (Usuario usuario : usuarios) {
            if (usuario.getLogin().equals(login) && usuario.autenticar(senha)) {
                usuarioLogado = usuario;
                return true;
            }
        }

        System.out.println("Login ou senha incorretos.");
        return false;
    }

    private void cadastrarUsuario(Scanner scanner) {
        System.out.println("ID: ");
        String id = scanner.nextLine();
        System.out.println("Login: ");
        String login = scanner.nextLine();
        System.out.println("Senha: ");
        String senha = scanner.nextLine();

        Usuario novoUsuario = new UsuarioComum(id, login, senha);
        usuarios.add(novoUsuario);
        System.out.println("Usuário cadastrado com sucesso!");
    }

    private void menuPrincipal(Scanner scanner) {
        while (true) {
            usuarioLogado.menu();
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir nova linha

            if (usuarioLogado instanceof Gerente) {
                Gerente gerente = (Gerente) usuarioLogado;
                switch (opcao) {
                    case 1:
                        System.out.println("Título do Livro: ");
                        String titulo = scanner.nextLine();
                        System.out.println("Autor do Livro: ");
                        String autor = scanner.nextLine();
                        gerente.adicionarLivro(biblioteca, titulo, autor);
                        break;
                    case 2:
                        System.out.println("Título do Livro a remover: ");
                        titulo = scanner.nextLine();
                        gerente.removerLivro(biblioteca, titulo);
                        break;
                    case 3:
                        // Outras opções de gerente...
                        break;
                    case 4:
                        System.out.println("Saindo...");
                        return;
                    default:
                        System.out.println("Opção inválida.");
                }
            } else if (usuarioLogado instanceof UsuarioComum) {
                UsuarioComum usuarioComum = (UsuarioComum) usuarioLogado;
                switch (opcao) {
                    case 1:
                        usuarioComum.consultarCatalogo(biblioteca);
                        break;
                    case 2:
                        System.out.println("Título do Livro para empréstimo: ");
                        String titulo = scanner.nextLine();
                        usuarioComum.fazerEmprestimo(biblioteca, titulo);
                        break;
                    case 3:
                        // Outras opções de usuário comum...
                        break;
                    case 4:
                        System.out.println("Saindo...");
                        return;
                    default:
                        System.out.println("Opção inválida.");
                }
            }
        }
    }
}
