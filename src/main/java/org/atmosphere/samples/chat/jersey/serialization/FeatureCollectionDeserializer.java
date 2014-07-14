/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.atmosphere.samples.chat.jersey.serialization;

import java.io.IOException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.geojson.FeatureCollection;

/**
 *
 * @author lroman
 */
public class FeatureCollectionDeserializer extends JsonDeserializer<FeatureCollection> {

    ObjectMapper mapper;

    @Override
    public FeatureCollection deserialize(JsonParser jp, DeserializationContext dc) throws IOException, JsonProcessingException {

        if (mapper == null) {
            mapper = new ObjectMapper();
        }

        return mapper.readValue(jp.getText(), FeatureCollection.class);
    }

}
