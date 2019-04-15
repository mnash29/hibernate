package com.hibernatedemo;

import com.hibernatedemo.entity.TvShow;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;
import java.util.logging.Logger;

public class ReadTvShow {

    // create logger
    private static Logger logger = Logger.getLogger(TvShow.class.getName());

    public static void main(String [] args) {



        // create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(TvShow.class)
                .buildSessionFactory();

        // create session
        Session session = factory.getCurrentSession();

        try {
            // build queries
            String query1 = "FROM TvShow s WHERE s.network='NBC'";
            String query2 = "FROM TvShow s WHERE s.network='HBO' OR s.genre='Mystery Fiction'";
            String query3 = "FROM TvShow s WHERE s.releaseDate LIKE '2000%'";

            // start a transaction
            session.beginTransaction();

            // query database
            List<TvShow> shows = session.createQuery(query3, TvShow.class).getResultList();
            displayTvShows(shows);

            // commit transaction
            session.getTransaction().commit();

        } finally {
            factory.close();
        }
    }

    private static void displayTvShows(List<TvShow> shows) {
        String msg;

        // display query
        for(TvShow show : shows) {
            msg = show.toString();
            logger.info(msg);
        }
    }
}
