package com.nichamita.sprsrv.model.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("messages")
public record Message(
    @Id long id,
    String text,
    long createdAt,
    long authorId
) {
    
}
