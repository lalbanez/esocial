package br.gov.esocial.servicos.empregador.envio.ws;

public class EnvioClientProxy implements br.gov.esocial.servicos.empregador.envio.ws.EnvioClient {
  private String _endpoint = null;
  private br.gov.esocial.servicos.empregador.envio.ws.EnvioClient envioClient = null;
  
  public EnvioClientProxy() {
    _initEnvioClientProxy();
  }
  
  public EnvioClientProxy(String endpoint) {
    _endpoint = endpoint;
    _initEnvioClientProxy();
  }
  
  private void _initEnvioClientProxy() {
    try {
      envioClient = (new br.gov.esocial.servicos.empregador.envio.ws.EnvioClientServiceLocator()).getEnvioClient();
      if (envioClient != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)envioClient)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)envioClient)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (envioClient != null)
      ((javax.xml.rpc.Stub)envioClient)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public br.gov.esocial.servicos.empregador.envio.ws.EnvioClient getEnvioClient() {
    if (envioClient == null)
      _initEnvioClientProxy();
    return envioClient;
  }
  
  public java.lang.String envioEsocial(java.lang.String xml) throws java.rmi.RemoteException{
    if (envioClient == null)
      _initEnvioClientProxy();
    return envioClient.envioEsocial(xml);
  }
  
  
}