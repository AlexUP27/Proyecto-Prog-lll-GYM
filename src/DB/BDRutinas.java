package DB;

import java.sql.*;

public class BDRutinas {
    public static Connection con;
    public static PreparedStatement ps;

    //Método que realiza la conexión con la base de datos
    public static void initBD() {
        con = null;

        try {
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection("jdbc:sqlite:Gym.db");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Método para cerrar la conexión de la base de datos
    public static void closeBD() {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    //Crear la tabla en la base de datos
    public static void crearTabla() {
        try {
            Statement stmt = con.createStatement();

            // Crear tabla ContraseñasInicioSesion
            String sqlContraseñasInicioSesion = """
                CREATE TABLE IF NOT EXISTS Rutinas (
				    Id INTEGER PRIMARY KEY AUTOINCREMENT,
				    Lunes TEXT NOT NULL,
				    Martes TEXT NOT NULL,
				    Miercoles TEXT NOT NULL,
				    Jueves TEXT NOT NULL,
				    Viernes TEXT NOT NULL
            	);
            """;
            stmt.executeUpdate(sqlContraseñasInicioSesion);

            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Borrar la tabla de la base de datos
    public static void borrarTabla() {
        try {
            Statement stmt = con.createStatement();

            String sql = "DROP TABLE IF EXISTS Rutinas";
            stmt.executeUpdate(sql);

            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Insertar un usuario y su contraseña en la tabla ContraseñasInicioSesion
    public static void insertarRutina(String Lunes, String Martes, String Miercoles, String Jueves, String Viernes, int Id, Connection con) {
        String sql = "INSERT INTO Rutinas (Lunes,Martes,Miercoles,Jueves,Viernes,Id) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, Lunes);
            ps.setString(2, Martes);
            ps.setString(3, Miercoles);
            ps.setString(4, Jueves);
            ps.setString(5, Viernes);
            ps.setInt(6, Id);
            ps.execute();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } 
    }

    //Verificar si existe un usuario en la tabla ContraseñasInicioSesion
    public static boolean existeRutina(String usuario) {
        boolean existe = false;
        String sql = "SELECT * FROM Rutinas WHERE Id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, usuario);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                existe = true;
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return existe;
    }

}
