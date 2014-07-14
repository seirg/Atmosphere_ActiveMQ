package org.research.smartcityeco.samples.chat.activemq;

import java.io.IOException;

import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet(name = "ReceiveMessageListenerChat", urlPatterns = "/receiveMessageListenerChat")
public class ReceiveMessageListenerChat extends HttpServlet {
	private static final Logger logger = LoggerFactory.getLogger(ReceiveMessageListenerChat.class);

	@Override
	protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
			throws ServletException, IOException {
		logger.info("receiveMessage3# doGet() called");
		receiveMessages();
		writeRepsonse(httpServletResponse, "Escuchando mensajitos");
	}

	private void writeRepsonse(HttpServletResponse httpServletResponse, String text) throws IOException {
		httpServletResponse.setContentType("text/plain");
		if (text!=null) {
			httpServletResponse.getWriter().write(String.format("Received message with text '%s'.", text));
		} else {
			httpServletResponse.getWriter().write("Received no message.");
		}
	}

	private void receiveMessages() {
		try {
			// Create a ConnectionFactory
			InitialContext initCtx = new InitialContext();
			QueueConnectionFactory connectionFactory = (QueueConnectionFactory) initCtx
					.lookup("java:comp/env/jms/ConnectionFactory");
			
			// Create a Connection
			QueueConnection queueConnection = connectionFactory.createQueueConnection();
			
			// Create a Session
			QueueSession queueSession = queueConnection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
			Queue queue = (Queue) initCtx.lookup("java:comp/env/jms/queue/MyQueue");
			
			// Create a consumer with listener
			MessageConsumer consumer = queueSession.createConsumer(queue);
			MessageListener listener = new MyListenerChat();
			consumer.setMessageListener(listener);			

			queueConnection.start();			
			
		} catch (Exception e) {
			logger.error("Receiving messages failed: " + e.getMessage(), e);
		}
	}

}
