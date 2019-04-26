package blueharvest.items;

import blueharvest.character.Character;
import blueharvest.character.CharacterDao;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {
    private ItemDao itemDao;
    private CharacterDao characterDao;

    public ItemService(ItemDao itemDao, CharacterDao characterDao) {
        this.itemDao = itemDao;
        this.characterDao = characterDao;
    }

    public void addToShop(long id, int price, long charId) {
        int remainingQtyAtChar = itemDao.addToShop(id, charId);
        if (remainingQtyAtChar == 0) {
            itemDao.deleteItemFromChar(id, charId);
        }
        Character character = characterDao.getCharacterById(charId);
        characterDao.updateMoney(charId,character.getMoney()+price);
    }

    public void removeFromShop(long id, int price, long charId) {
         itemDao.removeFromShop(id);
         if (itemDao.charAlreadyHasIt(id,charId)==0){
             itemDao.addNewItemToBag(id,charId);
         }else {
             itemDao.addQtyToBag(id,charId);
         }
         Character character = characterDao.getCharacterById(charId);
         characterDao.updateMoney(charId,character.getMoney()-price);
    }

    public List<Item> getAllItems() {
        return itemDao.getAllItems();
    }

    public List<Item> getItemsOwnedBy(long id) {
        return itemDao.getItemsOwnedBy(id);
    }
}
