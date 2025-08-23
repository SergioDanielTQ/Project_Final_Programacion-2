package models;
// Clase que representa a un Usuario dentro del sistema
public class Usuario {
    private int idUsuario;
    private String nombre;
    private String email;   
    private String password;
    private String rol;     
    
    // Constructor vacío (permite crear objetos sin inicializar atributos de inmediato)
    public Usuario() {
    }
    // Métodos GET: permiten acceder a los valores de los atributos
    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }
    // Métodos SET: permiten modificar los valores de los atributos
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
}
