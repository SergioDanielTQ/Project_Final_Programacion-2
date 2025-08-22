package models;

import java.util.Date;
import java.util.List;

public class Compra {
    private int idCompra;
    private int idUsuario;
    private Date fecha;
    private String estado;
    private List<DetalleCompra> detalles;

    public int getIdCompra() {
        return idCompra;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public Date getFecha() {
        return fecha;
    }

    public String getEstado() {
        return estado;
    }

    public List<DetalleCompra> getDetalles() {
        return detalles;
    }

    public void setIdCompra(int idCompra) {
        this.idCompra = idCompra;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setDetalles(List<DetalleCompra> detalles) {
        this.detalles = detalles;
    }

}    