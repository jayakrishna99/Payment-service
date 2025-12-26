package com.hyperswitch.core.payments.impl;

import com.hyperswitch.common.enums.PaymentMethod;
import com.hyperswitch.common.enums.PaymentStatus;
import com.hyperswitch.common.types.Amount;
import com.hyperswitch.common.types.PaymentId;
import com.hyperswitch.core.payments.PaymentIntent;
import com.hyperswitch.core.payments.Refund;
import com.hyperswitch.storage.entity.PaymentIntentEntity;
import com.hyperswitch.storage.entity.RefundEntity;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.HashMap;

@Component
public class PaymentMapper {
    
    public PaymentIntent toPaymentIntent(PaymentIntentEntity entity) {
        return PaymentIntent.builder()
            .paymentId(PaymentId.of(entity.getPaymentId()))
            .amount(Amount.of(
                BigDecimal.valueOf(entity.getAmount()).divide(BigDecimal.valueOf(100)),
                Currency.getInstance(entity.getCurrency())
            ))
            .paymentMethod(entity.getMetadata() != null && entity.getMetadata().containsKey("payment_method")
                ? PaymentMethod.valueOf((String) entity.getMetadata().get("payment_method"))
                : null)
            .status(PaymentStatus.valueOf(entity.getStatus()))
            .merchantId(entity.getMerchantId())
            .customerId(entity.getCustomerId())
            .createdAt(entity.getCreatedAt())
            .updatedAt(entity.getModifiedAt())
            .metadata(entity.getMetadata() != null ? entity.getMetadata() : new HashMap<>())
            .connectorName(entity.getConnectorId())
            .build();
    }
    
    public Refund toRefund(RefundEntity entity) {
        return Refund.builder()
            .refundId(entity.getRefundId())
            .paymentId(entity.getPaymentId())
            .amount(Amount.of(
                BigDecimal.valueOf(entity.getRefundAmount()).divide(BigDecimal.valueOf(100)),
                Currency.getInstance(entity.getCurrency())
            ))
            .status(entity.getRefundStatus())
            .reason(entity.getRefundReason())
            .createdAt(entity.getCreatedAt())
            .connectorRefundId(entity.getConnectorRefundId())
            .build();
    }
}

