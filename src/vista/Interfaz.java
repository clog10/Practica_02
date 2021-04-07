/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import control.ManejaDatosEst;
import datos.ModeloTabla;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;

/**
 *
 * @author Carlos Loaeza
 */
public class Interfaz extends JFrame implements ActionListener, Runnable {

    private ModeloTabla mt;
    ManejaDatosEst baseDatos;
    private JTable tabla1;
    private JPanel superior;
    private JPanel panel;
    private JPanel inferior;
    private JPanel principal;
    private JLabel titulo;
    private JLabel controlLabel;
    private JLabel numeroLabel;
    private JLabel nombreLabel;
    private JLabel sexoLabel;
    private JLabel edadLabel;
    private JLabel semestreLabel;
    private JLabel creditosLabel;
    private ButtonGroup G1;
    private JRadioButton jRadioButton1;
    private JRadioButton jRadioButton2;
    private JSpinner edadSpinner;
    private JSpinner semestreSpinner;
    private JSpinner creditosSpinner;
    private JTextField nombre;
    private JButton registrarButton;
    private JButton terminarButton;
    FormListener formListener = new FormListener();

    public Interfaz(ModeloTabla modTabla) {
        principal = new JPanel();
        principal.setLayout(new BorderLayout(0, 0));
        panel = new JPanel();
        panel.setLayout(new FlowLayout());
        superior = new JPanel();
        superior.setLayout(new BorderLayout());
        inferior = new JPanel();
        String consulta = "SELECT * FROM ADMINISTRADOR.ESTUDIANTE";
        mt = modTabla;
        baseDatos = new ManejaDatosEst();
        mt.setDatos(baseDatos.consultaDatos(consulta), baseDatos);
        tabla1 = new JTable();
        tabla1.setModel(mt);
        titulo = new JLabel(" Agrega, Visualiza y modifica datos de estudiantes", SwingConstants.CENTER);
        superior.add(titulo, BorderLayout.NORTH);
        principal.add(superior, BorderLayout.NORTH);
        controlLabel = new JLabel("Numero de Control:");
        panel.add(controlLabel);
        numeroLabel = new JLabel("numero");
        panel.add(numeroLabel);
        nombreLabel = new JLabel("Nombre");
        panel.add(nombreLabel);
        nombre = new JTextField(15);
        panel.add(nombre);
        sexoLabel = new JLabel("Sexo:");
        panel.add(sexoLabel);
        G1 = new ButtonGroup();
        jRadioButton1 = new JRadioButton("Hombre");
        jRadioButton2 = new JRadioButton("Mujer");
        panel.add(jRadioButton1);
        panel.add(jRadioButton2);
        G1.add(jRadioButton1);
        G1.add(jRadioButton2);
        edadLabel = new JLabel("Edad");
        panel.add(edadLabel);
        edadSpinner = new JSpinner(new SpinnerNumberModel(18, //initial value  
                18, //minimum value  
                30, //maximum value  
                1));
        panel.add(edadSpinner);
        semestreLabel = new JLabel("Semestre");
        panel.add(semestreLabel);
        semestreSpinner = new JSpinner(new SpinnerNumberModel(1, //initial value  
                1, //minimum value  
                13, //maximum value  
                1));
        panel.add(semestreSpinner);
        creditosLabel = new JLabel("Creditos");
        panel.add(creditosLabel);
        creditosSpinner = new JSpinner(new SpinnerNumberModel(0, //initial value  
                0, //minimum value  
                500, //maximum value  
                1));
        panel.add(creditosSpinner);
        registrarButton = new JButton("Registrar");
        registrarButton.addActionListener(formListener);
        panel.add(registrarButton);
        terminarButton = new JButton("Terminar");
        terminarButton.addActionListener(formListener);
        panel.add(terminarButton);
        principal.add(panel, BorderLayout.CENTER);
        JScrollPane t = new JScrollPane(tabla1);
        inferior.add(t);
        principal.add(inferior, BorderLayout.SOUTH);
        this.add(principal);
    }

    private void registrarActionPerformed(java.awt.event.ActionEvent evt) {
        System.out.println("Boton Registrar Presionado");
    }

    private void terminarActionPerformed(java.awt.event.ActionEvent evt) {
        if (baseDatos.cerrarSesion()) {
            System.exit(0);
        } else {
            this.dispose();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private class FormListener implements java.awt.event.ActionListener {
        FormListener() {
        }
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            if (evt.getSource() == registrarButton) {
                Interfaz.this.registrarActionPerformed(evt);
            } else if (evt.getSource() == terminarButton) {
                Interfaz.this.terminarActionPerformed(evt);
            }
        }
    }

    @Override
    public void run() {
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Registro Académico");
        this.setVisible(true);
        this.setSize(700, 600);
        this.setResizable(false);
    }

    public static void main(String[] args) {
        ModeloTabla model = new ModeloTabla();
        Interfaz in = new Interfaz(model);
        Thread h1 = new Thread(in);
        h1.start();
    }

}
