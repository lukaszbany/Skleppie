package skleppie.service;

import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service("productPictureService")
public class ProductPictureServiceImpl implements ProductPictureService {
    @Override
    public List<String> getFilenamesOfImages() {
        List<String> images = new ArrayList<>();

        File imageDirectory = new File(PRODUCT_PICTURES_PATH);
        if(imageDirectory.exists()) {
            for(File currentFile : imageDirectory.listFiles()) {
                images.add(currentFile.getName());
            }
        }

        return images;
    }
}
