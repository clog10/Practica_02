/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prueba;

import datos.ModeloTabla;
import vista.Interfaz;

/**
 *
 * @author Carlos Loaeza
 */
public class PruebaAplicacion {
    
    public static void main(String[] args) {
        ModeloTabla model = new ModeloTabla();
        Interfaz in1 = new Interfaz(model);
        Interfaz in2 = new Interfaz(model);
        Interfaz in3 = new Interfaz(model);
        Interfaz in4 = new Interfaz(model);
        Thread h1 = new Thread(in1);
        Thread h2 = new Thread(in2);
        Thread h3 = new Thread(in3);
        Thread h4 = new Thread(in4);
        h1.start();
        h2.start();
        h3.start();
        h4.start();
    }
}
