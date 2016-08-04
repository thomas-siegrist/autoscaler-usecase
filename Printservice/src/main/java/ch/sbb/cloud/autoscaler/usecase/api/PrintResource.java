package ch.sbb.cloud.autoscaler.usecase.api;

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
    public ResponseEntity<String> createPrintTask(@RequestBody PrintTask printTask) {
        try {
            for (int i = 0; i < printTask.getNumberOfOrders() - 1; i++)
                Thread.sleep(20);
        } catch (InterruptedException e) {
            return handleError(e);
        }
        return ResponseEntity.ok("Successfully posted the PrintTask");
    }

    private ResponseEntity<String> handleError(InterruptedException e) {
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }

}
