var leftCanvas = null;
var fgImage = null;
var rightCanvas = null;
var bgImage = null;
function loadForegroundImage() {
  leftCanvas = document.getElementById("left");
  var fgFile = document.getElementById("fgimage");
  fgImage = new SimpleImage(fgFile);
  fgImage.drawTo(leftCanvas);
}

function loadBackgroundImage() {
  rightCanvas = document.getElementById("right");
  var bgFile = document.getElementById("bgimage");
  bgImage = new SimpleImage(bgFile);
  bgImage.drawTo(rightCanvas);
}

function clearCanvas() {
  var ctx = leftCanvas.getContext("2d");
  ctx.clearRect(0, 0, leftCanvas.width, leftCanvas.height);
  ctx = rightCanvas.getContext("2d");
  ctx.clearRect(0, 0, rightCanvas.width, rightCanvas.height);
}

function doGreenScreen() {
  if(fgImage === null || !fgImage.complete()) {
    alert("foreground image not ready!");
  } else if(bgImage == null || !bgImage.complete()) {
    alert("background image not ready!");
  } else {
    clearCanvas();
    var blank = new SimpleImage(fgImage.width, fgImage.height);
    for(var pixel of fgImage.values()) {
      var x = pixel.getX();
      var y = pixel.getY();
      if(pixel.getGreen() > 240) {
        blank.setPixel(x, y, bgImage.getPixel(x, y));
      } else {
        blank.setPixel(x, y, pixel);
      }
    }
    blank.drawTo(rightCanvas);
  }
}
