package umg.principal.baseDatos.Service;

import umg.principal.baseDatos.Dao.ProductoDAO;
import umg.principal.baseDatos.Model.Producto;

import java.sql.SQLException;
import java.util.List;

public class ProductoService {
    private final ProductoDAO productoDAO;

    public ProductoService() {
        this.productoDAO = new ProductoDAO();
    }

    public void agregarProducto(Producto producto) throws SQLException {
        productoDAO.agregarProducto(producto);
    }

    public Producto obtenerProducto(int id) throws SQLException {
        return productoDAO.obtenerProducto(id);
    }

    public List<Producto> obtenerTodosLosProductos() throws SQLException {
        return productoDAO.obtenerTodosLosProductos();
    }

    public List<Producto> obtenerTodosLosProductosmenores30(String condicion) throws SQLException {
        return productoDAO.obtenerTodosLosProductosmenores30(condicion);
    }

    public void actualizarProducto(Producto producto) throws SQLException {
        productoDAO.actualizarProducto(producto);
    }

    public boolean eliminarProducto(int id) throws SQLException {
        productoDAO.eliminarProducto(id);
        return true;
    }


    public List<Producto> AgrupadosOrdenados() throws SQLException {
        return productoDAO.AgrupadosOrdenados();
    }

}
