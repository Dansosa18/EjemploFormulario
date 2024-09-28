package umg.principal.baseDatos.Dao;

import umg.principal.baseDatos.Conexion.ConexionDB;
import umg.principal.baseDatos.Model.Producto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAO {
    private final ConexionDB conexion;

    public ProductoDAO() {
        conexion = new ConexionDB();
    }

    public void agregarProducto(Producto producto) throws SQLException {
        String query = "INSERT INTO tb_producto (descripcion, origen) VALUES (?, ?)";
        try (Connection conn = conexion.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, producto.getDescripcion());
            ps.setString(2, producto.getOrigen());
            ps.executeUpdate();
        }
    }

    public Producto obtenerProducto(int id) throws SQLException {
        String query = "SELECT * FROM tb_producto WHERE id_producto = ?";
        try (Connection conn = conexion.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Producto(rs.getInt("id_producto"), rs.getString("descripcion"), rs.getString("origen"), rs.getInt("precio"), rs.getInt("existencia"));
            }
        }
        return null;
    }

    public List<Producto> obtenerTodosLosProductos() throws SQLException {
        List<Producto> productos = new ArrayList<>();
        String query = "SELECT * FROM tb_producto order by id_producto";
        try (Connection conn = conexion.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                productos.add(new Producto(rs.getInt("id_producto"), rs.getString("descripcion"), rs.getString("origen"), rs.getInt("precio"), rs.getInt("existencia")));
            }
        }
        return productos;
    }

    public List<Producto> obtenerTodosLosProductosmenores30(String condicion) throws SQLException {
        List<Producto> productos = new ArrayList<>();
        String query = "SELECT * FROM tb_producto where " + condicion;
        try (Connection conn = conexion.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                productos.add(new Producto(rs.getInt("id_producto"), rs.getString("descripcion"), rs.getString("origen"), rs.getInt("precio"), rs.getInt("existencia")));
            }
        }
        return productos;
    }

    public List<Producto> AgrupadosOrdenados() {
        List<Producto> productos = new ArrayList<>();
        String query = "SELECT origen, AVG(precio) as precio_promedio,SUM(existencia) AS total_existencia" + "FROM tb_producto GROUP BY origen ORDER BY promedio_precio DESC";

        try (Connection conn = conexion.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                productos.add(new Producto(
                        0, // Suponiendo que id no es aplicable aquí
                        "", // Descripción no disponible en la consulta
                        rs.getString("origen"),
                        rs.getDouble("precio_promedio"),
                        rs.getInt("total_existencia") // Esto debería ser una suma de existencias
                ));            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return productos;
    }


    public void actualizarProducto(Producto producto) throws SQLException {
        String query = "UPDATE tb_producto SET descripcion = ?, origen = ? WHERE id_producto = ?";
        try (Connection conn = conexion.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, producto.getDescripcion());
            ps.setString(2, producto.getOrigen());
            ps.setInt(3, producto.getIdProducto());
            ps.executeUpdate();
        }
    }

    public void eliminarProducto(int id) throws SQLException {
        String query = "DELETE FROM tb_producto WHERE id_producto = ?";
        try (Connection conn = conexion.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}
