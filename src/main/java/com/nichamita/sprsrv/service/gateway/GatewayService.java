package com.nichamita.sprsrv.service.gateway;

public interface GatewayService {

    void registerSession(GatewaySession session);

    void unregisterSession(GatewaySession session);

    GatewaySessionFilter sessions();

}
