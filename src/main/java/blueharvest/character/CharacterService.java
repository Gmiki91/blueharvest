package blueharvest.character;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.DAYS;

@Service
public class CharacterService {
    private CharacterDao characterDao;
    private PasswordEncoder passwordEncoder;

    public CharacterService(CharacterDao characterDao, PasswordEncoder passwordEncoder) {
        this.characterDao = characterDao;
        this.passwordEncoder = passwordEncoder;
    }

    public void createCharacter(Character character) {
        String hashedPassword = passwordEncoder.encode(character.getPassword());

        characterDao.createCharacter(new Character(
                character.getId(),
                character.getName(),
                hashedPassword,
                character.getImageId(),
                character.getLastVisit(),
                character.getFood()));
    }

    public Character getCharacterByName(String name) {
        Character character = characterDao.getCharacterByName(name);
        long daysPassed = DAYS.between(character.getLastVisit(), LocalDate.now());

        character.setFood(character.getFood() - ((int) daysPassed-1));
        updateFood(character);

        character.setLastVisit(LocalDate.now());
        updateLastVisit(character);

        return character;
    }

    private void updateLastVisit(Character character) {
        characterDao.updateLastVisit(character);
    }

    private void updateFood(Character character) {
        characterDao.updateFood(character);
    }
}
