package com.nichamita.sprsrv.json;

import dev.mccue.json.Json;

public interface JsonSerializer<T> {
    
    Json serialize(T object);

}
