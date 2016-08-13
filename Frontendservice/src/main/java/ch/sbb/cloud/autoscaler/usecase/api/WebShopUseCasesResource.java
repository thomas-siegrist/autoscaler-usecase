package ch.sbb.cloud.autoscaler.usecase.api;

import ch.sbb.cloud.autoscaler.usecase.model.interfaces.Article;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.PagedResources;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by thomas on 04.08.16.
 */
@RestController(value = "/webshop")
public class WebShopUseCasesResource {

    @Value("${backendservice.url}")
    private String backendserviceUrl;

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping(
            path = "/categories",
            produces = "application/json",
            method = RequestMethod.GET
    )
    public Collection getCategories() {
        ResponseEntity<PagedResources<Article>> response = restTemplate
                .exchange(backendserviceUrl + "/categories",
                        HttpMethod.GET, null,
                        new ParameterizedTypeReference<PagedResources<Article>>() {
                        },
                        Collections.emptyMap()
                );
        return safeGetContent(response);
    }

    @RequestMapping(
            path = "/articles",
            produces = "application/json",
            method = RequestMethod.GET
    )
    public Collection<Article> fulltextSearch(@RequestParam(value = "name", required = false) String name) {
        simulateSomeCpuUsage();
        ResponseEntity<PagedResources<Article>> response = restTemplate
                .exchange(backendserviceUrl + "/articles",
                        HttpMethod.GET, null,
                        new ParameterizedTypeReference<PagedResources<Article>>() {
                        },
                        StringUtils.isBlank(name) ? Collections.emptyMap() : urlVariables(name)
                );
        return safeGetContent(response);
    }

    @RequestMapping(
            path = "/checkout/{shoppingCartId}",
            produces = "text/plain",
            method = RequestMethod.POST
    )
    public ResponseEntity<String> placeOrderWithANumberOfItems(@PathVariable(value = "shoppingCartId") Long shoppingCartId) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity(null, headers);

        try {
            restTemplate.exchange(backendserviceUrl + "/checkout/" + shoppingCartId, HttpMethod.POST, entity, ResponseEntity.class);
            return ResponseEntity.ok("Successfully checked out Shopping-Cart with id : " + shoppingCartId);
        } catch (Throwable t) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Checkout unsuccessful! Error-Message: " + t.getMessage());
        }
    }

    private Map<String, String> urlVariables(String name) {
        Map<String, String> urlVariables = new HashMap<>();
        urlVariables.put("name", name);
        return urlVariables;
    }

    private Collection safeGetContent(ResponseEntity<PagedResources<Article>> response) {
        if (response == null || response.getBody() == null || response.getBody().getContent() == null)
            return Collections.EMPTY_LIST;
        return response.getBody().getContent();
    }

    private static void simulateSomeCpuUsage() {
        burnSomeCpu(500, 0);
    }

    private static double burnSomeCpu(int maxRecursionDepth, int currentRecursionDepth) {
        loppALittleBit();
        if (maxRecursionDepth > currentRecursionDepth) {
            if (currentRecursionDepth % 3 == 0) {
                return Math.tan(burnSomeCpu(maxRecursionDepth, ++currentRecursionDepth));
            } else if (currentRecursionDepth % 3 == 1) {
                return Math.tanh(burnSomeCpu(maxRecursionDepth, ++currentRecursionDepth));
            } else {
                return Math.atan(burnSomeCpu(maxRecursionDepth, ++currentRecursionDepth));
            }
        }
        return 1d;
    }

    private static void loppALittleBit() {
        for (Long i = 0l; i < 100_000; i++) {
            // waste CPU
        }
    }

}
