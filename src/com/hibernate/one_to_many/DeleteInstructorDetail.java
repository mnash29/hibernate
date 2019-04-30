package com.hibernate.one_to_many;

import com.hibernate.one_to_many.entity.Instructor;
import com.hibernate.one_to_many.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.logging.Level;
import java.util.logging.Logger;

public class DeleteInstructorDetail {
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
            int id = 4;
            InstructorDetail instructorDetail = session.get(InstructorDetail.class, id);

            // break bi-directional reference
            instructorDetail.getInstructor().setInstructorDetail(null);

            // delete instructor detail - will NOT cascade to Instructor
            logger.log(Level.INFO, "Deleting InstructorDetail: {0}", instructorDetail);
            session.delete(instructorDetail);

            // commit transaction
            session.getTransaction().commit();
        }
        catch (Exception exc) {
            logger.log(Level.WARNING, "Exception deleting instructor detail", exc);
        }
        finally {
            session.close();
            factory.close();
        }
    }
}
