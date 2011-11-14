package Pantalla;

import Controlador.ControladorPrincipal;
import javax.swing.JDesktopPane;
import javax.swing.JLabel;
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
        labelAlfa = new javax.swing.JLabel();
        labelBeta = new javax.swing.JLabel();
        labelGama = new javax.swing.JLabel();
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
        menuParametros = new javax.swing.JMenu();
        itemEstablecer = new javax.swing.JMenuItem();
        jmRealizarPedido = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sistemas de Gestion 1");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setMinimumSize(new java.awt.Dimension(1200, 600));
        setName("Pantalla Principal"); // NOI18N
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jDesktopPane1.setBackground(new java.awt.Color(172, 150, 102));

        labelAlfa.setForeground(new java.awt.Color(255, 0, 0));
        labelAlfa.setText("Alfa:");
        labelAlfa.setBounds(1276, 10, 120, 17);
        jDesktopPane1.add(labelAlfa, javax.swing.JLayeredPane.DEFAULT_LAYER);

        labelBeta.setForeground(new java.awt.Color(255, 0, 0));
        labelBeta.setText("Beta:");
        labelBeta.setBounds(1270, 30, 110, 17);
        jDesktopPane1.add(labelBeta, javax.swing.JLayeredPane.DEFAULT_LAYER);

        labelGama.setForeground(new java.awt.Color(255, 0, 0));
        labelGama.setText("Gama:");
        labelGama.setBounds(1262, 50, 100, 17);
        jDesktopPane1.add(labelGama, javax.swing.JLayeredPane.DEFAULT_LAYER);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(jDesktopPane1, gridBagConstraints);

        jMenu1.setText("Sistema");
        jMenu1.setMargin(new java.awt.Insets(0, 10, 0, 0));

        MenuCliente.setText("Cliente");
        MenuCliente.setMargin(new java.awt.Insets(0, 10, 0, 0));
        jMenu1.add(MenuCliente);

        MenuProducto.setText("Producto");
        MenuProducto.setMargin(new java.awt.Insets(0, 10, 0, 0));
        jMenu1.add(MenuProducto);

        MenuProveedor.setText("Proveedor");
        MenuProveedor.setMargin(new java.awt.Insets(0, 10, 0, 0));
        jMenu1.add(MenuProveedor);

        Ventas.setText("Ventas");
        Ventas.setMargin(new java.awt.Insets(0, 10, 0, 0));
        jMenu1.add(Ventas);

        Metodo.setText("Metodo");
        Metodo.setMargin(new java.awt.Insets(0, 10, 0, 0));
        jMenu1.add(Metodo);

        ABC.setText("ABC");
        ABC.setMargin(new java.awt.Insets(0, 10, 0, 0));
        jMenu1.add(ABC);

        Salir.setText("Salir");
        Salir.setMargin(new java.awt.Insets(0, 10, 0, 0));
        jMenu1.add(Salir);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Stock");
        jMenu2.setMargin(new java.awt.Insets(0, 10, 0, 0));

        jmPedidos.setText("Politicas Stock");
        jmPedidos.setMargin(new java.awt.Insets(0, 10, 0, 0));
        jMenu2.add(jmPedidos);

        jMenuBar1.add(jMenu2);

        menuParametros.setText("Parametros");
        menuParametros.setMargin(new java.awt.Insets(0, 10, 0, 0));

        itemEstablecer.setText("Establecer");
        itemEstablecer.setMargin(new java.awt.Insets(0, 10, 0, 0));
        menuParametros.add(itemEstablecer);

        jmRealizarPedido.setText("Pedido");
        jmRealizarPedido.setMargin(new java.awt.Insets(0, 10, 0, 0));
        menuParametros.add(jmRealizarPedido);

        jMenuBar1.add(menuParametros);

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
    private javax.swing.JMenuItem itemEstablecer;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jmPedidos;
    private javax.swing.JMenuItem jmRealizarPedido;
    private javax.swing.JLabel labelAlfa;
    private javax.swing.JLabel labelBeta;
    private javax.swing.JLabel labelGama;
    private javax.swing.JMenu menuParametros;
    // End of variables declaration//GEN-END:variables

    public JLabel getLabelAlfa() {
        return labelAlfa;
    }

    public void setLabelAlfa(JLabel labelAlfa) {
        this.labelAlfa = labelAlfa;
    }

    public JLabel getLabelBeta() {
        return labelBeta;
    }

    public void setLabelBeta(JLabel labelBeta) {
        this.labelBeta = labelBeta;
    }

    public JLabel getLabelGama() {
        return labelGama;
    }

    public void setLabelGama(JLabel labelGama) {
        this.labelGama = labelGama;
    }

    public JMenuItem getItemEstablecer() {
        return itemEstablecer;
    }

    public void setItemEstablecer(JMenuItem menuEstablecer) {
        this.itemEstablecer = menuEstablecer;
    }

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
