package myApplication.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "transaction")
public class Transaction {
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String message;

    @Column
    private long bill_id;

    @Column
    private long user_id;

    public long getId() {
        return id;
    }
}
