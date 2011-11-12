package Controlador;

import Excepciones.NoProductoExcepcion;
import Pantalla.*;
import Persistencia.AdministradorTx;
import java.awt.event.ActionEvent;
import java.beans.PropertyVetoException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;

public final class ControladorPrincipal {

    private PantallaPrincipal pantallaPrincipal;

    public ControladorPrincipal() {
        pantallaPrincipal = new PantallaPrincipal(this);
        pantallaPrincipal.setVisible(true);
        pantallaPrincipal.setLocationRelativeTo(null);
        pantallaPrincipal.setExtendedState(JFrame.MAXIMIZED_BOTH);
        crearConexion("jdbc:mysql://localhost:3306/sg1", "root", "duendecito");

        pantallaPrincipal.getItemCliente().addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    agregarCliente();
                } catch (PropertyVetoException ex) {
                    Logger.getLogger(ControladorPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        pantallaPrincipal.getItemProducto().addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    agregarProducto();
                } catch (PropertyVetoException ex) {
                    Logger.getLogger(ControladorPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });


        pantallaPrincipal.getItemProveedor().addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    AdministrarProveedores();
                } catch (PropertyVetoException ex) {
                    Logger.getLogger(ControladorPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        pantallaPrincipal.getVentas().addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    AdministrarVentas();
                } catch (PropertyVetoException ex) {
                    Logger.getLogger(ControladorPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        pantallaPrincipal.getMetodo().addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    elegirMetodos();
                } catch (NoProductoExcepcion ex) {
                    Logger.getLogger(ControladorPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        pantallaPrincipal.getABC().addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(ActionEvent e) {
                try {
                    curvaABC();
                } catch (PropertyVetoException ex) {
                    Logger.getLogger(ControladorPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        pantallaPrincipal.getJmPedidos().addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Politica();
            }
        });

        pantallaPrincipal.getJmRealizarPedido().addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RealizarPedido();
            }
        });

        pantallaPrincipal.getSalir().addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pantallaPrincipal.dispose();
                System.exit(0);
            }
        });

        pantallaPrincipal.getItemEstablecer().addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    establecerParametros();
                } catch (NoProductoExcepcion ex) {
                    Logger.getLogger(ControladorPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

    }

    public void crearConexion(String dir, String usuario, String pass) {
        try {
            AdministradorTx.getInstance().crearConexion(dir, usuario, pass);
        } catch (Exception e) {
            System.out.println("ud no esta conectado a la bd");
        }

    }

    private void agregarCliente() throws PropertyVetoException {
        new ControladorCliente(this).agregarCliente();
    }

    private void agregarProducto() throws PropertyVetoException {
        new ControladorProducto(this).agregarProducto();
    }

    private void AdministrarProveedores() throws PropertyVetoException {
        new ControladorProveedor(this).AdministrarProveedor();
    }

    private void AdministrarVentas() throws PropertyVetoException {
        new ControladorVenta(this).AdministrarVentas();
    }

    private void elegirMetodos() throws NoProductoExcepcion {
        new ControladorMetodos(this).agregarMetodos();
    }

    private void establecerParametros() throws NoProductoExcepcion {
        new ControladorParametros(this).agregarParametros();
    }

    private void curvaABC() throws PropertyVetoException {
        new ControladorCurvaABC(this).iniciar();
    }

    private void Politica() {
        new ControladorPoliticaSR(this).iniciar();
    }

    private void RealizarPedido() {
        new ControladorRealizarPedido(this).iniciar();
    }

    public void add(JInternalFrame jInternalFrame) {
        System.out.println("AGREGA");
        jInternalFrame.moveToFront();
        jInternalFrame.setMaximizable(true);
        pantallaPrincipal.getjDesktopPane1().add(jInternalFrame);
        //pantallaPrincipal.getjDesktopPane1().getDesktopManager().maximizeFrame(jInternalFrame);

    }

    public void error() {
        pantallaPrincipal.setVisible(true);
    }

    public void remove(JInternalFrame jInternalFrame) {
        System.out.println("Quita");
        pantallaPrincipal.getjDesktopPane1().remove(jInternalFrame);
    }
}
