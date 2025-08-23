package services;

import java.util.List;
import models.Compra;
// Interfaz que define los servicios relacionados con la gestión de compras
public interface CompraService {
    boolean registrarCompra(Compra compra);
    List<Compra> listarComprasPendientes();
    boolean actualizarEstadoCompra(int idCompra, String estado);
}
