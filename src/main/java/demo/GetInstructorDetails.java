package demo;

import entity.Instructor;
import entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class GetInstructorDetails {
    public static void main(String args[]) {
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .buildSessionFactory();

        Session session = sessionFactory.getCurrentSession();

        try {
            session.beginTransaction();
            int id = 1;
            //Fetching InstructorDetail object
            InstructorDetail instructorDetail = session.get(InstructorDetail.class, id);

            System.out.println("InstructorDetails : " + instructorDetail);

            //Bi-directional mapping
            Instructor instructor = instructorDetail.getInstructor();

            System.out.println("Instructor : " + instructor);

            session.getTransaction().commit();

            System.out.println("Done");
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            session.close();
            sessionFactory.close();
        }
    }
}
