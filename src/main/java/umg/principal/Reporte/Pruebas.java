package umg.principal.Reporte;

import umg.principal.baseDatos.Model.Producto;
import umg.principal.baseDatos.Service.ProductoService;

import javax.swing.*;
import java.util.List;

public class Pruebas {
    public static void main(String[] args) {
        try {
            List<Producto>prod =new ProductoService().obtenerTodosLosProductos();

            //Existencias menores a 20
            //List<Producto> prod = new ProductoService().ObtenerTodos("existencia < 20");

            //Pais especifico
            //List<Producto> prod = new ProductoService().ObtenerTodos("origen = 'Alemania'");

            //Precios mayores a 2000
            //List<Producto> prod = new ProductoService().ObtenerTodos("precio > 2000");

            //Agrupados por pais y ordenados por precio
            //List<Producto> prod = new ProductoService().AgrupadosOrdenados();

            new PDFreport().generateProductReport(prod, "C:\\PruebaPDF\\reporteLboratorio3.pdf");
            JOptionPane.showConfirmDialog(null, "Reporte generado en C:\\reporteLaboratorio3.pdf");
        }
        catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
