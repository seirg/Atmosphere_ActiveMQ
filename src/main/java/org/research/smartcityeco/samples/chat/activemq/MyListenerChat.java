package org.research.smartcityeco.samples.chat.activemq;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.atmosphere.cpr.BroadcasterFactory;
import org.research.smartcityeco.samples.chat.atmosphere.ResponseChat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyListenerChat implements MessageListener {
	
	private static final Logger logger = LoggerFactory.getLogger(MyListenerChat.class);
	
    @Override
	public void onMessage(Message message) {
		
		if (message != null && message instanceof TextMessage) {
            TextMessage tm = (TextMessage) message;

            try {
				logger.info(String.format("Received TextMessage with text '%s'.", tm.getText()));
                ResponseChat response = new ResponseChat("Active MQ", tm.getText());				
				BroadcasterFactory.getDefault().lookup("betis").broadcast(response);
			} catch (JMSException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
            } 
        } else {
			logger.info(String.format("No TextMessage received: '%s'", message));
		}
		// Read and handle message here.
	}
}
