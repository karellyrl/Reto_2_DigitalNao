package com.universidad.views; 

import com.universidad.controllers.InvestigadorController; 
import com.universidad.models.Investigador;

import java.util.List; 

// Clase que representa la vista de investigadores
public class InvestigadorView {

    public static void main(String[] args) {
        // Crear una instancia del controlador de investigadores
        InvestigadorController controller = new InvestigadorController();
        
        // Actualizar la lista de investigadores en la base de datos
        controller.actualizarInvestigadores();
        
        // Obtener la lista actualizada de investigadores
        List<Investigador> investigadores = controller.obtenerInvestigadores();

        // Iterar sobre la lista de investigadores y mostrar sus detalles
        for (Investigador investigador : investigadores) {
            System.out.println("Nombre: " + investigador.getNombre()); ¿
            System.out.println("Afiliación: " + investigador.getAfiliacion()); 
            System.out.println("Citaciones: " + investigador.getCitaciones()); 
            System.out.println("Correo: " + investigador.getCorreo()); 
            System.out.println("Intereses: " + String.join(", ", investigador.getIntereses())); 
            System.out.println();
        }
    }
}
