/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Excepciones.NoProductoExcepcion;
import Experto.ExpertoMetodos;
import Experto.FabricaExperto;
import Interfaces.Producto;
import Metodo.Periodo;
import Pantalla.PantallaMetodos;
import Persistencia.ConvertirFechas;
import java.awt.event.ActionEvent;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.security.pkcs.ParsingException;

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
    private String a;
    private List<Producto> vectorProductos = new ArrayList<Producto>();
    private List<Producto> productobuscado = new ArrayList<Producto>();
    private String valorItemSeleccionado = "";
    private String[][] vectorDemandaensimple = null;
    private String[][] vectorDemandaentendencia = null;
    private String[][] vectorDemandaenestacionalidad = null;

    public ControladorMetodos(ControladorPrincipal controladorPrincipal) {
        this.controladorPrincipal = controladorPrincipal;
        expertoMetodos = (ExpertoMetodos) FabricaExperto.getInstancia().FabricarExperto("ExpertoMetodos");
        pantallaMetodos = new PantallaMetodos(null, true);
        pantallaMetodos.setLocationRelativeTo(null);

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
                pantallaMetodos.getCampoAlfa().setEditable(true);
                pantallaMetodos.getCampoBeta().setEditable(false);
                pantallaMetodos.getCampoBeta().setVisible(false);
                pantallaMetodos.getCampoGama().setEditable(false);
                pantallaMetodos.getCampoGama().setVisible(false);
            }
        });
        /////////BOTON METODO TENDENCIA//////////
        pantallaMetodos.getBotonTendencia().addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pantallaMetodos.getCampoAlfa().setEditable(true);
                pantallaMetodos.getCampoBeta().setEditable(true);
                pantallaMetodos.getCampoGama().setEditable(false);
                pantallaMetodos.getCampoGama().setVisible(false);

            }
        });

        /////////BOTON METODO ESTACIONALIDAD//////////
        pantallaMetodos.getBotonEstacionalidad().addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pantallaMetodos.getCampoAlfa().setEditable(true);
                pantallaMetodos.getCampoBeta().setEditable(false);
                pantallaMetodos.getCampoBeta().setVisible(false);
                pantallaMetodos.getCampoGama().setEditable(true);
                pantallaMetodos.getCampoGama().setVisible(true);
            }
        });



        /////////BOTON CALCULAR CALCULO METODO//////////
        pantallaMetodos.getBotonCalcularMetodos().addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(ActionEvent e) {

                ///PARA EL METODO SIMPLE ///

                if (pantallaMetodos.getBotonSimple().isSelected()) {
                    Object itemSeleccionado = pantallaMetodos.getComboProductos().getSelectedItem();
                    valorItemSeleccionado = itemSeleccionado.toString();
                    System.out.println("El producto seleccionado es: " + valorItemSeleccionado);
                    a = pantallaMetodos.getCampoAlfa().getText();
                    alfa = Double.parseDouble(a);
                    fechadesde = pantallaMetodos.getTxFechadesde().getDate();
                    fechahasta = pantallaMetodos.getTxFechahasta().getDate();
                    try {
                        diferenciadeperiodos = periodo.getPeriodos(fechadesde, fechahasta);
                    } catch (ParsingException ex) {
                        Logger.getLogger(ControladorMetodos.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ParseException ex) {
                        Logger.getLogger(ControladorMetodos.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    perinicial = ConvertirFechas.stringAFecha(periodoinicial);
                    try {
                        valorperiodoinicial = periodo.getPeriodos(perinicial, fechadesde) + 1;
                    } catch (ParsingException ex) {
                        Logger.getLogger(ControladorMetodos.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ParseException ex) {
                        Logger.getLogger(ControladorMetodos.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    perfinal = ConvertirFechas.stringAFecha(periodofinal);
                    valorperiodofinal = valorperiodoinicial + diferenciadeperiodos;
                    periodosapredecir = Integer.parseInt(pantallaMetodos.getTxPeriodo().getText());
                    resultadosimple = new String[calcularsimple(alfa, valorperiodofinal, valorItemSeleccionado, valorperiodoinicial, valorperiodofinal, periodosapredecir).length][2];
                    resultadosimple = calcularsimple(alfa, diferenciadeperiodos, valorItemSeleccionado, valorperiodoinicial, valorperiodofinal, periodosapredecir);
                    String cadena = "";
                    String cadena2 = "";
                    for (int i = 0; i < resultadosimple.length; i++) {
                        cadena = resultadosimple[i][1] + "    \n";
                        cadena2 += cadena;
                    }
                    for (int i = 0; i < periodosapredecir; i++) {
                        cadena = resultadosimple[i][0] + "    \n";
                        cadena2 += cadena;
                    }


                    pantallaMetodos.getAreaResultado().setText(cadena2);
                    cadena = "";
                    cadena2 = "";

                }

                ///PARA EL METODO TENDENCIA ///

                if (pantallaMetodos.getBotonTendencia().isSelected()) {
                    Object itemSeleccionado = pantallaMetodos.getComboProductos().getSelectedItem();
                    valorItemSeleccionado = itemSeleccionado.toString();
                    System.out.println("El producto seleccionado es: " + valorItemSeleccionado);
                    a = pantallaMetodos.getCampoAlfa().getText();
                    alfa = Double.parseDouble(a);
                    //obtener los datos de las fechas y calculo de periodos y luego llamar a metodosimple
                    fechadesde = pantallaMetodos.getTxFechadesde().getDate();
                    fechahasta = pantallaMetodos.getTxFechahasta().getDate();
                    try {
                        diferenciadeperiodos = periodo.getPeriodos(fechadesde, fechahasta);
                    } catch (ParsingException ex) {
                        Logger.getLogger(ControladorMetodos.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ParseException ex) {
                        Logger.getLogger(ControladorMetodos.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    perinicial = ConvertirFechas.stringAFecha(periodoinicial);
                    try {
                        valorperiodoinicial = periodo.getPeriodos(perinicial, fechadesde) + 1;
                    } catch (ParsingException ex) {
                        Logger.getLogger(ControladorMetodos.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ParseException ex) {
                        Logger.getLogger(ControladorMetodos.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    perfinal = ConvertirFechas.stringAFecha(periodofinal);
                    valorperiodofinal = valorperiodoinicial + diferenciadeperiodos;
                    periodosapredecir = Integer.parseInt(pantallaMetodos.getTxPeriodo().getText());
                    resultadotendencia = new String[calculartendencia(alfa, valorperiodofinal, valorItemSeleccionado, valorperiodoinicial, valorperiodofinal, periodosapredecir).length][2];
                    resultadotendencia = calculartendencia(alfa, diferenciadeperiodos, valorItemSeleccionado, valorperiodoinicial, valorperiodofinal, periodosapredecir);
                    String cadena = "";
                    String cadena2 = "";
                    for (int i = 0; i < resultadotendencia.length; i++) {
                        cadena = resultadotendencia[i][1] + "    \n";
                        cadena2 += cadena;
                    }
                    for (int i = 0; i < periodosapredecir; i++) {
                        cadena = resultadotendencia[i][0] + "    \n";
                        cadena2 += cadena;
                    }


                    pantallaMetodos.getAreaResultado().setText(cadena2);
                    cadena = "";
                    cadena2 = "";
                }

                ///PARA EL METODO ESTACIONALIDAD ///

                if (pantallaMetodos.getBotonEstacionalidad().isSelected()) {
                    Object itemSeleccionado = pantallaMetodos.getComboProductos().getSelectedItem();
                    valorItemSeleccionado = itemSeleccionado.toString();
                    System.out.println("El producto seleccionado es: " + valorItemSeleccionado);
                    a = pantallaMetodos.getCampoAlfa().getText();
                    alfa = Double.parseDouble(a);
                    fechadesde = pantallaMetodos.getTxFechadesde().getDate();
                    fechahasta = pantallaMetodos.getTxFechahasta().getDate();
                    try {
                        diferenciadeperiodos = periodo.getPeriodos(fechadesde, fechahasta);
                    } catch (ParsingException ex) {
                        Logger.getLogger(ControladorMetodos.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ParseException ex) {
                        Logger.getLogger(ControladorMetodos.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    perinicial = ConvertirFechas.stringAFecha(periodoinicial);
                    try {
                        valorperiodoinicial = periodo.getPeriodos(perinicial, fechadesde) + 1;
                    } catch (ParsingException ex) {
                        Logger.getLogger(ControladorMetodos.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ParseException ex) {
                        Logger.getLogger(ControladorMetodos.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    perfinal = ConvertirFechas.stringAFecha(periodofinal);
                    valorperiodofinal = valorperiodoinicial + diferenciadeperiodos;
                    periodosapredecir = Integer.parseInt(pantallaMetodos.getTxPeriodo().getText());
                    resultadoestacionalidad = new String[calcularestacionalidad(alfa, valorperiodofinal, valorItemSeleccionado, valorperiodoinicial, valorperiodofinal, periodosapredecir).length][2];
                    resultadoestacionalidad = calcularestacionalidad(alfa, diferenciadeperiodos, valorItemSeleccionado, valorperiodoinicial, valorperiodofinal, periodosapredecir);
                    String cadena = "";
                    String cadena2 = "";
                    for (int i = 0; i < resultadoestacionalidad.length; i++) {
                        cadena = resultadoestacionalidad[i][1] + "    \n";
                        cadena2 += cadena;
                    }
                    for (int i = 0; i < periodosapredecir; i++) {
                        cadena = resultadoestacionalidad[i][0] + "    \n";
                        cadena2 += cadena;
                    }
                    pantallaMetodos.getAreaResultado().setText(cadena2);
                    cadena = "";
                    cadena2 = "";
                }

            }
        });


        ///FOCO PARA EL BETA EN EL METODO TENDENCIA ////////
        pantallaMetodos.getCampoBeta().addFocusListener(new java.awt.event.FocusAdapter() {

            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                a = pantallaMetodos.getCampoAlfa().getText();
                alfa = Double.parseDouble(a);
                beta = alfa / 2;
                pantallaMetodos.getCampoBeta().setText(String.valueOf(beta));

            }
        });


        ///FOCO PARA EL GAMMA EN EL METODO ESTACIONALIDAD ////////
        pantallaMetodos.getCampoGama().addFocusListener(new java.awt.event.FocusAdapter() {

            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                a = pantallaMetodos.getCampoAlfa().getText();
                alfa = Double.parseDouble(a);
                gama = alfa * 2;
                pantallaMetodos.getCampoGama().setText(String.valueOf(gama));

            }
        });
    }

    private List<Producto> buscarProducto() throws NoProductoExcepcion {
        vectorProductos = expertoMetodos.buscarProducto();
        return vectorProductos;
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
}
