package com.dammike.bookstore.graemelee.model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import java.util.Date;

/**
 * Super class representing all entities. This class can directly be extended by the entities that represent
 * neither a surrogate key or name.
 * @see HoldingRequest
 */
@MappedSuperclass
@Data
public abstract class SuperEntity {
    @CreationTimestamp
    protected Date created;
    protected Date modified;
    @Version
    @Column(name = "VERSION", columnDefinition = "long default 1")
    private Long version;
}
