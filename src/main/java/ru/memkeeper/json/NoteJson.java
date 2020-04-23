package ru.memkeeper.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.immutables.value.Value;

import java.time.LocalDateTime;
import java.util.Optional;

@ApiModel
@Value.Immutable
@JsonDeserialize(builder = NoteJson.Builder.class)
public interface NoteJson {

    @ApiModelProperty("Идентификатор")
    @JsonProperty
    Long id();

    @ApiModelProperty("Название заметки")
    @JsonProperty
    String title();

    @ApiModelProperty("Время создания поста")
    @JsonProperty
    LocalDateTime createdAt();

    @ApiModelProperty("Описание поста (опционально)")
    @JsonProperty
    Optional<String> text();

    class Builder extends ImmutableNoteJson.Builder {
    }

}