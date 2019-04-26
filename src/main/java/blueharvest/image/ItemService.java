package blueharvest.image;

import blueharvest.items.Item;
import blueharvest.items.ItemDao;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {
    private ItemDao itemDao;

    public ItemService(ItemDao itemDao) {
        this.itemDao = itemDao;
    }

    public void addToShop(long id, long charId) {
        int remainingQtyAtChar = itemDao.addToShop(id, charId);
        if (remainingQtyAtChar == 0) {
            itemDao.deleteItemFromChar(id, charId);
        }
    }

    public void removeFromShop(long id, long charId) {
         itemDao.removeFromShop(id);
         if (itemDao.charAlreadyHasIt(id,charId)==0){
             itemDao.addNewItemToBag(id,charId);
         }else {
             itemDao.addQtyToBag(id,charId);
         }
    }

    public List<Item> getAllItems() {
        return itemDao.getAllItems();
    }

    public List<Item> getItemsOwnedBy(long id) {
        return itemDao.getItemsOwnedBy(id);
    }
}
