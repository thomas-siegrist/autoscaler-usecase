package ch.sbb.cloud.autoscaler.usecase.model;

/**
 * Created by thomas on 04.08.16.
 */
public class PrintResponse {

    private String response;
    private Integer numberOfPages;

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public Integer getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(Integer numberOfPages) {
        this.numberOfPages = numberOfPages;
    }
}
