package blueharvest.image;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ImageController {
    private ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @GetMapping("/image/{id}")
    public Image getImageById(@PathVariable long id){
        return imageService.getImageById(id);
    }
    @GetMapping("/image/size")
    public Integer getLengthOfImages(){
       return imageService.getLengthOfImages();
    }
}
