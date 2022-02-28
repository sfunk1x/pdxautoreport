package com.sfunk1x.pdxautoreport.client;

import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.springframework.stereotype.Component;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;

@Component
public class PdxReporterHttpClient {

    private final Client client;

    public PdxReporterHttpClient() {
        try {
            this.client = buildSuboptimalSSLClient();
        } catch (NoSuchAlgorithmException|KeyManagementException exception) {
            throw new RuntimeException("Fatal error building Jersey client! Exiting.", exception);
        }
    }

    public Client getClient() {
        return client;
    }

    // TLS configuration at pdxreporter.org needs help.
    private Client buildSuboptimalSSLClient() throws NoSuchAlgorithmException, KeyManagementException {
        SSLContext sslcontext = SSLContext.getInstance("TLS");
        sslcontext.init(null, new TrustManager[]{new X509TrustManager() {
            public void checkClientTrusted(X509Certificate[] arg0, String arg1) {}
            public void checkServerTrusted(X509Certificate[] arg0, String arg1) {}
            public X509Certificate[] getAcceptedIssuers() { return new X509Certificate[0]; }
        }}, new java.security.SecureRandom());

        return ClientBuilder.newBuilder()
                .register(MultiPartFeature.class)
                .sslContext(sslcontext)
                .hostnameVerifier((s1, s2) -> true)
                .build();
    }
}
