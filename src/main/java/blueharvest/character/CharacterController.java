package blueharvest.character;

import blueharvest.validation.ResponseStatus;
import blueharvest.validation.ValidationStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CharacterController {
    private CharacterService characterService;

    public CharacterController(CharacterService characterService) {
        this.characterService = characterService;
    }

    @PostMapping("/character")
    public ResponseStatus createCharacter(@RequestBody Character character){
        CharacterValidator validator = new CharacterValidator(character.getName());
        if (validator.getResponseStatus().getStatus()== ValidationStatus.SUCCESS) {
            characterService.createCharacter(character);
            validator.getResponseStatus().addMessages(character.getName()+" sikeresen regisztrálva.");
        }
        return validator.getResponseStatus();
    }
}
