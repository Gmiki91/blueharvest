package com.dorcica.blueharvest.character;

import org.springframework.stereotype.Service;

@Service
public class CharacterService {
    private CharacterDao characterDao;

    public CharacterService(CharacterDao characterDao) {
        this.characterDao = characterDao;
    }

    public void createCharacter(Character character){
        characterDao.createCharacter(character);
    }
}
