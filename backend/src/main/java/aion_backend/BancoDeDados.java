
package aion_backend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class BancoDeDados {

    private static final String URL = "jdbc:sqlite:aion.db";

    public static Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL);
    }

    public static void inicializar() {
        String sql = """
            CREATE TABLE IF NOT EXISTS usuarios (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                usuario TEXT NOT NULL UNIQUE,
                senha_hash TEXT NOT NULL
            )
            """;

        try (
            Connection conexao = conectar();
            Statement comando = conexao.createStatement()
        ) {
            comando.execute(sql);
            System.out.println("Banco de dados AION inicializado!");
        } catch (SQLException erro) {
            throw new RuntimeException(
                "Erro ao inicializar o banco de dados.", erro
            );
        }
    }
}

