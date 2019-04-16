package blueharvest.skills;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkillsService {
    private SkillsDao skillsDao;

    public SkillsService(SkillsDao skillsDao) {
        this.skillsDao = skillsDao;
    }
    public List<Skill> getSkills(){
        return skillsDao.getSkills();
    }
}
