package ru.memkeeper.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.immutables.value.Value;

@ApiModel
@Value.Immutable
@JsonDeserialize(builder = SimpleJson.Builder.class)
public interface SimpleJson {

    @ApiModelProperty("Идентификатор простоты")
    @JsonProperty
    Long id();

    @ApiModelProperty("Идентификатор юзера")
    @JsonProperty
    String userId();

    @ApiModelProperty("Какая-то строка")
    @JsonProperty
    String title();

    class Builder extends ImmutableSimpleJson.Builder {
    }
}
