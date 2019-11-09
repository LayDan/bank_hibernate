package myApplication.domain;

import javax.persistence.*;

@Entity
@Table(name = "currency_rate")
public class CurrencyRate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 3)
    private Currency firstValue;

    @Column(nullable = false, length = 5)
    private double coefficient;

    @Column(nullable = false, length = 3)
    private Currency secondValue;

    public long getId() {
        return id;
    }

    public Currency getFirstValue() {
        return firstValue;
    }

    public void setFirstValue(Currency firstValue) {
        this.firstValue = firstValue;
    }

    public Currency getSecondValue() {
        return secondValue;
    }

    public void setSecondValue(Currency secondValue) {
        this.secondValue = secondValue;
    }

    public double getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(double coefficient) {
        this.coefficient = coefficient;
    }

}
