package com.haimp02.pointofsales.models.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
// import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Date transaction_date;

    
    @ManyToOne
    @JoinColumn(name = "customer_id")
    @JsonBackReference
    private Customer customer;

    @OneToMany(mappedBy = "transaction")
    private List<TransactionDetail> transaction_details;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getTransaction_date() {
        return transaction_date;
    }

    public void setTransaction_date(Date transaction_date) {
        this.transaction_date = transaction_date;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<TransactionDetail> getTransaction_details() {
        return transaction_details;
    }

    public void setTransaction_details(List<TransactionDetail> transaction_details) {
        this.transaction_details = transaction_details;
    }

    public String getTransactionNumber() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("ddMMYYYY");
        return "TRX/" + simpleDateFormat.format(this.transaction_date) + "/" + String.format("%03d", this.id);
    }

    
}
