package com.example.integrador2.entities;

import jakarta.persistence.*;
@Entity
public class CarreraEstudiante {
    @Id
    private Long id;
    @Id
    private Long num_doc;
    @Id
    private Long idCarrera;
    @Column
    private int fecha_inscripcion;
    @Column
    private int fecha_graduacion;
    @Column
    private int antiguedad;
    @ManyToOne
    @JoinColumn(name = "carrera-fk")
    private Carrera carrera;
    @ManyToOne
    @JoinColumn(name = "estudiante-fk")
    private Estudiante estudiante;

    public CarreraEstudiante() {
        super();
    }
    /**
     * Constructor con parámetros para crear una nueva relación sin fecha de graduación.
     * @param id                 id de la relación.
     * @param num_doc            Número de documento del estudiante.
     * @param idCarrera          id de la carrera.
     * @param fecha_inscripcion  Año de inscripción en la carrera.
     * @param antiguedad         Antigüedad del estudiante en la carrera.
     */
    public CarreraEstudiante(Long id, Long num_doc,Long idCarrera, int fecha_inscripcion, int antiguedad) {
        this.id = id;
        this.num_doc = num_doc;
        this.idCarrera = idCarrera;
        this.fecha_inscripcion = fecha_inscripcion;
        this.fecha_graduacion= 0;
        this.antiguedad=antiguedad;
    }
    /**
     * Constructor con parámetros para crear una nueva relación con fecha de graduación.
     *
     * @param id                 Id de la relación.
     * @param num_doc            Número de documento del estudiante.
     * @param idCarrera          Id de la carrera.
     * @param fecha_inscripcion  Año de inscripción en la carrera.
     * @param graduacion         Año de graduación de la carrera.
     * @param antiguedad         Antigüedad del estudiante en la carrera.
     */
    public CarreraEstudiante(Long id, Long num_doc,Long idCarrera, int fecha_inscripcion,int graduacion,int antiguedad) {
        this.id = id;
        this.num_doc = num_doc;
        this.idCarrera = idCarrera;
        this.fecha_inscripcion = fecha_inscripcion;
        this.fecha_graduacion= graduacion;
        this.antiguedad=antiguedad;
    }
    public Long getId() {
        return id;
    }

    public Long getidEstudiante() {
        return num_doc;
    }

    public Long getIdCarrera() {
        return idCarrera;
    }

    public int getFecha_graduacion() {
        return fecha_graduacion;
    }
    /**
     * Establece el año de graduación de la carrera.
     *  Si no cuenta con fecha de graduacion se valida que la fecha de graduación
     *  sea posterior a la fecha de inscripción
     * @param fecha_graduacion El año de graduación de la carrera.
     */
    public void setFecha_graduacion(int fecha_graduacion) {
        if(this.fecha_graduacion==0) {
            if (this.fecha_inscripcion<fecha_graduacion) {
                this.fecha_graduacion = fecha_graduacion;
            }
        }
    }

    public int getAntiguedad() {
        return antiguedad;
    }

    public int getFecha_inscripcion() {
        return fecha_inscripcion;
    }

    @Override
    public String toString() {
        return "CarreraEstudiante [carrera=" + idCarrera + ", Dni del alumno=" + num_doc + ", Año de inscripción=" + fecha_inscripcion + ", Año de graduación=" + fecha_graduacion + "]";
    }
}
