package kr.ac.kopo.waltdev29.bookmarket.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Address {
    @Id
    @GeneratedValue
    private Long id;
    private String country;
    private String zipcode;
    private String addressname;
    private String detailname;
}
