package gui;

import java.sql.*;

public class BDInicioSesion {
    private static Connection con;

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
                CREATE TABLE IF NOT EXISTS ContraseñasInicioSesion (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    Nom_usuario TEXT NOT NULL UNIQUE,
                    contraseña TEXT NOT NULL
                )
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

            String sql = "DROP TABLE IF EXISTS ContraseñasInicioSesion";
            stmt.executeUpdate(sql);

            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Insertar un usuario y su contraseña en la tabla ContraseñasInicioSesion
    public static void insertarUsuario(String usuario, String contrasena) {
        String sql = "INSERT INTO ContraseñasInicioSesion (Nom_usuario, contraseña) VALUES (?, ?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, usuario);
            ps.setString(2, contrasena);
            ps.execute();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Verificar si existe un usuario en la tabla ContraseñasInicioSesion
    public static boolean existeUsuario(String usuario) {
        boolean existe = false;
        String sql = "SELECT * FROM ContraseñasInicioSesion WHERE Nom_usuario = ?";
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

    //Verificar si la contraseña es correcta para un usuario
    public static boolean verificarContrasena(String usuario, String contrasena) {
        boolean esCorrecta = false;
        String sql = "SELECT * FROM ContraseñasInicioSesion WHERE Nom_usuario = ? AND contraseña = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, usuario);
            ps.setString(2, contrasena);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                esCorrecta = true;
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return esCorrecta;
    }
}
