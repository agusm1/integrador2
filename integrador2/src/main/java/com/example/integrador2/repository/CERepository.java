package com.example.integrador2.repository;

import com.example.integrador2.entities.Carrera;
import com.example.integrador2.entities.CarreraEstudiante;
import com.example.integrador2.entities.Estudiante;
import jakarta.persistence.EntityManager;

import java.time.LocalDate;

public class CERepository implements CarreraEstudianteRepository{
    private EntityManager em=null;

    public CERepository(){}

    public CERepository(EntityManager em){
        this.em=em;
    }

    /**
     * @brief Matricula a un estudiante en una carrera.
     * @param e El estudiante a matricular.
     * @param c La carrera en la que se matricula el estudiante.
     * @param id El ID de la matriculación.
     */
    @Override
    public void matricular(Estudiante e, Carrera c, Long id) {
        em.getTransaction().begin();
        Long num_doc =e.getNum_doc();
        Long idCarrera=c.getIdCarrera();
        int fechaInscripcion= LocalDate.now().getYear();
        CarreraEstudiante nuevoAlumno=new CarreraEstudiante(id, num_doc,idCarrera,fechaInscripcion, 0, 0);
        em.persist(nuevoAlumno);
        e.setCarrera(nuevoAlumno);
        em.merge(e);
        c.setEstudianteACarrera(nuevoAlumno);;
        em.merge(c);
        em.getTransaction().commit();
    }
}
