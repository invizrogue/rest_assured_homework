package study.qa.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResourceDataModel {

    Integer id;
    String name;
    Integer year;
    String color;
    @JsonProperty("pantone_value")
    String pantoneValue;
}
