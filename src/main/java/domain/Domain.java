package domain;

import entity.Bill;
import entity.User;
import hibernateInit.HibirnateUtil;
import org.hibernate.Session;

public class Domain {
    public static void main(String[] args) {
        Session session = HibirnateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        User user = new User(1, 17, "Kirill", "Polian");


        Bill bill = new Bill();
        bill.setId(12354);
        bill.setAmoung(0);
        bill.setCurrency("UAH");
        bill.setUser_id(user);

        session.save(user);
        session.save(bill);

        session.flush();
        session.getTransaction().commit();
        HibirnateUtil.shutdown();
    }
}
