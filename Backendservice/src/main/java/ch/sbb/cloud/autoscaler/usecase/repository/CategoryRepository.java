/*
 * Copyright (C) Schweizerische Bundesbahnen SBB, 2016.
 */

package ch.sbb.cloud.autoscaler.usecase.repository;

import org.springframework.data.repository.CrudRepository;

import ch.sbb.cloud.autoscaler.usecase.model.Category;

public interface CategoryRepository extends CrudRepository<Category, Long> {
}
