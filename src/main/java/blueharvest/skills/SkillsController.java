package blueharvest.skills;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SkillsController {
    private SkillsService skillsService;

    public SkillsController(SkillsService skillsService) {
        this.skillsService = skillsService;
    }

    @GetMapping("/skills/tolearn")
    public List<Skill> getSkillsToLearn(@RequestParam long id){
        return skillsService.getSkillsToLearn(id);
    }

    @GetMapping("/skills/todo")
    public List<Skill> getSkillsToDo(@RequestParam long id){return skillsService.getSkillsToDo(id);}

    @GetMapping("/skills/learned")
    public List<Skill> getSkillsLearned(@RequestParam long id){return skillsService.getSkillsLearned(id);}
}
