var cvs = null;
var originalImage = null;

function loadImage() {
  cvs = document.getElementById("canvas");
  var inputFile = document.getElementById("uploaded");
  originalImage = new SimpleImage(inputFile);
  originalImage.drawTo(cvs);
}

function imageLoaded() {
  if(originalImage === null || !originalImage.complete()) {
    return false;
  }
  return true;
}

function doGrayScale() {
  if(imageLoaded()) {
    var grayImage = new SimpleImage(originalImage.getWidth(), originalImage.getHeight());
    document.getElementById("size").innerHTML = originalImage.getWidth() + "*" + originalImage.getHeight();
    for(var pixel of originalImage.values()) {
      var x = pixel.getX();
      var y = pixel.getY();
      var grayPixel = grayImage.getPixel(x, y);
      var grayValue = (pixel.getRed() + pixel.getGreen() + pixel.getBlue()) / 3;
      grayPixel.setRed(grayValue);
      grayPixel.setGreen(grayValue);
      grayPixel.setBlue(grayValue);
    }
    var ctx = cvs.getContext("2d");
    ctx.clearRect(0, 0, cvs.width, cvs.height);
    grayImage.drawTo(cvs);
  } else {
    alert("Image not ready!");
  }
}

function doRed() {
  if(imageLoaded()) {
    var redImage = new SimpleImage(originalImage.getWidth(), originalImage.getHeight());
    document.getElementById("size").innerHTML = originalImage.getWidth() + "*" + originalImage.getHeight();
    for(var pixel of originalImage.values()) {
      var x = pixel.getX();
      var y = pixel.getY();
      var avg = (pixel.getRed() + pixel.getGreen() + pixel.getBlue()) / 3;
      var redPixel = redImage.getPixel(x, y);
      if(avg < 128) {
        redPixel.setRed(2 * avg);
        redPixel.setGreen(0);
        redPixel.setBlue(0);
      } else {
        redPixel.setRed(255);
        redPixel.setGreen(2 * avg - 255);
        redPixel.setBlue(2 * avg - 255);
      }
    }
    var ctx = cvs.getContext("2d");
    ctx.clearRect(0, 0, cvs.width, cvs.height);
    redImage.drawTo(cvs);
  } else {
    alert("Image not ready!");
  }
}

function doWindow() {
  if(imageLoaded()) {
    var imageWidth = originalImage.getWidth();
    var imageHeight = originalImage.getHeight();
    document.getElementById("size").innerHTML = originalImage.getWidth() + "*" + originalImage.getHeight();
    var windowedImage = new SimpleImage(imageWidth, imageHeight);
    var borderWidth = imageWidth / 30;
    var insideWidth = imageHeight / 25;
    for(var pixel of originalImage.values()) {
      var x = pixel.getX();
      var y = pixel.getY();
      var newPixel = windowedImage.getPixel(x, y);
      if(x < borderWidth || x > imageWidth - borderWidth || y < borderWidth || y > imageHeight - borderWidth ||
      (x > imageWidth / 4 && x < imageWidth / 4 + insideWidth) || (x > imageWidth / 2 && x < imageWidth / 2 + insideWidth) ||
    (x > imageWidth * 3 / 4 && x < imageWidth * 3 / 4 + insideWidth) || (y > imageHeight / 2 && y < imageHeight / 2 + insideWidth)) {
        newPixel.setRed(226);
        newPixel.setGreen(207);
        newPixel.setBlue(129);
      } else {
        windowedImage.setPixel(x, y, pixel);
      }
    }
    var ctx = cvs.getContext("2d");
    ctx.clearRect(0, 0, cvs.width, cvs.height);
    windowedImage.drawTo(cvs);
  } else {
    alert("Image not ready!");
  }
}

function turnRed(pixel, average) {
  if(average < 128) {
    pixel.setRed(2 * average);
    pixel.setGreen(0);
    pixel.setBlue(0);
  } else {
    pixel.setRed(255);
    pixel.setGreen(2 * average - 255);
    pixel.setBlue(2 * average - 255);
  }
}

function turnOrange(pixel, average) {
  if(average < 128) {
    pixel.setRed(2 * average);
    pixel.setGreen(0.8 * average);
    pixel.setBlue(0);
  } else {
    pixel.setRed(255);
    pixel.setGreen(1.2 * average - 51);
    pixel.setBlue(2 * average - 255);
  }
}

function turnYellow(pixel, average) {
  if(average < 128) {
    pixel.setRed(2 * average);
    pixel.setGreen(2 * average);
    pixel.setBlue(0);
  } else {
    pixel.setRed(255);
    pixel.setGreen(255);
    pixel.setBlue(2 * average - 255);
  }
}

function turnGreen(pixel, average) {
  if(average < 128) {
    pixel.setRed(0);
    pixel.setGreen(2 * average);
    pixel.setBlue(0);
  } else {
    pixel.setRed(2 * average - 255);
    pixel.setGreen(255);
    pixel.setBlue(2 * average - 255);
  }
}

function turnBlue(pixel, average) {
  if(average < 128) {
    pixel.setRed(0);
    pixel.setGreen(0);
    pixel.setBlue(2 * average);
  } else {
    pixel.setRed(2 * average - 255);
    pixel.setGreen(2 * average - 255);
    pixel.setBlue(255);
  }
}

function turnIndigo(pixel, average) {
  if(average < 128) {
    pixel.setRed(0.8 * average);
    pixel.setGreen(0);
    pixel.setBlue(2 * average);
  } else {
    pixel.setRed(1.2 * average - 51);
    pixel.setGreen(2 * average - 255);
    pixel.setBlue(255);
  }
}

function turnViolet(pixel, average) {
  if(average < 128) {
    pixel.setRed(1.6 * average);
    pixel.setGreen(0);
    pixel.setBlue(1.6 * average);
  } else {
    pixel.setRed(0.4 * average + 153);
    pixel.setGreen(2 * average - 255);
    pixel.setBlue(0.4 * average + 153);
  }
}

function doRainbow() {
  if(imageLoaded()) {
    var rainbowImage = new SimpleImage(originalImage.getWidth(), originalImage.getHeight());
    document.getElementById("size").innerHTML = originalImage.getWidth() + "*" + originalImage.getHeight();
    var stripeWidth = rainbowImage.height / 7;
    for(var pixel of originalImage.values()) {
      var x = pixel.getX();
      var y = pixel.getY();
      var avg = (pixel.getRed() + pixel.getGreen() + pixel.getBlue()) / 3;
      if(y < stripeWidth) {
        turnRed(rainbowImage.getPixel(x, y), avg);
      } else if(y < 2 * stripeWidth) {
        turnOrange(rainbowImage.getPixel(x, y), avg);
      } else if(y < 3 * stripeWidth) {
        turnYellow(rainbowImage.getPixel(x, y), avg);
      } else if(y < 4 * stripeWidth) {
        turnGreen(rainbowImage.getPixel(x, y), avg);
      } else if(y < 5 * stripeWidth) {
        turnBlue(rainbowImage.getPixel(x, y), avg);
      } else if(y < 6 * stripeWidth) {
        turnIndigo(rainbowImage.getPixel(x, y), avg);
      } else {
        turnViolet(rainbowImage.getPixel(x, y), avg);
      }
    }
    var ctx = cvs.getContext("2d");
    ctx.clearRect(0, 0, cvs.width, cvs.height);
    rainbowImage.drawTo(cvs);
  } else {
    alert("Image not ready!");
  }
}

function reset() {
  if(imageLoaded()) {
    var ctx = cvs.getContext("2d");
    ctx.clearRect(0, 0, cvs.width, cvs.height);
    originalImage.drawTo(cvs);
  } else {
    alert("Image not ready!");
  }
}
