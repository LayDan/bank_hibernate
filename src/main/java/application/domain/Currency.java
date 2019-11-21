package application.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
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

    @Column(name = "valute")
    private String valute;

}