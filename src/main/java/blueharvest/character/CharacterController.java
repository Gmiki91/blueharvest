package blueharvest.character;

import blueharvest.validation.ResponseStatus;
import blueharvest.validation.ValidationStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @GetMapping("/character")
    public CharacterInfo getCharacter(Authentication aut){
        if(aut != null){
           return characterService.getCharacterByName(aut.getName());
        } else {
            return new CharacterInfo();
        }
    }
    @GetMapping("/character/neighbours")
    public List<Character> getAllCharacters(@RequestParam long id){
       return characterService.getAllCharacters(id);
    }
    @PutMapping("/character/hunt")
    public void hunt(@RequestParam long id, @RequestParam long skillId){
        characterService.updateStatus(id,Status.INACTION);
        characterService.startAction(id, skillId);
    }

    @PutMapping("/character/learn")
    public void learn(@RequestParam long id, @RequestParam long skillId){
        characterService.updateStatus(id,Status.LEARNING);
        characterService.startLearning(id,skillId);
    }
}
