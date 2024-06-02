public abstract class Usuario {
    protected String login;
    protected String senha;
    protected String id;

    public Usuario(String id, String login, String senha) {
        this.id = id;
        this.login = login;
        this.senha = senha;
    }

    public String getLogin() {
        return login;
    }

    public boolean autenticar(String senha) {
        return this.senha.equals(senha);
    }

    public abstract void menu();
}
