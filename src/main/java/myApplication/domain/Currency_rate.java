package myApplication.domain;

import javax.persistence.*;

@Entity
@Table
public class Currency_rate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private long id;


    @Column(nullable = false)
    private String firstValue;

    @Column(nullable = false)
    private double coefficient;

    @Column(nullable = false)
    private String secondValue;


    public long getCurrency_id() {
        return id;
    }

    public Currency_rate(String firstValue, double coefficient, String secondValue) {
        this.firstValue = firstValue;
        this.coefficient = coefficient;
        this.secondValue = secondValue;
    }


    public String getFirstValue() {
        return firstValue;
    }

    public void setFirstValue(String firstValue) {
        this.firstValue = firstValue;
    }

    public double getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(double coefficient) {
        this.coefficient = coefficient;
    }

    public String getSecondValue() {
        return secondValue;
    }

    public void setSecondValue(String secondValue) {
        this.secondValue = secondValue;
    }

    public Currency_rate() {
    }
}