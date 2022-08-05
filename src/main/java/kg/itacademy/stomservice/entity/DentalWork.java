package kg.itacademy.stomservice.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "dental_work")
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DentalWork extends BaseEntity {

    @Column(name = "dental_work_name")
    String dentalWorkName;

    @Column(name = "code")
    Integer code;

    @Column(name = "category")
    String category;

    @OneToOne
    @JoinColumn(name = "dentist_id")
    Dentist dentist;

    @Column(name = "price")
    BigDecimal price;

}
