package clases;

import java.sql.*;
import javax.swing.JOptionPane;

/**
 *
 * @author manu_
 */
public class Sesion extends Conexion{
    private int ID;
    private String nivel;
    
    public Sesion() 
    {
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public Sesion(int user, String nivel)
    {
        this.nivel = nivel;
    }
    
    public String getNivel() 
    {
        return nivel;
    }

    public void setNivel(String nivel) 
    {
        this.nivel = nivel;
    }
    
    public int iniciar(String us, String contra)
    {
        int bandera=0;
        
        try {
            String sql="SELECT nivel FROM usuario WHERE user='"+us+"' AND contra='"+contra+"' AND id_estado=1";
            ResultSet res = this.consultar(sql);
            while(res.next()){
                this.setNivel(res.getString("nivel"));
            }
            if(this.getNivel().equals("1")){
                bandera=1;
            }
            else if(this.getNivel().equals("2")){
                bandera=2;
            }
            else if(this.getNivel().equals("3")){
                bandera=3;
            }
        } 
        catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error de conexion "+e);
        }
        return bandera;
    }
    
    public int extraerID(String us, String contra)
    {
        
        try {
            String sql="SELECT * FROM usuario WHERE user='"+us+"' AND contra='"+contra+"' AND id_estado=1";
            ResultSet res = this.consultar(sql);
            while(res.next()){
                this.setID(res.getInt("id_usuario"));
            }
            return this.getID();
        } 
        catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error de conexion "+e);
        }
        return 0;
    }
    
}
