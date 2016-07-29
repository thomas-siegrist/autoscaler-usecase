/*
 * Copyright (C) Schweizerische Bundesbahnen SBB, 2016.
 */

package ch.sbb.cloud.autoscaler.usecase.repository;

import org.springframework.data.repository.CrudRepository;

import ch.sbb.cloud.autoscaler.usecase.model.ShoppingCart;

public interface ShoppingCartRepository extends CrudRepository<ShoppingCart, Long> {
}
