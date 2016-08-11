package ch.sbb.cloud.autoscaler.usecase.model.interfaces;


import ch.sbb.cloud.autoscaler.usecase.model.interfaces.base.BaseIdModelClass;

/**
 * Created by thomas on 29.07.16.
 */
public class Article extends BaseIdModelClass {

    private String name;

    private String description;

    private Category category;

    private Double price;

    private Long stock;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Long getStock() {
        return stock;
    }

    public void setStock(Long stock) {
        this.stock = stock;
    }

}
