/*
 * Copyright (C) Schweizerische Bundesbahnen SBB, 2016.
 */

package ch.sbb.cloud.autoscaler.usecase.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import ch.sbb.cloud.autoscaler.usecase.model.base.BaseIdEntity;

@Entity
public class Category extends BaseIdEntity {

    @Column
    private String name;

    @Column
    @OneToMany(orphanRemoval = true, mappedBy = "category")
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
