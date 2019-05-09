package com.hibernate.eager_vs_lazy;

import com.hibernate.eager_vs_lazy.entity.Course;
import com.hibernate.eager_vs_lazy.entity.Instructor;
import com.hibernate.eager_vs_lazy.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.logging.Level;
import java.util.logging.Logger;

public class EagerLoading {
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
            // start transaction
            session.beginTransaction();

            // get instructor with eager loading
            int instructorId = 1;
            Instructor instructor = session.get(Instructor.class, instructorId);
            logger.log(Level.INFO, "Eager: Instructor: {0}", instructor);

            // get courses for the instructor
            logger.log(Level.INFO, "Eager: Courses: {0}", instructor.getCourses());

            // commit transaction
            session.getTransaction().commit();
        }
        catch (Exception exc) {
            logger.log(Level.WARNING, "Exception in EagerLoading Main", exc);
        }
        finally {
            session.close();
            factory.close();
        }
    }
}
