package petstore.pubsub.config;

import com.google.cloud.spring.pubsub.core.PubSubTemplate;
import com.google.cloud.spring.pubsub.integration.AckMode;
import com.google.cloud.spring.pubsub.integration.inbound.PubSubInboundChannelAdapter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.messaging.MessageChannel;

@Profile("!test")
@Configuration
@Slf4j
public class PubSubConfig {

    @Value("${this.petStoreSubscription}")
    String petStoreSubscription;

    @Bean
    public MessageChannel petStoreSubscriptionMessageChannel() {
        return new DirectChannel();
    }

    @Bean
    public MessageChannel petStoreSubscriptionErrorChannel() {
        return new DirectChannel();
    }

    @Bean
    public PubSubInboundChannelAdapter petStoreSubscriptionChannelAdapter(
            @Qualifier("petStoreSubscriptionMessageChannel") MessageChannel outputChannel,
            PubSubTemplate pubSubTemplate) {
        PubSubInboundChannelAdapter adapter = new PubSubInboundChannelAdapter(pubSubTemplate, petStoreSubscription);
        adapter.setOutputChannel(outputChannel);
        adapter.setAckMode(AckMode.AUTO_ACK);
        adapter.setPayloadType(String.class);
        adapter.setErrorChannelName("petStoreSubscriptionErrorChannel");
        return adapter;
    }
}
