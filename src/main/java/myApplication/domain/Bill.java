package myApplication.domain;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "bill")
public class Bill {
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long number_card;

    //Currency
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(referencedColumnName = "valute")
    private Currency currency;

    @Column(nullable = false)
    private double amoung;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(referencedColumnName = "id")
    @NonNull
    private User user_id;


    public Bill(Currency currency, double amoung, User user_id) {
        this.currency = currency;
        this.amoung = amoung;
        this.user_id = user_id;
    }

    public Bill() {
    }


}
