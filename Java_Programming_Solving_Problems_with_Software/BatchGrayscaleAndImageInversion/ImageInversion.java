import edu.duke.*;
import java.io.*;
/**
 * Write a description of ImageInversion here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ImageInversion {
    public ImageResource makeInversion(ImageResource image) {
        ImageResource inverted = new ImageResource(image.getWidth(), image.getHeight());
        for(Pixel pixel : image.pixels()) {
            Pixel invertedPixel = inverted.getPixel(pixel.getX(), pixel.getY());
            invertedPixel.setRed(255 - pixel.getRed());
            invertedPixel.setGreen(255 - pixel.getGreen());
            invertedPixel.setBlue(255 - pixel.getBlue());
        }
        return inverted;
    }
    
    public void selectAndConvert() {
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles()) {
            ImageResource ir = new ImageResource(f);
            ImageResource inverted = makeInversion(ir);
            String oriName = ir.getFileName();
            String newName = "inverted-" + oriName;
            inverted.setFileName(newName);
            inverted.save();
        }
    }
}
