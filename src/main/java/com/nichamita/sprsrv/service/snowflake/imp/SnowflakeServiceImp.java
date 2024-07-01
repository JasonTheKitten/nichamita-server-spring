package com.nichamita.sprsrv.service.snowflake.imp;

import org.springframework.stereotype.Service;

import com.nichamita.sprsrv.model.misc.Snowflake;
import com.nichamita.sprsrv.service.snowflake.SnowflakeService;

@Service
public class SnowflakeServiceImp implements SnowflakeService {

    private final com.callicoder.snowflake.Snowflake snowflake;

    public SnowflakeServiceImp() {
        this.snowflake = new com.callicoder.snowflake.Snowflake(0);
    }

    @Override
    public Snowflake generateId() {
        return new Snowflake(snowflake.nextId());
    }
    
}
