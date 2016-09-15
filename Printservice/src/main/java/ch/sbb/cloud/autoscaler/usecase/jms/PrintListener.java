package ch.sbb.cloud.autoscaler.usecase.jms;

import ch.sbb.cloud.autoscaler.usecase.model.PrintResponse;
import ch.sbb.cloud.autoscaler.usecase.model.PrintTask;
import ch.sbb.cloud.autoscaler.usecase.model.interfaces.Email;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Created by thomas on 04.08.16.
 */
@Component
public class PrintListener {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RabbitListener(queues = "print-queue")
    public void receive(String json) {
        System.out.println(json);
        PrintTask printTask = jsonToObject(json);
        print(printTask);
        sendEmail(printResponse("Printed Successfully", printTask.getNumberOfOrders()));
    }

    private void sendEmail(PrintResponse printResponse) {
        Email email = email(printResponse);
        rabbitTemplate.convertAndSend("email-queue", toJson(email));
    }

    private Email email(PrintResponse printResponse) {
        Email email = new Email();
        email.setContent("An EMail with " + printResponse.getNumberOfPages() + " pages to attached.");
        email.setNumberOfAttachments(Long.valueOf(printResponse.getNumberOfPages()));
        email.setReceiver("test@gmail.com");
        return email;
    }

    private String toJson(Email email) {
        try {
            return new ObjectMapper().writeValueAsString(email);
        } catch (JsonProcessingException e) {
            handleError(e);
            return null;
        }
    }

    private PrintTask jsonToObject(String json) {
        try {
            return new ObjectMapper().readValue(json, PrintTask.class);
        } catch (IOException e) {
            e.printStackTrace();
            return dummyPrintTask();
        }
    }

    private PrintTask dummyPrintTask() {
        PrintTask printTask = new PrintTask();
        printTask.setNumberOfOrders(10);
        return printTask;
    }

    private void print(PrintTask printTask) {
        try {
            for (int i = 0; i < printTask.getNumberOfOrders() - 1; i++)
                Thread.sleep(10);
        } catch (InterruptedException e) {
            handleError(e);
        }
    }

    private PrintResponse printResponse(String response, Integer numberOfOrders) {
        PrintResponse printResponse = new PrintResponse();
        printResponse.setResponse(response);
        printResponse.setNumberOfPages(numberOfPages(numberOfOrders)); // 10 Orders per page
        return printResponse;
    }

    private int numberOfPages(Integer numberOfOrders) {
        return Double.valueOf(Math.ceil(numberOfOrders / 10)).intValue();
    }

    private String handleError(Exception e) {
        e.printStackTrace();
        return e.getMessage();
    }

}
