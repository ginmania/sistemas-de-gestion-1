package Controlador;

import Excepciones.NoClienteExcepcion;
import Excepciones.NoProductoExcepcion;
import Experto.ExpertoParametros;
import Experto.ExpertoReloj;
import Experto.FabricaExperto;
import Interfaces.Parametros;
import Pantalla.*;
import Persistencia.AdministradorTx;
import java.awt.event.ActionEvent;
import java.beans.PropertyVetoException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;

public final class ControladorPrincipal {

    private PantallaPrincipal pantallaPrincipal;
    private PantallaFechaSistema pantallaFecha;
    private static ControladorParametros controladorParametros;
    private ExpertoReloj clock;
    public GregorianCalendar fechaSistema;
    final ControladorPrincipal ctlr;
    private ExpertoParametros expertoparametros;
    private double alfa, beta, gama;

    public ControladorPrincipal() {
        pantallaPrincipal = new PantallaPrincipal(this);
        ctlr = this;
        fechaSistema = new GregorianCalendar();
        pantallaPrincipal.getLabelAlfa().setVisible(false);
        pantallaPrincipal.getLabelBeta().setVisible(false);
        pantallaPrincipal.getLabelGama().setVisible(false);
        pantallaPrincipal.setVisible(true);
        pantallaPrincipal.setLocationRelativeTo(null);
        pantallaPrincipal.setExtendedState(JFrame.MAXIMIZED_BOTH);
        crearConexion("jdbc:mysql://localhost:3306/sg1", "root", "duendecito");
        //cargamos bandeja de entrada
        clock = (ExpertoReloj) FabricaExperto.getInstancia().FabricarExperto("ExpertoReloj");
        clock.iniciar(ctlr);
        expertoparametros = (ExpertoParametros) FabricaExperto.getInstancia().FabricarExperto("ExpertoParametros");
        buscarParametros();
        pantallaPrincipal.getLabelAlfa().setText("Alfa: " + alfa);
        pantallaPrincipal.getLabelBeta().setText("Beta: " + beta);
        pantallaPrincipal.getLabelGama().setText("Gama: " + gama);
        pantallaPrincipal.getLabelAlfa().setVisible(true);
        pantallaPrincipal.getLabelBeta().setVisible(true);
        pantallaPrincipal.getLabelGama().setVisible(true);

        //////////Generar Reporte datos de la Curva ABC
        pantallaPrincipal.getMenuABC().addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    reportes("ABC");
                } catch (Exception ex) {
                    Logger.getLogger(ControladorPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        //////////Generar Reporte Clientes
        pantallaPrincipal.getMenuClientes().addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    reportes("Cliente");
                } catch (Exception ex) {
                    Logger.getLogger(ControladorPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

//////////Generar Reporte Pedidos
        pantallaPrincipal.getMenuPedidos().addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    reportes("Pedido");
                } catch (Exception ex) {
                    Logger.getLogger(ControladorPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        //////////Generar Reporte Productos
        pantallaPrincipal.getMenuProductos().addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    reportes("Producto");
                } catch (Exception ex) {
                    Logger.getLogger(ControladorPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        //////////Generar Reporte Ventas
        pantallaPrincipal.getMenuVentas().addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    reportes("Venta");
                } catch (Exception ex) {
                    Logger.getLogger(ControladorPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
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
                    buscarParametros();
                    pantallaPrincipal.getLabelAlfa().setText("Alfa: " + alfa);
                    pantallaPrincipal.getLabelBeta().setText("Beta: " + beta);
                    pantallaPrincipal.getLabelGama().setText("Gama: " + gama);

                } catch (NoProductoExcepcion ex) {
                    Logger.getLogger(ControladorPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

      /* pantallaPrincipal.getFechaSistema().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
               pantallaFecha = new PantallaFechaSistema();
               pantallaFecha.setVisible(true);
               add(pantallaFecha);
            }

        /*  pantallaPrincipal.getFechaSistema().addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(ActionEvent e) {
        pantallaFecha = new PantallaFechaSistema();
        pantallaFecha.setVisible(true);
        add(pantallaFecha);
        }

        });
        
        pantallaFecha.getJbAceptarFecha().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fechaSistema.setGregorianChange(pantallaFecha.getJd_FechaSistema().getDate());
                llamarreloj();
            }

        public void actionPerformed(ActionEvent e) {
        fechaSistema.setGregorianChange(pantallaFecha.getJd_FechaSistema().getDate());
        clock.iniciar(ctlr);
        }

        });*/


    }

    public void llamarreloj(){
        clock.iniciar(ctlr);
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
        new ControladorParametros(this).agregarParametros(alfa, beta, gama);
    }

    private void reportes(String nombreReporte) throws NoClienteExcepcion {
        new ControladorReportes(this).generarReporte(nombreReporte);
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
        try {
        pantallaPrincipal.getBandejaEntrada().setVisible(false);
        pantallaPrincipal.getBandejaProductos().setVisible(false);
        jInternalFrame.moveToFront();
        jInternalFrame.setMaximizable(true);
        
            jInternalFrame.setSelected(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(ControladorPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        pantallaPrincipal.getjDesktopPane1().add(jInternalFrame);
        //pantallaPrincipal.getjDesktopPane1().getDesktopManager().maximizeFrame(jInternalFrame);

    }

    public void error() {
        pantallaPrincipal.setVisible(true);
    }

    public void remove(JInternalFrame jInternalFrame) {
        System.out.println("Quita");
        pantallaPrincipal.getjDesktopPane1().remove(jInternalFrame);
        pantallaPrincipal.getBandejaEntrada().setVisible(true);
        pantallaPrincipal.getBandejaProductos().setVisible(true);
        clock.iniciar(this);
    }

    public PantallaPrincipal getPantallaPrincipal() {
        return pantallaPrincipal;
    }

    public void setPantallaPrincipal(PantallaPrincipal pantallaPrincipal) {
        this.pantallaPrincipal = pantallaPrincipal;
    }

    public List<Parametros> buscarParametros() {
        List<Parametros> vectorParametros = new ArrayList<Parametros>();
        vectorParametros = expertoparametros.buscarParametros();
        alfa = vectorParametros.get(0).getAlfa();
        beta = vectorParametros.get(0).getBeta();
        gama = vectorParametros.get(0).getGama();
        return vectorParametros;
    }
}
