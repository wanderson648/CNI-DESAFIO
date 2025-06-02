package br.com.cnidesafio.exception.handler;

import br.com.cnidesafio.exception.StandardResponseError;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ValidationError extends StandardResponseError {
    private final List<FieldMessage> errors = new ArrayList<>();

    public void addError(String fieldName, String message) {
        errors.add(new FieldMessage(fieldName, message));
    }
}
