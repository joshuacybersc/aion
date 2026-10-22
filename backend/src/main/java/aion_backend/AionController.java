package aion_backend;

import java.util.Map;

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

        String nome = infos.get("nome");
        String servico = infos.get("servico");
        String cidade = infos.get("cidade");

        if (nome == null || nome.isBlank() || servico == null || servico.isBlank() || cidade == null || cidade.isBlank()) {
            return ResponseEntity
                .badRequest()
                .body(Map.of("mensagem", "Informe nome, serviço e cidade."));
        }

        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(Map.of("mensagem", "Informações criadas!"));
    }
}