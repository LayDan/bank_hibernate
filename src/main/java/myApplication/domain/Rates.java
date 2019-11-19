package myApplication.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "rates")
public class Rates {

    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "valute")
    private Currency first;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "valute")
    private Currency second;

    @Column(nullable = false)
    private double x;


    public Rates(Currency first, Currency second, double x) {
        this.first = first;
        this.second = second;
        this.x = x;
    }

    public Rates() {
    }
}