package Pantalla;

import Controlador.ControladorPrincipal;
import javax.swing.JDesktopPane;
import javax.swing.JMenuItem;

public class PantallaPrincipal extends javax.swing.JFrame {

    public PantallaPrincipal(ControladorPrincipal controladorPrincipal) {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jDesktopPane1 = new javax.swing.JDesktopPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        MenuCliente = new javax.swing.JMenuItem();
        MenuProducto = new javax.swing.JMenuItem();
        MenuProveedor = new javax.swing.JMenuItem();
        Ventas = new javax.swing.JMenuItem();
        Metodo = new javax.swing.JMenuItem();
        ABC = new javax.swing.JMenuItem();
        Salir = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jmPedidos = new javax.swing.JMenuItem();
        jmRealizarPedido = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sistemas de Gestion 1");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setMinimumSize(new java.awt.Dimension(1200, 600));
        setName("Pantalla Principal"); // NOI18N
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jDesktopPane1.setBackground(new java.awt.Color(172, 150, 102));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(jDesktopPane1, gridBagConstraints);

        jMenu1.setText("Sistema");

        MenuCliente.setText("Cliente");
        jMenu1.add(MenuCliente);

        MenuProducto.setText("Producto");
        jMenu1.add(MenuProducto);

        MenuProveedor.setText("Proveedor");
        jMenu1.add(MenuProveedor);

        Ventas.setText("Ventas");
        jMenu1.add(Ventas);

        Metodo.setText("Metodo");
        jMenu1.add(Metodo);

        ABC.setText("ABC");
        jMenu1.add(ABC);

        Salir.setText("Salir");
        jMenu1.add(Salir);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Stock");

        jmPedidos.setText("Politicas Stock");
        jMenu2.add(jmPedidos);

        jmRealizarPedido.setText("Pedido");
        jMenu2.add(jmRealizarPedido);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem ABC;
    private javax.swing.JMenuItem MenuCliente;
    private javax.swing.JMenuItem MenuProducto;
    private javax.swing.JMenuItem MenuProveedor;
    private javax.swing.JMenuItem Metodo;
    private javax.swing.JMenuItem Salir;
    private javax.swing.JMenuItem Ventas;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jmPedidos;
    private javax.swing.JMenuItem jmRealizarPedido;
    // End of variables declaration//GEN-END:variables

    public JMenuItem getItemCliente() {
        return MenuCliente;
    }

    public JMenuItem getMetodo() {
        return Metodo;
    }

    public void setMetodo(JMenuItem Metodo) {
        this.Metodo = Metodo;
    }

    public JMenuItem getSalir() {
        return Salir;
    }

    public void setSalir(JMenuItem Salir) {
        this.Salir = Salir;
    }

    public void setMenuCliente(JMenuItem MenuCliente) {
        this.MenuCliente = MenuCliente;
    }

    public JMenuItem getItemProducto() {
        return MenuProducto;
    }
    public void setMenuProducto(JMenuItem MenuProducto) {
        this.MenuProducto = MenuProducto;
    }

        public JMenuItem getItemProveedor() {
        return MenuProveedor;
    }

    public void setMenuProveedor(JMenuItem MenuProveedor) {
        this.MenuProveedor = MenuProveedor;
    }

    public JDesktopPane getjDesktopPane1() {
        return jDesktopPane1;
    }

    public JMenuItem getVentas() {
        return Ventas;
    }

    public void setVentas(JMenuItem Ventas) {
        this.Ventas = Ventas;
    }

    public JMenuItem getABC() {
        return ABC;
    }

    public void setABC(JMenuItem ABC) {
        this.ABC = ABC;
    }

    public JMenuItem getJmPedidos() {
        return jmPedidos;
    }

    public void setJmPedidos(JMenuItem jmPedidos) {
        this.jmPedidos = jmPedidos;
    }

    public JMenuItem getJmRealizarPedido() {
        return jmRealizarPedido;
    }

    public void setJmRealizarPedido(JMenuItem jmRealizarPedido) {
        this.jmRealizarPedido = jmRealizarPedido;
    }
    
    
    
}
