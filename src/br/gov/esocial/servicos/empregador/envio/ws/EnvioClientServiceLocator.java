/**
 * EnvioClientServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.gov.esocial.servicos.empregador.envio.ws;

public class EnvioClientServiceLocator extends org.apache.axis.client.Service implements br.gov.esocial.servicos.empregador.envio.ws.EnvioClientService {

    public EnvioClientServiceLocator() {
    }


    public EnvioClientServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public EnvioClientServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for EnvioClient
    private java.lang.String EnvioClient_address = "http://localhost:7001/envioconsultaesocial/services/EnvioClient";

    public java.lang.String getEnvioClientAddress() {
        return EnvioClient_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String EnvioClientWSDDServiceName = "EnvioClient";

    public java.lang.String getEnvioClientWSDDServiceName() {
        return EnvioClientWSDDServiceName;
    }

    public void setEnvioClientWSDDServiceName(java.lang.String name) {
        EnvioClientWSDDServiceName = name;
    }

    public br.gov.esocial.servicos.empregador.envio.ws.EnvioClient getEnvioClient() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(EnvioClient_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getEnvioClient(endpoint);
    }

    public br.gov.esocial.servicos.empregador.envio.ws.EnvioClient getEnvioClient(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            br.gov.esocial.servicos.empregador.envio.ws.EnvioClientSoapBindingStub _stub = new br.gov.esocial.servicos.empregador.envio.ws.EnvioClientSoapBindingStub(portAddress, this);
            _stub.setPortName(getEnvioClientWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setEnvioClientEndpointAddress(java.lang.String address) {
        EnvioClient_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (br.gov.esocial.servicos.empregador.envio.ws.EnvioClient.class.isAssignableFrom(serviceEndpointInterface)) {
                br.gov.esocial.servicos.empregador.envio.ws.EnvioClientSoapBindingStub _stub = new br.gov.esocial.servicos.empregador.envio.ws.EnvioClientSoapBindingStub(new java.net.URL(EnvioClient_address), this);
                _stub.setPortName(getEnvioClientWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("EnvioClient".equals(inputPortName)) {
            return getEnvioClient();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://envio.empregador.servicos.esocial.gov.br", "EnvioClientService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://envio.empregador.servicos.esocial.gov.br", "EnvioClient"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("EnvioClient".equals(portName)) {
            setEnvioClientEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
