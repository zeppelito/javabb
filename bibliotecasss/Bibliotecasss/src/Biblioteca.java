import java.util.ArrayList;
import java.util.List;

public class Biblioteca {
    private List<Livro> catalogo;
    private List<Emprestimo> emprestimos;

    public Biblioteca() {
        catalogo = new ArrayList<>();
        emprestimos = new ArrayList<>();
    }

    public void adicionarLivro(Livro livro) {
        catalogo.add(livro);
        System.out.println("Livro adicionado: " + livro);
    }

    public void removerLivro(String titulo) {
        Livro livro = buscarLivro(titulo);
        if (livro != null) {
            catalogo.remove(livro);
            System.out.println("Livro removido: " + titulo);
        } else {
            System.out.println("Livro não encontrado: " + titulo);
        }
    }

    public Livro buscarLivro(String titulo) {
        for (Livro livro : catalogo) {
            if (livro.getTitulo().equalsIgnoreCase(titulo)) {
                return livro;
            }
        }
        return null;
    }

    public void listarLivros() {
        for (Livro livro : catalogo) {
            System.out.println(livro);
        }
    }

    public void emprestarLivro(UsuarioComum usuario, String titulo) {
        Livro livro = buscarLivro(titulo);
        if (livro != null && livro.isDisponivel()) {
            livro.setDisponivel(false);
            emprestimos.add(new Emprestimo(usuario, livro));
            System.out.println("Empréstimo realizado: " + titulo);
        } else {
            System.out.println("Livro não disponível: " + titulo);
        }
    }

    // Outros métodos de gerenciamento de empréstimos...
}
