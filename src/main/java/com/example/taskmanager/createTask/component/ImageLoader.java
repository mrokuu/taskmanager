package com.example.taskmanager.createTask.component;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;

import java.io.*;

@Component
public class ImageLoader {

    private final String imagesPath;

    public ImageLoader(@Value("${imagesPath}") String imagesPath) {
        this.imagesPath = imagesPath;
    }

    public void copyImage(String imageName, Long itemId, HttpServletResponse response) throws IOException {
        InputStream inputStream = new BufferedInputStream(
                new FileInputStream(imagesPath + "appaItem-" + itemId + File.separator + imageName));

        FileCopyUtils.copy(inputStream, response.getOutputStream());
    }

    public InputStream  loadImage(String imageName, Long itemId) throws IOException {
        InputStream inputStream = new BufferedInputStream(
                new FileInputStream(imagesPath + "appaItem-" + itemId + File.separator + imageName));

        return inputStream;
    }
}
