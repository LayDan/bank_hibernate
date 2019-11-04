package myApplication.domain;

import javax.persistence.*;

@Entity
@Table(name = "bill")
public class Bill {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(nullable = false, length = 3)
    private String currency;

    @Column(nullable = false)
    private double amoung;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(referencedColumnName = "id")
    private User user_id;


    public Bill(long number_card, String currency, double amoung, User user_id) {
        this.number_card = number_card;
        this.currency = currency;
        this.amoung = amoung;
        this.user_id = user_id;
    }

    public User getUser_id() {
        return user_id;
    }

    @Column(nullable = false)
    private long number_card;

    public long getNumber_card() {
        return number_card;
    }

    public void setNumber_card(long number_card) {
        this.number_card = number_card;
    }

    public Bill() {
    }

    public int getId() {
        return id;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public double getAmoung() {
        return amoung;
    }

    public void setAmoung(double amoung) {
        this.amoung = amoung;
    }

}
