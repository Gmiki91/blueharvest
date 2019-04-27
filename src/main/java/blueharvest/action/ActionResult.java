package blueharvest.action;

import blueharvest.items.Item;
import blueharvest.items.ItemDao;

public class ActionResult {
    private String act;
    private ItemDao itemDao;


    public ActionResult(String status, ItemDao itemDao) {
        this.act = status;
        this.itemDao = itemDao;
    }

    public int receivedFood(){
        if (act.equals("Vadászat")){
            return 2;
        }
        return 1;
    }
    public int receivedMoney(){
        if (act.equals("Vadászat")){
            return 1;
        }
        return 0;
    }
    public Item receivedItem(){
        if (act.equals("Orrtúrás")){
            return itemDao.getRandomItem(10,30);
        }
        return null;
    }
}
