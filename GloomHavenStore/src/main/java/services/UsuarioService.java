package services;

import java.util.List;
import java.util.Optional;
import models.Usuario;


public interface UsuarioService {
    List<Usuario> listar();
    Optional<Usuario> porId(int id);
    void guardar(Usuario usuario);
    void eliminar(int id);
}