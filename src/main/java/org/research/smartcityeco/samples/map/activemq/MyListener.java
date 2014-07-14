package org.research.smartcityeco.samples.map.activemq;

import java.io.IOException;
import java.util.logging.Level;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.atmosphere.cpr.BroadcasterFactory;
import org.codehaus.jackson.map.ObjectMapper;
import org.geojson.Feature;
import org.geojson.FeatureCollection;
import org.geojson.Point;
import org.research.smartcityeco.samples.map.atmosphere.GeoMessage;
import org.research.smartcityeco.samples.map.atmosphere.RTAction;
import org.research.smartcityeco.samples.map.atmosphere.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyListener implements MessageListener {
	
	private static final Logger logger = LoggerFactory.getLogger(MyListener.class);
	
    @Override
	public void onMessage(Message message) {
		
		if (message != null && message instanceof TextMessage) {
            TextMessage tm = (TextMessage) message;

            FeatureCollection collection = new FeatureCollection();

            try {
                GeoMessage parsedMessage = new ObjectMapper().readValue(tm.getText(), GeoMessage.class);
                Feature feature = new Feature();
                feature.setGeometry(new Point(parsedMessage.getLongitude(), parsedMessage.getLatitude()));
                collection.add(feature);

				logger.info(String.format("Received TextMessage with text '%s'.", tm.getText()));
                Response response = new Response("Active MQ", RTAction.ADD, collection);
				BroadcasterFactory.getDefault().lookup("/*").broadcast(response);
			} catch (JMSException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
            } catch (IOException ex) {
                logger.info(String.format("Error serializing GeoJSON: '%s'", message));
            }
        } else {
			logger.info(String.format("No TextMessage received: '%s'", message));
		}
		// Read and handle message here.
	}
}
