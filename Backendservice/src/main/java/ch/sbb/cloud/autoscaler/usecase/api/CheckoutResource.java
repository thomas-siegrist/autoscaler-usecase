package ch.sbb.cloud.autoscaler.usecase.api;

import ch.sbb.cloud.autoscaler.usecase.model.ShoppingCart;
import ch.sbb.cloud.autoscaler.usecase.model.ShoppingCartItem;
import ch.sbb.cloud.autoscaler.usecase.model.interfaces.Payment;
import ch.sbb.cloud.autoscaler.usecase.model.interfaces.PrintTask;
import ch.sbb.cloud.autoscaler.usecase.repository.ShoppingCartRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Created by thomas on 04.08.16.
 */
@RestController(value = "/checkout")
public class CheckoutResource {

    @Value("${paymentservice.url}")
    private String paymentserviceUrl;

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private RabbitTemplate template;

    @RequestMapping(
            value = "/checkout/{id}",
            consumes = "application/json",
            method = RequestMethod.POST
    )
    public void checkout(@PathVariable(value = "id") Long shoppingCartId) {
        ShoppingCart shoppingCart = shoppingCartRepository.findOne(shoppingCartId);
        makePayment(shoppingCart);
        createPrintTask(shoppingCart);
    }

    public void makePayment(ShoppingCart shoppingCart) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Payment payment = createPayment(shoppingCart);
        restTemplate.postForObject(paymentserviceUrl + "/payment", new HttpEntity(payment, headers), String.class);
    }

    private Payment createPayment(ShoppingCart shoppingCart) {
        Payment payment = new Payment();
        payment.setAccount("CH99 1234 3412 4934 02");
        payment.setAmount(Long.valueOf(shoppingCart.getItems().size()));
        return payment;
    }

    private void createPrintTask(ShoppingCart shoppingCart) {
        PrintTask printTask = new PrintTask();
        printTask.setNumberOfOrders(numberOfItemsIn(shoppingCart));
        this.template.convertAndSend("print-queue", toJson(printTask));
    }

    private Integer numberOfItemsIn(ShoppingCart shoppingCart) {
        int counter = 0;
        for (ShoppingCartItem shoppingCartItem : shoppingCart.getItems()) {
            counter += shoppingCartItem.getAmount();
        }
        return counter;
    }

    private String toJson(PrintTask printTask) {
        try {
            return new ObjectMapper().writeValueAsString(printTask);
        } catch (JsonProcessingException e) {
            handleError(e);
            return null;
        }
    }

    private void handleError(Throwable t) {
        t.printStackTrace();
    }

}
