package com.universidad.utils; 
import java.sql.Connection; 
import java.sql.DriverManager; 
import java.sql.SQLException;
import java.util.Properties;
import java.io.InputStream; 

public class DatabaseConnection {
    private static Connection connection; // Conexión única a la base de datos

    // Método para obtener la conexión a la base de datos
    public static Connection getConnection() {
        if (connection == null) { // Si la conexión no ha sido establecida
            try {
                Properties props = new Properties(); // Crear un objeto Properties para cargar configuraciones
                // Cargar el archivo de propiedades para la configuración de la base de datos
                InputStream input = DatabaseConnection.class.getClassLoader().getResourceAsStream("config/application.properties");

                // Verificar si el archivo fue encontrado
                if (input == null) {
                    throw new RuntimeException("No se pudo encontrar el archivo application.properties");
                }

                props.load(input); // Cargar las propiedades desde el archivo

                // Obtener valores de conexión desde las propiedades
                String url = props.getProperty("db.url");
                String username = props.getProperty("db.username");
                String password = props.getProperty("db.password");

                // Asegurar de que las propiedades no sean nulas
                if (url == null || username == null || password == null) {
                    throw new RuntimeException("Los valores de conexión no pueden ser nulos");
                }

                System.out.println("DB URL: " + url); // Imprimir URL de la base de datos
                System.out.println("DB Username: " + username); // Imprimir nombre de usuario

                // Establecer la conexión utilizando DriverManager
                connection = DriverManager.getConnection(url, username, password);
            } catch (SQLException e) {
                e.printStackTrace(); // Imprimir el error de SQL
                throw new RuntimeException("Error al conectar a la base de datos: " + e.getMessage());
            } catch (Exception e) {
                e.printStackTrace(); // Imprimir error inesperado
                throw new RuntimeException("Error inesperado: " + e.getMessage());
            }
        }
        return connection; 
    }
}
