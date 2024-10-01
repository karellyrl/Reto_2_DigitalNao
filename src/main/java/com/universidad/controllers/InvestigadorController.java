package com.universidad.controllers; 

import com.universidad.models.Investigador; 
import com.universidad.models.InvestigadorDAO; 
import org.apache.http.HttpResponse; 
import org.apache.http.client.methods.HttpGet; 
import org.apache.http.impl.client.CloseableHttpClient; 
import org.apache.http.impl.client.HttpClients; 
import com.fasterxml.jackson.databind.JsonNode; 
import com.fasterxml.jackson.databind.ObjectMapper; 

import java.io.BufferedReader; 
import java.io.InputStreamReader; 
import java.util.ArrayList; 
import java.util.List; 

// Clase que maneja la lógica para investigadores
public class InvestigadorController {
    
    // URL de la API para obtener información sobre investigadores de la UNAM
    private static final String API_URL = "https://serpapi.com/search.json?engine=google_scholar_profiles&hl=en&q=investigadores+de+la+Universidad+Nacional+Aut%C3%B3noma+de+M%C3%A9xico&mauthors=UNAM&api_key=6939001c16519a5a2f8546dc4711931014ddc3980e126dffa0daec479f2c8154";

    // Método para obtener una lista de investigadores desde la API
    public List<Investigador> obtenerInvestigadores() {
        List<Investigador> investigadores = new ArrayList<>(); // Lista para almacenar investigadores
        try (CloseableHttpClient client = HttpClients.createDefault()) { // Crear cliente HTTP
            HttpGet request = new HttpGet(API_URL); // Preparar la solicitud GET
            HttpResponse response = client.execute(request); // Ejecutar la solicitud
            BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent())); // Leer la respuesta
            ObjectMapper mapper = new ObjectMapper(); // Crear objeto para manejar JSON
            JsonNode root = mapper.readTree(reader); // Parsear la respuesta a un nodo JSON
            JsonNode resultados = root.get("profiles"); // Obtener los resultados de perfiles

            // Verificar si se encontraron resultados y si es un array
            if (resultados != null && resultados.isArray()) {
                int contador = 0; // Contador para limitar a los primeros 10 investigadores
                for (JsonNode resultado : resultados) {
                    if (contador >= 10) {
                        break;
                    }

                    // Extraer información del investigador con manejo de valores nulos
                    String nombre = resultado.has("name") ? resultado.get("name").asText() : "N/A";
                    String afiliacion = resultado.has("affiliations") ? resultado.get("affiliations").asText() : "N/A";
                    String citaciones = resultado.has("cited_by") ? resultado.get("cited_by").asText() : "N/A";
                    String correo = resultado.has("email") ? resultado.get("email").asText() : "N/A";

                    // Extraer intereses
                    List<String> intereses = new ArrayList<>();
                    if (resultado.has("interests")) {
                        JsonNode interesesNode = resultado.get("interests"); 
                        if (interesesNode.isArray()) {
                            for (JsonNode interes : interesesNode) {
                                if (interes.has("title")) {
                                    intereses.add(interes.get("title").asText());
                                }
                            }
                        }
                    }

                    // Crear el objeto Investigador con la información extraída
                    Investigador investigador = new Investigador(nombre, afiliacion, citaciones, correo, intereses);
                    investigadores.add(investigador); 
                    contador++; // Incrementar contador
                }
            } else {
                System.out.println("No se encontraron resultados en la respuesta de la API."); // Mensaje si no hay resultados
            }
        } catch (Exception e) {
            e.printStackTrace(); 
        }
        return investigadores; 
    }

    // Método para actualizar los investigadores en la base de datos
    public void actualizarInvestigadores() {
        InvestigadorDAO investigadorDAO = new InvestigadorDAO(); 
        List<Investigador> investigadores = obtenerInvestigadores();
        investigadorDAO.actualizarInvestigadores(investigadores); 
    }
}
