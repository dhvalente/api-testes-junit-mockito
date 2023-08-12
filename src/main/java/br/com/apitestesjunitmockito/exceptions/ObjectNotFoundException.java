package br.com.apitestesjunitmockito.exceptions;

public class ObjectNotFoundException  extends RuntimeException{
    public ObjectNotFoundException(String message) {
        super(message);
    }
}
