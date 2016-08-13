package ch.sbb.cloud.autoscaler.usecase.infrastructure;

import ch.sbb.cloud.autoscaler.usecase.model.Email;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by thomas on 13.08.16.
 */
@Component
public class EmailServer {

    public static final int MAX_MESSAGES_PER_SECOND = 10;

    private static int BUFFER = 0;

    @Scheduled(fixedRate = 2_000)
    public void clearBuffer() {
        BUFFER = 0;
    }

    public synchronized void sendMail(Email email) {
        if (BUFFER >= MAX_MESSAGES_PER_SECOND * 2) {
            System.out.println("EMail-Server-Buffer is full: waiting 2 seconds to retry!");
            try {
                Thread.sleep(2_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        BUFFER++;
    }

}
