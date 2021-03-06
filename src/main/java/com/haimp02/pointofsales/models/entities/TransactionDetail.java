package com.haimp02.pointofsales.models.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.text.DecimalFormat;

@Entity
@Table(name = "transaction_details")
public class TransactionDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer quantity;

    @OneToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    @JsonManagedReference
    private Product product;

    @ManyToOne
    @JoinColumn(name="transaction_id",insertable = true, nullable=false)
    @JsonBackReference
    private Transaction transaction;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public String getSelling_price() {
        DecimalFormat df = new DecimalFormat("###,###,###");
        return "Rp " + df.format(this.product.getSelling_price() * this.quantity);
    }


    
}
