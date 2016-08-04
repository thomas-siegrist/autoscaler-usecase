package ch.sbb.cloud.autoscaler.usecase.api;

import ch.sbb.cloud.autoscaler.usecase.model.PrintResponse;
import ch.sbb.cloud.autoscaler.usecase.model.PrintTask;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by thomas on 04.08.16.
 */
@RestController(value = "print")
public class PrintResource {

    @RequestMapping(
            consumes = "application/json",
            method = RequestMethod.POST
    )
    public ResponseEntity<PrintResponse> createPrintTask(@RequestBody PrintTask printTask) {
        String response = "";
        try {
            for (int i = 0; i < printTask.getNumberOfOrders() - 1; i++)
                Thread.sleep(20);
        } catch (InterruptedException e) {
            response = handleError(e);
        }
        response = "Successfully posted the PrintTask";
        return ResponseEntity.ok(printResponse(response, printTask.getNumberOfOrders()));
    }

    private PrintResponse printResponse(String response, Integer numberOfOrders) {
        PrintResponse printResponse = new PrintResponse();
        printResponse.setResponse(response);
        printResponse.setNumberOfPages(numberOfOrders / 10); // 10 Orders per page
        return printResponse;
    }

    private String handleError(InterruptedException e) {
        e.printStackTrace();
        return e.getMessage();
    }

}
