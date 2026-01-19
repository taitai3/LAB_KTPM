package com.example.payment.factory;

import com.example.payment.payment.*;

public class PaymentFactory {

    public static Payment createPayment(String type) {
        return switch (type.toUpperCase()) {
            case "VNPAY" -> new VnPayPayment();
            case "MOMO" -> new MomoPayment();
            case "PAYPAL" -> new PaypalPayment();
            default -> throw new IllegalArgumentException("Payment type not supported");
        };
    }
}
