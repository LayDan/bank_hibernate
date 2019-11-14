package myApplication.domain;

import javax.persistence.*;

@Entity
@Table(name = "rates")
public class Rates {

    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(referencedColumnName = "valute")
    private Currency first;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(referencedColumnName = "valute")
    private Currency second;

    @Column(nullable = false)
    private double x;

    public Currency getFirst() {
        return first;
    }

    public void setFirst(Currency first) {
        this.first = first;
    }

    public Currency getSecond() {
        return second;
    }

    public void setSecond(Currency second) {
        this.second = second;
    }

    public Rates(Currency first, Currency second, double x) {
        this.first = first;
        this.second = second;
        this.x = x;
    }

    public Rates() {
    }


    public void setX(double x) {
        this.x = x;
    }

    public long getId() {
        return id;
    }


    public double getX() {
        return x;
    }
}