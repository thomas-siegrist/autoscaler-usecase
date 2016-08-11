package ch.sbb.cloud.autoscaler.usecase.model.interfaces;

import org.springframework.hateoas.ResourceSupport;

/**
 * Created by thomas on 04.08.16.
 */
public class Email extends ResourceSupport {

    private String receiver;
    private String content;
    private Long numberOfAttachments;

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getNumberOfAttachments() {
        return numberOfAttachments;
    }

    public void setNumberOfAttachments(Long numberOfAttachments) {
        this.numberOfAttachments = numberOfAttachments;
    }
}
