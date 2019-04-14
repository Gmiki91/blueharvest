package blueharvest.character;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CharacterService {
    private CharacterDao characterDao;
    private PasswordEncoder passwordEncoder;

    public CharacterService(CharacterDao characterDao, PasswordEncoder passwordEncoder) {
        this.characterDao = characterDao;
        this.passwordEncoder=passwordEncoder;
    }

    public void createCharacter(Character character){
        String hashedPassword = passwordEncoder.encode(character.getPassword());

        characterDao.createCharacter(new Character(character.getId(),character.getName(),hashedPassword,character.getImageId()));
    }
    public Character getCharacterByName(String name){
       return characterDao.getCharacterByName(name);
    }
}
