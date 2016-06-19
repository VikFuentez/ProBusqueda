/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author manu_
 */
public class Institucion extends Conexion{
    private String estado;
    
    public Institucion() {
    }

    public Institucion(String estado) {
        this.estado = estado;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    public void agregar(String nombre, String abreviatura,  String estado){
        
        if(estado.equals("Activo"))
        {
            this.setEstado("1");
        }
        else if(estado.equals("Inactivo"))
        {
            this.setEstado("2");
        }
        
        String sql="INSERT INTO institucion_afiliadas (nombre,abreviatura,id_estado) VALUES ('"+nombre+"','"+abreviatura+"',"+this.getEstado()+")";
        this.ejecutar(sql,"Institución agregado exitosamente");
        
    }
    
    public void modificar(int id, String nombre, String abreviatura,  int estado)
    {
        String sql="UPDATE institucion_afiliadas set nombre='"+nombre+"',abreviatura='"+abreviatura+"', id_estado="+estado+" where id_institucion="+id;
        this.ejecutar(sql, "Institución modificado correctamente");
    }
    
    public JTable mostrar(JTable tabla)
    {
        try 
        {
            DefaultTableModel tbl = new DefaultTableModel();
            Object[] fila = new Object[4];
            tbl.addColumn("ID Institucion");
            tbl.addColumn("Nombre");
            tbl.addColumn("abreviatura");
            tbl.addColumn("Estado");
            tabla.setModel(tbl);
            ResultSet rs = this.consultar("SELECT * FROM institucion_afiliadas");
            while(rs.next())
            {
                
                if(rs.getInt(4)==1)
                {
                    fila[3]="Activo";
                }
                else if(rs.getInt(4)==2)
                {
                    fila[3]="Inactivo";
                }
                fila[0]=rs.getInt("id_institucion");
                fila[1]=rs.getString("nombre");
                fila[2]=rs.getString("abreviatura");
                tbl.addRow(fila);
            }
            
            tabla.setModel(tbl);
        } catch (SQLException ex) 
        {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tabla;
    }
    
    public JTable filtrar(JTable tabla, String datos)
    {
        int contador=0;
        try 
        {
            DefaultTableModel tbl = new DefaultTableModel();
            Object[] fila = new Object[4];
            tbl.addColumn("ID Usuario");
            tbl.addColumn("Nombre");
            tbl.addColumn("Abreviatura");
            tbl.addColumn("Estado");
            tabla.setModel(tbl);
            ResultSet rs = this.consultar("SELECT * FROM institucion_afiliadas WHERE id_institucion LIKE '%"+datos+"%' OR nombre LIKE '%"+datos+"%' OR abreviatura LIKE '%"+datos+"%' OR id_estado LIKE '%"+datos+"%'");
            while(rs.next())
            {
                contador++;
                
                if(rs.getInt(4)==1)
                {
                    fila[3]="Activo";
                }
                else if(rs.getInt(4)==2)
                {
                    fila[3]="Inactivo";
                }
                fila[0]=rs.getInt("id_institucion");
                fila[1]=rs.getString("nombre");
                fila[2]=rs.getString("abreviatura");
                tbl.addRow(fila);
            }
            if(contador==0)
            {
                return null;
            }
            tabla.setModel(tbl);
        } catch (SQLException ex) 
        {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tabla;
    }
    
    public void eliminar(int id)
    {
        String sql="DELETE FROM institucion_afiliadas WHERE id_institucion="+id;
        this.ejecutar(sql, "Institución eliminado correctamente");
    }
    
    public ResultSet comboEstado(JComboBox combo){
        try
        {
            ResultSet rs=this.consultar("SELECT * FROM estado");
            while(rs.next())
            {
                combo.addItem(rs.getString("estado"));
            }
            return rs;  
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "Nose pudo cargar el Desplegable de Estado");
        }
        
        return null;
    }
}
