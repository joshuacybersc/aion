package aion_backend;

import java.util.Map;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = {
    "http://127.0.0.1:5500",
    "http://localhost:5500"
})

@RestController
public class AionController {
    
    @PostMapping("/api/login")
    public ResponseEntity<Map<String, String>> login(
        @RequestBody Map<String, String> dados) {

            String usuario = dados.get("usuario");
            String senha = dados.get("senha");

            if(usuario == null || usuario.isBlank() || senha == null || senha.length() < 8) {
                return ResponseEntity
                    .badRequest()
                    .body(Map.of(
                        "mensagem",
                        "Informe o usuário e sua senha com pelo menos 8 caracteres."
                    ));
            }

            return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(Map.of(
                    "mensagem",
                    "Dados validados! A autenticação fornecida."
                ));
    }

    @PostMapping("/api/profissionais")
    public ResponseEntity<Map<String, String>> prof(
        @RequestBody Map<String, String> infos) {

        String sql = """
            INSERT INTO profissionais 
            (nome, servico, categoria, cidade, cidade_nome, descricao, telefone)
            VALUES (?, ?, ?, ?, ?, ?, ?)
            """;

        String nome = infos.get("nome");
        String servico = infos.get("servico");
        String categoria = infos.get("categoria");
        String cidade = infos.get("cidade");
        String cidade_nome = infos.get("cidade_nome");
        String descricao = infos.get("descricao");
        String telefone = infos.get("telefone");

        if (nome == null || nome.isBlank() || servico == null || servico.isBlank() || categoria == null || categoria.isBlank() || cidade == null || cidade.isBlank() || cidade_nome == null || cidade_nome.isBlank() || descricao == null || descricao.isBlank() || telefone == null || telefone.isBlank()) {
            return ResponseEntity
                .badRequest()
                .body(Map.of("mensagem", "Informe nome, serviço, cidade."));
        }

        try (
            Connection conexao = BancoDeDados.conectar();
            PreparedStatement comando = conexao.prepareStatement(sql);
        ) {
            comando.setString(1, nome);
            comando.setString(2, servico);
            comando.setString(3, categoria);
            comando.setString(4, cidade);
            comando.setString(5, cidade_nome);
            comando.setString(6, descricao);
            comando.setString(7, telefone);
            comando.executeUpdate();
        } catch (SQLException erro) {
            return ResponseEntity
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(Map.of("mensagem", "Erro ao cadastrar profissional."));
        }

        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(Map.of("mensagem", "Informações criadas!"));
    }
}