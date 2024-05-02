package com.example.integrador2.factory;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class FactoryDerby extends AbstractFactory{
    private static FactoryDerby instance = null;
    public static String nombreUnidad="bDDerby";
    private EntityManager em=null;
    private EntityManagerFactory emf=null;

    public FactoryDerby() {
    }
    /**
     * @brief Constructor parametrizado de FactoryDerby.
     * @param em El EntityManager para la base de datos Derby.
     * @param emf El EntityManagerFactory para la base de datos Derby.
     */
    public FactoryDerby(EntityManager em, EntityManagerFactory emf) {
        this.em = em;
        this.emf = emf;
    }
    /**
     * @brief Método estático sincronizado para obtener la instancia de FactoryDerby.
     * @return FactoryDerby La instancia de FactoryDerby.
     */
    public static synchronized FactoryDerby getEntityManagerFactory() {
        if (instance == null) {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory(FactoryDerby.nombreUnidad);
            EntityManager em =emf.createEntityManager();
            instance = new FactoryDerby(em,emf);
        }
        return instance;
    }
}
