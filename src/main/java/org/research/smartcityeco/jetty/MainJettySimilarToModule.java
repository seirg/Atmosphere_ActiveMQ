package org.research.smartcityeco.jetty;

import org.atmosphere.cpr.AtmosphereServlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class MainJettySimilarToModule {

	public static void main(String[] args) throws Exception {
		Server server = new Server(8888); 
		
        //static files handler        
        ResourceHandler resource_handler = new ResourceHandler();
        resource_handler.setDirectoriesListed(true);
        resource_handler.setWelcomeFiles(new String[] { "index.html" });
        resource_handler.setResourceBase("./src/main/webapp/");
        
        ContextHandler context1 = new ContextHandler();
        context1.setHandler(resource_handler);
        
        //Atmosphere
		AtmosphereServlet atmosphereServlet = new AtmosphereServlet();
        ServletHolder servletHolder = new ServletHolder(atmosphereServlet);
        servletHolder.setInitParameter("com.sun.jersey.config.property.packages",
        		"org.research.smartcityeco.samples.map.atmosphere "
        		+ "org.research.smartcityeco.samples.chat.atmosphere "
        		+ "org.research.smartcityeco.samples.rest.jersey");
        
        servletHolder.setInitParameter("org.atmosphere.websocket.messageContentType", "application/json");
        servletHolder.setAsyncSupported(true);
        servletHolder.setInitParameter("org.atmosphere.useWebSocket","true");
        //servletHolder.setInitParameter("org.atmosphere.cpr.asyncSupport", "org.atmosphere.container.Jetty9AsyncSupportWithWebSocket");
        
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(servletHolder, "/atmosphere/*");
        
        HandlerList handlers = new HandlerList();
        handlers.addHandler(context1);
        handlers.addHandler(context);        
        
        server.setHandler(handlers);
                
        server.start();
        server.join();

	}
}
