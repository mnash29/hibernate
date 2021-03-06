package com.hibernate.one_to_many;

import com.hibernate.one_to_many.entity.Course;
import com.hibernate.one_to_many.entity.Instructor;
import com.hibernate.one_to_many.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.logging.Level;
import java.util.logging.Logger;

public class CreateInstructor {
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

            // Create objects
            Instructor instructor = new Instructor("Elizabeth", "Monnat", "liz.monnat.luv2code.com");

            InstructorDetail instructorDetail = new InstructorDetail(
                    "http://www.youtube.com", "Gaming");

            // Associate Instructor with InstructorDetail
            instructor.setInstructorDetail(instructorDetail);

            // start a transaction
            session.beginTransaction();

            /*
             save the instructor
             NOTE: this will ALSO save the details object of CascadeType.ALL
            */
            session.save(instructor);
            logger.log(Level.INFO, "Saving instructor: {0}", instructor);

            // commit transaction
            session.getTransaction().commit();
            logger.info("Save successful");

        } finally {
            session.close();
            factory.close();
        }
    }
}
