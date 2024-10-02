# Reto_2_Digital_Nao
# Proyecto de Automatización de Información de Investigadores

Este proyecto tiene como objetivo automatizar la extracción de información de los investigadores más destacados de la Universidad Nacional Autónoma de México (UNAM) mediante la API de SerpApi y almacenarla en una base de datos MySQL. La aplicación sigue el patrón de diseño MVC (Modelo-Vista-Controlador) para facilitar la separación de responsabilidades, y utiliza **Maven** para la gestión de dependencias y la construcción del proyecto.

## Tabla de Contenidos
- [Descripción del Proyecto](#descripción-del-proyecto)
- [Tecnologías Utilizadas](#tecnologías-utilizadas)
- [Arquitectura del Proyecto (MVC)](#arquitectura-del-proyecto-mvc)
  - [Modelo](#modelo)
  - [Vista](#vista)
  - [Controlador](#controlador)
- [Configuración y Ejecución](#configuración-y-ejecución)
  - [Requisitos](#requisitos)
  - [Instalación](#instalación)
  - [Ejecución](#ejecución)
- [Estructura del Proyecto](#estructura-del-proyecto)
- [Contribuciones](#contribuciones)
- [Licencia](#licencia)

## Descripción del Proyecto

Esta aplicación extrae información del Top 10 de investigadores de Google Scholar para la Universidad Nacional Autónoma de México (UNAM) usando la API de SerpApi. Los datos obtenidos se almacenan en una base de datos MySQL, y se presenta una interfaz para consultar y gestionar la información. El proyecto implementa características de búsqueda y actualización de los datos de los investigadores.

## Tecnologías Utilizadas

- **Java**: Lenguaje principal utilizado en el proyecto.
- **Maven**: Herramienta de gestión de dependencias y construcción del proyecto.
- **SerpApi**: API utilizada para extraer la información de Google Scholar.
- **MySQL**: Base de datos utilizada para almacenar la información de los investigadores.
- **Jackson**: Librería para el manejo de datos en formato JSON.
- **Apache HttpClient**: Librería para realizar peticiones HTTP a la API.

## Arquitectura del Proyecto (MVC)

El proyecto sigue el patrón de diseño **Modelo-Vista-Controlador (MVC)**, que permite separar la lógica de negocio, la representación de los datos y el flujo de control de la aplicación. A continuación, se describen cada uno de los componentes:

### Modelo

El **Modelo** es responsable de la interacción con la base de datos y de representar los datos de los investigadores. Aquí es donde se define la estructura de los datos y se implementa la lógica para acceder y manipular la información almacenada en MySQL.

**Responsabilidades**:
- Conexión a la base de datos MySQL.
- Consultas y actualizaciones en la base de datos (insertar, eliminar, actualizar registros de los investigadores).
- Mapeo de datos de Google Scholar a objetos Java y viceversa.

### Vista

La **Vista** es responsable de la presentación de los datos al usuario. Aunque en este caso específico el proyecto no incluye una interfaz gráfica avanzada, se puede extender para mostrar los resultados en una página web o interfaz gráfica (GUI) en Java.

**Responsabilidades**:
- Presentar la información de los investigadores.
- Mostrar mensajes de error o confirmación de acciones.

### Controlador

El **Controlador** gestiona la lógica de la aplicación, procesa las solicitudes del usuario, se comunica con el modelo para obtener y modificar datos, y luego envía los resultados a la vista para su presentación.

**Responsabilidades**:
- Realizar peticiones GET a la API de SerpApi.
- Parsear los datos JSON obtenidos de la API.
- Interactuar con el modelo para almacenar o actualizar los datos en la base de datos.
- Gestionar la lógica de control de flujo entre la vista y el modelo.

## Configuración y Ejecución

### Requisitos

- **Java 8 o superior**
- **Maven**
- **MySQL**

### Instalación

1. Clona el repositorio en tu máquina local:

   ```bash
   git clone https://github.com/karellyrl/Reto_2_Digital_Nao.git
   cd investigadores
   
2. Configura la base de datos MySQL:
   Crea una base de datos llamada universidad_investigadores y ajusta las credenciales en el archivo de configuración para la conexión a la base de datos.

3. Instala las dependencias del proyecto usando Maven:
   ```bash
   mvn clean install

### Ejecución

1. Ejecuta la aplicación usando Maven:

   ```bash
   mvn exec:java

La aplicación hará las peticiones a la API de SerpApi, extraerá la información de los investigadores y la almacenará en la base de datos MySQL.

## Estructura del Proyecto

1. Esta es la estructura general del proyecto:

   ```bash
   /src
   /main
     /java
       /com/universidad
         /Main.java         # Clase principal
         /model             # Contiene las clases del Modelo
         /controller        # Contiene las clases del Controlador
         /view              # Contiene las clases de la Vista
     /resources             # Archivos de configuración y recursos
   /pom.xml                   # Archivo de configuración de Maven

   
## Ver Resultados en MySQL
Para verificar los datos almacenados en MySQL después de ejecutar la aplicación, sigue estos pasos:

1. Inicia sesión en MySQL desde la línea de comandos:

   ```bash
   mysql -u tu_usuario -p

2. Selecciona la base de datos universidad_investigadores:

   ```bash
   USE universidad_investigadores;

3. Consulta la tabla donde se han almacenado los investigadores:

   ```bash
   SELECT * FROM investigadores;

3. (Opcional) Se puede filtrar los resultados, por ejemplo, buscando un investigador específico por su nombre:

   ```bash
   SELECT * FROM investigadores WHERE nombre = 'Nombre del Investigador';
