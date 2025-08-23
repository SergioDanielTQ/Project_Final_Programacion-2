package models;
// Clase que representa el detalle de una compra.
// Generalmente se utiliza en sistemas de facturación, ventas o inventario.
public class DetalleCompra {
    private int idDetalle;
    private int idCompra;
    private int idProducto;
    private int cantidad;
    private double subtotal;

    // Métodos GET: permiten acceder a los valores de los atributos
    public int getIdDetalle() {
        return idDetalle;
    }

    public int getIdCompra() {
        return idCompra;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public double getSubtotal() {
        return subtotal;
    }

    // Métodos GET: permiten acceder a los valores de los atributos
    public void setIdDetalle(int idDetalle) {
        this.idDetalle = idDetalle;
    }

    public void setIdCompra(int idCompra) {
        this.idCompra = idCompra;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }


}
