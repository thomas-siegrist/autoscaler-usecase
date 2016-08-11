package ch.sbb.cloud.autoscaler.usecase.jms;

import ch.sbb.cloud.autoscaler.usecase.model.Email;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by thomas on 04.08.16.
 */
@RestController(value = "email")
public class EmailResource {

    @RequestMapping(
            consumes = "application/json",
            method = RequestMethod.POST
    )
    public ResponseEntity<String> createPrintTask(@RequestBody Email email) {
        try {
            for (int i = 0; i < email.getNumberOfAttachments() - 1; i++)
                Thread.sleep(20);
        } catch (InterruptedException e) {
            return handleError(e);
        }
        System.out.println("Successfully posted Email");
        return ResponseEntity.ok("Successfully posted the Email");
    }

    private ResponseEntity<String> handleError(InterruptedException e) {
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }

}
