package com.hibernate.one_to_many;

import com.hibernate.one_to_many.entity.Course;
import com.hibernate.one_to_many.entity.Instructor;
import com.hibernate.one_to_many.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CreateCourses {
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

            // get instructor from database
            int id = 1;
            Instructor instructor = session.get(Instructor.class, id);

            // create sample courses
            Course [] courses = { new Course("Data Structures"),
                new Course("Machine Learning"),
                new Course("Big Data Analytics")
            };

            // add courses to instructor
            instructor.add(Arrays.asList(courses));

            // save courses
            for(Course course : courses) {
                session.save(course);
            }
            // commit transaction
            session.getTransaction().commit();
            logger.log(Level.INFO, "Complete!");

        } finally {
            session.close();
            factory.close();
        }
    }
}
