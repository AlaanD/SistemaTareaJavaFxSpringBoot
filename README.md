# Sistema de Tareas con JavaFx y Spring Boot

Un proyecto que combina **JavaFX** y **Spring Boot** para crear una aplicación de gestión de tareas. Este repositorio contiene el código fuente, configuraciones y dependencias necesarias para ejecutar el proyecto.

## Características

- Interfaz gráfica interactiva con JavaFX.
- Backend robusto con Spring Boot.
- Arquitectura modular y escalable.
- Código limpio y estructurado para fácil mantenimiento.


## Funcionalidades

* Agregar tareas
* Gestionar tareas (editar, eliminar)
* Visualizar lista de tareas pendientes


## Requisitos

Para ejecutar este proyecto, necesitarás:

- Java 11 o superior.
- IDE compatible (como IntelliJ IDEA o Eclipse).

## Clona este repositorio:
    ```
    git clone https://github.com/AlaanD/SistemaTareaJavaFxSpringBoot.git
    ```

## Estructura del Proyecto

- **SistemaTareaJavaFxSpringBoot/**
  - **src/**
    - **main/**
      - **java/**
        - **com.sistema.tareas/**  
          - **controller/**  
            - `IndexController.java`: Maneja la lógica de la interfaz de usuario.  
          - **model/**  
            - `Tarea.java`: Representa las entidades de tareas.  
          - **service/**  
            - `TareaService.java`: Implementa la lógica de negocio de las tareas.  
            - `ITareaService.java`: Interfaz de servicios.  
          - **repository/**  
            - `ITareaRepository.java`: Interfaz para la manipulación de datos.  
          - **presentacion/**  
            - `SistemasTareasFx.java`: Clase principal para iniciar la aplicación. 
      - **resources/**  
        - **templates/**  
          - `index.fxml`: Archivo principal de la interfaz gráfica.  
        - `application.properties`: Configuración de Spring Boot.
  - `.mvn/`: Configuración de Maven Wrapper.
  - `.gitattributes`: Configuración de Git.
  - `.gitignore`: Archivos y carpetas ignorados por Git.
  - `mvnw`: Script para ejecutar Maven Wrapper en Linux/Mac.
  - `mvnw.cmd`: Script para ejecutar Maven Wrapper en Windows.
  - `pom.xml`: Archivo de configuración principal de Maven.


**Autor**

* Alán David Dri
