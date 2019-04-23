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
import java.util.List;

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

        skillsDao.addSkillLearned(characterDao.getCharacterByName(character.getName()).getId(),1);
    }

    public CharacterInfo getCharacterByName(String name) {
        Character character = characterDao.getCharacterByName(name);
        checkIfDayHasPassed(character);
        fillCharacterSkills(character);

        int receivedFood = -1;
        int receivedMoney = -1;
        long remainingTime = 0;
        String nameOfSkill = "";

        if (!character.getStatus().equals(Status.AVAILABLE)) {
            remainingTime = Duration.between(LocalDateTime.now(),
                    actionDao.getEndTimeOfOngoingAction(character)).minusHours(1L).toHours();
            nameOfSkill = skillsDao.getSkillById(actionDao.getSkillIdOfOngoingAction(character.getId())).getName();
            if (actionDao.getEndTimeOfOngoingAction(character).minusHours(2L).isBefore(LocalDateTime.now())) {
                remainingTime=-1;
                characterDao.updateStatus(character.getId(), Status.AVAILABLE);
                if (character.getStatus().equals(Status.INACTION)) {
                    receivedFood = new ActionResult(nameOfSkill).receivedFood();
                    receivedMoney = new ActionResult(nameOfSkill).receivedMoney();

                    updateFood(character.getId(), character.getFood() + receivedFood);
                    updateMoney(character.getId(), character.getMoney() + receivedMoney);
                } else if (character.getStatus().equals(Status.LEARNING)){
                    long skillId = actionDao.getSkillIdOfOngoingAction(character.getId());
                    skillsDao.addSkillLearned(character.getId(),skillId);

                }
                actionDao.removeAction(character.getId());
            }
        }
        Character updatedCharacter = characterDao.getCharacterByName(name);
        return new CharacterInfo(updatedCharacter, receivedFood, receivedMoney, remainingTime, nameOfSkill);
    }
    public List<Character>getAllCharacters(long id){
        return characterDao.getAllCharacters(id);
    }

    public void startAction(long id, long skillId){
        actionDao.startAction(id,skillId, skillsDao.getSkillById(skillId).getTimeToExecute());
    }
    public void startLearning(long id, long skillId){
        actionDao.startAction(id,skillId, skillsDao.getSkillById(skillId).getTimeToMaster());
    }
    public void updateFood(long id, int food) {
        characterDao.updateFood(id, food);
    }

    public void updateStatus(long id, Status status) {
        characterDao.updateStatus(id, status);
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
    private void fillCharacterSkills(Character character){
        List<Skill> skills = skillsDao.getSkillsLearned(character.getId());
        for (Skill skill : skills){
            character.addSkill(skill);
        }
    }
}
