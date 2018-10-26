/**
 * EnvioClientService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.gov.esocial.servicos.empregador.envio.ws;

public interface EnvioClientService extends javax.xml.rpc.Service {
    public java.lang.String getEnvioClientAddress();

    public br.gov.esocial.servicos.empregador.envio.ws.EnvioClient getEnvioClient() throws javax.xml.rpc.ServiceException;

    public br.gov.esocial.servicos.empregador.envio.ws.EnvioClient getEnvioClient(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
