package petstore.pubsub.service;

import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.integration.aggregator.DefaultAggregatingMessageGroupProcessor;
import org.springframework.integration.store.MessageGroup;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class PetOutputProcessor extends DefaultAggregatingMessageGroupProcessor {

    @Override
    protected Map<String, Object> aggregateHeaders(MessageGroup group) {
        Map<String, Object> headers = super.aggregateHeaders(group);
        headers.put("COMPLETE_GROUP", group.isComplete());
        return headers;
    }
}
