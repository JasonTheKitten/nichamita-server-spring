package com.nichamita.sprsrv.json.entity;

import com.nichamita.sprsrv.model.entity.Message;

import dev.mccue.json.Json;

public final class MessageEntitySerializer {

    private MessageEntitySerializer() {}

    public static Json serialize(Message object) {
        return Json.objectBuilder()
            .put("id", object.id())
            .put("text", object.text())
            .put("createdAt", object.createdAt())
            .put("authorId", object.authorId())
            .build();
    }
    
}
