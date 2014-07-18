/*
 * Copyright 2014 Jeanfrancois Arcand
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.research.smartcityeco.wasync;

import org.atmosphere.wasync.ClientFactory;
import org.atmosphere.wasync.Function;
import org.atmosphere.wasync.Request;
import org.atmosphere.wasync.RequestBuilder;
import org.atmosphere.wasync.Socket;
import org.atmosphere.wasync.impl.AtmosphereClient;
import org.codehaus.jackson.map.ObjectMapper;
import org.research.smartcityeco.samples.map.atmosphere.GeoResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class wAsyncChat {

	private final static Logger logger = LoggerFactory
			.getLogger(wAsyncChat.class);
	private final static ObjectMapper mapper = new ObjectMapper();

	public static void main(String[] args) throws IOException {

		if (args.length == 0) {
			args = new String[] { "http://127.0.0.1:8888" };
		}

		AtmosphereClient client = ClientFactory.getDefault().newClient(
				AtmosphereClient.class);
		final RequestBuilder request = client.newRequestBuilder()
				.method(Request.METHOD.GET).uri("http://localhost:8888/atmosphere-activemq-chat/")
				.trackMessageLength(true)
				.transport(Request.TRANSPORT.WEBSOCKET)
				.transport(Request.TRANSPORT.LONG_POLLING);

		/*
		 * code to create the socket at server side using the created client
		 */
		Socket socket = client.create();

		/*
		 * code to open the socket
		 */
		socket.open(request.build());

		/*
		 * code for Message handler
		 */
		socket.on("message", new Function<GeoResponse>() {
			@Override
			public void on(final GeoResponse s) {				
                logger.info(s.getFeatureCollection().toString());
			};
		});

//		AtmosphereClient client = ClientFactory.getDefault().newClient(
//				AtmosphereClient.class);
//
//		RequestBuilder request = client.newRequestBuilder()
//				.method(Request.METHOD.GET)
//				.uri(args[0] + "/atmosphere-activemq-chat")
//				.trackMessageLength(true)
//				.encoder(new Encoder<Message, String>() {
//					@Override
//					public String encode(Message data) {
//						try {
//							return mapper.writeValueAsString(data);
//						} catch (IOException e) {
//							throw new RuntimeException(e);
//						}
//					}
//				}).decoder(new Decoder<String, Message>() {
//					@Override
//					public Message decode(Event type, String data) {
//
//						data = data.trim();
//
//						// Padding
//						if (data.length() == 0) {
//							return null;
//						}
//
//						if (type.equals(Event.MESSAGE)) {
//							try {
//								return mapper.readValue(data, Message.class);
//							} catch (IOException e) {
//								logger.debug("Invalid message {}", data);
//								return null;
//							}
//						} else {
//							return null;
//						}
//					}
//				}).transport(Request.TRANSPORT.WEBSOCKET)
//				.transport(Request.TRANSPORT.SSE)
//				.transport(Request.TRANSPORT.LONG_POLLING);

//		Socket socket = client.create();
//		socket.on("message", new Function<Message>() {
//			@Override
//			public void on(Message t) {
//				logger.info("Author {}: {}", t.getAuthor(), t.getMessage());
//			}
//		}).on(new Function<Throwable>() {
//
//			@Override
//			public void on(Throwable t) {
//				t.printStackTrace();
//			}
//
//		}).on(Event.CLOSE.name(), new Function<String>() {
//			@Override
//			public void on(String t) {
//				logger.info("Connection closed");
//			}
//		}).on(Event.OPEN.name(), new Function<String>() {
//			@Override
//			public void on(String t) {
//				logger.info("Connection opened");
//			}
//		}).open(request.build());
//
//		logger.info("Choose Name: ");
//		String name = null;
//		String a = "";
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		while (!(a.equals("quit"))) {
//			a = br.readLine();
//			if (name == null) {
//				name = a;
//			}
//			socket.fire(new Message(name, a));
//		}
//		socket.close();
	}

}
