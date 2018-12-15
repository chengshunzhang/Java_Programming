import edu.duke.*;
import java.io.*;
/**
 * Write a description of BatchGrayscale here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BatchGrayscale {
    public void grayscaleAndSave() {
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles()) {
            ImageResource original = new ImageResource(f);
            ImageResource grayscaled = new ImageResource(original.getWidth(), original.getHeight());
            for(Pixel pixel : original.pixels()) {
                int average = (pixel.getRed() + pixel.getGreen() + pixel.getBlue()) / 3;
                Pixel grayPixel = grayscaled.getPixel(pixel.getX(), pixel.getY());
                grayPixel.setRed(average);
                grayPixel.setGreen(average);
                grayPixel.setBlue(average);
            }
            String fName = original.getFileName();
            String newName = "gray-" + fName;
            grayscaled.setFileName(newName);
            grayscaled.save();
        }
    }
}
