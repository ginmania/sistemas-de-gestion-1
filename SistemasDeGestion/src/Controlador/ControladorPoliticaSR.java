/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Experto.ExpertoPoliticaSR;
import Experto.ExpertoProveedor;
import Experto.FabricaExperto;
import Interfaces.Proveedor;
import Pantalla.PantallaPolitica;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.JComboBox;

/**
 *
 * @author duende
 */
public class ControladorPoliticaSR {
    private ControladorPrincipal objCG;
    private PantallaPolitica objGUIPolSR;
    private ExpertoPoliticaSR objEPSR;
    private ExpertoProveedor expProv;
    private ArrayList<Proveedor> provs;

    public ControladorPoliticaSR(ControladorPrincipal obj){
        this.objCG = obj;
        objEPSR = (ExpertoPoliticaSR)FabricaExperto.getInstancia().FabricarExperto("ExpertoPoliticaSR");
        objGUIPolSR = new PantallaPolitica(this);
        objGUIPolSR.setVisible(true);
        //this.iniciarVPsr();
        objCG.add(objGUIPolSR);
        
        objGUIPolSR.getJbAceptar().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                 objGUIPolSR.Aceptar();
            }
        });
        
        objGUIPolSR.getJcProveedores().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                objGUIPolSR.AccionCombo();
            }
        });
        
        objGUIPolSR.getJbEliminar().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                objGUIPolSR.Eliminar();
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
        expProv = new ExpertoProveedor();
        //this.iniciar();
        objEPSR.iniciar();
        objEPSR.verificarPolitica();
    }

    public void iniciar() {    
        objEPSR.iniciar();
        provs = expProv.ListarProveedor();         
        for(int i=0; i< provs.size();i++){
            objGUIPolSR.getJcProveedores().addItem(provs.get(i).getNombre());
        }                
    }

    public Object[][] getProductosPrev(int posVec){
        return objEPSR.getProductosPrev(objGUIPolSR.getJcProveedores().getSelectedIndex());
    }

    public void setPedidoManual(String [][]tabla){
        objEPSR.setPedidoManual(tabla);
    }
}
