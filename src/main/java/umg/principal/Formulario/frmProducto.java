package umg.principal.Formulario;

import umg.principal.baseDatos.Model.Producto;
import umg.principal.baseDatos.Service.ProductoService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class frmProducto {
    private JPanel JframeProducto;
    private JLabel lblTitulo;
    private JLabel lblCodigo;
    private JTextField textFieldProducto;
    private JLabel lblDescripcion;
    private JTextField textFieldDescripcion;
    private JLabel lblOrigen;
    private JComboBox comboBoxOrigen;
    private JButton buttonGrabar;
    private JButton buttonBuscar;
    private JButton buttonActulizar;
    private JButton buttonEliminar;
    private JButton buttonLimpiar;
    private JButton buttonSalir;
    private JLabel lbExistencia;
    private JTextField textFieldExistencia;
    private JLabel lblPrecio;
    private JTextField textFieldPrecio;


    public frmProducto (){
        comboBoxOrigen.addItem("Japon");
        comboBoxOrigen.addItem("China");
        comboBoxOrigen.addItem("Corea");
        comboBoxOrigen.addItem("Guatemala");


        buttonEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Eliminar
                int idProducto = textFieldProducto.getText().isEmpty() ? -1 : Integer.parseInt(textFieldProducto.getText());
                try {
                    new ProductoService().eliminarProducto(idProducto);
                    JOptionPane.showMessageDialog(null, "Producto eliminado exitosamente");
                    // Limpiar los campos después de eliminar
                    textFieldProducto.setText("");
                    textFieldDescripcion.setText("");
                    comboBoxOrigen.setSelectedIndex(0);
                textFieldExistencia.setText("");
                textFieldPrecio.setText("");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error al eliminar el producto: " + ex.getMessage());
                }
            }
        });
        buttonGrabar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //JOptionPane.showMessageDialog(null, "descrip "+textFieldDescripcion.getText());
              //  textFieldDescripcion.setText("Baterias de plutonio para Laptop");
                Producto producto = new Producto();

                producto.setDescripcion(textFieldDescripcion.getText());
                producto.setOrigen(comboBoxOrigen.getSelectedItem().toString());
                producto.setPrecio(Double.parseDouble(textFieldPrecio.getText()));
                producto.setExistencia(Integer.parseInt(textFieldExistencia.getText()));
                try{
                    new ProductoService().agregarProducto(producto);
                    JOptionPane.showMessageDialog(null, "Producto creado exitosamente");

                }catch (Exception ex){
                    JOptionPane.showMessageDialog(null, "Error al crear el producto: "+ex.getMessage());

                }
            }
        });
        buttonBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //buscar
                int idProducto= textFieldProducto.getText().isEmpty()?-1:Integer.parseInt(textFieldProducto.getText());
                try{
                    Producto productoEncontrado = new ProductoService().obtenerProducto(idProducto);
                    if (productoEncontrado != null){
                        textFieldDescripcion.setText(String.valueOf(productoEncontrado.getIdProducto()));
                        textFieldDescripcion.setText(productoEncontrado.getDescripcion());
                        comboBoxOrigen.setSelectedItem(productoEncontrado.getOrigen());
                    textFieldPrecio.setText(String.valueOf(productoEncontrado.getPrecio()));
                    textFieldExistencia.setText(String.valueOf(productoEncontrado.getExistencia()));
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "No se encontro el producto");
                    }
                } catch (Exception ex){
                    JOptionPane.showMessageDialog(null, "Error al obtener el producto: "+ex.getMessage());
                }
            }
        });
        buttonActulizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Actualizar
                Producto producto = new Producto();
                producto.setIdProducto(Integer.parseInt(textFieldProducto.getText()));
                producto.setDescripcion(textFieldDescripcion.getText());
                producto.setOrigen(comboBoxOrigen.getSelectedItem().toString());
               producto.setPrecio(Double.parseDouble(textFieldPrecio.getText()));
               producto.setExistencia(Integer.parseInt(textFieldExistencia.getText()));

                try {
                    new ProductoService().actualizarProducto(producto);
                    JOptionPane.showMessageDialog(null, "Producto Actualizado exitosamente");
                } catch (Exception ex){
                    JOptionPane.showMessageDialog(null, "Error al actualizar el producto: "+ex.getMessage());
                }
            }
        });

        buttonLimpiar.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                textFieldProducto.setText("");
                textFieldDescripcion.setText("");
                comboBoxOrigen.setSelectedIndex(0);
            }
        });

        buttonSalir.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("frmProducto");
        frame.setContentPane(new frmProducto().JframeProducto);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
