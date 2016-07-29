package ch.sbb.cloud.autoscaler.usecase.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import ch.sbb.cloud.autoscaler.usecase.model.base.BaseIdEntity;

/**
 * Created by thomas on 29.07.16.
 */
@Entity
public class Article extends BaseIdEntity {

    @Column
    private String name;

    @Column
    private String description;

    @ManyToOne(cascade = CascadeType.ALL)
    private Category category;

    @Column
    private Double price;

    @Column
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
