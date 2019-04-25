package com.hibernate.one_to_one;

import com.hibernate.one_to_one.entity.Instructor;
import com.hibernate.one_to_one.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.logging.Level;
import java.util.logging.Logger;

public class DeleteInstructor {
    public static void main(String [] args) {

        // create logger
        Logger logger = Logger.getLogger(Instructor.class.getName());

        // create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .buildSessionFactory();

        // create session
        Session session = factory.getCurrentSession();

        try {

            // start a transaction
            session.beginTransaction();

            // get instructor by primary key: id
            int id = 3;
            Instructor instructor = session.get(Instructor.class, id);
            logger.log(Level.INFO, "Found instructor: {0}", instructor);

            // delete instructor
            if(instructor != null) {
                logger.log(Level.INFO, "Deleting: {0}", instructor);

                // NOTE: will ALSO delete associated "instructorDetails" object
                // becuase of CascadeType.ALL
                session.delete(instructor);
            }

            // commit transaction
            session.getTransaction().commit();

        } finally {
            factory.close();
        }
    }
}
