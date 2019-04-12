package com.hibernatedemo;

import com.hibernatedemo.entity.TvShow;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.logging.Logger;

public class TvShowDemo {
    public static void main(String [] args) {

        // create logger
        Logger logger = Logger.getLogger(TvShow.class.getName());

        // create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(TvShow.class)
                .buildSessionFactory();

        // create session
        Session session = factory.getCurrentSession();

        try {

            // create tvshow object
            logger.info("Creating new TvShow object...");
            TvShow show = new TvShow(
                    "Game of Thrones",
                    "HBO",
                    "2011-04-17",
                    "Fantasy, Serial Drama");

            // start a transaction
            session.beginTransaction();

            // save tvshow object
            logger.info("Saving the TvShow object");
            session.save(show);

            // commit transaction
            session.getTransaction().commit();
            logger.info("Save successful");

        } finally {
            factory.close();
        }
    }
}
