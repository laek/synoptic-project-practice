package com.example.synopticprojectpractice;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class PaymentCard {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer paymentCardId;

    private Double balance = 0.0;

    public Integer getPaymentCardId() {
        return paymentCardId;
    }

    public void setPaymentCardId(Integer cardId) {
        this.paymentCardId = cardId;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(int amount) {
        this.balance = balance - amount;
    }
}
