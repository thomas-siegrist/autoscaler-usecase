package ch.sbb.cloud.autoscaler.usecase.api;

import ch.sbb.cloud.autoscaler.usecase.model.Payment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by thomas on 04.08.16.
 */
@RestController(value = "payment")
public class PaymentResource {

    @RequestMapping(
            consumes = "application/json",
            method = RequestMethod.POST
    )
    public ResponseEntity<String> makePayment(@RequestBody Payment payment) {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            return handleError(e);
        }
        return ResponseEntity.ok("Successfully posted the Payment");
    }

    private ResponseEntity<String> handleError(InterruptedException e) {
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }

}
