package ru.memkeeper.data;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.immutables.value.Value;

@ApiModel
@Value.Immutable
@JsonDeserialize(builder = TabData.Builder.class)
public interface TabData {

    @ApiModelProperty("Идентификатор")
    @JsonProperty
    Long id();

    @ApiModelProperty("Название вкладки")
    @JsonProperty
    String name();

    @ApiModelProperty("Активная ли вкладка")
    @JsonProperty
    Boolean isActive();

    class Builder extends ImmutableTabData.Builder {
    }

}
