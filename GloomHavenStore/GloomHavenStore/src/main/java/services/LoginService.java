package services;

import models.Usuario;
/**
 * Interfaz que define el servicio de login del sistema.
 * 
 * Proporciona el método necesario para autenticar a un usuario 
 * en base a sus credenciales (correo y contraseña).
 */
public interface LoginService {
    Usuario login(String email, String password);
}

