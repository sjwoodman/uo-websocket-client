package io.streamzi;

import io.streamzi.eventflow.FlowUtil;
import io.streamzi.eventflow.annotations.CloudEventComponent;
import io.streamzi.eventflow.annotations.CloudEventComponentTimer;
import io.streamzi.eventflow.annotations.CloudEventProducer;
import io.streamzi.eventflow.annotations.CloudEventProducerTarget;
import io.streamzi.eventflow.utils.EnvironmentResolver;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Logger;

@CloudEventComponent
public class UOWebSocketReceiver {

    private static final Logger logger = Logger.getLogger(UOWebSocketReceiver.class.getName());

    @CloudEventProducer(name = "OUTPUT_DATA")
    private CloudEventProducerTarget output;

    private String URL = "wss://api.usb.urbanobservatory.ac.uk/stream";

    //Hack because @CloudEventComponentStart is not implemented
    @CloudEventComponentTimer(interval = 100000000)
    public void connect() {

        try {
            Client client = new Client(new URI(URL), output);
            client.connect();

        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
}


