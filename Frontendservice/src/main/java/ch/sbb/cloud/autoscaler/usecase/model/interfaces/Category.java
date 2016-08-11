/*
 * Copyright (C) Schweizerische Bundesbahnen SBB, 2016.
 */

package ch.sbb.cloud.autoscaler.usecase.model.interfaces;

import ch.sbb.cloud.autoscaler.usecase.model.interfaces.base.BaseIdModelClass;

import java.util.Set;

public class Category extends BaseIdModelClass {

    private String name;

    private Set<Article> articles;

    public Set<Article> getArticles() {
        return articles;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
