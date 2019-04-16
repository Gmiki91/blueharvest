package blueharvest.character;

import blueharvest.action.ActionDao;
import blueharvest.action.ActionResult;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

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

    public CharacterInfo getCharacterByName(String name) {
        Character character = characterDao.getCharacterByName(name);
        long daysPassed = DAYS.between(character.getLastVisit(), LocalDate.now());
        int receivedFood = -1;
        int receivedMoney = -1;
        long remainingTime = 0;

        character.setFood(character.getFood() - ((int) daysPassed - 1));
        updateFood(character);

        character.setLastVisit(LocalDate.now());
        updateLastVisit(character);
        if (character.getStatus().equals(Status.HUNTING)){
            remainingTime = Duration.between(LocalDateTime.now(),actionDao.getEndTimeOfOngoingAction(character)).minusHours(1L).toHours();
            if (actionDao.getEndTimeOfOngoingAction(character).minusHours(2L).isBefore(LocalDateTime.now())) {
                receivedFood = new ActionResult(character.getStatus().name()).receivedFood();
                receivedMoney = new ActionResult(character.getStatus().name()).receivedMoney();
                character.setStatus(Status.AVAILABLE);
                character.setFood(character.getFood()+receivedFood);
                character.setMoney(character.getMoney()+receivedMoney);
                actionDao.removeAction(character.getId());
            }
        }
        return new CharacterInfo(character,receivedFood,receivedMoney, remainingTime);
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
