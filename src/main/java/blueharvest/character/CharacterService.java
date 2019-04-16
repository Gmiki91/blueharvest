package blueharvest.character;

import blueharvest.action.ActionDao;
import blueharvest.action.ActionResult;
import blueharvest.skills.Skill;
import blueharvest.skills.SkillsDao;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static java.time.temporal.ChronoUnit.DAYS;

@Service
public class CharacterService {
    private CharacterDao characterDao;
    private PasswordEncoder passwordEncoder;
    private ActionDao actionDao;
    private SkillsDao skillsDao;

    public CharacterService(CharacterDao characterDao, PasswordEncoder passwordEncoder, ActionDao actionDao, SkillsDao skillsDao) {
        this.characterDao = characterDao;
        this.passwordEncoder = passwordEncoder;
        this.actionDao = actionDao;
        this.skillsDao = skillsDao;
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

        if (!character.getStatus().equals(Status.AVAILABLE)) {
            remainingTime = Duration.between(LocalDateTime.now(),
                    actionDao.getEndTimeOfOngoingAction(character)).minusHours(1L).toHours();


            if (actionDao.getEndTimeOfOngoingAction(character).minusHours(2L).isBefore(LocalDateTime.now())) {
                if (character.getStatus().equals(Status.HUNTING)) {
                    receivedFood = new ActionResult(character.getStatus().name()).receivedFood();
                    receivedMoney = new ActionResult(character.getStatus().name()).receivedMoney();

                    characterDao.updateStatus(character.getId(), Status.AVAILABLE);
                    updateFood(character.getId(), character.getFood() + receivedFood);
                    updateMoney(character.getId(), character.getMoney() + receivedMoney);
                } else if (character.getStatus().equals(Status.LEARNING)){

                }
            }
        }
        Character updatedCharacter = characterDao.getCharacterByName(name);
        return new CharacterInfo(updatedCharacter, receivedFood, receivedMoney, remainingTime);
    }

    public void updateFood(long id, int food) {
        characterDao.updateFood(id, food);
    }

    public void updateStatus(long id, Status status) {
        actionDao.startAction(id, status);
        characterDao.updateStatus(id, status);
    }

    public void updateStatus(long id, long skillId) {
        Skill skill = skillsDao.getSkillById(skillId);
        actionDao.startLearning(id, skill.getTimeToMaster());
        skillsDao.addSkillLearned(id, skillId);
        characterDao.updateStatus(id, Status.LEARNING);
    }

    public void updateMoney(long id, int money) {
        characterDao.updateMoney(id, money);
    }

    private void updateLastVisit(long id, LocalDate lastVisit) {
        characterDao.updateLastVisit(id, lastVisit);
    }

    private void checkIfDayHasPassed(Character character) {
        long daysPassed = DAYS.between(character.getLastVisit(), LocalDate.now());
        updateFood(character.getId(), character.getFood() - ((int) daysPassed - 1));
        updateLastVisit(character.getId(), LocalDate.now());
    }
}
