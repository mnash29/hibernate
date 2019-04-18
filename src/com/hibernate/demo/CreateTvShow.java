package com.hibernate.demo;

import com.hibernate.demo.entity.TvShow;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.logging.Logger;

public class CreateTvShow {
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
            logger.info("Creating new TvShow objects...");

            TvShow [] shows = { new TvShow("This Is Us", "NBC", "2016-09-16", "Family Drama"),
                    new TvShow("The Office", "NBC", "2005-03-24", "Sitcom"),
                    new TvShow("Seinfeld", "NBC", "1989-04-05", "Sitcom") };

            // start a transaction
            session.beginTransaction();

            logger.info("Saving the TvShow objects");
            for(TvShow show : shows) {
                // save tvshow objects
                session.save(show);
            }

            // commit transaction
            session.getTransaction().commit();
            logger.info("Save successful");

        } finally {
            factory.close();
        }
    }
}
