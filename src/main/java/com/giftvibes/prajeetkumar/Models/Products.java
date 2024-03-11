package com.giftvibes.prajeetkumar.Models;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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
@Table(name = "_products")
public class Products {


    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String prodId;

    @Column(nullable = false)
    private String prodName;
    
    @Column(nullable = false)
    private String price;
    
    @Column(nullable = false)
    private String quantity;


    @ManyToOne
    @JoinColumn(name = "fk_catId")
    private  Category category ;

    

}