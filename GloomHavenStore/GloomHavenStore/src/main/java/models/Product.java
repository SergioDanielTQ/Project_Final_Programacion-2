package models;
// Clase que representa un producto dentro del sistema.
// Se suele usar en aplicaciones de inventario, facturación, ventas, etc.
public class Product {
    private int id;
    private String nombre;
    private String descripcion; 
    private double precio;
    private int stock;
    private String imagen;
    private String estado;
  // Métodos GET: permiten acceder a los valores de los atributos
    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public int getStock() {
        return stock;
    }

    public String getImagen() {
        return imagen;
    }

    public String getEstado() {
        return estado;
    }
    
  // Métodos SET: permiten modificar los valores de los atributos
    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    
}
