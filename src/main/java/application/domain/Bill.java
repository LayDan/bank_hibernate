package application.domain;

import lombok.*;

import javax.persistence.*;

//Data вызывает ошибки в hashcode из-за циклической зависимости между счётом пользователя и самим счётом
@Entity
@Data
@Builder
@EqualsAndHashCode(exclude = "userId")
@AllArgsConstructor
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
    private User userId;
}
