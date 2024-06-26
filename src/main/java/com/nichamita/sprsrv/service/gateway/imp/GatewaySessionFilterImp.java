package com.nichamita.sprsrv.service.gateway.imp;

import java.util.List;
import java.util.stream.Stream;

import com.nichamita.sprsrv.service.gateway.GatewaySession;
import com.nichamita.sprsrv.service.gateway.GatewaySessionFilter;

public class GatewaySessionFilterImp implements GatewaySessionFilter {

    private final Stream<GatewaySession> sessions;

    public GatewaySessionFilterImp(Stream<GatewaySession> sessions) {
        this.sessions = sessions;
    }

    @Override
    public List<GatewaySession> collect() {
        return sessions.toList();
    }
    
}
