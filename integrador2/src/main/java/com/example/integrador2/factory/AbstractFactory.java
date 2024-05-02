package com.example.integrador2.factory;

public abstract class AbstractFactory {
    public static final int MYSQL_JDBC = 1;
    public static final int DERBY_JDBC = 2;
    private static FactoryMySQL fabricaMysql= FactoryMySQL.getInstance();
    private static FactoryDerby fabricaDerby= new FactoryDerby();

    /**
     * @brief Retorna el objeto de fábrica apropiado basado en el tipo especificado.
     * @param whichFactory El tipo de fábrica a recuperar.
     * @return AbstractFactory* Un puntero al objeto de fábrica solicitado.
     */
    public static AbstractFactory getFactory(int whichFactory) {
        switch (whichFactory) {
            case MYSQL_JDBC : {
                return fabricaMysql;
            }
            case DERBY_JDBC: {
                return null;
                //return fabricaDerby;
            }
            default: return null;
        }
    }
}

