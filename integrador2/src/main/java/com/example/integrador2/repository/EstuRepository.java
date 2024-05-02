package com.example.integrador2.repository;

import com.example.integrador2.entities.Carrera;
import com.example.integrador2.entities.Estudiante;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class EstuRepository implements EstudianteRepository{
    private EntityManager em;

    public EstuRepository(EntityManager em){
        this.em=em;
    }

    public EstuRepository() {
    }

    /**
     * @brief Inserta un estudiante en la base de datos.
     * @param e El estudiante a insertar.
     */
    @Override
    public void InsertarEstudiante(Estudiante e) {
            this.em.getTransaction().begin();
            this.em.persist(e);
            this.em.getTransaction().commit();
        }

    /**
     * @brief Elimina un estudiante de la base de datos.
     * @param e El estudiante a eliminar.
     */
    @Override
    public void eliminarEstudiante(Estudiante e) {
            em.getTransaction().begin();
            em.remove(e);
            em.getTransaction().commit();
        }

    /**
     * @brief Obtiene una lista de todos los estudiantes ordenados por número de libreta.
     * @return List<Estudiante> La lista de estudiantes ordenada.
     */
    @Override
    public List<Estudiante> GetEstudiantes() {
        this.em.getTransaction().begin();
        @SuppressWarnings("unchecked")
        List<Estudiante> list = em.createQuery("SELECT e FROM Estudiante e ORDER BY e.num_Libreta DESC").getResultList();
        this.em.close();
        return list;
    }

    /**
     * @brief Obtiene un estudiante por su número de libreta.
     * @param id El número de libreta del estudiante.
     * @return Estudiante El estudiante con el número de libreta proporcionado.
     */
    @Override
    public Estudiante getEstudianteByNumLibreta(Long id) {
        em.getTransaction().begin();
        String jpql="SELECT e FROM Estudiante e WHERE (e.num_Libreta=?1)";
        Query query=em.createQuery(jpql);
        query.setParameter(1,id);
        Estudiante estu= (Estudiante) query.getSingleResult();
        em.getTransaction().commit();
        return estu;
    }

    /**
     * @brief Obtiene una lista de estudiantes ordenados por apellido.
     * @return List<Estudiante> La lista de estudiantes ordenada por apellido.
     */
    @Override
    public List<Estudiante> getEstudiantesByOrdenDelApellido() {
            List<Estudiante> estudiantesxApellido = new ArrayList<>();
            String jpql = "SELECT e FROM Estudiante e " +
                    "ORDER BY e.apellido DESC";
            Query query= em.createQuery(jpql);
            estudiantesxApellido=query.getResultList();
            return estudiantesxApellido;
        }

    /**
     * @brief Obtiene una lista de estudiantes por género.
     * @param genero El género de los estudiantes a buscar.
     * @return List<Estudiante> La lista de estudiantes que cumplen con el género proporcionado.
     */
    @Override
    public List<Estudiante> GetEstudiantesByGenero(String genero) {
            List<Estudiante> estudiantesxGenero=new ArrayList<>();
            String jpql="SELECT e FROM Estudiante e WHERE e.genero=:buscado";
            Query query= em.createQuery(jpql);
            query.setParameter("buscado",genero);
            estudiantesxGenero=query.getResultList();
            return estudiantesxGenero;
        }

    /**
     * @brief Obtiene una lista de estudiantes de una carrera en una ciudad específica.
     * @param carr La carrera de los estudiantes a buscar.
     * @param ciu La ciudad donde residen los estudiantes.
     * @return List<Estudiante> La lista de estudiantes que cumplen con los criterios de carrera y ciudad proporcionados.
     */
    @Override
    public List<Estudiante> GetEstudiantesByCarrera(Carrera carr, String ciu) {
        em.getTransaction().begin();
        List<Estudiante> estudiantes;
        Long idCarrera=carr.getIdCarrera();
        String jpql="SELECT e FROM Estudiante e " +
                    "JOIN CarreraEstudiante ce JOIN Carrera c " +
                    "WHERE (e.num_doc=ce.num_doc) " +
                    "AND (e.ciudad=?1) " +
                    "AND (ce.idCarrera=?2)";
        Query query=em.createQuery(jpql);
        query.setParameter(1,ciu);
        query.setParameter(2,idCarrera);
        estudiantes=query.getResultList();
        em.getTransaction().commit();
        return estudiantes;
    }
}
