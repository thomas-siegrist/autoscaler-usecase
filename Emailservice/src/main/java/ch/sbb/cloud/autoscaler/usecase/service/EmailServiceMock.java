package ch.sbb.cloud.autoscaler.usecase.service;

import ch.sbb.cloud.autoscaler.usecase.infrastructure.EmailServer;
import ch.sbb.cloud.autoscaler.usecase.model.Email;
import org.springframework.stereotype.Component;

/**
 * Created by thomas on 13.08.16.
 */
@Component
public class EmailServiceMock {

    private static EmailServer EMAIL_SERVER;

    private static EmailServer instance() {
        if (EMAIL_SERVER == null)
            EMAIL_SERVER = new EmailServer();
        return EMAIL_SERVER;
    }

    public void sendEmail(Email email) {
        instance().sendMail(email);
    }

}
