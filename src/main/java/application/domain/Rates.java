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
@Table(name = "rates")
public class Rates {

    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "first", referencedColumnName = "valute")
    private Currency first;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "second", referencedColumnName = "valute")
    private Currency second;

    @Column(nullable = false)
    private Double coefficient;

}