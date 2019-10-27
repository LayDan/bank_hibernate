package entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "bill")
public class Bill implements Serializable {
    @Id
    @Column(name = "Number_card_Id")
    private int id;

    @Column(name = "Currency", nullable = false, length = 3)
    private String currency;

    @Column(name = "Amoung", nullable = false)
    private double amoung;

    @ManyToOne()
    @JoinColumn(name = "UserID", referencedColumnName = "Id")
    private User user_id;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public User getUser_id() {
        return user_id;
    }

    public void setUser_id(User user_id) {
        this.user_id = user_id;
    }

}
