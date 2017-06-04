package joaoneto.iot.cf.server;

import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.CoapServer;
import org.eclipse.californium.core.coap.CoAP.ResponseCode;
import org.eclipse.californium.core.coap.MediaTypeRegistry;
import org.eclipse.californium.core.server.resources.CoapExchange;
import static org.eclipse.californium.core.coap.CoAP.ResponseCode.*;

import java.io.UnsupportedEncodingException;
import java.util.Random;
import java.util.Timer;

public class BasicServer {

	public static void main(String[] args) {
		
		//binds on UDP port 5683
		CoapServer server = new CoapServer();
		
		TemperatureResource tmpResource = new TemperatureResource();
		tmpResource.setObservable(true);
		
		server.add(tmpResource);
		
		server.start();

	}
	
}