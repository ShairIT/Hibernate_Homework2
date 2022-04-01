package peaksoft;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import peaksoft.entity.User;
import peaksoft.util.HibernateUtil;

import java.io.Serializable;
import java.util.List;

public class App 
{
    public static void main( String[] args )
    {

        HibernateUtil.getSessionFactory();

        User user = new User("Gulshair", "Matraimova",  37);
       // create(user);
        getRollback();



    }


    public static int create(User user) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();
        session.close();
        System.out.println("Added successfully " + user);
        return user.getId();
    }

        public static List<User> read() {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            List<User> user = session.createQuery("FROM User").getResultList();
            session.getTransaction().commit();
            session.close();
            System.out.println("Finded " + user.size() + " user ");
            return user;

        }

        public static void update(int id, String name, String surname, int age){
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            User user = session.get(User.class, id);
            user.setName(name);
            user.setSurname(surname);
            user.setAge(age);
            session.getTransaction().commit();
            System.out.println("User is successfully updated");
            session.close();
    }

        public static void delete(int id){
        Session session = HibernateUtil.getSessionFactory().openSession();
        User user = session.get(User.class, id);
        session.delete(user);
        session.getTransaction().commit();
        System.out.println("Successfully deleted");
        session.close();
    }

    public static void deleteAll(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("DELETE FROM User");
        query.executeUpdate();
        session.getTransaction().commit();
        System.out.println("Succesfully deleted all datas in User");
        session.close();
    }

    public static User getById(int id){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        User user = session.get(User.class, id);
        session.getTransaction().commit();
        session.close();
        return user;
    }

    public static User getRollback(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.getTransaction();
        session.beginTransaction();
        session.getTransaction().commit();
        User user = new User();
        session.getTransaction().rollback();
        session.close();
        System.out.println("User successfully rollback" + user);
        return user;

    }
}


