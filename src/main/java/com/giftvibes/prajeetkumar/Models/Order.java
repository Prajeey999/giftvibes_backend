package com.giftvibes.prajeetkumar.Models;

import java.sql.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "_orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String ordId;

    @Column(nullable = false)
    private Date date ;

    
    @ManyToMany
    @JoinTable(
        name = "order_product",
        joinColumns = { @JoinColumn(name = "order_id") },
        inverseJoinColumns = { @JoinColumn(name = "product_id") }
    )
    private List<Products> products ;

    @ManyToOne
    @JoinColumn(name = "fk_Id")
    private User user;

}
