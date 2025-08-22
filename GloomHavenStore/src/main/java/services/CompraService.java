package services;

import java.util.List;
import models.Compra;

public interface CompraService {
    boolean registrarCompra(Compra compra);
    List<Compra> listarComprasPendientes();
    boolean actualizarEstadoCompra(int idCompra, String estado);
}
