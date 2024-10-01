package com.example.tasktrackerbootstrap.controller;

import com.example.tasktrackerbootstrap.domain.Usuario;
import com.example.tasktrackerbootstrap.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:63342")
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/cadastro")
    public ResponseEntity<String> cadastrarUsuario(@RequestParam String nome, @RequestParam String email, @RequestParam String senha) {
        Usuario usuario = new Usuario();
        usuario.setUsername(nome);
        usuario.setEmail(email);
        usuario.setSenha(senha);

        usuarioService.salvarUsuario(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body("Usuário cadastrado com sucesso");
    }

    @GetMapping("")
    public List<Usuario> listarUsuarios() {
        return usuarioService.listarUsuarios();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> exibirUsuario(@PathVariable Long id) {
        Usuario usuario = usuarioService.buscarPorId(id);
        if (usuario != null) {
            return ResponseEntity.ok(usuario);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> atualizarUsuario(@PathVariable Long id, @RequestBody Usuario usuarioAtualizado) {
        Usuario usuario = usuarioService.atualizarUsuario(id, usuarioAtualizado);
        if (usuario != null) {
            return ResponseEntity.ok(usuario);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @CrossOrigin(origins = "http://localhost:63342")
    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody Usuario usuario) {
        boolean isAuthenticated = usuarioService.autenticarUsuario(usuario.getEmail(), usuario.getSenha());
        if (isAuthenticated) {
            Usuario usuarioLogado = usuarioService.findByEmail(usuario.getEmail());
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Login bem-sucedido!");
            response.put("id", usuarioLogado.getId());
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }
}
