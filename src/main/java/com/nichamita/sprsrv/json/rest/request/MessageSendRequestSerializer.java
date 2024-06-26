package com.nichamita.sprsrv.json.rest.request;

import com.nichamita.sprsrv.json.JsonDeserializer;
import com.nichamita.sprsrv.model.rest.request.MessageSendRequest;

import dev.mccue.json.Json;
import dev.mccue.json.JsonDecoder;

public class MessageSendRequestSerializer implements JsonDeserializer<MessageSendRequest>{

    @Override
    public MessageSendRequest deserialize(Json json) {
        return new MessageSendRequest(
            JsonDecoder.field(json, "message", JsonDecoder::string)
        );
    }

}
