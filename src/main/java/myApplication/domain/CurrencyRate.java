package myApplication.domain;

import javax.persistence.*;

@Entity
@Table(name = "currency_rate")
public class CurrencyRate {


    public CurrencyRate(String firstValue, double coefficient, String secondValue) {
        this.firstValue = firstValue;
        this.coefficient = coefficient;
        this.secondValue = secondValue;
    }

    public CurrencyRate() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 3)
    private String firstValue;

    @Column(nullable = false, length = 4)
    private double coefficient;

    @Column(nullable = false, length = 3)
    private String secondValue;

    public long getId() {
        return id;
    }

    public String getFirstValue() {
        return firstValue;
    }

    public void setFirstValue(String firstValue) {
        this.firstValue = firstValue;
    }

    public String getSecondValue() {
        return secondValue;
    }

    public void setSecondValue(String secondValue) {
        this.secondValue = secondValue;
    }

    public double getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(double coefficient) {
        this.coefficient = coefficient;
    }

}