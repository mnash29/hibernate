package com.hibernate.one_to_one;

import com.hibernate.one_to_one.entity.Instructor;
import com.hibernate.one_to_one.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.logging.Level;
import java.util.logging.Logger;

public class GetInstructorDetail {
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
            // start transaction
            session.beginTransaction();

            // get instructor detail
            int id = 2;
            InstructorDetail instructorDetail = session.get(InstructorDetail.class, id);

            // print instructor detail
            logger.log(Level.INFO, "InstructorDetail: {0}", instructorDetail);

            // print associated instructor
            logger.log(Level.INFO, "Associated Instructor: {0}", instructorDetail.getInstructor());

            // commit transaction
            session.getTransaction().commit();
        } finally {
            factory.close();
        }
    }
}
