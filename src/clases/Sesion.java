package clases;

import java.sql.*;
import javax.swing.JOptionPane;

/**
 *
 * @author manu_
 */
public class Sesion extends Conexion{
    private String user;
    private String contra;
    private String nivel;
    private Connection cone;

    public Sesion() {
        this.cone = this.conectar();
    }

    public Sesion(String user, String contra, String nivel) {
        this.user = user;
        this.contra = contra;
        this.nivel = nivel;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }
    
    public int iniciar(String us, String contra){
        int bandera=0;
        
        String sql="SELECT nivel FROM usuario WHERE user='"+us+"' AND contra='"+contra+"' AND id_estado=1";
        try {
            Statement st = cone.createStatement();
            ResultSet res = st.executeQuery(sql);
            while(res.next()){
                this.setNivel(res.getString("nivel"));
            }
            if(this.getNivel().equals("ADMIN")){
                bandera=1;
            }
            else if(this.getNivel().equals("DIGITALIZADOR")){
                bandera=2;
            }
            else if(this.getNivel().equals("CONSULTOR")){
                bandera=3;
            }
        } 
        catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error de conexion "+e);
        }
        return bandera;
    }
    
}
