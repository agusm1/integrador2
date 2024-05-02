package com.example.integrador2.factory;

import com.example.integrador2.repository.CERepository;
import com.example.integrador2.repository.CarrRepository;
import com.example.integrador2.repository.EstuRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class FactoryMySQL  extends AbstractFactory{
    private static FactoryMySQL instance = null;
    private static String nombreUnidad="MySQL";
    private static EntityManagerFactory emf= Persistence.createEntityManagerFactory("BDMySQL");
    private static EntityManager entManager=emf.createEntityManager();

    private FactoryMySQL() {
    }

    /**
     * @brief Método estático sincronizado para obtener la instancia de FactoryMySQL.
     * @return FactoryMySQL La instancia de FactoryMySQL.
     */
    public static synchronized FactoryMySQL getInstance() {
        if (instance == null) {
            instance = new FactoryMySQL();
        }
        return instance;
    }

    /**
     * @brief Método para obtener un objeto EstuRepository.
     * @return EstuRepository Un objeto EstuRepository.
     */
    public EstuRepository   getEstudiante(){
        return new EstuRepository(entManager);
    }

    /**
     * @brief Método para obtener un objeto CarrRepository.
     * @return CarrRepository Un objeto CarrRepository.
     */
    public CarrRepository getCarrera(){
        return new CarrRepository(entManager);
    }

    /**
     * @brief Método para obtener un objeto CERepository.
     * @return CERepository Un objeto CERepository.
     */
    public CERepository getCarreraEstudiante(){
        return new CERepository(entManager);
    }

    /**
     * @brief Método estático para obtener el EntityManager de la base de datos MySQL.
     * @return EntityManager El EntityManager de la base de datos MySQL.
     */
    public static EntityManager getEntManager() {
        return entManager;
    }
}
