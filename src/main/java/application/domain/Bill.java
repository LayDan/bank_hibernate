package application.domain;

import lombok.*;

import javax.persistence.*;

//Data вызывает ошибки в hashcode из-за циклической зависимости между счётом пользователя и самим счётом
@Entity
@Data
@Builder
@Inheritance
@EqualsAndHashCode(exclude = "user")
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "bill")
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long numberCard;

    //Currency
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "currency", referencedColumnName = "valute")
    private Currency currency;

    @Column(nullable = false)
    private Double amoung;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(referencedColumnName = "id")
    private User user;
}
