package application.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Table(name = "transaction")
public class Transaction {
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String message;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "bill", referencedColumnName = "numberCard")
    private Bill bill;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user", referencedColumnName = "id")
    private User user;
}
