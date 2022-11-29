package tools;

import org.jetbrains.annotations.NotNull;

import java.io.FileNotFoundException;

public class FileExtension {

    public static @NotNull String getExtension(@NotNull String fileName) throws FileNotFoundException {
        if (fileName.indexOf('.') < 0 || fileName.indexOf('.') == fileName.length() - 1) {
            throw new FileNotFoundException("Некорректное имя файла");
        }

        return fileName.substring(fileName.lastIndexOf('.') + 1);
    }
}