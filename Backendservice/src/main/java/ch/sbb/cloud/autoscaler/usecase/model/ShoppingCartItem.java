/*
 * Copyright (C) Schweizerische Bundesbahnen SBB, 2016.
 */

package ch.sbb.cloud.autoscaler.usecase.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import ch.sbb.cloud.autoscaler.usecase.model.base.BaseIdEntity;

@Entity
public class ShoppingCartItem extends BaseIdEntity {

    @ManyToOne
    private Article article;

    @Column
    private Long amount;

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

}
