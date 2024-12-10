package petstore.pubsub.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class GlobalPetErrorHandler {

    @ServiceActivator(inputChannel = "petStoreSubscriptionErrorChannel")
    public void pubsubErrorHandler(Message<MessagingException> message) {
        log.warn(
                "Message={} will be automatically acked because error handler completes successfully",
                message,
                message.getPayload());
    }
}
