package petstore.pubsub.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;
import petstore.pubsub.domain.Pet;

@Component
@Slf4j
public class PetGroupHandler {

    @ServiceActivator
    public void handle(Message<Pet> message) {
        log.info("In PetGroupHandler, message={}", message);
    }
}
