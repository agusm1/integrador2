package com.example.integrador2.entities;
import jakarta.persistence.*;

import javax.xml.namespace.QName;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Carrera implements Serializable {
    @Id
    private Long idCarrera;
    @Column
    private String carrera;
    @Column
    private int duracion;
    @OneToMany(mappedBy = "carrera")
    private List<CarreraEstudiante> estudiantes;

    /**
     * Constructor por defecto.
     */
    public Carrera() {
    }
    /**
     * Constructor con parámetros para crear una nueva carrera.
     * @param idCarrera Identificador único de la carrera.
     * @param carrera   Nombre de la carrera.
     * @param duracion  Duración de la carrera en años.
     */
    public Carrera(Long idCarrera, String carrera, int duracion) {
        this.idCarrera = idCarrera;
        this.carrera = carrera;
        this.duracion = duracion;
        this.estudiantes = new ArrayList<>();
    }

    /**
     * Obtiene el id de la carrera.
     * @return El id de la carrera.
     */
    public Long getIdCarrera() {
        return idCarrera;
    }

    /**
     * Obtiene el nombre de la carrera.
     * @return El nombre de la carrera.
     */
    public String getCarrera() {
        return carrera;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }
    /**
     * Establece la duración de la carrera en años.
     * Si la duracion con la que se registro la carrera es 0 se puede setear la nueva duracion
     * @param duracion La duración de la carrera en años.
     */
    public void setDuracion(int duracion) {
        if(duracion>0){
            this.duracion = duracion;
        }
    }
    /**
     * Agrega un estudiante a la lista de estudiantes asociados a esta carrera.
     * @param ce La relación entre estudiante y carrera a agregar.
     */
    public void setEstudianteACarrera(CarreraEstudiante ce){
        this.estudiantes.add(ce);
    }
    @Override
    public String toString() {
        return "Carrera{ idCarrera=" + idCarrera + ", carrera='" + carrera + '\'' + ", duracion=" + duracion + '}';
    }
}
