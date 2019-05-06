package com.hibernate.one_to_many;

import com.hibernate.one_to_many.entity.Course;
import com.hibernate.one_to_many.entity.Instructor;
import com.hibernate.one_to_many.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.logging.Level;
import java.util.logging.Logger;

public class DeleteCourse {
    public static void main(String [] args) {

        // create logger
        Logger logger = Logger.getLogger(Instructor.class.getName());

        // create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .buildSessionFactory();

        // create session
        Session session = factory.getCurrentSession();

        try {

            // start a transaction
            session.beginTransaction();

            // get course
            int id = 10;
            Course course = session.get(Course.class, id);
            logger.log(Level.INFO, "Deleting Course: {0}", course);

            // delete course
            session.delete(course);

            // commit transaction
            session.getTransaction().commit();

        } finally {
            factory.close();
        }
    }
}
