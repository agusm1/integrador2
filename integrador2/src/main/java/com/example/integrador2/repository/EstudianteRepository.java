package com.example.integrador2.repository;
import java.util.List;
import com.example.integrador2.entities.Carrera;
import com.example.integrador2.entities.Estudiante;

public interface EstudianteRepository {

    void InsertarEstudiante(Estudiante e);
    void eliminarEstudiante(Estudiante e);

    List<Estudiante> GetEstudiantes();

    List<Estudiante> getEstudiantesByOrdenDelApellido();

    Estudiante getEstudianteByNumLibreta(Long id);

    List<Estudiante> GetEstudiantesByGenero(String genero);

    List<Estudiante> GetEstudiantesByCarrera(Carrera carr, String ciudad);
}
