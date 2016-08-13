/*
 * Copyright (C) Schweizerische Bundesbahnen SBB, 2016.
 */

package ch.sbb.cloud.autoscaler.usecase.model.interfaces;

import ch.sbb.cloud.autoscaler.usecase.model.interfaces.base.BaseIdModelClass;
import org.springframework.hateoas.ResourceSupport;

import java.util.Set;

public class Category {

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
