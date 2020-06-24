package demo;

import entity.Instructor;
import entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteInstructorDetails {
    public static void main(String args[]) {
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .buildSessionFactory();

        Session session = sessionFactory.getCurrentSession();

        try {
            session.beginTransaction();
            int id = 5;
            //Fetching InstructorDetail object
            InstructorDetail instructorDetail = session.get(InstructorDetail.class, id);
            System.out.println("InstructorDetails : " + instructorDetail);

            //Bi-directional mapping
            Instructor instructor = instructorDetail.getInstructor();
            System.out.println("Instructor : " + instructor);

            /*In order to disable Cascade delete, the cascade options are set accordingly
            in InstructorDetail class. But, due to bi-directional mapping, this will fail
            unless the mapping from instructor to instructorDetail is removed. Hence,
            performing the below step.*/
            instructor.setInstructorDetail(null);
            //Try commenting the above step and see what happens

            System.out.println("Deleting InstructorDetail...");
            session.delete(instructorDetail);

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
