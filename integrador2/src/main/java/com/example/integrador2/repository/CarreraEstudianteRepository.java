package com.example.integrador2.repository;

import com.example.integrador2.entities.Carrera;
import com.example.integrador2.entities.Estudiante;

public interface CarreraEstudianteRepository {
    void matricular(Estudiante est, Carrera carr, Long id);
}
