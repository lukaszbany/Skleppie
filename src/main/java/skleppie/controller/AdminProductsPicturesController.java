package skleppie.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import skleppie.service.ProductPictureService;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;


@Controller
@RequestMapping("/admin")
public class AdminProductsPicturesController {

    @Autowired
    ProductPictureService productPictureService;


    @RequestMapping(value = "/pictures", method = RequestMethod.GET)
    public ModelAndView pictures() {
        ModelAndView modelAndView = new ModelAndView();
        prepareModelAndView(modelAndView);

        return modelAndView;
    }

    @RequestMapping(value = "/pictures", method = RequestMethod.POST, consumes = "multipart/form-data")
    public ModelAndView pictureUpload(@RequestParam("file") MultipartFile file) {
        File imagesDirectory = new File(productPictureService.PRODUCT_PICTURES_PATH);
        ModelAndView modelAndView = new ModelAndView();
        if(!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                File newFile = new File( imagesDirectory + "/" + file.getOriginalFilename());
                newFile.createNewFile();
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(newFile));
                stream.write(bytes);
                stream.close();
                modelAndView.addObject("successMessage", "Plik " + file.getOriginalFilename() + " został wgrany pomyślnie.");
            }catch (IOException exc) {
                modelAndView.addObject("warningMessage", "Wystąpił błąd. Plik nie został wgrany.");
            }
        }

        prepareModelAndView(modelAndView);

        return modelAndView;
    }

    @RequestMapping(value = "/pictures/remove", method = RequestMethod.GET)
    public ModelAndView pictureUpload(@RequestParam("filename") String filename) {
        ModelAndView modelAndView = new ModelAndView("/admin/edit-message");
        File file = new File(productPictureService.PRODUCT_PICTURES_PATH + "/" + filename);

        if(file.delete()) {
            modelAndView.addObject("successMessage", "Plik został usunięty.");
        }else {
            modelAndView.addObject("warningMessage", "Wystąpił błąd. Plik nie został usunięty.");
        }
        modelAndView.addObject("backLink", "/admin/pictures");

        return modelAndView;
    }



    private void prepareModelAndView(ModelAndView modelAndView) {
        modelAndView.setViewName("admin/pictures");
        List<String> files = productPictureService.getFilenamesOfImages();
        modelAndView.addObject("files", files);
    }



}
