import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SistemaDeLogin {
    private static final String URL = "jdbc:mysql://localhost:3306/sistema_login";
    private static final String USER = "root"; // altere conforme necessário
    private static final String PASSWORD = "password"; // altere conforme necessário

    public SistemaDeLogin() {
        // Carregar driver JDBC
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public String acessarSistema(String login, String senha) {
        String sql = "SELECT senha FROM usuarios WHERE login = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, login);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String senhaNoBanco = rs.getString("senha");
                if (senha.equals(senhaNoBanco)) {
                    return "Sistema acessado com sucesso!";
                } else {
                    return "Login ou senha inválidos.";
                }
            } else {
                return "Login ou senha inválidos.";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "Erro de conexão com o banco de dados.";
        }
    }

    public void adicionarUsuario(Usuario usuario) {
        String sql = "INSERT INTO usuarios (login, senha) VALUES (?, ?)";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, usuario.getLogin());
            stmt.setString(2, usuario.getSenha());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean usuarioExiste(String login) {
        String sql = "SELECT 1 FROM usuarios WHERE login = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, login);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public String pesquisarUsuario(String login) {
        if (login == null || login.isEmpty()) {
            return "Campo login está vazio.";
        }

        String sql = "SELECT * FROM usuarios WHERE login = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, login);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return "Usuário encontrado: Login = " + rs.getString("login") + ", Senha = " + rs.getString("senha");
            } else {
                return "Usuário não encontrado.";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "Erro de conexão com o banco de dados.";
        }
    }

    public String removerUsuario(String login) {
        if (login == null || login.isEmpty()) {
            return "Campo login está vazio.";
        }

        String sql = "DELETE FROM usuarios WHERE login = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, login);
            int affectedRows = stmt.executeUpdate();

            if (affectedRows > 0) {
                return "Usuário removido com sucesso.";
            } else {
                return "Usuário não encontrado.";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "Erro de conexão com o banco de dados.";
        }
    }
}
