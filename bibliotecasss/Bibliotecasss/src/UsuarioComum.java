public class UsuarioComum extends Usuario {
    public UsuarioComum(String id, String login, String senha) {
        super(id, login, senha);
    }

    @Override
    public void menu() {
        System.out.println("1. Consultar Catálogo");
        System.out.println("2. Fazer Empréstimo");
        System.out.println("3. Fazer Reserva");
        System.out.println("4. Sair");
        // Implementação das funcionalidades aqui...
    }

    public void consultarCatalogo(Biblioteca biblioteca) {
        biblioteca.listarLivros();
    }

    public void fazerEmprestimo(Biblioteca biblioteca, String titulo) {
        biblioteca.emprestarLivro(this, titulo);
    }

    // Outros métodos de usuário comum...
}
