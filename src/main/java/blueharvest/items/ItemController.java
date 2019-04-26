package blueharvest.items;

import blueharvest.image.ItemService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ItemController {
    private ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }
    @GetMapping("/items")
    public List<Item> getAllItems(){
        return itemService.getAllItems();
    }
    @GetMapping("/items/ownedBy")
    public List<Item> getItemsOwnedBy(@RequestParam long id){
        return itemService.getItemsOwnedBy(id);
    }
    @GetMapping("/items/removeFromShop")
    public void removeFromShop(@RequestParam long id,@RequestParam long charId){
        itemService.removeFromShop(id,charId);
    }
    @GetMapping("/items/addToShop")
    public void addToShop(@RequestParam long id, @RequestParam long charId){
        itemService.addToShop(id, charId);

    }
}
