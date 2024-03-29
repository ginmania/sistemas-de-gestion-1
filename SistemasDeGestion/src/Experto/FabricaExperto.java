package Experto;

import Interfaces.Producto;

public class FabricaExperto {

    private static FabricaExperto instancia;

    public static FabricaExperto getInstancia() {
        if (instancia == null) {
            instancia = new FabricaExperto();
        }
        return instancia;
    }

    public Experto FabricarExperto(String experto) {
        if (experto.equals("ExpertoCliente")) {
            return new DecoradorCliente();
        } else if (experto.equals("ExpertoProveedor")) {
            return new DecoradorProveedor();
        } else if (experto.equals("ExpertoProducto")) {
            return new DecoradorProducto();
        } else if (experto.equals("ExpertoVentas")) {
            return new DecoradorVentas();
        } else if (experto.equals("ExpertoGestionStock")) {
            return new DecoradorGestionStock();
        } else if (experto.equals("ExpertoMetodos")) {
            return new DecoradorMetodos();
        } else if (experto.equals("ExpertoCurvaABC")) {
            return new DecoradorCurvaABC();
        } else if (experto.equals("ExpertoPoliticaSR")) {
            return new DecoradorPoliticaSR();
        } else if (experto.equals("ExpertoPoliticaSQ")) {
            return new DecoradorPoliticaSQ();
        } else if (experto.equals("ExpertoRealizarPedido")) {
            return new DecoradorRealizarPedido();
        } else if (experto.equals("ExpertoRecibirPedido")) {
            return new DecoradorRecibirPedido();
        } else if (experto.equals("ExpertoReportes")) {
            return new DecoradorReportes();
        } else if (experto.equals("ExpertoReloj")) {
            return new DecoradorReloj();
        }else if (experto.equals("ExpertoParametros")) {
            return new DecoradorParametros();
        }
        return null;
    }

    
}
