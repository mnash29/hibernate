package com.hibernate.eager_vs_lazy;

import com.hibernate.one_to_many.entity.Course;
import com.hibernate.one_to_many.entity.Instructor;
import com.hibernate.one_to_many.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.logging.Level;
import java.util.logging.Logger;

public class EagerVsLazy {
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

            // get instructor
            int id = 1;
            Instructor instructor = session.get(Instructor.class, id);
            logger.log(Level.INFO, "Instructor: {0}", instructor);

            // get courses for the instructor
            logger.log(Level.INFO, "Courses: {0}", instructor.getCourses());

            // commit transaction
            session.getTransaction().commit();
        }
        catch (Exception exc) {
            logger.log(Level.WARNING, "Exception in EagerVsLazy Main", exc);
        }
        finally {
            session.close();
            factory.close();
        }
    }
}
