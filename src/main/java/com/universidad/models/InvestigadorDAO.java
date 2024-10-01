package com.universidad.models;

import com.universidad.utils.DatabaseConnection; 

import java.sql.*; 
import java.util.List; 

// Clase que maneja las operaciones de acceso a datos de Investigador
public class InvestigadorDAO {
    
    // Método para actualizar la lista de investigadores en la base de datos
    public void actualizarInvestigadores(List<Investigador> investigadores) {
        try (Connection conn = DatabaseConnection.getConnection()) { // Establece conexión con la base de datos
            // Verificar si la conexión es válida
            if (conn == null) {
                System.err.println("Error: No se pudo establecer conexión con la base de datos.");
                return;
            }

            // Limpiar la tabla de investigadores
            String deleteSQL = "DELETE FROM investigadores";
            try (PreparedStatement stmt = conn.prepareStatement(deleteSQL)) {
                stmt.executeUpdate(); // Ejecuta la eliminación de datos
            }

            // Reiniciar el autoincremento de la tabla (después de eliminar datos)
            String resetSQL = "ALTER TABLE investigadores AUTO_INCREMENT = 1";
            try (PreparedStatement resetStmt = conn.prepareStatement(resetSQL)) {
                resetStmt.executeUpdate(); // Reinicia el contador de autoincremento
            }

            // Preparar la instrucción SQL para insertar nuevos datos
            String insertSQL = "INSERT INTO investigadores (nombre, afiliacion, citaciones, correo, intereses) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(insertSQL)) {
                // Iterar sobre la lista de investigadores
                for (Investigador investigador : investigadores) {
                    stmt.setString(1, investigador.getNombre()); 
                    stmt.setString(2, investigador.getAfiliacion());
                    stmt.setString(3, investigador.getCitaciones()); 
                    stmt.setString(4, investigador.getCorreo()); 
                    
                    // Convertir la lista de intereses a una cadena separada por comas
                    String interesesString = String.join(", ", investigador.getIntereses());
                    stmt.setString(5, interesesString);

                    stmt.executeUpdate();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Imprimir el error en caso de excepción
        }
    }
}
