function clearLowerBits(val) {
  return Math.floor(val / 16) * 16;
}

function toHide(hideImage) {
  for(var pixel of hideImage.values()) {
    pixel.setRed(Math.floor(pixel.getRed() / 16));
    pixel.setGreen(Math.floor(pixel.getGreen() / 16));
    pixel.setBlue(Math.floor(pixel.getBlue() / 16));
  }
  return hideImage;
}

function toShow(showImage) {
  for(var pixel of showImage.values()) {
    pixel.setRed(clearLowerBits(pixel.getRed()));
    pixel.setGreen(clearLowerBits(pixel.getGreen()));
    pixel.setBlue(clearLowerBits(pixel.getBlue()));
  }
  return showImage;
}

function combine(showImage, hideImage) {
  var result = new SimpleImage(showImage.getWidth(), showImage.getHeight());
  for(var pixel of result.values()) {
    var x = pixel.getX();
    var y = pixel.getY();
    pixel.setRed(showImage.getPixel(x, y).getRed() + hideImage.getPixel(x, y).getRed());
    pixel.setGreen(showImage.getPixel(x, y).getGreen() + hideImage.getPixel(x, y).getGreen());
    pixel.setBlue(showImage.getPixel(x, y).getBlue() + hideImage.getPixel(x, y).getBlue());
  }
  return result;
}
