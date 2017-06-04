package joaoneto.iot.cf.server;

import static org.eclipse.californium.core.coap.CoAP.ResponseCode.BAD_REQUEST;
import static org.eclipse.californium.core.coap.CoAP.ResponseCode.CHANGED;
import static org.eclipse.californium.core.coap.CoAP.ResponseCode.CREATED;
import static org.eclipse.californium.core.coap.CoAP.ResponseCode.NOT_ACCEPTABLE;

import java.io.UnsupportedEncodingException;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.coap.MediaTypeRegistry;
import org.eclipse.californium.core.coap.CoAP.ResponseCode;
import org.eclipse.californium.core.server.resources.CoapExchange;

public class TemperatureResource extends CoapResource {
	
	private String temperatura;
	
	public TemperatureResource(){
		super("Temperature");
		getAttributes().setTitle("Temperature Resource");
		//Timer timer = new Timer();
		//timer.schedule(new UpdateTask(this), 0, 1000);
	}
	
	@Override
	public void handleGET(CoapExchange exchange){
		exchange.respond(ResponseCode.CONTENT, this.temperatura, MediaTypeRegistry.TEXT_PLAIN);
	}
	
	@Override
	public void handlePOST(CoapExchange exchange){
		byte[] temp_payload = exchange.getRequestPayload();
		exchange.accept();
		
		if (this.temperatura == null){
			try {
				this.temperatura = new String(temp_payload, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			exchange.respond(CREATED);
		}else{
			exchange.respond(NOT_ACCEPTABLE);
		}
		
	}
	
	@Override
	public void handlePUT(CoapExchange exchange){
		byte[] temp_payload = exchange.getRequestPayload();
	
		try {
			this.temperatura = new String(temp_payload, "UTF-8");
			exchange.respond(CHANGED, this.temperatura);
		}catch (Exception e){
			e.printStackTrace();
			exchange.respond(BAD_REQUEST, "Invalid String");
		}
		this.changed();
	}
	
	/*private class UpdateTask extends TimerTask {
		private CoapResource mCoapRes;
		
		public UpdateTask(CoapResource coapRes) {
			mCoapRes = coapRes;
		}
		
		@Override
		public void run() {
			temperatura = temperatura + 4.3;
			mCoapRes.changed();
			}
		}*/
	
}
