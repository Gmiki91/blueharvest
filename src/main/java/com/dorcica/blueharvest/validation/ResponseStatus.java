package com.dorcica.blueharvest.validation;

import java.util.ArrayList;
import java.util.List;

public class ResponseStatus {
    private ValidationStatus validationStatus = ValidationStatus.SUCCESS;
    private List<String> messages  = new ArrayList<>();

    public ResponseStatus addMessages(String message){
        messages.add(message);
        return this;
    }
    public ResponseStatus setStatus(ValidationStatus status){
        validationStatus = status;
        return this;
    }

    public ValidationStatus getStatus() {
        return validationStatus;
    }

    public List<String> getMessages() {
        return messages;
    }
}
