package com.example.payment.factory.abstractfactory;

import com.example.payment.payment.MomoPayment;
import com.example.payment.payment.Payment;
import com.example.payment.refund.Refund;

public class MomoFactory implements PaymentAbstractFactory {

    @Override
    public Payment createPayment() {
        return new MomoPayment();
    }

    @Override
    public Refund createRefund() {
        return amount -> System.out.println("Hoàn tiền MoMo: " + amount);
    }
}
