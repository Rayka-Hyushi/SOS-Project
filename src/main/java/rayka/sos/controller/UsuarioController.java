package rayka.sos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import rayka.sos.model.Usuario;
import rayka.sos.service.UsuarioService;

import java.io.IOException;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    private UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    /*@PostMapping("/login")
    public Usuario login(@RequestParam String email, @RequestParam String pass) {
        Usuario usuario = usuarioService.login(email, pass);
        if(usuario != null){
            return "pagina do usuario";
        }
    }*/

    /*@GetMapping
    public String userProfile(Model model) {
        return "";
    }

    @PostMapping("/alterar")
    public String alterar(@RequestParam String username,
                          @RequestParam String email,
                          @RequestParam String password,
                          @RequestParam("photo")MultipartFile photo,
                          Model model) throws IOException {
    }*/
}
