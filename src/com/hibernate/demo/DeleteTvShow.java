package com.hibernate.demo;

import com.hibernate.demo.entity.TvShow;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

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
            //
        } finally {
            factory.close();
        }
    }
}
