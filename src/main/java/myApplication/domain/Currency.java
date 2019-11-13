package myApplication.domain;

import javax.persistence.*;

@Entity
@Table(name = "currency")
public class Currency {
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    public Currency(String value) {
        this.value = value;
    }

    public Currency() {
    }

    @Column(nullable = false, length = 3)
    private String value;

    public void setValue(String value) {
        this.value = value;
    }

    public long getId() {
        return id;
    }

    public String getValue() {
        return value;
    }
}
