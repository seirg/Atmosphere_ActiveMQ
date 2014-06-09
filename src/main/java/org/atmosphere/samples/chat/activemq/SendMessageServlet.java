package org.atmosphere.samples.chat.activemq;

import java.io.IOException;
import java.util.Date;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet(name = "SendMessageServlet", urlPatterns = "/sendMessage")
public class SendMessageServlet extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(SendMessageServlet.class);

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
    	logger.info("doGet() called");
    	String parameter = getTextParameter(httpServletRequest);
        sendMessage(parameter);
        writeResponse(httpServletResponse, parameter);
    }

	private void writeResponse(HttpServletResponse httpServletResponse,
			String parameter) throws IOException {
		httpServletResponse.setContentType("text/plain");
        httpServletResponse.getWriter().write(String.format("Sent message with content '%s'.", parameter));
	}

	private String getTextParameter(HttpServletRequest httpServletRequest) {
		String parameter = httpServletRequest.getParameter("text");
    	if(parameter==null || parameter.isEmpty()) {
    		parameter = (new Date()).toString();
    	}
		return parameter;
	}

    private void sendMessage(String text) {
        try {
        	// Create a ConnectionFactory
            InitialContext initCtx = new InitialContext();
            ConnectionFactory connectionFactory = (ConnectionFactory) initCtx.lookup("java:comp/env/jms/ConnectionFactory");
            
            // Create a Connection
            Connection connection = connectionFactory.createConnection();
            
            // Create a Session
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            
            // Create a MessageProducer from the Session to the Topic or Queue
            MessageProducer producer = session.createProducer((Destination) initCtx.lookup("java:comp/env/jms/queue/MyQueue"));

            // Create a messages
            TextMessage testMessage = session.createTextMessage();
            testMessage.setText(text);
            testMessage.setStringProperty("aKey", "someRandomTestValue");
            
            // Tell the producer to send the message
            producer.send(testMessage);
            logger.debug("Successfully sent message.");
        } catch (Exception e) {
            logger.error("Sending JMS message failed: "+e.getMessage(), e);
        }
    }
}
