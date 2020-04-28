package ru.memkeeper.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.immutables.value.Value;

import java.util.Date;
import java.util.Optional;

@ApiModel
@Value.Immutable
@JsonDeserialize(builder = AddNoteData.Builder.class)
public interface AddNoteData {

    @ApiModelProperty(value = "Id вкладки", required = true)
    @JsonProperty
    Long tabId();

    @ApiModelProperty(value = "Название заметки", required = true)
    @JsonProperty
    String title();

    @ApiModelProperty("Описание поста (опционально)")
    @JsonProperty
    Optional<String> text();

    @ApiModelProperty("Дата создания (опционально)")
    @JsonProperty
    Optional<Date> createdAt();

    class Builder extends ImmutableAddNoteData.Builder {
    }
}
