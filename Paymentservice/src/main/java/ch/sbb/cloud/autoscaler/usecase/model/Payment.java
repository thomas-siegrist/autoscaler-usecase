package ch.sbb.cloud.autoscaler.usecase.model;

import org.springframework.hateoas.ResourceSupport;

/**
 * Created by thomas on 04.08.16.
 */
public class Payment extends ResourceSupport {

    private String account;
    private Long amount;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

}
