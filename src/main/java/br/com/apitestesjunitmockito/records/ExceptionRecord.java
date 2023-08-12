package br.com.apitestesjunitmockito.records;

import java.time.LocalDateTime;

public record ExceptionRecord(LocalDateTime timeStamp, Integer statusCodeError, String messageError, String path) {
}