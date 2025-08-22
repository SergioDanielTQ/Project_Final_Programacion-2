package services;

import models.Usuario;

public interface LoginService {
    Usuario login(String email, String password);
}

