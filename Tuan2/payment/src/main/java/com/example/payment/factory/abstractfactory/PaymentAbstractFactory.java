package com.example.payment.factory.abstractfactory;

import com.example.payment.payment.Payment;
import com.example.payment.refund.Refund;

public interface PaymentAbstractFactory {
    Payment createPayment();
    Refund createRefund();
}
