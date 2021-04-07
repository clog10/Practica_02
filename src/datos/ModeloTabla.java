/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import control.ManejaDatosEst;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Carlos Loaeza
 */
public class ModeloTabla extends AbstractTableModel {
    private List <Object []> dato;
    private ManejaDatosEst baseDatos;
    private String encabezado[] = new String[]{
        "Num. Control","Nombre","Edad","Sexo","Semestre","Creditos","Carrera"
    };
    private Class tipos[] = new Class[]{
      Integer.class,String.class,Integer.class,String.class,Integer.class,Integer.class,Integer.class  
    };
    
    public void setDatos(List <Object []> d, ManejaDatosEst bd){
        dato=d;
        baseDatos=bd;
    }
    
    public ManejaDatosEst getBaseDatos(){
        return baseDatos;
    }
    
    @Override
    public Class getColumnClass(int c){
        return tipos[c];
    }
    
    @Override
    public int getRowCount(){
        return dato.size();
    }
    
    @Override
    public int getColumnCount(){
        return tipos.length;
    }
    
    @Override
    public Object getValueAt(int r, int c){
        return dato.get(r)[c];
    }
    
    @Override
    public void setValueAt(Object aValue, int r, int c){
        if(c>0){
            dato.get(r)[c]=(Object) aValue;
            String datos = "UPDATE ADMINISTRADOR.ESTUDIANTE SET nombre='"+
                    (String) dato.get(r)[1]+"', edad="+dato.get(r)[2]+" where NUMCONTROL="+(Integer)dato.get(r)[0];
            baseDatos.actualizaDatos(datos); 
            System.out.println(datos);
        }
    }
    
    @Override
    public String getColumnName(int col){
        return encabezado[col];
    }
    
    @Override
    public boolean isCellEditable(int r, int c){
        return c>0;
    }
}
