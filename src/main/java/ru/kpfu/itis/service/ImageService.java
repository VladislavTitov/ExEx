package ru.kpfu.itis.service;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.kpfu.itis.exceptions.shared.NoContentException;
import ru.kpfu.itis.exceptions.shared.NoSuchIdException;

import java.io.*;
import java.util.UUID;

@Service
public class ImageService {

    private File imageStorageDir;

    @Autowired
    public ImageService(File imageStorageDir) {
        this.imageStorageDir = imageStorageDir;
    }

    public byte[] getImage(String imageName) throws NoSuchIdException {
        File image = new File(imageStorageDir, imageName);
        if (!image.exists()) {
            throw new NoSuchIdException("Image with id = " + imageName + " doesn't exist!");
        }
        try(InputStream fileInputStream = new BufferedInputStream(new FileInputStream(image))) {
            byte[] bytes = new byte[fileInputStream.available()];
            fileInputStream.read(bytes);
            return bytes;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String saveImage(MultipartFile rawImage) {
        if (rawImage == null || rawImage.isEmpty()) {
            throw new NoContentException("Uploaded file is empty!");
        }

        String filename = rawImage.getOriginalFilename();
        String newFileName = getNewFileName(filename);
        File savedImage = getFile(imageStorageDir, newFileName);
        try {
            rawImage.transferTo(savedImage);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return savedImage.getName();
    }

    @NotNull
    private String getNewFileName(String filename) {
        UUID uuid = UUID.randomUUID();
        String stringUUID = uuid.toString();
        String[] splittedFilename = filename.split("\\.");
        String extension = splittedFilename[splittedFilename.length - 1];
        return stringUUID + "." + extension;
    }

    private File getFile(String filename) {
        File file = new File(filename);
        return createIfNotExists(file);
    }

    private File getFile(File parentDirectory, String filename) {
        File file = new File(parentDirectory, filename);
        return createIfNotExists(file);
    }

    private File createIfNotExists(File file) {
        if (!file.exists()) {
            if (!file.mkdirs()) {
                throw new RuntimeException("Image directory can not be created!");
            }
        }
        return file;
    }


}
