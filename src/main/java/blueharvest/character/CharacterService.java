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
        checkIfDayHasPassed(character);

        int receivedFood = -1;
        int receivedMoney = -1;
        long remainingTime = 0;

        if (character.getStatus().equals(Status.HUNTING)){
            remainingTime = Duration.between(LocalDateTime.now(),
                    actionDao.getEndTimeOfOngoingAction(character)).minusHours(1L).toMinutes();

            if (actionDao.getEndTimeOfOngoingAction(character).minusHours(2L).isBefore(LocalDateTime.now())) {
                receivedFood = new ActionResult(character.getStatus().name()).receivedFood();
                receivedMoney = new ActionResult(character.getStatus().name()).receivedMoney();

                characterDao.updateStatus(character.getId(), Status.AVAILABLE);
                updateFood(character.getId(),character.getFood()+receivedFood);
                updateMoney(character.getId(),character.getMoney()+receivedMoney);
                actionDao.removeAction(character.getId());
            }
        }
        Character updatedCharacter = characterDao.getCharacterByName(name);
        return new CharacterInfo(updatedCharacter,receivedFood,receivedMoney, remainingTime);
    }

    public void updateFood(long id, int food) {
        characterDao.updateFood(id,food);
    }

    public void updateStatus(long id, Status status) {
        actionDao.startAction(id,status);
        characterDao.updateStatus(id,status);
    }
    public void updateMoney(long id, int money){
        characterDao.updateMoney(id, money);
    }
    private void updateLastVisit(long id, LocalDate lastVisit) {
        characterDao.updateLastVisit(id,lastVisit);
    }
    private void checkIfDayHasPassed(Character character){
        long daysPassed = DAYS.between(character.getLastVisit(), LocalDate.now());
        updateFood(character.getId(), character.getFood()- ((int) daysPassed - 1));
        updateLastVisit(character.getId(), LocalDate.now());
    }
}
