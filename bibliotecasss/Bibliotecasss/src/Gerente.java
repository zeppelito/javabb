public class Gerente extends Usuario {
    public Gerente(String id, String login, String senha) {
        super(id, login, senha);
    }

    @Override
    public void menu() {
        System.out.println("1. Adicionar Livro");
        System.out.println("2. Remover Livro");
        System.out.println("3. Gerenciar Empréstimos");
        System.out.println("4. Sair");
        // Implementação das funcionalidades aqui...
    }

    public void adicionarLivro(Biblioteca biblioteca, String titulo, String autor) {
        Livro livro = new Livro(titulo, autor);
        biblioteca.adicionarLivro(livro);
    }

    public void removerLivro(Biblioteca biblioteca, String titulo) {
        biblioteca.removerLivro(titulo);
    }

    // Outros métodos de gerenciamento...
}
