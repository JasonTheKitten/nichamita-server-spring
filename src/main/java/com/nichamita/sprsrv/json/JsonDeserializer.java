package com.nichamita.sprsrv.json;

import dev.mccue.json.Json;

public interface JsonDeserializer<T> {
        
    T deserialize(Json json);

}
