package pe.edu.upeu.oracle.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import pe.edu.upeu.oracle.config.Conexion;
import pe.edu.upeu.oracle.dao.EstudianteDao;
import pe.edu.upeu.oracle.entity.Estudiante;

public class EstudianteDaoImpl implements EstudianteDao {
    private PreparedStatement ps;
    private ResultSet rs;
    private Connection cx = null;

    //se implementa los metodos 
    
    @Override
 public int createEstudiante(Estudiante est) {
    String SQL = "INSERT INTO estudiante (idestudiante, nombre, apellido) VALUES (?, ?, ?)";
    try {
        cx = Conexion.getConexion();
        ps = cx.prepareStatement(SQL);
        ps.setInt(1, est.getIdestudiante()); 
        ps.setString(2, est.getNombre());
        ps.setString(3, est.getApellido());
        return ps.executeUpdate();
    } catch (SQLException e) {
        System.out.println("Error: " + e);
    }
    return 0;
}

    @Override
public int updateEstudiante(Estudiante est) {
    String SQL = "UPDATE estudiante SET nombre = ?, apellido = ? WHERE idEstudiante = ?";
    try {
        cx = Conexion.getConexion();
        ps = cx.prepareStatement(SQL);
        ps.setString(1, est.getNombre());
        ps.setString(2, est.getApellido());
        ps.setInt(3, est.getIdestudiante()); // Cambio a setInt porque idEstudiante ahora es int
        return ps.executeUpdate();
    } catch (SQLException e) {
        System.out.println("Error: " + e);
        return 0; // Agregar un valor de retorno en caso de excepción
    }
}


    @Override
    public int deleteEstudiante(String id) {
        String SQL = "DELETE FROM estudiante WHERE idEstudiante = ?";
        int x = 0;
        try {
            cx = Conexion.getConexion();
            ps = cx.prepareStatement(SQL);
            ps.setString(1, id);
            x = ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error: " + e);
        }
        return x;
    }

    @Override
public Estudiante readEstudiante(String id) {
    Estudiante est = new Estudiante();
    String SQL = "SELECT * FROM estudiante WHERE idestudiante = ?";
    try {
        cx = Conexion.getConexion();
        ps = cx.prepareStatement(SQL);
        ps.setInt(1, Integer.parseInt(id)); // Convertir a int
        rs = ps.executeQuery();
        if (rs.next()) {
            est.setIdestudiante(rs.getInt("idestudiante"));
            est.setNombre(rs.getString("nombre"));
            est.setApellido(rs.getString("apellido"));
        }
    } catch (SQLException e) {
        System.out.println("Error: " + e);
    }
    return est;
}

    @Override
   public List<Estudiante> readAllEstudiante() {
    List<Estudiante> lista = new ArrayList<>();
    String SQL = "SELECT * FROM estudiante";
    try {
        cx = Conexion.getConexion();
        ps = cx.prepareStatement(SQL);
        rs = ps.executeQuery();
        while (rs.next()) {
            Estudiante est = new Estudiante();
            est.setIdestudiante(rs.getInt("idestudiante")); // Cambio a getInt porque idEstudiante ahora es int
            est.setNombre(rs.getString("nombre"));
            est.setApellido(rs.getString("apellido"));
            lista.add(est);
        }
    } catch (SQLException e) {
        System.out.println("Error: " + e);
    }
    return lista;
}
}
