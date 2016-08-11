/*
 * Copyright (C) Schweizerische Bundesbahnen SBB, 2016.
 */

package ch.sbb.cloud.autoscaler.usecase.model.interfaces;

import ch.sbb.cloud.autoscaler.usecase.model.interfaces.base.BaseIdModelClass;

public class ShoppingCartItem extends BaseIdModelClass {

    private Article article;

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
