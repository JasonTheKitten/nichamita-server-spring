package com.nichamita.sprsrv.service.gateway.imp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.nichamita.sprsrv.service.gateway.GatewayService;
import com.nichamita.sprsrv.service.gateway.GatewaySession;
import com.nichamita.sprsrv.service.gateway.GatewaySessionFilter;

@Service
public class GatewayServiceImp implements GatewayService {

    private final List<GatewaySession> sessions = new ArrayList<>();

    @Override
    public void registerSession(GatewaySession session) {
        sessions.add(session);
    }

    @Override
    public void unregisterSession(GatewaySession session) {
        sessions.remove(session);
    }

    @Override
    public GatewaySessionFilter sessions() {
        return new GatewaySessionFilterImp(sessions.stream());
    }
    


}
