package com.mapping.mapping.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder(alphabetic = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Person {
    @Getter(onMethod_ = @JsonProperty("Id"))
    @Setter(onMethod_ = @JsonProperty("Id"))
    private Integer id;
    @Getter(onMethod_ = @JsonProperty("FirstName"))
    @Setter(onMethod_ = @JsonProperty("FirstName"))
    private String firstName;
    @Getter(onMethod_ = @JsonProperty("LastName"))
    @Setter(onMethod_ = @JsonProperty("LastName"))
    private String lastName;
    @Getter(onMethod_ = @JsonProperty("FavoriteMovies"))
    @Setter(onMethod_ = @JsonProperty("FavoriteMovies"))
    private List<Movie> favoriteMovies;
}
