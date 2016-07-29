/*
 * Copyright (C) Schweizerische Bundesbahnen SBB, 2016.
 */

package ch.sbb.cloud.autoscaler.usecase.repository;

import org.springframework.data.repository.CrudRepository;

import ch.sbb.cloud.autoscaler.usecase.model.Article;

public interface ArticleRepository extends CrudRepository<Article, Long> {
}
