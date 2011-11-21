package Pantalla;

import Controlador.ControladorPrincipal;
import com.toedter.calendar.JDateChooser;
import javax.swing.JDesktopPane;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JTable;

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
        jScrollPane1 = new javax.swing.JScrollPane();
        bandejaProductos = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        bandejaPedidos = new javax.swing.JTable();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        MenuCliente = new javax.swing.JMenuItem();
        MenuProducto = new javax.swing.JMenuItem();
        MenuProveedor = new javax.swing.JMenuItem();
        Ventas = new javax.swing.JMenuItem();
        Metodo = new javax.swing.JMenuItem();
        Salir = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jmPedidos = new javax.swing.JMenuItem();
        jmRealizarPedido = new javax.swing.JMenuItem();
        menuParametros = new javax.swing.JMenu();
        itemEstablecer = new javax.swing.JMenuItem();
        FechaSistema = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        menuABC = new javax.swing.JMenuItem();
        menuClientes = new javax.swing.JMenuItem();
        menuPedidos = new javax.swing.JMenuItem();
        menuProductos = new javax.swing.JMenuItem();
        menuVentas = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sistemas de Gestion 1");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setMinimumSize(new java.awt.Dimension(1200, 600));
        setName("Pantalla Principal"); // NOI18N
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jDesktopPane1.setBackground(new java.awt.Color(204, 204, 255));

        labelAlfa.setForeground(new java.awt.Color(255, 0, 0));
        labelAlfa.setText("Alfa:");
        labelAlfa.setBounds(1276, 10, 120, 15);
        jDesktopPane1.add(labelAlfa, javax.swing.JLayeredPane.DEFAULT_LAYER);

        labelBeta.setForeground(new java.awt.Color(255, 0, 0));
        labelBeta.setText("Beta:");
        labelBeta.setBounds(1270, 30, 110, 15);
        jDesktopPane1.add(labelBeta, javax.swing.JLayeredPane.DEFAULT_LAYER);

        labelGama.setForeground(new java.awt.Color(255, 0, 0));
        labelGama.setText("Gama:");
        labelGama.setBounds(1262, 50, 100, 15);
        jDesktopPane1.add(labelGama, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jScrollPane1.setBackground(new java.awt.Color(204, 204, 255));
        jScrollPane1.setViewportBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(153, 153, 255), new java.awt.Color(153, 153, 153)));

        bandejaProductos.setBackground(new java.awt.Color(204, 204, 255));
        bandejaProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        bandejaProductos.setGridColor(new java.awt.Color(204, 204, 204));
        bandejaProductos.setRowSelectionAllowed(false);
        bandejaProductos.setSelectionBackground(new java.awt.Color(204, 204, 255));
        bandejaProductos.setSelectionForeground(new java.awt.Color(204, 204, 204));
        jScrollPane1.setViewportView(bandejaProductos);
        bandejaProductos.getAccessibleContext().setAccessibleName("Bandeja");
        bandejaProductos.getAccessibleContext().setAccessibleDescription("bandeja de entrada");

        jScrollPane1.setBounds(80, 210, 510, 140);
        jDesktopPane1.add(jScrollPane1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jScrollPane2.setBackground(new java.awt.Color(204, 204, 255));
        jScrollPane2.setViewportBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(153, 153, 255), new java.awt.Color(153, 153, 153)));

        bandejaPedidos.setBackground(new java.awt.Color(204, 204, 255));
        bandejaPedidos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        bandejaPedidos.setGridColor(new java.awt.Color(204, 204, 204));
        bandejaPedidos.setRowSelectionAllowed(false);
        bandejaPedidos.setSelectionBackground(new java.awt.Color(204, 204, 255));
        bandejaPedidos.setSelectionForeground(new java.awt.Color(204, 204, 204));
        jScrollPane2.setViewportView(bandejaPedidos);

        jScrollPane2.setBounds(80, 60, 510, 140);
        jDesktopPane1.add(jScrollPane2, javax.swing.JLayeredPane.DEFAULT_LAYER);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(jDesktopPane1, gridBagConstraints);

        jMenu1.setText("Sistema");
        jMenu1.setDelay(0);
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

        Salir.setText("Salir");
        Salir.setMargin(new java.awt.Insets(0, 10, 0, 0));
        jMenu1.add(Salir);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Stock");
        jMenu2.setMargin(new java.awt.Insets(0, 10, 0, 0));

        jmPedidos.setText("Politicas Stock");
        jmPedidos.setMargin(new java.awt.Insets(0, 10, 0, 0));
        jMenu2.add(jmPedidos);

        jmRealizarPedido.setText("Pedido");
        jmRealizarPedido.setMargin(new java.awt.Insets(0, 10, 0, 0));
        jMenu2.add(jmRealizarPedido);

        jMenuBar1.add(jMenu2);

        menuParametros.setText("Parametros");
        menuParametros.setMargin(new java.awt.Insets(0, 10, 0, 0));

        itemEstablecer.setText("Establecer");
        itemEstablecer.setMargin(new java.awt.Insets(0, 10, 0, 0));
        menuParametros.add(itemEstablecer);

        FechaSistema.setText("Fecha Sistema");
        menuParametros.add(FechaSistema);

        jMenuBar1.add(menuParametros);

        jMenu3.setText("Reportes");
        jMenu3.setMargin(new java.awt.Insets(0, 10, 0, 0));

        menuABC.setText("ABC");
        menuABC.setMargin(new java.awt.Insets(0, 10, 0, 0));
        jMenu3.add(menuABC);

        menuClientes.setText("Clientes");
        menuClientes.setMargin(new java.awt.Insets(0, 10, 0, 0));
        jMenu3.add(menuClientes);

        menuPedidos.setText("Pedidos");
        menuPedidos.setMargin(new java.awt.Insets(0, 10, 0, 0));
        jMenu3.add(menuPedidos);

        menuProductos.setText("Productos");
        menuProductos.setMargin(new java.awt.Insets(0, 10, 0, 0));
        jMenu3.add(menuProductos);

        menuVentas.setText("Ventas");
        menuVentas.setMargin(new java.awt.Insets(0, 10, 0, 0));
        jMenu3.add(menuVentas);

        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem FechaSistema;
    private javax.swing.JMenuItem MenuCliente;
    private javax.swing.JMenuItem MenuProducto;
    private javax.swing.JMenuItem MenuProveedor;
    private javax.swing.JMenuItem Metodo;
    private javax.swing.JMenuItem Salir;
    private javax.swing.JMenuItem Ventas;
    private javax.swing.JTable bandejaPedidos;
    private javax.swing.JTable bandejaProductos;
    private javax.swing.JMenuItem itemEstablecer;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JMenuItem jmPedidos;
    private javax.swing.JMenuItem jmRealizarPedido;
    private javax.swing.JLabel labelAlfa;
    private javax.swing.JLabel labelBeta;
    private javax.swing.JLabel labelGama;
    private javax.swing.JMenuItem menuABC;
    private javax.swing.JMenuItem menuClientes;
    private javax.swing.JMenu menuParametros;
    private javax.swing.JMenuItem menuPedidos;
    private javax.swing.JMenuItem menuProductos;
    private javax.swing.JMenuItem menuVentas;
    // End of variables declaration//GEN-END:variables

    public JMenuItem getMenuABC() {
        return menuABC;
    }

    public void setMenuABC(JMenuItem menuABC) {
        this.menuABC = menuABC;
    }

    public JMenuItem getMenuClientes() {
        return menuClientes;
    }

    public void setMenuClientes(JMenuItem menuClientes) {
        this.menuClientes = menuClientes;
    }

    public JMenuItem getMenuPedidos() {
        return menuPedidos;
    }

    public void setMenuPedidos(JMenuItem menuPedidos) {
        this.menuPedidos = menuPedidos;
    }

    public JMenuItem getMenuProductos() {
        return menuProductos;
    }

    public void setMenuProductos(JMenuItem menuProductos) {
        this.menuProductos = menuProductos;
    }

    public JMenuItem getMenuVentas() {
        return menuVentas;
    }

    public void setMenuVentas(JMenuItem menuVentas) {
        this.menuVentas = menuVentas;
    }

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
        return menuABC;
    }

    public void setABC(JMenuItem menuABC) {
        this.menuABC = menuABC;
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

    public JTable getBandejaEntrada() {
        return bandejaPedidos;
    }

    public void setBandejaEntrada(JTable bandejaEntrada) {
        this.bandejaPedidos = bandejaEntrada;
    }

    public JTable getBandejaProductos() {
        return bandejaProductos;
    }

    public void setBandejaProductos(JTable bandejaProductos) {
        this.bandejaProductos = bandejaProductos;
    }

    public JMenuItem getFechaSistema() {
        return FechaSistema;
    }

    public void setFechaSistema(JMenuItem FechaSistema) {
        this.FechaSistema = FechaSistema;
    }

    
}
