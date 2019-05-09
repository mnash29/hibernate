package com.hibernate.eager_vs_lazy;

import com.hibernate.eager_vs_lazy.entity.Course;
import com.hibernate.eager_vs_lazy.entity.Instructor;
import com.hibernate.eager_vs_lazy.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.logging.Level;
import java.util.logging.Logger;

public class LazyLoading {
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

            // get instructor with lazy loading
            int instructorId = 1;
            Instructor instructor = session.get(Instructor.class, instructorId);
            logger.log(Level.INFO, "Lazy: Instructor: {0}", instructor);

            // get courses for the instructor
            logger.log(Level.INFO, "Lazy: Courses: {0}", instructor.getCourses());

            // commit transaction
            session.getTransaction().commit();
        }
        catch (Exception exc) {
            logger.log(Level.WARNING, "Exception in LazyLoading Main", exc);
        }
        finally {
            session.close();
            factory.close();
        }
    }
}
