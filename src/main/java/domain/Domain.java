package domain;

import entity.Bill;
import entity.User;
import hibernateInit.HibirnateUtil;
import org.hibernate.Session;

public class Domain {
    public static void main(String[] args) {
        Session session = HibirnateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        User user = new User();
        user.setAge(17);
        user.setFirstName("Kirill");
        user.setSecondName("Polian");
        user.setID(1);

        Bill bill = new Bill();
        bill.setId(123);
        bill.setAmoung(0);
        bill.setCurrency("UAH");
        bill.setUser_id(user.getID());

        session.save(user);
        session.save(bill);

        session.getTransaction().commit();
        HibirnateUtil.shutdown();
    }
}
