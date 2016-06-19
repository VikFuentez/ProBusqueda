package clases;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class Usuario extends Conexion
{
    private String nivel;
    private String estado;

    public Usuario() 
    {     
    }

    public Usuario(String nivel, String estado) {
        this.nivel = nivel;
        this.estado = estado;
    }
    
    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) 
    {
        this.nivel = nivel;
    }

    
    public String getEstado() 
    {
        return estado;
    }

    public void setEstado(String estado)
    {
        this.estado = estado;
    }
    
    public void agregar(String nombre, String user, String contra, String nivel, String estado){
        if(nivel.equals("Administrador"))
        {
            this.setNivel("1");
        }
        else if(nivel.equals("Digitalizador"))
        {
            this.setNivel("2");
        }
        else if(nivel.equals("Consultor"))
        {
            this.setNivel("3");
        }
        
        if(estado.equals("Activo"))
        {
            this.setEstado("1");
        }
        else if(estado.equals("Inactivo"))
        {
            this.setEstado("2");
        }
        
        String sql="INSERT INTO usuario (nombre_usuario,user,contra,nivel,id_estado) VALUES ('"+nombre+"','"+user+"','"+contra+"',"+this.getNivel()+","+this.getEstado()+")";
        this.ejecutar(sql,"Usuario agregado exitosamente");
        
    }
    
    public void modificar(int id, String nombre,String user,int nivel, int estado)
    {
        String sql="UPDATE usuario set nombre_usuario='"+nombre+"',user='"+user+"', nivel="+nivel+", id_estado="+estado+" where id_usuario="+id;
        this.ejecutar(sql, "Usuario modificado correctamente");
    }
    
    public JTable mostrar(JTable tabla)
    {
        try 
        {
            DefaultTableModel tbl = new DefaultTableModel();
            Object[] fila = new Object[5];
            tbl.addColumn("ID Usuario");
            tbl.addColumn("Nombre");
            tbl.addColumn("Username");
            tbl.addColumn("Nivel");
            tbl.addColumn("Estado");
            tabla.setModel(tbl);
            ResultSet rs = this.consultar("SELECT * FROM usuario");
            while(rs.next())
            {
                
                if(rs.getInt(5)==1)
                {
                    fila[3]="Administrador";
                }
                else if(rs.getInt(5)==2)
                {
                    fila[3]="Digitalizador";
                }
                else if(rs.getInt(5)==3)
                {
                    fila[3]="Consultor";
                }
                if(rs.getInt(6)==1)
                {
                    fila[4]="Activo";
                }
                else if(rs.getInt(6)==2)
                {
                    fila[4]="Inactivo";
                }
                fila[0]=rs.getInt("id_usuario");
                fila[1]=rs.getString("nombre_usuario");
                fila[2]=rs.getString("user");
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
            Object[] fila = new Object[5];
            tbl.addColumn("ID Usuario");
            tbl.addColumn("Nombre");
            tbl.addColumn("Username");
            tbl.addColumn("Nivel");
            tbl.addColumn("Estado");
            tabla.setModel(tbl);
            ResultSet rs = this.consultar("SELECT * FROM usuario WHERE id_usuario LIKE '%"+datos+"%' OR nombre_usuario LIKE '%"+datos+"%' OR user LIKE '%"+datos+"%' OR nivel LIKE '%"+datos+"%'");
            while(rs.next())
            {
                contador++;
                if(rs.getInt(5)==1)
                {
                    fila[3]="Administrador";
                }
                else if(rs.getInt(5)==2)
                {
                    fila[3]="Digitalizador";
                }
                else if(rs.getInt(5)==3)
                {
                    fila[3]="Consultor";
                }
                if(rs.getInt(6)==1)
                {
                    fila[4]="Activo";
                }
                else if(rs.getInt(6)==2)
                {
                    fila[4]="Inactivo";
                }
                fila[0]=rs.getInt("id_usuario");
                fila[1]=rs.getString("nombre_usuario");
                fila[2]=rs.getString("user");
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
        String sql="DELETE FROM usuario WHERE id_usuario="+id;
        this.ejecutar(sql, "Usuario eliminado correctamente");
    }
    
    public ResultSet comboNivel(JComboBox combo){
        try
        {
            ResultSet rs=this.consultar("SELECT * FROM niveles");
            while(rs.next()){
                combo.addItem(rs.getString("nombre_nivel"));
            }
            return rs;  
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "Nose pudo cargar el Desplegable de Nivel");
        }
        
        return null;
    }
    
    public ResultSet comboEstado(JComboBox combo){
        try
        {
            ResultSet rs=this.consultar("SELECT * FROM estado");
            while(rs.next()){
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
