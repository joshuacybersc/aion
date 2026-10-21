package aion_backend;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
                .status(HttpStatus.NOT_IMPLEMENTED)
                .body(Map.of(
                    "mensagem",
                    "Dados validados! A autenticação ainda está em desenvolvimento."
                ));
    }
}