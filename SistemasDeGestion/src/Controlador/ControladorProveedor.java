 /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Agentes.AgenteProveedor;
import Experto.ExpertoProveedor;
import Experto.FabricaExperto;
import Pantalla.PantallaProveedor;
import Excepciones.NoProveedorExcepcion;
import Interfaces.Proveedor;
import Pantalla.ModeloTablaProveedor;
import Pantalla.PantallaAgregarProveedor;
import Pantalla.PantallaEliminarProveedor;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author duende
 */
public class ControladorProveedor {

    private final ControladorPrincipal controladorPrincipal;
    private final ExpertoProveedor expertoproveedor;
    private final PantallaProveedor pantalla;
    private PantallaAgregarProveedor NuevoProveedor;
    private PantallaAgregarProveedor ModificarProveedor;
    private PantallaEliminarProveedor EliminarProveedor;
    private Proveedor ProvSeleccionado;

    public ControladorProveedor(ControladorPrincipal controladorPrincipal) {
        this.controladorPrincipal = controladorPrincipal;
        pantalla = new PantallaProveedor();     
        expertoproveedor = (ExpertoProveedor) FabricaExperto.getInstancia().FabricarExperto("ExpertoProveedor");
        
        pantalla.getTabProveedores().getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent lse) {
                pantalla.getModificar().setEnabled(true);
                pantalla.getEliminar().setEnabled(true);
            }
        });       
        
        //método lanzado al hacer click en 'Buscar'
        pantalla.getBuscar().addActionListener(
                new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                            buscarProveedor(pantalla.getTextBuscar());                        
                    }
        });        
        
        //método lanzado al clickear en 'Agregar'
        
        pantalla.getAgregar().addActionListener(
                new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        try {
                            agregarProveedor();
                        } catch (NoProveedorExcepcion ex) {
                            Logger.getLogger(ControladorProveedor.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    
        });
        
        //método lanzado al clickear en 'Eliminar'
        pantalla.getEliminar().addActionListener(
                new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        String Codigo = pantalla.getTextEliminar();
                        if (!Codigo.isEmpty()){
                        try {
                            EliminarProveedor(Codigo);
                        } catch (NoProveedorExcepcion ex) {
                            Logger.getLogger(ControladorProveedor.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        }
                    }
                    
        });
        
        //método lanzado al clickear en 'Modificar'
        pantalla.getModificar().addActionListener(
                new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        String Codigo = pantalla.getTextEliminar();
                        if (Codigo != null){                        
                            ModificarProveedor(Codigo);                       
                        }else{
                            System.err.print("Ingrese el código del proveedor");
                        }
                    }
                    
        });
    }

public void agregarProveedor() throws NoProveedorExcepcion{
    NuevoProveedor = new PantallaAgregarProveedor();
    NuevoProveedor.setVisible(true);        
    controladorPrincipal.add(NuevoProveedor);      
    NuevoProveedor.toFront();        
    NuevoProveedor.getAgregar().addActionListener(
            new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {      
                    //Nombre,CUIT,Direccion,Telefono,email
                       expertoproveedor.InsertarProveedor(NuevoProveedor.getTxNombre().getText(),
                                NuevoProveedor.getTxCUIT().getText(),
                                NuevoProveedor.getTxDir().getText(),
                                NuevoProveedor.getTxFono().getText(),
                                NuevoProveedor.getTxEmail().getText());                         
                        buscarProveedor("");                    
                }  

    });
}
    
    //busca un único proveedor por nombre
private void buscarProveedor(String busca){
    ArrayList<Proveedor> Proveedores = new ArrayList<Proveedor>();
    try {        
        Proveedores = expertoproveedor.buscarProveedor("Nombre",'%'+busca+'%',"like");
        DibujarTabla(Proveedores);             
      } catch (NullPointerException e) {
        System.out.println("Excepcion controlador buscar proveedor método buscar proveedor: \n" + e.toString());        
   }  
}


//este método inicia la modificación de un proveedor
private void ModificarProveedor(String codigo){       
    final int i = 0;
    try{        
        //lleno la pantalla de confirmación            
            ProvSeleccionado = ((ModeloTablaProveedor) pantalla.getTabProveedores().getModel()).getProveedor(pantalla.getTabProveedores().getSelectedRow());
            //setea la pantalla de modificacion            
            setPantallaAgregarProveedor(ProvSeleccionado);
            //espera a que presione el 'MODIFICAR'
            ModificarProveedor.getAgregar().addActionListener(
                    new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        try {
                            actualizarCampos();                            
                            expertoproveedor.ModificarProveedor(ProvSeleccionado); 
                            ModificarProveedor.setVisible(false);
                            buscarProveedor(" ");
                        } catch (NullPointerException e) {
                            System.out.println("Excepcion: \n"+this.getClass().getName()+"método Modifica Proveedor" + e.toString());                            
                      } 
                    }}
                    );          
    }catch (NullPointerException e){
        System.out.println("Excepcion ControladorProveedor método modificar: \t"+e.toString());        
    }
}

//comienza la eliminación
private void EliminarProveedor(String codigo) throws NoProveedorExcepcion{   
    EliminarProveedor = new PantallaEliminarProveedor();
    final Proveedor Proveedor;
    final int i = 0, codigoProv;
    try{
        Proveedor = expertoproveedor.buscarUnProveedor("Codigo_Proveedor",codigo,"=");
        //llenarDTOProveedor(Proveedor);
        //lleno la pantalla de confirmación
        if(Proveedor != null){    
            boolean ok = EliminarProveedor.FormatearPantalla(Proveedor, "Eliminar Proveedor", "Desea eliminar este proveedor?");
            codigoProv = Proveedor.getCodigo_proveedor();            
            EliminarProveedor.setVisible(true);
            EliminarProveedor.setClosable(true);
            controladorPrincipal.add(EliminarProveedor);
            EliminarProveedor.toFront();
            EliminarProveedor.getBtAceptar().addActionListener(
                    new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        try {
                            expertoproveedor.EliminarProveedor(Proveedor);   
                            EliminarProveedor.setVisible(false);
                            pantalla.getTabProveedores().removeAll();
                            buscarProveedor("");
                        } catch (NullPointerException e) {
                            System.out.println("Excepcion: \n"+this.getClass().getName()+"método EliminarProveedor" + e.toString());
                            throw new NullPointerException();
                      } 
                    }}
                    );  
        }
    }catch (NullPointerException e){
        System.out.println("Excepcion ControladorProveedor método Eliminar: \t"+e.toString());        
    }
    
}

//de array a DTO
private DTOProveedor[] llenarDTOProveedor(ArrayList<Proveedor> Proveedor){
    int i = 0,j=Proveedor.size();
    DTOProveedor[] nuevo = new DTOProveedor[j];
    while (Proveedor.size() != i) {            
            nuevo[i].setCodProveedor(Proveedor.get(i).getCodigo_proveedor());
            nuevo[i].setNombreProveedor( Proveedor.get(i).getNombre());
            nuevo[i].setDireccion(Proveedor.get(i).getDireccion());
            nuevo[i].setTelefono(Proveedor.get(i).getTelefono());
            nuevo[i].setCUIT(Proveedor.get(i).getCUIT());
            nuevo[i].setEmail(Proveedor.get(i).getEmail());
            i++;            
        }  
    return nuevo;
}

//de proveedor a DTO
private DTOProveedor llenarDTOProveedor(Proveedor Proveedor){
    DTOProveedor nuevo = new DTOProveedor();
    if (Proveedor != null) {        
            //nuevo.setOIDProveedor(Proveedor.get);
            nuevo.setCodProveedor(Proveedor.getCodigo_proveedor());
            nuevo.setNombreProveedor( Proveedor.getNombre());
            nuevo.setDireccion(Proveedor.getDireccion());
            nuevo.setTelefono(Proveedor.getTelefono());
            nuevo.setCUIT(Proveedor.getCUIT());
            nuevo.setEmail(Proveedor.getEmail());                       
        }  
    return nuevo;
}

//este método agrega el resultado de la búsqueda a la tabla
private void DibujarTabla(ArrayList<Proveedor> Proveedores){               
    ModeloTablaProveedor mtc = new ModeloTablaProveedor();           
    mtc.setProveedores(Proveedores);
    pantalla.getTabProveedores().setModel(mtc);          
    pantalla.getModificar().setEnabled(false);
    pantalla.getEliminar().setEnabled(false);
}

//seteo los datos a la pantalla
private void setPantallaAgregarProveedor(Proveedor proveedor){
    ModificarProveedor = new PantallaAgregarProveedor();
    ModificarProveedor.setCodigo_Proveedor(String.valueOf(proveedor.getCodigo_proveedor()));
    ModificarProveedor.setTxNombre(proveedor.getNombre());
    ModificarProveedor.setTxCUIT(proveedor.getCUIT());
    ModificarProveedor.setTxDir(proveedor.getDireccion());
    ModificarProveedor.setTxEmail(proveedor.getEmail());
    ModificarProveedor.setTxFono(proveedor.getTelefono());            
    ModificarProveedor.setTitle("Modificar Proveedor");
    ModificarProveedor.getAgregar().setText("Modificar");
    ModificarProveedor.setClosable(true);
    ModificarProveedor.setVisible(true);            
    controladorPrincipal.add(ModificarProveedor);
    ModificarProveedor.toFront();
}

public void actualizarCampos() {
        ProvSeleccionado.setCodigo_proveedor(Integer.parseInt(ModificarProveedor.getCodigo_Proveedor()));
        ProvSeleccionado.setNombre(ModificarProveedor.getTxNombre().getText());
        ProvSeleccionado.setCUIT(ModificarProveedor.getTxCUIT().getText());
        ProvSeleccionado.setDireccion(ModificarProveedor.getTxDir().getText());
        ProvSeleccionado.setTelefono(ModificarProveedor.getTxFono().getText());
        ProvSeleccionado.setEmail(ModificarProveedor.getTxEmail().getText());
    }

public void AdministrarProveedor() {
    pantalla.setVisible(true);        
    controladorPrincipal.add(pantalla);
}

}
