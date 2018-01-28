package skleppie.service;

import java.util.List;

public interface ProductPictureService {
    String PRODUCT_PICTURES_PATH = "src/main/resources/static/public/images/products";

    List<String> getFilenamesOfImages();
}
