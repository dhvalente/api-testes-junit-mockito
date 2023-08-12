package br.com.apitestesjunitmockito.records;

import com.fasterxml.jackson.annotation.JsonIgnore;

public record UserRecord(Integer id, String name, String email, @JsonIgnore String password) {
}
