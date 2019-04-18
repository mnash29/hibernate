package com.hibernate.demo;

import com.hibernate.demo.entity.TvShow;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.logging.Level;
import java.util.logging.Logger;

public class DeleteTvShow {

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
            int tvShowId = 1;

            // start transaction
            session.beginTransaction();

            // retrieve show based on id: primary key
            logger.log(Level.INFO, "Getting TvShow with id: {0}", tvShowId);
            TvShow show = session.get(TvShow.class, tvShowId);

            // delete the TvShow
            logger.log(Level.INFO, "Deleting TvShow: {0}", show);
            session.delete(show);

            // delete TvShow using createQuery
            logger.log(Level.INFO, "Deleting TvShow with id: 2");
            session.createQuery("DELETE FROM TvShow WHERE id=2").executeUpdate();

            // commit transaction
            session.getTransaction().commit();
        } finally {
            logger.info("Complete!");
            factory.close();
        }
    }
}
