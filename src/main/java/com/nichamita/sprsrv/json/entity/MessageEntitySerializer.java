package com.nichamita.sprsrv.json.entity;

import com.nichamita.sprsrv.model.entity.MessageEntity;

import dev.mccue.json.Json;

public final class MessageEntitySerializer {

    private MessageEntitySerializer() {}

    public static Json serialize(MessageEntity object) {
        return Json.objectBuilder()
            .put("text", object.text())
            .put("createdAt", object.createdAt())
            .put("authorId", object.authorId())
            .build();
    }
    
}
