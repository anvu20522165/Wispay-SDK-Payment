package org.wispay.payment.sdk.mservice.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class QueryResponse implements Serializable {
    public List<Transaction> transList;
    private String requestId;
    private String billId;
    private Double amount;
    private String currency;
    private String state;
    private Long transId;
    private Integer resultCode;
    private Long paymentDate;
    private String pspRefId;
    private String pspCode;
    private Double totalPaidTransAmount;
    private Double remainingAmount;
    private Double totalRefundAmount;
    private String paymentState;

    public static class Transaction {

        private Long id;

        private String state;

        private Double amount;

        private String currency;

        private String requestId;

        private String pspCode;

        private String pspRefId;

        private String type;

        private Integer resultCode;

        private Long paymentTime;

        private Long createdAt;

        private String phase;

        private Set<Transaction> refundedList = new HashSet<>();
    }

    public List<Transaction> getTransList() {
        return transList;
    }

    public String getRequestId() {
        return requestId;
    }

    public String getBillId() {
        return billId;
    }

    public Double getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }

    public String getState() {
        return state;
    }

    public Long getTransId() {
        return transId;
    }

    public Integer getResultCode() {
        return resultCode;
    }

    public Long getPaymentDate() {
        return paymentDate;
    }

    public String getPspRefId() {
        return pspRefId;
    }

    public String getPspCode() {
        return pspCode;
    }

    public Double getTotalPaidTransAmount() {
        return totalPaidTransAmount;
    }

    public String getPaymentState() {
        return paymentState;
    }

    public Double getTotalRefundAmount() {
        return totalRefundAmount;
    }

    public Double getRemainingAmount() {
        return remainingAmount;
    }
}
