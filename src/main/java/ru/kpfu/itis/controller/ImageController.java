package ru.kpfu.itis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.kpfu.itis.dto.response.Response;
import ru.kpfu.itis.service.ImageService;

@RestController
public class ImageController {

    private ImageService imageService;

    @Autowired
    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @GetMapping(value = "/images/{imageName:.+}")
    public byte[] getImage(@PathVariable("imageName") String imageName) {
        return imageService.getImage(imageName);
    }

    @PostMapping(value = "/images")
    public Response<String> saveImage(@RequestParam("file") MultipartFile file) {
        String newFileName = imageService.saveImage(file);
        return new Response<>(201, newFileName);
    }

}
