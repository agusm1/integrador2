package com.example.integrador2.DTO;

public class CarreraDTO {
    private Long idCarrera;
    private String nombre;
    private int duracion;
    private int totalInscriptos;

    /**
     * @brief Constructor de CarreraDTO.
     * @param idCarrera El ID de la carrera.
     * @param nombre El nombre de la carrera.
     * @param duracion La duración de la carrera en años.
     * @param totalInscriptos El total de inscriptos en la carrera.
     */
    public CarreraDTO(Long idCarrera, String nombre, int duracion, int totalInscriptos) {
        this.idCarrera = idCarrera;
        this.nombre = nombre;
        this.duracion = duracion;
        this.totalInscriptos = totalInscriptos;
    }
    /**
     * @brief Método para obtener el ID de la carrera.
     * @return Long El ID de la carrera.
     */
    public Long getIdCarrera() {
        return idCarrera;
    }
    /**
     * @brief Método para obtener el nombre de la carrera.
     * @return String El nombre de la carrera.
     */
    public String getNombre() {
        return nombre;
    }
    /**
     * @brief Método para obtener la duración de la carrera.
     * @return int La duración de la carrera en años.
     */
    public int getDuracion() {
        return duracion;
    }
    /**
     * @brief Método para obtener el total de inscriptos en la carrera.
     * @return int El total de inscriptos en la carrera.
     */
    public int getTotalInscriptos() {
        return totalInscriptos;
    }

    @Override
    public String toString() {
        return "CarreraDTO{" + "idCarrera=" + idCarrera + ", nombre='" + nombre + '\'' + ", duracion=" + duracion + ", Total inscriptos=" + totalInscriptos + '}';
    }
}
