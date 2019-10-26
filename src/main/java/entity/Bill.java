package entity;

import javax.persistence.*;

@Entity
@Table(name = "bill")
public class Bill {
    @Id
    @PrimaryKeyJoinColumn(name = "Number_card_Id")
    private int id;

    @Column(name = "Currency", nullable = false, length = 3)
    private String currency;

    @Column(name = "Amoung", nullable = false)
    private double amoung;

    @Column(name = "UserID", nullable = false)
    private int user_id;

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

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
}
