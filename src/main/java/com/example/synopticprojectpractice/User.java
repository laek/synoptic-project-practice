package com.example.synopticprojectpractice;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer employeeId;

    private String employeeName;

    private Integer paymentCardId;

    public Integer getEmployeeIdId() {
        return employeeId;
    }

    public void setId(Integer id) {
        this.employeeId = id;
    }

    public String getName() {
        return employeeName;
    }

    public void setName(String name) {
        this.employeeName = name;
    }

    public Integer getPaymentCard() {
        return paymentCardId;
    }

    public void setPaymentCard(Integer cardId) {
        this.paymentCardId = cardId;
    }
}