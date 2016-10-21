package com.tommy.myplamlibrary.api;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;


public class ServerClient {

	public static String method(String methodName,String data){
		String result = "";
		HttpTransportSE ht = new HttpTransportSE("");
		
		ht.debug = true;
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER10);
		
		SoapObject request = new SoapObject(HttpConfig.getNameSpace(), methodName);
		if(data != null){
			request.addProperty("obj",data);
		}
		
		envelope.dotNet  = true;
		
		envelope.setOutputSoapObject(request);
		try {
			ht.call(null, envelope);
			if(envelope.getResponse() != null){
				SoapObject resp = (SoapObject) envelope.bodyIn;
				
				result = resp.getProperty(0).toString();
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	
}
