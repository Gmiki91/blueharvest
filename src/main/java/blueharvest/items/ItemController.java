package blueharvest.items;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ItemController {
    private ItemDao itemDao;

    public ItemController(ItemDao itemDao) {
        this.itemDao = itemDao;
    }
    @GetMapping("/items")
    public List<Item> getAllItems(){
        return itemDao.getAllItems();
    }
}
