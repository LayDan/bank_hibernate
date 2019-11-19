package myApplication.domain;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
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

}
