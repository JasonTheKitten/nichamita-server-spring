package com.nichamita.sprsrv.model.misc;

public record Snowflake(long longValue) {
    
    public String stringValue() {
        return Long.toString(longValue);
    }

    public static Snowflake fromString(String string) {
        return new Snowflake(Long.parseLong(string));
    }

}