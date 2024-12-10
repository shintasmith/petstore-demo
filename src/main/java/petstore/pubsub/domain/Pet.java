package petstore.pubsub.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Pet implements Serializable {

    private static final long serialVersionUID = -7344345420829574601L;

    String name;
    String type;
    String breed;
    String status;
    Integer ageInMonths;
    String admittedSinceDate;
    String dischargedDate;
}
