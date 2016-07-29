package ch.sbb.cloud.autoscaler.usecase.model;

import ch.sbb.cloud.autoscaler.usecase.model.base.BaseIdEntity;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * Created by thomas on 29.07.16.
 */
@Entity
public class Article extends BaseIdEntity {

    @Column
    private String name;

    @Column
    private Double price;



}
