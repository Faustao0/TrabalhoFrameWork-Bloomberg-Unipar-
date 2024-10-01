package com.example.tasktrackerbootstrap.service;

import com.example.tasktrackerbootstrap.domain.Tarefa;
import com.example.tasktrackerbootstrap.domain.Usuario;
import com.example.tasktrackerbootstrap.repository.TarefaRepository;
import com.example.tasktrackerbootstrap.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TarefaRepository tarefaRepository;

    public Usuario salvarUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    public Usuario buscarPorId(Long id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        return usuario.orElse(null);
    }

    public Usuario atualizarUsuario(Long id, Usuario usuarioAtualizado) {
        Optional<Usuario> usuarioExistenteOpt = usuarioRepository.findById(id);
        if (usuarioExistenteOpt.isPresent()) {
            Usuario usuarioExistente = usuarioExistenteOpt.get();

            usuarioExistente.setUsername(usuarioAtualizado.getUsername());
            usuarioExistente.setEmail(usuarioAtualizado.getEmail());
            usuarioExistente.setSenha(usuarioAtualizado.getSenha());

            List<Tarefa> tarefasAtualizadas = usuarioAtualizado.getTarefas();

            usuarioExistente.getTarefas().removeIf(tarefa ->
                    !tarefasAtualizadas.contains(tarefa)
            );

            for (Tarefa tarefaAtualizada : tarefasAtualizadas) {
                Optional<Tarefa> tarefaExistenteOpt = tarefaRepository.findById(tarefaAtualizada.getId());
                if (tarefaExistenteOpt.isPresent()) {
                    Tarefa tarefaExistente = tarefaExistenteOpt.get();
                    tarefaExistente.setUsuario(usuarioExistente);
                    if (!usuarioExistente.getTarefas().contains(tarefaExistente)) {
                        usuarioExistente.getTarefas().add(tarefaExistente);
                    }
                } else {
                    tarefaAtualizada.setUsuario(usuarioExistente);
                    usuarioExistente.getTarefas().add(tarefaAtualizada);
                }
            }

            return usuarioRepository.save(usuarioExistente);
        }
        return null;
    }

    public boolean autenticarUsuario(String email, String senha) {
        Usuario usuario = usuarioRepository.findByEmail(email);
        if (usuario == null) {
            return false;
        }
        return usuario.getSenha().equals(senha);
    }

    public Usuario buscarPorUsername(String username) {
        return usuarioRepository.findByUsername(username);
    }

    public Usuario findByEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    public void adicionarTarefaAoUsuario(Long userId, Tarefa tarefaNova) {
        Usuario usuario = usuarioRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        boolean tarefaExistente = usuario.getTarefas().stream()
                .anyMatch(tarefa -> tarefa.getDescricao().equals(tarefaNova.getDescricao()));

        if (!tarefaExistente) {
            Optional<Tarefa> tarefaExistenteOpt = tarefaRepository.findById(tarefaNova.getId());
            if (tarefaExistenteOpt.isPresent()) {
                Tarefa tarefaExistenteDB = tarefaExistenteOpt.get();
                usuario.adicionarTarefa(tarefaExistenteDB);
            } else {
                usuario.adicionarTarefa(tarefaNova);
            }
            usuarioRepository.save(usuario);
        } else {
            System.out.println("Essa tarefa já está associada ao usuário.");
        }
    }
}