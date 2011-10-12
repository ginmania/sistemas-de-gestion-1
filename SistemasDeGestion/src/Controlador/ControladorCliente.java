/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Excepciones.NoClienteExcepcion;
import Experto.ExpertoCliente;
import Experto.FabricaExperto;
import Interfaces.Cliente;
import Pantalla.ModeloTablaCliente;
import Pantalla.PantallaClientes;
import Pantalla.PantallaNuevoCliente;
import java.awt.event.ActionEvent;
import java.beans.PropertyVetoException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author diego
 */
public class ControladorCliente {

    private ControladorPrincipal controladorPrincipal;
    private ExpertoCliente expertocliente;
    private PantallaClientes pantallaClientes;
    private PantallaNuevoCliente nuevocliente;
    private static final Integer NUEVO = 0;
    private static final Integer ACTUALIZAR = 1;
    private static final Integer ACTUALIZAR2 = 2;
    private Integer actual;
    private Cliente clienteSeleccionado;

    public ControladorCliente(ControladorPrincipal controladorPrincipal) throws PropertyVetoException {
        this.controladorPrincipal = controladorPrincipal;
        expertocliente = (ExpertoCliente) FabricaExperto.getInstancia().FabricarExperto("ExpertoCliente");
        pantallaClientes = new PantallaClientes();
        nuevocliente = new PantallaNuevoCliente(null, true);
        nuevocliente.setLocationRelativeTo(null);
        pantallaClientes.columnasize();

        pantallaClientes.getjTable1().getSelectionModel().addListSelectionListener(new ListSelectionListener() {

            public void valueChanged(ListSelectionEvent lse) {
                pantallaClientes.getBotonModificar().setEnabled(true);
                pantallaClientes.getBotoneliminar().setEnabled(true);
                
            }
        });
        /////////BOTON ELIMINAR CLIENTE//////////
        pantallaClientes.getBotoneliminar().addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actual = ACTUALIZAR2;
                clienteSeleccionado = ((ModeloTablaCliente) pantallaClientes.getjTable1().getModel()).getCliente(pantallaClientes.getjTable1().getSelectedRow());
                registrarCliente();
            }
        });
        /////////BOTON MODIFICAR CLIENTE//////////
        pantallaClientes.getBotonModificar().addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actual = ACTUALIZAR;
                clienteSeleccionado = ((ModeloTablaCliente) pantallaClientes.getjTable1().getModel()).getCliente(pantallaClientes.getjTable1().getSelectedRow());
                mostrarCliente(clienteSeleccionado);
                registrarCliente();
            }
        });
        /////////BOTON BUSCAR CLIENTE//////////
        pantallaClientes.getBotonBuscar().addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                String apellido, dni;
                try {
                    apellido = pantallaClientes.getCampoApellido().getText();
                    dni = pantallaClientes.getCampoDni().getText();
                    buscarCliente(apellido, dni);
                } catch (NoClienteExcepcion ex) {
                    ex.toString();
                    Logger.getLogger(ControladorCliente.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        /////////BOTON NUEVO CLIENTE//////////
        pantallaClientes.getBotonNuevo().addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    actual = NUEVO;
                    nuevoCliente();
                } catch (Exception ex) {
                    Logger.getLogger(ControladorCliente.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        /////////BOTON ACEPTAR NUEVO CLIENTE//////////
        nuevocliente.getBotoncancelarnuevocliente().addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(ActionEvent e) {
                nuevocliente.dispose();
            }
        });
        /////////BOTON CANCELAR NUEVO CLIENTE//////////
        nuevocliente.getBotonaceptarnuevocliente().addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(ActionEvent e) {
                registrarCliente();
            }
        });
    }

    private void registrarCliente() {
        switch (actual) {
            case 0:
                if (insertarCliente(nuevocliente.getCamponombre().getText(), nuevocliente.getCampoapellido().getText(),
                        nuevocliente.getCampodni().getText(), nuevocliente.getCampocuit().getText(),
                        nuevocliente.getCampodireccion().getText(), nuevocliente.getCampotelfijo().getText(),
                        nuevocliente.getCampocelular().getText(), nuevocliente.getCampoemail().getText(), 0) == true) {
                    System.out.println("Inserto!!!!");
                    JOptionPane.showMessageDialog(pantallaClientes, "Se grabo el cliente", "Nuevo Cliente", JOptionPane.INFORMATION_MESSAGE);
                    try {
                        buscarCliente("", "");
                    } catch (NoClienteExcepcion ex) {
                        Logger.getLogger(ControladorCliente.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                break;
            case 1:
                actualizarCampos();
                if (expertocliente.modificarCliente(clienteSeleccionado)) {
                    System.out.println("modifico!!!!");
                    JOptionPane.showMessageDialog(pantallaClientes, "Se modifico el cliente", "Modificar Cliente", JOptionPane.INFORMATION_MESSAGE);
                    try {
                        buscarCliente("", "");
                    } catch (NoClienteExcepcion ex) {
                        Logger.getLogger(ControladorCliente.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                break;
            case 2:
                actualizarCampos();
                if (expertocliente.eliminarCliente(clienteSeleccionado)) {
                    System.out.println("elimino!!!!");
                    JOptionPane.showMessageDialog(pantallaClientes, "Se elimino el cliente", "Eliminar cliente", JOptionPane.INFORMATION_MESSAGE);
                    try {
                        buscarCliente("", "");
                    } catch (NoClienteExcepcion ex) {
                        Logger.getLogger(ControladorCliente.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                break;
        }
        nuevocliente.dispose();
    }

    private boolean insertarCliente(String nombre, String apellido, String dni, String cuit, String direccion,
            String telfijo, String celular, String email, int baja) {
        boolean resultado = false;
        baja = 0;
        resultado = expertocliente.insertarCliente(nombre, apellido, dni, cuit, direccion, telfijo, celular, email, baja);
        return resultado;
    }

    private List<Cliente> buscarCliente(String valornombre, String valordni) throws NoClienteExcepcion {
        List<Cliente> vectorclientes = new ArrayList<Cliente>();
        try {
            vectorclientes = expertocliente.buscarCliente(valornombre, valordni);
            ModeloTablaCliente mtc = new ModeloTablaCliente();
            //mtc.addAll para agregar todos los clientes y seguir sumando las busquedas
            //mtc.setClientes para agregar solamente un cliente
            mtc.setClientes(vectorclientes);
            pantallaClientes.getjTable1().setModel(mtc);
            pantallaClientes.getBotonModificar().setEnabled(false);
            pantallaClientes.getBotoneliminar().setEnabled(false);
        } catch (NoClienteExcepcion ex) {
            ex.toString();
        }
        return vectorclientes;

    }

    public void agregarCliente() {
        //  FormatearGUI.formatear(pantallaClientes);
        pantallaClientes.setVisible(true);
        controladorPrincipal.add(pantallaClientes);
    }

    public void nuevoCliente() {
        nuevocliente.getCamponombre().setText("");
        nuevocliente.getCampoapellido().setText("");
        nuevocliente.getCampodni().setText("");
        nuevocliente.getCampocuit().setText("");
        nuevocliente.getCampodireccion().setText("");
        nuevocliente.getCampotelfijo().setText("");
        nuevocliente.getCampocelular().setText("");
        nuevocliente.getCampoemail().setText("");
        nuevocliente.setTitle("NUEVO CLIENTE");
        nuevocliente.setVisible(true);

    }

    public void mostrarCliente(Cliente cliente) {
        if (cliente.getbaja() == 0) {
            nuevocliente.getCamponombre().setText(cliente.getNombre());
            nuevocliente.getCampoapellido().setText(cliente.getApellido());
            nuevocliente.getCampodni().setText(cliente.getdni());
            nuevocliente.getCampocuit().setText(cliente.getCUIT());
            nuevocliente.getCampodireccion().setText(cliente.getDireccion());
            nuevocliente.getCampotelfijo().setText(cliente.getTelefono_Fijo());
            nuevocliente.getCampocelular().setText(cliente.getCelular());
            nuevocliente.getCampoemail().setText(cliente.getemail());
            nuevocliente.setTitle("MODIFICAR CLIENTE");
            nuevocliente.setVisible(true);
        }
    }

    public void actualizarCampos() {
        clienteSeleccionado.setNombre(nuevocliente.getCamponombre().getText());
        clienteSeleccionado.setApellido(nuevocliente.getCampoapellido().getText());
        clienteSeleccionado.setdni(nuevocliente.getCampodni().getText());
        clienteSeleccionado.setCUIT(nuevocliente.getCampocuit().getText());
        clienteSeleccionado.setDireccion(nuevocliente.getCampodireccion().getText());
        clienteSeleccionado.setTelefono_Fijo(nuevocliente.getCampotelfijo().getText());
        clienteSeleccionado.setCelular(nuevocliente.getCampocelular().getText());
        clienteSeleccionado.setemail(nuevocliente.getCampoemail().getText());
    }
}
