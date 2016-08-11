package ch.sbb.cloud.autoscaler.usecase.api;

import ch.sbb.cloud.autoscaler.usecase.model.interfaces.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.hateoas.PagedResources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

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
    public Collection<Category> getCategories() {
        ResponseEntity<PagedResources> response = restTemplate.getForEntity(backendserviceUrl + "/categories", PagedResources.class);
        return safeGetContent(response);
    }

    @RequestMapping(
            path = "/articles",
            produces = "application/json",
            method = RequestMethod.GET
    )
    public Collection<Article> fulltextSearch(@RequestParam(value = "name") String name) {
        simulateSomeCpuUsage();
        ResponseEntity<PagedResources> response = restTemplate.getForEntity(backendserviceUrl + "/articles", PagedResources.class, urlVariables(name));
        return safeGetContent(response);
    }

    @RequestMapping(
            path = "/checkout",
            method = RequestMethod.POST
    )
    public void placeOrderWithANumberOfItems(@RequestParam(value = "numberOfItems") Long numberOfItems) {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setCustomer(customer());
        shoppingCart.getItems().addAll(someSoppingCartItems(numberOfItems));
        Long shoppingCartId = restTemplate.postForObject(backendserviceUrl + "/shoppingCart", shoppingCart, Long.class);
        restTemplate.postForObject(backendserviceUrl + "/checkout", shoppingCartId, String.class);
    }

    private Collection<? extends ShoppingCartItem> someSoppingCartItems(Long numberOfItems) {
        List<Article> allArticles = getAllArticles().stream().collect(Collectors.toList());
        List<ShoppingCartItem> items = new ArrayList<>();
        if (allArticles.size() >= numberOfItems) {
            items.addAll(allArticles.stream()
                    .map(article -> toShoppingCartItem(article, 1L))
                    .collect(Collectors.toList()));
        } else {
            items.add(toShoppingCartItem(allArticles.get(0), numberOfItems));
        }
        return items;
    }

    private Collection<Article> getAllArticles() {
        ResponseEntity<PagedResources> response = restTemplate.getForEntity(backendserviceUrl + "/articles", PagedResources.class);
        return safeGetContent(response);
    }

    private ShoppingCartItem toShoppingCartItem(Article article, Long amount) {
        ShoppingCartItem item = new ShoppingCartItem();
        item.setAmount(amount);
        item.setArticle(article);
        return item;
    }

    private Customer customer() {
        Customer customer = new Customer();
        customer.setEmail("test@gmail.com");
        customer.setFirstName("FirstName");
        customer.setLastName("LastName");
        return customer;
    }

    private Map<String, String> urlVariables(String name) {
        Map<String, String> urlVariables = new HashMap<>();
        urlVariables.put("name", name);
        return urlVariables;
    }

    private Collection safeGetContent(ResponseEntity<PagedResources> response) {
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
        for (Long i = 0l; i<100_000; i++) {
            // waste CPU
        }
    }

}
