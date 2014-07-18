package org.research.smartcityeco.samples.map.activemq;

import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import org.atmosphere.cpr.BroadcasterFactory;
import org.geojson.Feature;
import org.geojson.FeatureCollection;
import org.geojson.Point;
import org.research.smartcityeco.samples.map.atmosphere.GeoPayload;
import org.research.smartcityeco.samples.map.atmosphere.GeoResponse;
import org.research.smartcityeco.samples.map.atmosphere.RTAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyListener implements MessageListener {

    private static final Logger logger = LoggerFactory.getLogger(MyListener.class);

    @Override
    public void onMessage(Message message) {

        if (message != null && message instanceof ObjectMessage) {

            ObjectMessage om = (ObjectMessage) message;

            Serializable payload =null;
            try {
                payload = om.getObject();
            } catch (JMSException ex) {
                throw new RuntimeException(ex);
            }

            if (payload != null && payload instanceof GeoPayload) {
                GeoPayload gm = (GeoPayload) payload;

                FeatureCollection collection = new FeatureCollection();

                Feature feature = new Feature();
                feature.setGeometry(new Point(gm.getLongitude(), gm.getLatitude()));
                collection.add(feature);

                logger.info(String.format("Received GeoMessage: %s", gm.toString()));
                GeoResponse response = new GeoResponse("Active MQ", RTAction.ADD, collection);
                BroadcasterFactory.getDefault().lookup("/*").broadcast(response);

            }

        } else {
            logger.info(String.format("No TextMessage received: '%s'", message));
        }
        // Read and handle message here.
    }
}
