/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Agentes.AgenteProducto;
import Agentes.AgenteProveedor;
import Experto.ExpertoPoliticaSQ;
import Experto.ExpertoPoliticaSR;
import Experto.ExpertoProducto;
import Experto.ExpertoProveedor;
import Experto.ExpertoRealizarPedido;
import Experto.FabricaEntidad;
import Experto.FabricaExperto;
import Interfaces.Producto;
import Interfaces.Proveedor;
import Pantalla.PantallaPolitica;
import java.awt.AWTEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author duende
 */
public class ControladorPoliticaSR {
    private ControladorPrincipal objCG;
    private PantallaPolitica objGUIPolSR;
    private ExpertoPoliticaSR objEPSR;
    private ExpertoPoliticaSQ objEPSQ;
    private ExpertoProveedor expProv; 
    private ExpertoProducto expProd;
    private ExpertoRealizarPedido expPed;
    private ArrayList<Proveedor> provs;
    private Hashtable proveedores;
    private Hashtable productos;
    private DefaultTableModel dtm;  
    private boolean provsCargados;

    public ControladorPoliticaSR(ControladorPrincipal obj){
        this.objCG = obj;
        proveedores = new Hashtable();
        productos = new Hashtable();       
        provsCargados = false;
        Object[][] data = new Object[0][6];
        String[] columnNames = {"Codigo","Nombre","Politica", "Precio", "Stock Actual", "Lote"};
         dtm = new javax.swing.table.DefaultTableModel(data, columnNames);
        objEPSR = (ExpertoPoliticaSR)FabricaExperto.getInstancia().FabricarExperto("ExpertoPoliticaSR");
        objEPSQ = (ExpertoPoliticaSQ)FabricaExperto.getInstancia().FabricarExperto("ExpertoPoliticaSQ");
        expProd = (ExpertoProducto)FabricaExperto.getInstancia().FabricarExperto("ExpertoProducto");
        objGUIPolSR = new PantallaPolitica(this);
        objGUIPolSR.setVisible(true);
        
        objCG.add(objGUIPolSR);
        
        objGUIPolSR.getJbAceptar().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                 llenarTabla();
                 objGUIPolSR.Aceptar();
            }
        });
        
        objGUIPolSR.getJcProveedores().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(objGUIPolSR.getJcProveedores().getItemCount() >= 3)
                 buscarProductos(objGUIPolSR.getJcProveedores().getSelectedItem());
            }
        });
        
        objGUIPolSR.getJbEliminar().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                objGUIPolSR.Eliminar();
            }
        });
        
        objGUIPolSR.getJbCrearPedido().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
               //hay que hacer que lo haga para un producto en particular.
               if(!CrearPedidoPendiente())
                  JOptionPane.showMessageDialog(objGUIPolSR, "No es necesario generar pedido", "Generar Pedido", JOptionPane.ERROR_MESSAGE);
            }
        });
        
        
    }

    public DTO obtenerDTO(DTO dTO) {
        return null;
    }

    public void ingresarDatos(DTO dto) {}

    public void cerrarGUI() {
        objGUIPolSR.setVisible(false);        
        objCG.remove(this.objGUIPolSR);
        //objGUIPolSR.getJcProveedores().removeAllItems();
    }

    public void iniciarVPsr(){ //Verifica la polÃ­tica(s,r)...
        //expProv = new ExpertoProveedor();
        //this.iniciar();
        objEPSR.iniciar(objCG.fechaSistema);
        objEPSR.verificarPolitica();
    }

    public void iniciar() {    
        objEPSR.iniciar(objCG.fechaSistema);
        provs = expProv.ListarProveedor(); 
        
        for(int i=0; i< provs.size();i++){
            objGUIPolSR.getJcProveedores().addItem(provs.get(i).getNombre());
            proveedores.put(provs.get(i).getNombre(), provs.get(i));
        }   
        provsCargados = true;
    }

    public Object[][] getProductosPrev(int posVec){
        return objEPSR.getProductosPrev(objGUIPolSR.getJcProveedores().getSelectedIndex());
    }

    public void setPedidoManual(String [][]tabla){
        objEPSR.setPedidoManual(tabla);
    }
    
    
    private void buscarProductos(Object selectedItem) {
       String prov = (String) selectedItem;
       ArrayList<Producto> prods;
       //limpio la tabla al cambiar de proveedor
       if(provsCargados){
            limpiarTabla();
            Proveedor P = (Proveedor) proveedores.get(prov);
            prods = expProd.buscarEnCatalogo(P);
            objGUIPolSR.getJcProductos().removeAllItems();
          for(int i=0; i< prods.size();i++){
            objGUIPolSR.getJcProductos().addItem(prods.get(i).getNombreProducto());
            productos.put(prods.get(i).getNombreProducto(),prods.get(i));
           }
       }
    }
    
    private void llenarTabla(){        
        //llena la tabla de acuerdo a como deberia hacerse el pedido
        String index = (String) objGUIPolSR.getJcProveedores().getSelectedItem();
        Proveedor P = (Proveedor) proveedores.get(index);
        String item = (String) objGUIPolSR.getJcProductos().getSelectedItem();
        Producto p = (Producto) productos.get(item);
        AgenteProducto ap = (AgenteProducto) p;
        String politica = new String();
        
        //cambiar para permitir el envio de un producto y de un proveedor en particular
        int lote = -1;
        //lote = objEPSR.calcularLote(p,P);
        if(ap.getOIDPolitica().equals("1")){
           lote = objEPSR.calcularLote(p,P);
           politica = "S,R";
        }else{
            //si es la politica S,Q llamo al otro experto
           lote = objEPSQ.calcularLoteOptimo(p, P);
           politica = "S,Q";
        }
        //lleno la tabla del detalle en pantalla
        Object[] newRow = new Object[6];
        newRow[0] = p.getCodigoProducto();
        newRow[1] = p.getNombreProducto();
        newRow[2] = politica;
        newRow[3] = p.getPrecioCompra();
        newRow[4] = p.getStock().getCantidad() + p.getStock().getStockPendiente();        
        newRow[5] = lote;
        dtm.addRow(newRow);
        
        objGUIPolSR.getJtTabla().setModel(dtm);
    }
    
    private boolean CrearPedidoPendiente(){
        expPed = (ExpertoRealizarPedido)FabricaExperto.getInstancia().FabricarExperto("ExpertoRealizarPedido");
        ArrayList<Producto> prods = new ArrayList<Producto>();
        Hashtable cantidad = new Hashtable();
        String cbProv = (String) objGUIPolSR.getJcProveedores().getSelectedItem();
        Proveedor proveedor = (Proveedor) proveedores.get(cbProv);
        Date fechaEmision = objGUIPolSR.getJdFecha().getDate();
        int detalles = objGUIPolSR.getJtTabla().getRowCount();
        for(int i=0; i< detalles;i++){
            //el producto
           String prod = (String) objGUIPolSR.getJtTabla().getValueAt(i, 1);
           Producto p = (Producto) FabricaEntidad.getInstancia().FabricarEntidad(Producto.class);
           p = (Producto) productos.get(prod);
           prods.add(p);
           //el lote
           int cant = (Integer) objGUIPolSR.getJtTabla().getValueAt(i, 5);
           if(cant<=0) return false;
           cantidad.put(p.getCodigoProducto(), cant);
        }
        if(expPed.CrearPedidoPendiente(fechaEmision, proveedor, prods, cantidad)){
            JOptionPane.showMessageDialog(objGUIPolSR, "Se creo un pedido pendiente", "Generar Pedido", JOptionPane.INFORMATION_MESSAGE);
            limpiarTabla();
        }else
            JOptionPane.showMessageDialog(objGUIPolSR, "Error al generar pedido", "Generar Pedido", JOptionPane.ERROR_MESSAGE);
        /*CrearPedidoPendiente(String fecha,Proveedor prov, ArrayList<Producto> prod, Hashtable cantidad)*/
        return true;
    }
    
    private void limpiarTabla(){
       int t= dtm.getRowCount()-1;
       for(int j=0; j<t;j++)
           dtm.removeRow(j);
    }
}
