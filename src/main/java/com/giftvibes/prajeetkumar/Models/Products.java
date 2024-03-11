package com.giftvibes.prajeetkumar.Models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "_products", uniqueConstraints = @UniqueConstraint(columnNames = {"prodName"}))
public class Products {


    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String prodId;

    @Column(nullable = false)
    private String prodName;
    
    @Column(nullable = false)
    private String price;
    
    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private  String category;

    

}