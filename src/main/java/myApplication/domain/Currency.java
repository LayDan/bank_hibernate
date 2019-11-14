package myApplication.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "currencys")
//Сериалайзибл нужен для отображения в currencyRate.ftl
public class Currency implements Serializable {
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    public Currency(String value) {
        this.valute = value;
    }

    public Currency() {
    }

    @Column(name = "valute")
    private String valute;

    public void setValue(String value) {
        this.valute = value;
    }

    public long getId() {
        return id;
    }

    public String getValue() {
        return valute;
    }
}
