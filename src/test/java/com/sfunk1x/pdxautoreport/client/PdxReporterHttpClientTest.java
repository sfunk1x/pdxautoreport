package com.sfunk1x.pdxautoreport.client;

import org.junit.jupiter.api.Test;
import javax.ws.rs.client.Client;

import static org.junit.jupiter.api.Assertions.*;

class PdxReporterHttpClientTest {

    @Test
    void getClient() {
        PdxReporterHttpClient pdxReporterHttpClient = new PdxReporterHttpClient();
        Client client = pdxReporterHttpClient.getClient();
        assertNotNull(client);
    }
}