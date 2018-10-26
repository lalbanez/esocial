package br.gov.esocial.servicos.empregador.consulta.ws;

public class ConsultaClientProxy implements br.gov.esocial.servicos.empregador.consulta.ws.ConsultaClient {
  private String _endpoint = null;
  private br.gov.esocial.servicos.empregador.consulta.ws.ConsultaClient consultaClient = null;
  
  public ConsultaClientProxy() {
    _initConsultaClientProxy();
  }
  
  public ConsultaClientProxy(String endpoint) {
    _endpoint = endpoint;
    _initConsultaClientProxy();
  }
  
  private void _initConsultaClientProxy() {
    try {
      consultaClient = (new br.gov.esocial.servicos.empregador.consulta.ws.ConsultaClientServiceLocator()).getConsultaClient();
      if (consultaClient != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)consultaClient)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)consultaClient)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (consultaClient != null)
      ((javax.xml.rpc.Stub)consultaClient)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public br.gov.esocial.servicos.empregador.consulta.ws.ConsultaClient getConsultaClient() {
    if (consultaClient == null)
      _initConsultaClientProxy();
    return consultaClient;
  }
  
  public java.lang.String consultaEsocial(java.lang.String xml) throws java.rmi.RemoteException{
    if (consultaClient == null)
      _initConsultaClientProxy();
    return consultaClient.consultaEsocial(xml);
  }
  
  
}