package org.atmosphere.samples.chat;

import java.io.File;
import java.net.URL;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

public class MainJettyDeployWar {

	public static void main(String[] args) throws Exception {
		final Server server = new Server(8080);
		final WebAppContext webapp = new WebAppContext();
		webapp.setContextPath("/");
		final URL url = new File("target/atmosphere-activemq-chat.war").getAbsoluteFile().toURI().toURL();		
		webapp.setWar(url.getPath());
		//webapp.setWar("target/atmosphere-activemq-chat.war");
		webapp.setExtractWAR(true);
		webapp.setCopyWebInf(true);
		server.setHandler(webapp);

		server.start();
		server.join();
	}
	
	/*
	 * final Server server = new Server(8080);
		final WebAppContext webapp = new WebAppContext();
		webapp.setContextPath("/");		
		webapp.setWar("target/atmosphere-activemq-chat.war");
		webapp.setExtractWAR(true);
		webapp.setCopyWebInf(true);
		server.setHandler(webapp);

		server.start();
		server.join();
	 */
}