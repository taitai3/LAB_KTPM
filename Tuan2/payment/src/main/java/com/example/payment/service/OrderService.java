package com.example.payment.service;

import com.example.payment.factory.PaymentFactory;
import com.example.payment.factory.abstractfactory.PaymentAbstractFactory;
import com.example.payment.payment.Payment;
import com.example.payment.refund.Refund;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    public void checkout(String type, double amount) {
        Payment payment = PaymentFactory.createPayment(type);
        payment.pay(amount);
    }

    public void checkoutWithRefund(PaymentAbstractFactory factory, double amount) {
        Payment payment = factory.createPayment();
        Refund refund = factory.createRefund();

        payment.pay(amount);
        refund.refund(amount);
    }
}
