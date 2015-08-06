package com.videumcorp.desarrolladorandroid.navigationdrawerandroiddesignsupportlibrary;


/**
 * Created by Gallo on 31/07/2015.
 */
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.*;
import org.ksoap2.transport.HttpTransportSE;

public class webservice {

    private static final String SOAP_ACTION = "http://WebService/";
    private static final String NAMESPACE = "http://WebService/";
    private static final String URL = "http://192.168.43.126:8080/clinicaWS/clinica?xsd=1";

    public static String LlamadaLogin(String userName,String passWord, String webMethName) {
        String loginStatus = "NO";
        SoapObject request = new SoapObject(NAMESPACE,webMethName);
        PropertyInfo parametroUsr = new PropertyInfo();
        PropertyInfo parametroClave = new PropertyInfo();
        parametroUsr.setName("usuario");
        parametroUsr.setValue(userName);
        parametroUsr.setType(String.class);
        request.addProperty(parametroUsr);
        parametroClave.setName("password");
        parametroClave.setValue(passWord);
        parametroClave.setType(String.class);
        request.addProperty(parametroClave);
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(request);
        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
        try {
            androidHttpTransport.call(SOAP_ACTION+webMethName,envelope);
            SoapPrimitive response = (SoapPrimitive) envelope.getResponse();
            loginStatus = response.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return loginStatus;
    }


    public static String Recetas(int id,String webMethName) {
        String loginStatus = "NO";
        SoapObject request = new SoapObject(NAMESPACE,webMethName);
        PropertyInfo parametroId = new PropertyInfo();
        parametroId.setName("id");
        parametroId.setValue(id);
        parametroId.setType(int.class);
        request.addProperty(parametroId);
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(request);
        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
        try {
            androidHttpTransport.call(SOAP_ACTION+webMethName,envelope);
            SoapPrimitive response = (SoapPrimitive) envelope.getResponse();
            loginStatus = response.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return loginStatus;
    }


    public static String Citas(int opc,String fecha, String hora,int medico, int idPac, int id,String webMethName) {
        String loginStatus = "NO";
        SoapObject request = new SoapObject(NAMESPACE,webMethName);
        PropertyInfo parametroOpc= new PropertyInfo();
        PropertyInfo parametroFecha = new PropertyInfo();
        PropertyInfo parametroHora = new PropertyInfo();
        PropertyInfo parametroMedico = new PropertyInfo();
        PropertyInfo parametroIdPac = new PropertyInfo();
        PropertyInfo parametroId = new PropertyInfo();
        parametroOpc.setName("opc"); parametroOpc.setValue(opc); parametroOpc.setType(int.class); request.addProperty(parametroOpc);
        parametroHora.setName("hora"); parametroHora.setValue(hora); parametroHora.setType(int.class); request.addProperty(parametroHora);
        parametroFecha.setName("fecha"); parametroFecha.setValue(fecha); parametroFecha.setType(int.class); request.addProperty(parametroFecha);
        parametroMedico.setName("idmedico"); parametroMedico.setValue(medico); parametroMedico.setType(int.class); request.addProperty(parametroMedico);
        parametroIdPac.setName("idpaciente"); parametroIdPac.setValue(idPac); parametroIdPac.setType(int.class); request.addProperty(parametroIdPac);
        parametroId.setName("id"); parametroId.setValue(id); parametroId.setType(int.class); request.addProperty(parametroId);
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(request);
        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
        try {
            androidHttpTransport.call(SOAP_ACTION+webMethName,envelope);
            SoapPrimitive response = (SoapPrimitive) envelope.getResponse();
            loginStatus = response.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return loginStatus;
    }

}
