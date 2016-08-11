package ch.sbb.cloud.autoscaler.usecase.jms;

import ch.sbb.cloud.autoscaler.usecase.model.interfaces.Email;
import ch.sbb.cloud.autoscaler.usecase.model.PrintResponse;
import ch.sbb.cloud.autoscaler.usecase.model.PrintTask;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

/**
 * Created by thomas on 04.08.16.
 */
@RabbitListener(queues = "print-queue")
public class PrintListener {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RabbitHandler
    public void receive(String json) {
        PrintTask printTask = jsonToObject(json);
        print(printTask);
        sendEmail(printResponse("Printed Successfully", printTask.getNumberOfOrders()));
    }

    private void sendEmail(PrintResponse printResponse) {
        rabbitTemplate.convertAndSend("email-queue", email(printResponse));
    }

    private Email email(PrintResponse printResponse) {
        Email email = new Email();
        email.setContent("An EMail with " + printResponse.getNumberOfPages() + " pages to attached.");
        email.setNumberOfAttachments(Long.valueOf(printResponse.getNumberOfPages()));
        email.setReceiver("test@gmail.com");
        return email;
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
                Thread.sleep(20);
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

    private String handleError(InterruptedException e) {
        e.printStackTrace();
        return e.getMessage();
    }

}
