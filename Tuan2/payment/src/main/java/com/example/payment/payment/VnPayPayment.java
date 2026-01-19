package com.example.payment.payment;

public class VnPayPayment implements Payment {
    @Override
    public void pay(double amount) {
        System.out.println("Thanh toán bằng VNPay: " + amount);
    }
}
