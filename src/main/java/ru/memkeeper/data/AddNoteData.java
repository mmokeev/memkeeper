package ru.memkeeper.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.immutables.value.Value;

import java.util.Optional;

@ApiModel
@Value.Immutable
@JsonDeserialize(builder = AddNoteData.Builder.class)
public interface AddNoteData {

    @ApiModelProperty("Id вкладки")
    @JsonProperty
    Long tabId();

    @ApiModelProperty("Название заметки")
    @JsonProperty
    String title();

    @ApiModelProperty("Описание поста (опционально)")
    @JsonProperty
    Optional<String> text();

    class Builder extends ImmutableAddNoteData.Builder {
    }
}
