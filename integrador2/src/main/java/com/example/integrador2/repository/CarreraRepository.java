package com.example.integrador2.repository;

import com.example.integrador2.DTO.CarreraDTO;
import com.example.integrador2.DTO.ReporteCarreraDTO;
import com.example.integrador2.entities.Carrera;

import java.util.List;

public interface CarreraRepository {
    Carrera getCarrera(long idCarrera);
    List<Carrera> GetCarrerasOrderByInscriptos();
    List<CarreraDTO> getCarrerasConInscriptos();
    List<ReporteCarreraDTO> getCarrerasConEgresados();
}
