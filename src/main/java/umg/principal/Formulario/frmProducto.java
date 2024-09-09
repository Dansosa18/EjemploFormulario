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


    public frmProducto (){
        comboBoxOrigen.addItem("Japon");
        comboBoxOrigen.addItem("China");
        comboBoxOrigen.addItem("Corea");
        comboBoxOrigen.addItem("Guatemala");

        buttonGrabar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //JOptionPane.showMessageDialog(null, "descrip "+textFieldDescripcion.getText());
              //  textFieldDescripcion.setText("Baterias de plutonio para Laptop");
                Producto producto = new Producto();

                producto.setDescripcion(textFieldDescripcion.getText());
                producto.setOrigen(comboBoxOrigen.getSelectedItem().toString());
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
                try {
                    new ProductoService().actualizarProducto(producto);
                    JOptionPane.showMessageDialog(null, "Producto Actualizado exitosamente");
                } catch (Exception ex){
                    JOptionPane.showMessageDialog(null, "Error al actualizar el producto: "+ex.getMessage());
                }
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
