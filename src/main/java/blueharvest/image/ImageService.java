package blueharvest.image;

import org.springframework.stereotype.Service;

@Service
public class ImageService {
    private ImageDao imageDao;

    public ImageService(ImageDao imageDao) {
        this.imageDao = imageDao;
    }

    public Image getImageById(long id){
        return imageDao.getImageById(id);
    }
    public Integer getLengthOfImages(){
        return imageDao.getLengthOfImages();
    }
}
