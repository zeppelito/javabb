import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        SistemaDeLogin sistemaDeLogin = new SistemaDeLogin();
        CadastroDeUsuario cadastroDeUsuario = new CadastroDeUsuario(sistemaDeLogin);
        Scanner scanner = new Scanner(System.in);

        // Loop para testar o login e cadastro de usuário
        while (true) {
            System.out.println("Escolha uma opção: 1. Login 2. Cadastro 3. Sair");
            int escolha = scanner.nextInt();
            scanner.nextLine(); // consumir nova linha

            if (escolha == 1) {
                System.out.print("Digite o login: ");
                String login = scanner.nextLine();

                System.out.print("Digite a senha: ");
                String senha = scanner.nextLine();

                String resultado = sistemaDeLogin.acessarSistema(login, senha);
                System.out.println(resultado);
            } else if (escolha == 2) {
                System.out.print("Digite o login: ");
                String login = scanner.nextLine();

                System.out.print("Digite a senha: ");
                String senha = scanner.nextLine();

                String resultado = cadastroDeUsuario.cadastrarUsuario(login, senha);
                System.out.println(resultado);
            } else if (escolha == 3) {
                break;
            } else {
                System.out.println("Opção inválida. Tente novamente.");
            }
        }

        scanner.close();
    }
}
