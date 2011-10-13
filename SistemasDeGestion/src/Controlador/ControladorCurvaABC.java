/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Experto.ExpertoCurvaABC;
import Experto.FabricaExperto;
import java.beans.PropertyVetoException;

/**
 *
 * @author natalia
 */
public class ControladorCurvaABC {
    private ControladorPrincipal controladorPrincipal;
    private ExpertoCurvaABC exp;
    
    public ControladorCurvaABC(ControladorPrincipal controladorPrincipal) throws PropertyVetoException {
        this.controladorPrincipal = controladorPrincipal;
        exp = (ExpertoCurvaABC) FabricaExperto.getInstancia().FabricarExperto("ExpertoCurvaABC");
        System.out.println("  Controlador Informe ABC Creado...");        
    }

    public void iniciar() {
        exp.iniciar();
    }
    
}
