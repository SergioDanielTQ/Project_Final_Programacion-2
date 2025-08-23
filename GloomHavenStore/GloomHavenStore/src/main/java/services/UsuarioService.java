package services;

import java.util.List;
import java.util.Optional;
import models.Usuario;

/**
 * Interfaz que define los métodos que debe implementar un servicio de Usuario.
 * Un "service" es una capa intermedia entre los controladores y la base de datos,
 * encargada de la lógica de negocio.
 */
public interface UsuarioService {
    List<Usuario> listar();
    Optional<Usuario> porId(int id);
    void guardar(Usuario usuario);
    void eliminar(int id);
}