package demo;

import entity.Instructor;
import entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Delete {
    public static void main(String args[]) {
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .buildSessionFactory();

        Session session = sessionFactory.getCurrentSession();

        try {
            int id = 4;
            session.beginTransaction();
            Instructor instructor = session.get(Instructor.class, id);

            if (instructor != null) {
                System.out.println("Deleting the instructor..." + instructor);
                //This will delete InstructorDetail also due to CascadeType.ALL
                session.delete(instructor);
            } else
                System.out.println("Instructor not found...");
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
