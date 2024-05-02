package com.example.integrador2.entities;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Estudiante implements Serializable {

    @Id
    private Long num_doc;
    @Column
    private String name;
    @Column
    private String apellido;
    @Column
    private int edad;
    @Column
    private String genero;
    @Column
    private String ciudad;
    @Column (nullable = false)
    private Long num_Libreta;
    @OneToMany(mappedBy = "estudiante")
    private List<CarreraEstudiante> carreras;
    /**
     * Constructor por defecto.
     */
    public Estudiante() {
        super();
    }
    /**
     * Constructor con parámetros.
     *
     * @param num_doc     Número de documento del estudiante.
     * @param num_Libreta Número de libreta universitaria del estudiante.
     * @param nombres     Nombre/s del estudiante.
     * @param apellido    Apellido del estudiante.
     * @param edad        Edad del estudiante.
     * @param genero      Género del estudiante.
     * @param ciudad      Ciudad de residencia del estudiante.
     */
    public Estudiante(Long num_doc, Long num_Libreta, String nombres, String apellido, int edad, String genero, String ciudad) {
        this.num_doc = num_doc;
        this.name = nombres;
        this.apellido = apellido;
        this.edad = edad;
        this.genero = genero;
        this.ciudad = ciudad;
        this.num_Libreta = num_Libreta;
        this.carreras = new ArrayList<CarreraEstudiante>();
    }

    public Long getNum_Libreta() {
        return num_Libreta;
    }

    public Long getNum_doc() {
        return num_doc;
    }

    public String getNombres() {
        return name;
    }

    public String getApellido() {
        return apellido;
    }

    public int getEdad() {
        return edad;
    }

    public String getGenero() {
        return genero;
    }

    public String getciudad() {
        return ciudad;
    }

    public void setNum_doc(Long num_doc) {
        this.num_doc = num_doc;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public void setNum_Libreta(Long num_Libreta) {
        this.num_Libreta = num_Libreta;
    }

    /**
     * Agrega una carrera a la lista de carreras del estudiante.
     * @param carrera Carrera a agregar.
     */
    public void setCarrera(CarreraEstudiante carrera) {this.carreras.add(carrera);}

    @Override
    public String toString() {
        return "Estudiante{" + "num_doc=" + num_doc + ", num_Libreta=" + num_Libreta + ", name='" + name + '\'' + ", apellido='" + apellido + '\'' + ", edad=" + edad + ", genero='" + genero + '\'' + ", ciudad='" + ciudad + '\'' + '}';
    }
}
