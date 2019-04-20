package com.mapping.mapping.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.mapping.mapping.constant.Genre;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder(alphabetic = true)
public class Movie {
    @Getter(onMethod_ = @JsonProperty("Id"))
    @Setter(onMethod_ = @JsonProperty("Id"))
    private Integer id;
    @Getter(onMethod_ = @JsonProperty("Name"))
    @Setter(onMethod_ = @JsonProperty("Name"))
    private String name;
    @Getter(onMethod_ = @JsonProperty("Genre"))
    @Setter(onMethod_ = @JsonProperty("Genre"))
    private Genre genre;
}
