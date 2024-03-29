/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Excepciones.NoProductoExcepcion;
import Experto.ExpertoMetodos;
import Experto.ExpertoParametros;
import Experto.FabricaExperto;
import Interfaces.Demanda;
import Interfaces.Parametros;
import Interfaces.Producto;
import Metodo.Periodo;
import Pantalla.PantallaMetodos;
import Persistencia.ConvertirFechas;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author malayo
 */
public class ControladorMetodos {

    private ExpertoMetodos expertoMetodos;
    private ControladorPrincipal controladorPrincipal;
    private PantallaMetodos pantallaMetodos;
    private double alfa, beta, gama;
    private Date fechadesde, fechahasta, fechadesdetendencia, fechahastatendencia, fechadesdeestacionalidad, fechahastaestacionalidad;
    private Date perinicial, perfinal;
    private String periodoinicial = "01012010";
    private String periodofinal = "31122010";
    private Periodo periodo = new Periodo();
    private int diferenciadeperiodos, valorperiodoinicial, valorperiodofinal, periodosapredecir;
    private String[][] resultadosimple;
    private String[][] resultadotendencia;
    private String[][] resultadoestacionalidad;
    private List<Producto> vectorProductos = new ArrayList<Producto>();
    private String[][] vectorDemandas = null;
    private List<Demanda> vectorDemandas2 = new ArrayList<Demanda>();
    private List<Producto> productobuscado = new ArrayList<Producto>();
    private String valorItemSeleccionado = "";
    private String[][] vectorDemandaensimple = null;
    private String[][] vectorDemandaentendencia = null;
    private String[][] vectorDemandaenestacionalidad = null;
    private String seleccionado;
    private final ControladorParametros controladorParametros;
    private List<Parametros> vectorParametros = new ArrayList<Parametros>();

    public ControladorMetodos(ControladorPrincipal controladorPrincipal) throws NoProductoExcepcion {
        this.controladorPrincipal = controladorPrincipal;
        controladorParametros = new ControladorParametros(controladorPrincipal);
        expertoMetodos = (ExpertoMetodos) FabricaExperto.getInstancia().FabricarExperto("ExpertoMetodos");
        pantallaMetodos = new PantallaMetodos(null, true);
        pantallaMetodos.setLocationRelativeTo(null);
        vectorParametros = controladorParametros.buscarParametros();
        alfa = vectorParametros.get(0).getAlfa();
        beta = vectorParametros.get(0).getBeta();
        gama = vectorParametros.get(0).getGama();
        System.out.println("Alfa: " + alfa);
        System.out.println("Beta: " + beta);
        System.out.println("Gama: " + gama);
        /////////BOTON METODO CONSULTAR//////////
        pantallaMetodos.getBotonConsultar().addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    seleccionado = pantallaMetodos.getComboProductos().getSelectedItem().toString();
                    vectorDemandas = buscarDemandaPronosticada(seleccionado);
                    String auxiliar1 = "";
                    String auxiliar2 = "";
                    String auxiliar3 = "Demanda real: ";
                    String auxiliar4 = "  Demanda pronosticada: ";
                    String auxiliar5 = "  periodo: ";
                    String auxiliar6 = "  año: ";
                    for (int i = 0; i < vectorDemandas.length; i++) {
                        auxiliar1 = auxiliar3 + vectorDemandas[i][0] + auxiliar4 + vectorDemandas[i][1] + auxiliar5 + vectorDemandas[i][2] + auxiliar6 + vectorDemandas[i][3] + "    \n";
                        auxiliar2 += auxiliar1;
                    }
                    pantallaMetodos.getAreaResultado().setText(auxiliar2);

                } catch (Exception ex) {
                    Logger.getLogger(ControladorProducto.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        /////////BOTON METODO GUARDAR//////////
        pantallaMetodos.getBotonGuardar().addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    guardar();
                } catch (Exception ex) {
                    Logger.getLogger(ControladorProducto.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        /////////BOTON METODO CANCELAR//////////
        pantallaMetodos.getBotonCancelarMetodos().addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    pantallaMetodos.dispose();

                } catch (Exception ex) {
                    Logger.getLogger(ControladorProducto.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        /////////BOTON METODO SIMPLE//////////
        pantallaMetodos.getBotonSimple().addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
            }
        });
        /////////BOTON METODO TENDENCIA//////////
        pantallaMetodos.getBotonTendencia().addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
            }
        });

        /////////BOTON METODO ESTACIONALIDAD//////////
        pantallaMetodos.getBotonEstacionalidad().addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
            }
        });

        /////////BOTON CALCULAR CALCULO METODO//////////
        pantallaMetodos.getBotonCalcularMetodos().addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (pantallaMetodos.getTxPeriodo().getText().equalsIgnoreCase("")) {
                    JOptionPane.showMessageDialog(pantallaMetodos, "Debe ingresar la cantidad de periodos a predecir", "¡Atención!", JOptionPane.INFORMATION_MESSAGE);
                }
                ///PARA EL METODO SIMPLE ///
                if (!pantallaMetodos.getBotonSimple().isSelected() && !pantallaMetodos.getBotonTendencia().isSelected() && !pantallaMetodos.getBotonTendencia().isSelected()) {
                    JOptionPane.showMessageDialog(pantallaMetodos, "Debe seleccionar un metodo!", "¡Atención!", JOptionPane.INFORMATION_MESSAGE);
                }

                if (pantallaMetodos.getBotonSimple().isSelected()) {
                    Object itemSeleccionado = pantallaMetodos.getComboProductos().getSelectedItem();
                    valorItemSeleccionado = itemSeleccionado.toString();
                    System.out.println("El producto seleccionado es: " + valorItemSeleccionado);
                    fechadesde = pantallaMetodos.getTxFechadesde().getDate();
                    fechahasta = pantallaMetodos.getTxFechahasta().getDate();
                    try {
                        diferenciadeperiodos = periodo.getPeriodos(fechadesde, fechahasta);
                    } catch (Exception ex) {
                        Logger.getLogger(ControladorMetodos.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    perinicial = ConvertirFechas.stringAFecha(periodoinicial);
                    try {
                        valorperiodoinicial = periodo.getPeriodos(perinicial, fechadesde) + 1;
                    } catch (Exception ex) {
                        Logger.getLogger(ControladorMetodos.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    perfinal = ConvertirFechas.stringAFecha(periodofinal);
                    valorperiodofinal = valorperiodoinicial + diferenciadeperiodos;
                    periodosapredecir = Integer.parseInt(pantallaMetodos.getTxPeriodo().getText());
                    resultadosimple = new String[calcularsimple(alfa, valorperiodofinal, valorItemSeleccionado, valorperiodoinicial, valorperiodofinal, periodosapredecir).length][2];
                    resultadosimple = calcularsimple(alfa, diferenciadeperiodos, valorItemSeleccionado, valorperiodoinicial, valorperiodofinal, periodosapredecir);
                    String cadena = "";
                    String cadena2 = "";
                    String cadena3 = "";
                    String cadena4 = "";
                    pantallaMetodos.getAreaResultado().setText(cadena2);
                    pantallaMetodos.getAreaResultado2().setText(cadena4);
                    for (int i = 0; i < resultadosimple.length; i++) {
                        if (!(resultadosimple[i][1] == null)) {
                            cadena = resultadosimple[i][1] + "    \n";
                            cadena2 += cadena;
                        }
                    }
                    for (int i = 0; i < periodosapredecir; i++) {
                        if (!(resultadosimple[i][0] == null)) {
                            cadena = resultadosimple[i][0] + "    \n";
                            cadena2 += cadena;
                        }
                    }
                    pantallaMetodos.getAreaResultado().setText(cadena2);
                    if (pantallaMetodos.getBotonDE().isSelected()) {
                        for (int i = 0; i < resultadosimple.length; i++) {
                            if (!(resultadosimple[i][2] == null)) {
                                cadena3 = resultadosimple[i][2] + "    \n";
                                cadena4 += cadena3;
                            }
                        }
                        pantallaMetodos.getAreaResultado2().setText(cadena4);
                    }
                    if (pantallaMetodos.getBotonSR().isSelected()) {
                        for (int i = 0; i < resultadosimple.length; i++) {
                            if (!(resultadosimple[i][3] == null)) {
                                cadena3 = resultadosimple[i][3] + "    \n";
                                cadena4 += cadena3;
                            }
                        }
                        pantallaMetodos.getAreaResultado2().setText(cadena4);
                    }
                    cadena = "";
                    cadena2 = "";
                    cadena3 = "";
                    cadena4 = "";
                }
                ///PARA EL METODO TENDENCIA ///

                if (pantallaMetodos.getBotonTendencia().isSelected()) {
                    Object itemSeleccionado = pantallaMetodos.getComboProductos().getSelectedItem();
                    valorItemSeleccionado = itemSeleccionado.toString();
                    System.out.println("El producto seleccionado es: " + valorItemSeleccionado);
                    //obtener los datos de las fechas y calculo de periodos y luego llamar a metodosimple
                    fechadesde = pantallaMetodos.getTxFechadesde().getDate();
                    fechahasta = pantallaMetodos.getTxFechahasta().getDate();
                    try {
                        diferenciadeperiodos = periodo.getPeriodos(fechadesde, fechahasta);
                    } catch (Exception ex) {
                        Logger.getLogger(ControladorMetodos.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    perinicial = ConvertirFechas.stringAFecha(periodoinicial);
                    try {
                        valorperiodoinicial = periodo.getPeriodos(perinicial, fechadesde) + 1;
                    } catch (Exception ex) {
                        Logger.getLogger(ControladorMetodos.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    perfinal = ConvertirFechas.stringAFecha(periodofinal);
                    valorperiodofinal = valorperiodoinicial + diferenciadeperiodos;
                    periodosapredecir = Integer.parseInt(pantallaMetodos.getTxPeriodo().getText());
                    resultadotendencia = new String[calculartendencia(alfa, valorperiodofinal, valorItemSeleccionado, valorperiodoinicial, valorperiodofinal, periodosapredecir).length][2];
                    resultadotendencia = calculartendencia(alfa, diferenciadeperiodos, valorItemSeleccionado, valorperiodoinicial, valorperiodofinal, periodosapredecir);
                    String cadena = "";
                    String cadena2 = "";
                    String cadena3 = "";
                    String cadena4 = "";
                    pantallaMetodos.getAreaResultado().setText(cadena2);
                    pantallaMetodos.getAreaResultado2().setText(cadena4);
                    for (int i = 0; i < resultadotendencia.length; i++) {
                        if (!(resultadotendencia[i][1] == null)) {
                            cadena = resultadotendencia[i][1] + "    \n";
                            cadena2 += cadena;
                        }
                    }
                    for (int i = 0; i < periodosapredecir; i++) {
                        if (!(resultadotendencia[i][0] == null)) {
                            cadena = resultadotendencia[i][0] + "    \n";
                            cadena2 += cadena;
                        }
                    }
                    pantallaMetodos.getAreaResultado().setText(cadena2);
                    if (pantallaMetodos.getBotonDE().isSelected()) {
                        for (int i = 0; i < resultadotendencia.length; i++) {
                            if (!(resultadotendencia[i][2] == null)) {
                                cadena3 = resultadotendencia[i][2] + "    \n";
                                cadena4 += cadena3;
                            }
                        }
                        pantallaMetodos.getAreaResultado2().setText(cadena4);
                    }
                    if (pantallaMetodos.getBotonSR().isSelected()) {
                        for (int i = 0; i < resultadotendencia.length; i++) {
                            if (!(resultadotendencia[i][3] == null)) {
                                cadena3 = resultadotendencia[i][3] + "    \n";
                                cadena4 += cadena3;
                            }
                        }
                        pantallaMetodos.getAreaResultado2().setText(cadena4);
                    }
                    cadena = "";
                    cadena2 = "";
                    cadena3 = "";
                    cadena4 = "";
                }

                ///PARA EL METODO ESTACIONALIDAD ///

                if (pantallaMetodos.getBotonEstacionalidad().isSelected()) {
                    Object itemSeleccionado = pantallaMetodos.getComboProductos().getSelectedItem();
                    valorItemSeleccionado = itemSeleccionado.toString();
                    System.out.println("El producto seleccionado es: " + valorItemSeleccionado);
                    fechadesde = pantallaMetodos.getTxFechadesde().getDate();
                    fechahasta = pantallaMetodos.getTxFechahasta().getDate();
                    try {
                        diferenciadeperiodos = periodo.getPeriodos(fechadesde, fechahasta);
                    } catch (Exception ex) {
                        Logger.getLogger(ControladorMetodos.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    perinicial = ConvertirFechas.stringAFecha(periodoinicial);
                    try {
                        valorperiodoinicial = periodo.getPeriodos(perinicial, fechadesde) + 1;
                    } catch (Exception ex) {
                        Logger.getLogger(ControladorMetodos.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    perfinal = ConvertirFechas.stringAFecha(periodofinal);
                    valorperiodofinal = valorperiodoinicial + diferenciadeperiodos;
                    periodosapredecir = Integer.parseInt(pantallaMetodos.getTxPeriodo().getText());
                    resultadoestacionalidad = new String[calcularestacionalidad(alfa, valorperiodofinal, valorItemSeleccionado, valorperiodoinicial, valorperiodofinal, periodosapredecir).length][2];
                    resultadoestacionalidad = calcularestacionalidad(alfa, diferenciadeperiodos, valorItemSeleccionado, valorperiodoinicial, valorperiodofinal, periodosapredecir);
                    String cadena = "";
                    String cadena2 = "";
                    String cadena3 = "";
                    String cadena4 = "";
                    pantallaMetodos.getAreaResultado().setText(cadena2);
                    pantallaMetodos.getAreaResultado2().setText(cadena4);

                    for (int i = 0; i < resultadoestacionalidad.length; i++) {
                        if (!(resultadoestacionalidad[i][1] == null)) {
                            cadena = resultadoestacionalidad[i][1] + "    \n";
                            cadena2 += cadena;
                        }
                    }
                    for (int i = 0; i < periodosapredecir; i++) {
                        if (!(resultadoestacionalidad[i][0] == null)) {
                            cadena = resultadoestacionalidad[i][0] + "    \n";
                            cadena2 += cadena;
                        }
                    }
                    pantallaMetodos.getAreaResultado().setText(cadena2);

                    if (pantallaMetodos.getBotonDE().isSelected()) {
                        for (int i = 0; i < resultadoestacionalidad.length; i++) {
                            if (!(resultadoestacionalidad[i][2] == null)) {
                                cadena3 = resultadoestacionalidad[i][2] + "    \n";
                                cadena4 += cadena3;
                            }
                        }
                        pantallaMetodos.getAreaResultado2().setText(cadena4);
                    }
                    if (pantallaMetodos.getBotonSR().isSelected()) {
                        for (int i = 0; i < resultadoestacionalidad.length; i++) {
                            if (!(resultadoestacionalidad[i][3] == null)) {
                                cadena3 = resultadoestacionalidad[i][3] + "    \n";
                                cadena4 += cadena3;
                            }
                        }
                        pantallaMetodos.getAreaResultado2().setText(cadena4);
                    }
                    cadena = "";
                    cadena2 = "";
                    cadena3 = "";
                    cadena4 = "";
                }

            }
        });
    }

    private List<Producto> buscarProducto() throws NoProductoExcepcion {
        vectorProductos = expertoMetodos.buscarProducto();
        return vectorProductos;
    }

    private void guardar() {
        expertoMetodos.guardar();
    }

    public void agregarMetodos() throws NoProductoExcepcion {
        productobuscado = buscarProducto();
        if (!productobuscado.isEmpty()) {
            for (int i = 0; i < productobuscado.size(); i++) {
                pantallaMetodos.getComboProductos().addItem(productobuscado.get(i).getNombreProducto());
            }
            pantallaMetodos.setTitle("Metodos");
            pantallaMetodos.setVisible(true);
        } else {
            System.out.println("No hay productos");
        }
    }

    private String[][] calcularsimple(double alfa, int valorperiodo, String productoSeleccionado, int valorperiodoinicial, int valorperiodofinal, int periodosapredecir) {
        vectorDemandaensimple = expertoMetodos.calcularsimple(alfa, valorperiodo, productoSeleccionado, valorperiodoinicial, valorperiodofinal, periodosapredecir);
        return vectorDemandaensimple;
    }

    private String[][] calculartendencia(double alfa, int valorperiodo, String productoSeleccionado, int valorperiodoinicial, int valorperiodofinal, int periodosapredecir) {
        vectorDemandaentendencia = expertoMetodos.calculartendencia(alfa, valorperiodo, productoSeleccionado, valorperiodoinicial, valorperiodofinal, periodosapredecir);
        return vectorDemandaentendencia;
    }

    private String[][] calcularestacionalidad(double alfa, int valorperiodo, String productoSeleccionado, int valorperiodoinicial, int valorperiodofinal, int periodosapredecir) {
        vectorDemandaenestacionalidad = expertoMetodos.calcularestacionalidad(alfa, valorperiodo, productoSeleccionado, valorperiodoinicial, valorperiodofinal, periodosapredecir);
        return vectorDemandaenestacionalidad;
    }

    private String[][] buscarDemandaPronosticada(String seleccionado) throws NoProductoExcepcion {
        vectorDemandas = expertoMetodos.buscarDemandaPronosticada(seleccionado);
        return vectorDemandas;
    }
}
