package ru.uoles.proj.utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class FileUtils {

    public static File urlToFile(String path, String name) throws IOException {
        URL url = new URL(path);
        BufferedImage img = ImageIO.read(url);

        File file = new File("temp/" + name + "/" + java.util.UUID.randomUUID().toString() + ".jpg");
        file.mkdirs();

        ImageIO.write(img, "jpg", file);
        return file;
    }
}
