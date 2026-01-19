package com.example.payment.factory.abstractfactory;

import com.example.payment.payment.Payment;
import com.example.payment.payment.VnPayPayment;
import com.example.payment.refund.Refund;

public class VnPayFactory implements PaymentAbstractFactory {

    @Override
    public Payment createPayment() {
        return new VnPayPayment();
    }

    @Override
    public Refund createRefund() {
        return amount -> System.out.println("Hoàn tiền VNPay: " + amount);
    }
}
