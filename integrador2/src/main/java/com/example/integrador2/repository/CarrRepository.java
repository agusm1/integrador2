package com.example.integrador2.repository;
import com.example.integrador2.DTO.CarreraDTO;
import com.example.integrador2.DTO.ReporteCarreraDTO;
import com.example.integrador2.entities.Carrera;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
public class CarrRepository implements CarreraRepository{
    private EntityManagerFactory emf;
    private EntityManager em;

    public CarrRepository() {
    }
    /**
     * @brief Constructor de CarrRepository que toma un EntityManager.
     * @param em El EntityManager a utilizar.
     */
    public CarrRepository(EntityManager em) {
        this.em = em;
    }

    private void CreateEntityManager() {
        this.emf  = Persistence.createEntityManagerFactory("Example");
        this.em = emf.createEntityManager();
    }

    /**
     * @brief Obtiene una carrera por su ID.
     * @param idCarrera El ID de la carrera.
     * @return Carrera La carrera con el ID proporcionado.
     */
    @Override
    public Carrera getCarrera(long idCarrera) {
        em.getTransaction().begin();
        Carrera carrera=em.find(Carrera.class,idCarrera);
        em.getTransaction().commit();
        return carrera;
    }

    /**
     * @brief Obtiene una lista de carreras ordenadas por la cantidad de inscriptos.
     * @return List<Carrera> La lista de carreras ordenadas por inscriptos.
     */
    @Override
    public List<Carrera> GetCarrerasOrderByInscriptos() {
        CreateEntityManager();
        this.emf  = Persistence.createEntityManagerFactory("BDMySQL");
        this.em = emf.createEntityManager();
        this.em.getTransaction().begin();
        @SuppressWarnings("unchecked")
        List<Carrera> list = em.createQuery("SELECT c FROM Carrera c JOIN CarreraEstudiante ce ON c.id = ce.idCarrera GROUP BY c.id ORDER BY COUNT(*)").getResultList();
        this.em.close();
        return list;
    }

    /**
     * @brief Obtiene una lista de carreras con la cantidad de inscriptos en cada una.
     * @return List<CarreraDTO> La lista de carreras con la cantidad de inscriptos.
     */
    @Override
    public List<CarreraDTO> getCarrerasConInscriptos() {
        em.getTransaction().begin();
        List<CarreraDTO> carreras=new ArrayList<>();
        String jpql="SELECT c.idCarrera,c.carrera,c.duracion, COUNT(c.duracion) AS cant_inscriptos " +
                "FROM Carrera AS c " +
                "JOIN CarreraEstudiante AS ce " +
                "WHERE (c.idCarrera=ce.idCarrera) " +
                "GROUP BY (c.idCarrera) " +
                "ORDER BY cant_inscriptos DESC";
        Query query= em.createQuery(jpql);
        List<Object[]> resultado=query.getResultList();
        for(Object[]result: resultado) {
            Long id = (Long) result[0];
            String nombre = (String) result[1];
            int duracion = (int) result[2];
            Long inscriptos = (Long) result[3];
            Integer totalInscriptios=Integer.parseInt(String.valueOf(inscriptos));
            carreras.add(new CarreraDTO(id,nombre,duracion,totalInscriptios));
        }
        em.getTransaction().commit();
        return carreras;
    }

    /**
     * @brief Obtiene una lista de reportes de carreras con inscriptos y egresados.
     * @return List<ReporteCarreraDTO> La lista de reportes de carreras.
     */
    @Override
    public List<ReporteCarreraDTO> getCarrerasConEgresados() {
        em.getTransaction().begin();
        List<ReporteCarreraDTO> lista=new ArrayList<ReporteCarreraDTO>();
        String jpql="SELECT c.carrera, ce.fecha_inscripcion," +
                "COUNT(ce.num_doc) , COUNT(ce.fecha_graduacion) " +
                "FROM Carrera AS c " +
                "JOIN CarreraEstudiante AS ce " +
                "WHERE (c.idCarrera = ce.idCarrera) "+
                "GROUP BY c.carrera,ce.fecha_inscripcion "+
                "ORDER BY c.carrera,ce.fecha_inscripcion,ce.fecha_graduacion ASC ";
        Query query=em.createQuery(jpql);
        List<Object[]> resultado=query.getResultList();
        for(Object[]r: resultado) {
            String nombre = (String) r[0];
            int fecha = (int) r[1];
            Long inscriptos = (Long) r[2];
            Long graduados = (Long) r[3];
            if(graduados==0){
                graduados=0L;
            }
            ReporteCarreraDTO nuevo=new ReporteCarreraDTO(nombre,fecha,inscriptos,graduados);
            lista.add(nuevo);
        }
        em.getTransaction().commit();
        return lista;
    }

}
