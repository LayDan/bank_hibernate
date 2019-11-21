package application.domain;

import lombok.*;

import javax.persistence.*;

//Data вызывает ошибки в hashcode из-за циклической зависимости между счётом пользователя и самим счётом
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "bill")
public class Bill {
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long numberCard;

    //Currency
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(referencedColumnName = "valute")
    private Currency currency;

    @Column(nullable = false)
    private double amoung;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(referencedColumnName = "id")
    @NonNull
    private User userId;


    public Bill(Currency currency, double amoung, User userId) {
        this.currency = currency;
        this.amoung = amoung;
        this.userId = userId;
    }


}
