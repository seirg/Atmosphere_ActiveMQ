package org.atmosphere.samples.chat;

import org.atmosphere.cpr.AtmosphereServlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class MainJetty {

	public static void main(String[] args) throws Exception {
		Server server = new Server(8080);
    
//    /*  <init-param>
//            <param-name>com.sun.jersey.config.property.packages</param-name>
//            <param-value>org.atmosphere.samples.chat.jersey</param-value>
//        </init-param>
//        <init-param>
//            <param-name>org.atmosphere.websocket.messageContentType</param-name>
//            <param-value>application/json</param-value>
//        </init-param>
//        <async-supported>true</async-supported>
//        <load-on-startup>0</load-on-startup> 
//     */
        
        //Atmosphere
        AtmosphereServlet atmosphereServlet = new AtmosphereServlet();
        ServletHolder servletHolder = new ServletHolder(atmosphereServlet);
        servletHolder.setInitParameter("com.sun.jersey.config.property.packages","org.atmosphere.samples.chat.jersey");
        servletHolder.setInitParameter("org.atmosphere.websocket.messageContentType", "application/json");
        servletHolder.setAsyncSupported(true);
        servletHolder.setInitParameter("org.atmosphere.useWebSocket","true");
        
        //static files handler
        ResourceHandler resourceHandler = new ResourceHandler();
        resourceHandler.setDirectoriesListed(true);
        resourceHandler.setResourceBase("./src/main/webapp/");
        
        ServletContextHandler servletContextHandler = new ServletContextHandler(server, "/", ServletContextHandler.SESSIONS);
        servletContextHandler.addServlet(servletHolder, "/chat/*");        
        servletContextHandler.setHandler(resourceHandler);
                
        server.start();
        server.join();
	}
}
