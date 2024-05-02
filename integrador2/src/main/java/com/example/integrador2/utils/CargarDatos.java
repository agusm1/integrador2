package com.example.integrador2.utils;

import com.example.integrador2.entities.Carrera;
import com.example.integrador2.entities.CarreraEstudiante;
import com.example.integrador2.entities.Estudiante;
import jakarta.persistence.EntityManager;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Clase para cargar datos en la base de datos.
 */
public class CargarDatos {
    private EntityManager eManager;
    /**
     * Constructor de la clase CargarDatos.
     * @param em EntityManager para gestionar las entidades.
     */
    public CargarDatos(EntityManager em) {
        this.eManager = em;
    }

    /**
     * Inserta carreras desde un archivo CSV en la base de datos.
     * @param url La ubicación del archivo CSV.
     * @throws IOException Si ocurre un error de entrada/salida.
     */
    public void insertarCarreras(String url) throws IOException {
        eManager.getTransaction().begin();
        BufferedReader lector = new BufferedReader(new FileReader(url));
        String linea = "";
        String filas[] = null;
        while ((linea = lector.readLine()) != null) {
            filas = linea.split(",");
            if (!filas[1].equals("carrera")) {
                Long id = Long.parseLong(filas[0]);
                String nombre = filas[1];
                int duracion = Integer.parseInt(filas[2]);
                Carrera nueva = new Carrera(id, nombre, duracion);
                eManager.persist(nueva);
            }
        }
        eManager.getTransaction().commit();
    }

    /**
     * Inserta estudiantes desde un archivo CSV en la base de datos.
     * @param url La ubicación del archivo CSV.
     * @throws IOException Si ocurre un error de entrada/salida.
     */
    public void insertarEstudiantes(String url) throws IOException {
        eManager.getTransaction().begin();
        BufferedReader lector = new BufferedReader(new FileReader(url));
        String linea = "";
        String filas[] = null;
        while ((linea = lector.readLine()) != null) {
            filas = linea.split(",");
            if (!filas[1].equals("nombre")) {//este control es porque en la primera fila se encuentra los nombre de las columnas
                Long dni = Long.parseLong(filas[0]);
                String nombre = filas[1];
                String apellido=filas[2];
                int edad=Integer.parseInt(filas[3]);
                String genero=filas[4];
                String ciudad=filas[5];
                Long libreUni=Long.parseLong(filas[6]);
                Estudiante estudiante=new Estudiante(dni,libreUni,nombre,apellido,edad,genero,ciudad);
                eManager.persist(estudiante);
            }
        }
        eManager.getTransaction().commit();
    }

    /**
     * Inserta relaciones entre estudiantes y carreras desde un archivo CSV en la base de datos.
     *
     * @param urlEstuCarre La ubicación del archivo CSV.
     * @throws IOException Si ocurre un error de entrada/salida.
     */
    public void insertarCarreraEstudiante(String urlEstuCarre) throws IOException {
        eManager.getTransaction().begin();
        BufferedReader lector = new BufferedReader(new FileReader(urlEstuCarre));
        String linea = "";
        String filas[] = null;
        while ((linea = lector.readLine()) != null) {
            filas = linea.split(",");
            if (!filas[0].equals("id")) {
                Long id = Long.parseLong(filas[0]);
                Long num_doc=Long.parseLong(filas[1]);
                Long idCarrera= Long.parseLong(filas[2]);
                int inscripcion = Integer.parseInt(filas[3]);
                int graduacion = Integer.parseInt(filas[4]);
                int antiguedad = Integer.parseInt(filas[5]);
                if(graduacion==0){
                    graduacion=0;
                }
                CarreraEstudiante ce = new CarreraEstudiante(id, num_doc, idCarrera, inscripcion, graduacion, antiguedad);

                eManager.persist(ce);
            }
        }
        eManager.getTransaction().commit();
    }

}
