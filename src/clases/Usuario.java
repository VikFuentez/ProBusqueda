package clases;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author manu_
 */
public class Usuario extends Conexion{
    private String nombre;
    private String username;
    private String contra;
    private String nivel;
    private String estado;

    public Usuario() {
    }

    public Usuario(String nombre, String username, String contra, String nivel) {
        this.nombre = nombre;
        this.username = username;
        this.contra = contra;
        this.nivel = nivel;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContra() {
        return contra;
    }

    public void setContra(String contra) {
        this.contra = contra;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    public void agregar(String nombre, String user, String contra, String nivel, String estado){
        if(nivel.equals("Administrador")){
            this.setNivel("1");
        }
        else if(nivel.equals("Digitalizador")){
            this.setNivel("2");
        }
        else if(nivel.equals("Consultor")){
            this.setNivel("3");
        }
        
        if(estado.equals("Activo")){
            this.setEstado("1");
        }
        else if(estado.equals("Inactivo")){
            this.setEstado("2");
        }
        
        String sql="INSERT INTO usuario (nombre_usuario,user,contra,nivel,id_estado) VALUES ('"+nombre+"','"+user+"','"+contra+"',"+this.getNivel()+","+this.getEstado()+")";
        this.ejecutar(sql,"Usuario agregado exitosamente");
        
    }
    
    public void modificar(){
    }
    
    public JTable mostrar(JTable tabla){
 
        try {
            DefaultTableModel tbl = new DefaultTableModel();
            Object[] fila = new Object[4];
            tbl.addColumn("Nombre");
            tbl.addColumn("Username");
            tbl.addColumn("Nivel");
            tbl.addColumn("Estado");
            tabla.setModel(tbl);
            ResultSet rs = this.consultar("SELECT * FROM usuario");
            while(rs.next()){
                if(rs.getInt(5)==1){
                    fila[2]="Administrador";
                }
                else if(rs.getInt(5)==2){
                    fila[2]="Digitalizador";
                }
                else if(rs.getInt(5)==3){
                    fila[2]="Consultor";
                }
                if(rs.getInt(6)==1){
                    fila[3]="Activo";
                }
                else if(rs.getInt(6)==2){
                    fila[3]="Inactivo";
                }
                fila[0]=rs.getString("nombre_usuario");
                fila[1]=rs.getString("user");
                tbl.addRow(fila);
            }
            tabla.setModel(tbl);
        } catch (SQLException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tabla;
    }
    public JTable filtrar(JTable tabla, String datos){
        try {
            DefaultTableModel tbl = new DefaultTableModel();
            Object[] fila = new Object[4];
            tbl.addColumn("Nombre");
            tbl.addColumn("Username");
            tbl.addColumn("Nivel");
            tbl.addColumn("Estado");
            tabla.setModel(tbl);
            ResultSet rs = this.consultar("SELECT * FROM usuario WHERE id_usuario LIKE '%"+datos+"%' OR nombre_usuario LIKE '%"+datos+"%' OR user LIKE '%"+datos+"%' OR nivel LIKE '%"+datos+"%'");
            while(rs.next()){
                if(rs.getInt(5)==1){
                    fila[2]="Administrador";
                }
                else if(rs.getInt(5)==2){
                    fila[2]="Digitalizador";
                }
                else if(rs.getInt(5)==3){
                    fila[2]="Consultor";
                }
                if(rs.getInt(6)==1){
                    fila[3]="Activo";
                }
                else if(rs.getInt(6)==2){
                    fila[3]="Inactivo";
                }
                fila[0]=rs.getString("nombre_usuario");
                fila[1]=rs.getString("user");
                tbl.addRow(fila);
            }
            tabla.setModel(tbl);
        } catch (SQLException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tabla;
    }
    public void eliminar(){
    }
    
    public void comboNivel(){
        
    }
    
}
