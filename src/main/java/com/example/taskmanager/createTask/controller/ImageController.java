package com.example.taskmanager.createTask.controller;

import com.example.taskmanager.createTask.component.ImageLoader;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("/api/images")
public class ImageController {

    private final ImageLoader imageLoader;

    public ImageController(ImageLoader imageLoader) {
        this.imageLoader = imageLoader;
    }

    @GetMapping(value = "/image/{itemId}/{imageName:.+}")
    public ResponseEntity<byte[]> getImageByteArr(@PathVariable Long itemId,
                                                  @PathVariable String imageName) throws IOException {

        InputStream inputStream = imageLoader.loadImage(imageName, itemId);
        return new ResponseEntity<>(IOUtils.toByteArray(inputStream), HttpStatus.OK);
    }

    @GetMapping(value = "/image/{itemId}/{imageName:.+}")
    public void getImage(@PathVariable Long itemId, @PathVariable String imageName,
                         HttpServletResponse response) throws IOException {

        imageLoader.copyImage(imageName, itemId, response);
    }
}
