package ch.sbb.cloud.autoscaler.usecase.model.base;

import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by thomas on 29.07.16.
 */
public abstract class BaseIdEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
