import java.time.LocalDate;

public class Emprestimo {
    private UsuarioComum usuario;
    private Livro livro;
    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucao;

    public Emprestimo(UsuarioComum usuario, Livro livro) {
        this.usuario = usuario;
        this.livro = livro;
        this.dataEmprestimo = LocalDate.now();
        this.dataDevolucao = dataEmprestimo.plusWeeks(2); // Por exemplo, 2 semanas para devolução
    }

    public UsuarioComum getUsuario() {
        return usuario;
    }

    public Livro getLivro() {
        return livro;
    }

    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }

    @Override
    public String toString() {
        return "Empréstimo: " + livro.getTitulo() + " para " + usuario.getLogin() + " até " + dataDevolucao;
    }
}
