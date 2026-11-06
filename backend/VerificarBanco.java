import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class VerificarBanco {
    public static void main(String[] args) throws Exception {

        String url = "jdbc:sqlite:aion.db";

        try (
            Connection conexao = DriverManager.getConnection(url);
            Statement comando = conexao.createStatement();
            ResultSet resultado = comando.executeQuery(
                "PRAGMA table_info(profissionais)"
            )
        ) {
            while (resultado.next()) {
                System.out.println(resultado.getString("name"));
            }
        }
    }
}