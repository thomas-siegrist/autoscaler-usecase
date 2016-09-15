package ch.sbb.cloud.autoscaler.usecase.jms;

import ch.sbb.cloud.autoscaler.usecase.model.Email;
import ch.sbb.cloud.autoscaler.usecase.service.EmailServiceMock;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Created by thomas on 04.08.16.
 */
@Component
public class EmailListener {

    @Autowired
    private EmailServiceMock emailServiceMock;

    @RabbitListener(queues = "email-queue")
    public void receive(String json) {
        System.out.println(json);
        Email email = jsonToObject(json);
        sendEmail(email);
        System.out.println("Successfully posted Email");
    }

    private void sendEmail(Email email) {
        try {
            for (int i = 0; i < email.getNumberOfAttachments() - 1; i++)
                Thread.sleep(50);
            emailServiceMock.sendEmail(email);
        } catch (InterruptedException e) {
            handleError(e);
        }
    }

    private Email jsonToObject(String json) {
        try {
            return new ObjectMapper().readValue(json, Email.class);
        } catch (IOException e) {
            e.printStackTrace();
            return dummyEmail();
        }
    }

    private Email dummyEmail() {
        Email email = new Email();
        email.setContent("Dummy-Mail");
        email.setNumberOfAttachments(1L);
        email.setReceiver("dummy@receiver.com");
        return email;
    }

    private String handleError(InterruptedException e) {
        e.printStackTrace();
        return e.getMessage();
    }

}
