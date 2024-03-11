package com.giftvibes.prajeetkumar.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name = "_category")
public class Category {


    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String catId;

    @Column(nullable = false)
    private String catName;

}
