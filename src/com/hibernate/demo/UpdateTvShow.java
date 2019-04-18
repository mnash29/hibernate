package com.hibernate.demo;

import com.hibernate.demo.entity.TvShow;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.logging.Logger;

public class UpdateTvShow {

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
            int tvShowId = 1000;
            String msg;

            // start the session
            session.beginTransaction();

            // update database object
            TvShow show = session.get(TvShow.class, tvShowId);
            msg = "Get complete: " + show.toString();
            logger.info(msg);
            logger.info("Making Update");
            show.setGenre("Comedy Drama");

            // commit the transaction
            session.getTransaction().commit();
            logger.info("Complete");

        } finally {
            factory.close();
        }
    }
}
