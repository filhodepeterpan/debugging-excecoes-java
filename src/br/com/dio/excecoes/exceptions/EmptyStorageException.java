package br.com.dio.excecoes.exceptions;

public class EmptyStorageException extends RuntimeException{

    public EmptyStorageException(final String message) {
        super(message);
    }
}
