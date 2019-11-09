package myApplication.domain;

import javax.persistence.*;

@Entity
@Table(name = "bill")
public class Bill {
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int number_card;

    @Column(nullable = false, length = 3)
    @JoinColumn(referencedColumnName = "currency")
    private Currency currency;

    @Column(nullable = false)
    private double amoung;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(referencedColumnName = "id")
    private User user_id;


    public Bill(Currency currency, double amoung, User user_id) {
        this.currency = currency;
        this.amoung = amoung;
        this.user_id = user_id;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public void setNumber_card(int number_card) {
        this.number_card = number_card;
    }

    public void setAmoung(double amoung) {
        this.amoung = amoung;
    }

    public void setUser_id(User user_id) {
        this.user_id = user_id;
    }

    public int getNumber_card() {
        return number_card;
    }


    public double getAmoung() {
        return amoung;
    }

    public User getUser_id() {
        return user_id;
    }

    public Bill() {
    }


}
