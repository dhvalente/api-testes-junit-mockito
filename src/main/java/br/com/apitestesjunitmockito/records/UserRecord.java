package br.com.apitestesjunitmockito.records;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UserRecord(Integer id, String name, String email, @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) String password) {
}
