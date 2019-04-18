package blueharvest.skills;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkillsService {
    private SkillsDao skillsDao;

    public SkillsService(SkillsDao skillsDao) {
        this.skillsDao = skillsDao;
    }

    public List<Skill> getSkillsToLearn(long id) {
        return skillsDao.getSkillsToLearn(id);
    }

    public List<Skill> getSkillsLearned(long id) {
        return skillsDao.getSkillsLearned(id);
    }

    public List<Skill> getSkillsToDo(long id) {
        return skillsDao.getActiveSkillsLearned(id);
    }
}
