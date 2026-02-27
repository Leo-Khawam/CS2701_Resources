package com.example.demo.Models;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Produce")
public class Produce {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "produce", cascade = CascadeType.ALL)
    private List<Seller_Produce> sellerProduces;

    public Produce() {
    }

    public Produce(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Seller_Produce> getSellerProduces() {
        return sellerProduces;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSellerProduces(List<Seller_Produce> sellerProduces) {
        this.sellerProduces = sellerProduces;
    }
}