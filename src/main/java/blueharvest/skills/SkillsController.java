package blueharvest.skills;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SkillsController {
    private SkillsService skillsService;

    public SkillsController(SkillsService skillsService) {
        this.skillsService = skillsService;
    }

    @GetMapping("/skills")
    public List<Skill> getSkills(){
        return skillsService.getSkills();
    }
}
