package aion_backend;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AionController {

    @GetMapping("/")
    public String inicio() {
        return "AION funcionando";
    }
    
}