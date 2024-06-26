package com.nichamita.sprsrv.json.rest.request;

import com.nichamita.sprsrv.model.rest.request.MessageSendRequest;

import dev.mccue.json.Json;
import dev.mccue.json.JsonDecoder;

public final class MessageSendRequestSerializer {

    private MessageSendRequestSerializer() {}

    public static MessageSendRequest deserialize(Json json) {
        return new MessageSendRequest(
            JsonDecoder.field(json, "message", JsonDecoder::string)
        );
    }

}
