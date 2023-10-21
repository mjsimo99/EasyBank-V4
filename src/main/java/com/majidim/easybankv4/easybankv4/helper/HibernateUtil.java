package com.majidim.easybankv4.easybankv4.helper;


import com.majidim.easybankv4.easybankv4.dto.Client;
import com.majidim.easybankv4.easybankv4.dto.Employe;
import com.majidim.easybankv4.easybankv4.dto.Personne;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class HibernateUtil {
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            Configuration configuration = new Configuration();

            configuration.setProperty("hibernate.connection.driver_class", "org.postgresql.Driver");
            configuration.setProperty("hibernate.connection.url", "jdbc:postgresql://localhost:5432/easybankv3");
            configuration.setProperty("hibernate.connection.username", "postgres");
            configuration.setProperty("hibernate.connection.password", "hatikmi");
            configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
            configuration.setProperty("hibernate.show_sql", "true");

            configuration.addAnnotatedClass(Personne.class);
            configuration.addAnnotatedClass(Client.class);
            configuration.addAnnotatedClass(Employe.class);

            sessionFactory = configuration.buildSessionFactory();
        }
        return sessionFactory;
    }
}