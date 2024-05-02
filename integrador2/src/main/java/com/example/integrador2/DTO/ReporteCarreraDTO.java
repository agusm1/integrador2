package com.example.integrador2.DTO;

public class ReporteCarreraDTO {
    private String nombre;
    private int anio;
    private Long inscriptos;
    private Long egresados;

    /**
     * @brief Constructor de ReporteCarreraDTO.
     * @param nombre El nombre de la carrera.
     * @param anio El año del reporte.
     * @param inscriptos El número de inscriptos en la carrera.
     * @param egresados El número de egresados de la carrera.
     */
    public ReporteCarreraDTO(String nombre, int anio, Long inscriptos, Long egresados) {
        this.nombre = nombre;
        this.anio = anio;
        this.inscriptos = inscriptos;
        this.egresados = egresados;
    }
    /**
     * @brief Método para obtener el nombre de la carrera.
     * @return String El nombre de la carrera.
     */
    public String getNombre() {
        return nombre;
    }
    /**
     * @brief Método para obtener el año del reporte.
     * @return int El año del reporte.
     */
    public int getAnio() {
        return anio;
    }
    /**
     * @brief Método para obtener el número de inscriptos en la carrera.
     * @return Long El número de inscriptos en la carrera.
     */
    public Long getInscriptos() {
        return inscriptos;
    }
    /**
     * @brief Método para obtener el número de egresados de la carrera.
     * @return Long El número de egresados de la carrera.
     */
    public Long getEgresados() {
        return egresados;
    }

    @Override
    public String toString() {
        return "ReporteTdo{" +
                "nombre='" + nombre + '\'' +
                ", anio=" + anio +
                ", inscriptos=" + inscriptos +
                ", egresados=" + egresados +
                '}';
    }
}
