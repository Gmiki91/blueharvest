package blueharvest.character;

import blueharvest.validation.ResponseStatus;
import blueharvest.validation.ValidationStatus;

public class CharacterValidator {
    private ResponseStatus responseStatus = new ResponseStatus();

    public CharacterValidator(String name){
        checkCharacterName(name);
        if (responseStatus.getMessages().isEmpty()){
            responseStatus.setStatus(ValidationStatus.SUCCESS);
        } else {
            responseStatus.setStatus(ValidationStatus.FAIL);
        }
    }

    private void checkCharacterName(String name){
        if (name==null || name.trim().isEmpty()) {
            responseStatus.addMessages("A név mező nem lehet üres.");
        } else if (name.trim().length()<3){
            responseStatus.addMessages("A névnek legalább 3 karakter hosszúnak kell lennie.");
        }
    }

    public ResponseStatus getResponseStatus() {
        return responseStatus;
    }
}
