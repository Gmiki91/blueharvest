package blueharvest.character;

import blueharvest.actionInProgress.ActionDao;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static java.time.temporal.ChronoUnit.DAYS;

@Service
public class CharacterService {
    private CharacterDao characterDao;
    private PasswordEncoder passwordEncoder;
    private ActionDao actionDao;

    public CharacterService(CharacterDao characterDao, PasswordEncoder passwordEncoder, ActionDao actionDao) {
        this.characterDao = characterDao;
        this.passwordEncoder = passwordEncoder;
        this.actionDao = actionDao;
    }

    public void createCharacter(Character character) {
        String hashedPassword = passwordEncoder.encode(character.getPassword());

        characterDao.createCharacter(new Character(
                character.getId(),
                character.getName(),
                hashedPassword,
                character.getImageId(),
                character.getLastVisit(),
                character.getFood(),
                character.getMoney(),
                character.getStatus()));
    }

    public Character getCharacterByName(String name) {
        Character character = characterDao.getCharacterByName(name);
        long daysPassed = DAYS.between(character.getLastVisit(), LocalDate.now());

        character.setFood(character.getFood() - ((int) daysPassed - 1));
        updateFood(character);

        character.setLastVisit(LocalDate.now());
        updateLastVisit(character);

        if (character.getStatus().equals(Status.HUNTING) &&
                actionDao.getOngoingAction(character).isBefore(LocalDateTime.now())) {
            character.setStatus(Status.AVAILABLE);
            character.setFood(character.getFood() + 1);
        }

        return character;
    }

    public Character getCharacterById(long id) {
        return characterDao.getCharacterById(id);
    }

    public void updateFood(Character character) {
        characterDao.updateFood(character);
    }

    public void updateStatus(Character character) {
        actionDao.startAction(character);
        characterDao.updateStatus(character);
    }

    private void updateLastVisit(Character character) {
        characterDao.updateLastVisit(character);
    }

}
