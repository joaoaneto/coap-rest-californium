package joaoneto.iot.cf.server;

import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.CoapServer;
import org.eclipse.californium.core.server.resources.CoapExchange;
import static org.eclipse.californium.core.coap.CoAP.ResponseCode.*;

public class BasicServer {

	public static void main(String[] args) {
		
		CoapServer server = new CoapServer();
		
		//server.add(new HelloResource());
		//server.add(new WritableResource());
		server.add(new EnvironmentResource());
		
		//server.addEndpoint(endpoint);
		server.start();

	}
	
	public static class HelloResource extends CoapResource {
		public HelloResource(){
			super("Hello");
			getAttributes().setTitle("Hello-World Resource");
		}
		
		@Override
		public void handleGET(CoapExchange exchange){
			exchange.respond("Hello world Cu");
		}
		
	}
	
	public static class WritableResource extends CoapResource {
		
		public String value = "to be replaced";
		
		public WritableResource(){
			super("Write");
			getAttributes().setTitle("Writable Resource");
		}
		
		/*public void handlePOST(CoapExchange exchange){
			byte[] payload = exchange.getRequestPayload();
		}*/
		
		public void handlePUT(CoapExchange exchange){
			byte[] payload = exchange.getRequestPayload();
			
			try {
				value = new String(payload, "UTF-8");
				exchange.respond(CHANGED, value);
			}catch (Exception e){
				e.printStackTrace();
				exchange.respond(BAD_REQUEST, "Invalid String");
			}
		}
		
	}
	
	public static class EnvironmentResource extends CoapResource {
		
		public String data = null;
		
		
		public EnvironmentResource(){
			super("Environment");
			getAttributes().setTitle("Environment Resource");
		}
		
		@Override
		public void handlePUT(CoapExchange exchange){
			
			byte[] payload = exchange.getRequestPayload();
			
			try{
				data = new String(payload, "UTF-8");
				exchange.respond(CHANGED, data);
			}catch(Exception e){
				e.printStackTrace();
				exchange.respond(BAD_REQUEST, "Invalid String");
			}
			
		}
		
		@Override
		public void handleGET(CoapExchange exchange){
			
			try{
				exchange.respond(data);
			}catch(Exception e){
				e.printStackTrace();
				exchange.respond(BAD_REQUEST, "Invalid Data");
			}
			
		}
		
		//implement the others methods
		
	}

}
