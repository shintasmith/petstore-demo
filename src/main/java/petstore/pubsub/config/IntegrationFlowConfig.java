package petstore.pubsub.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.http.dsl.Http;
import org.springframework.messaging.MessageChannel;
import petstore.pubsub.service.PetGroupHandler;

@Configuration
@EnableIntegration
@IntegrationComponentScan
public class IntegrationFlowConfig {

    @Autowired
    PetGroupHandler petGroupHandler;

    @Bean
    IntegrationFlow petStoreSubscriptionFlow(
            @Qualifier("petStoreSubscriptionMessageChannel") MessageChannel petStoreSubscriptionChannel) {
        return IntegrationFlow.from(petStoreSubscriptionChannel)
                .enrichHeaders(spec -> spec.header("petStore.FlowName", "PetStoreIntake"))
                .handle(petGroupHandler, "handle")
                .get();
    }

    @Bean
    IntegrationFlow petStoreSubscriptionFlowHttp(IntegrationFlow petStoreSubscriptionFlow) {
        return IntegrationFlow.from(Http.inboundChannelAdapter("/pet")
                        .requestMapping(m -> m.methods(HttpMethod.POST).consumes("application/json"))
                        .payloadExpression("body")
                        .requestPayloadType(String.class))
                .to(petStoreSubscriptionFlow);
    }
}
