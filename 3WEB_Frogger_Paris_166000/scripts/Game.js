var windowBorderLeft = 0;
var windowBorderRight = 650;
var windowHigh = 40;
var windowBottom = 450;
var riverYPosition = 215;
var lives = 3;
var cars = [];
var platforms = [];
var waterLilies = [];
var gameloop = null;
var timer = null;
var timeLeft = 60;
var frog = null;
var canMove = true;

function isOver() {
    canMove = false;
    if (lives-- <= 0) {
        game_over.setAttribute("opacity", 1);
        endGame();
    }
    else {
        frog.id.setAttribute("opacity", 0);
        loose.setAttribute("opacity", 1);
        clearInterval(timer);
        timeLeft = 60;
        setTimeout(function () {
            canMove = true;
            frog.id.setAttribute("opacity", 1);
            loose.setAttribute("opacity", 0);
            timer = setInterval(function () {
                document.getElementById("timer").textContent = timeLeft--;
            }, 1000);
        }, 2000);
        frog.reinitializePosition();
        document.getElementById("life").textContent = "lives:" + lives;
    }
}


function isComplete() {
    var finish = true;
    for (var i = 0; i < waterLilies.length; i++) {
        if (!waterLilies[i].isOccupied) {
            finish = false;
        }
    }
    if (finish) {
        success.setAttribute("opacity", 1);
        endGame();
    }
}

function moveCars() {
    for (var i = 0; i < 5; i++) {
        for (var j = 0; j < 3; j++) {
            cars[i][j].moveCar();

        }
    }
}

function movePlatforms() {
    for (var i = 0; i < 5; i++) {
        for (var j = 0; j < 3; j++) {
            platforms[i][j].movePlatform();

        }
    }
}

function endGame() {
    frog.id.setAttribute("opacity", 0);
    clearInterval(gameloop);
    clearInterval(timer);
}

function animateGame() {
    if (timeLeft < 0) {
        isOver();
    }
    movePlatforms();
    moveCars();
    frog.verify();
}

function whatKey(evt) {
    if(canMove){
    var key = evt.keyCode;
    frog.move(key);
    }
}

function drawCars(){
    for (var i = 0; i < 5; i++) {
        cars[i] = [];
        var line = "car_line" + (i + 1);
        for (var j = 0; j < 3; j++) {
            var id = i + "-" + j;
            var car = new Car();
            car.createCar(line, id);
            cars[i][j] = car;
        }
    }
    var carsToDelete = document.getElementById("carToDelete");
    var parentNode = carsToDelete.parentNode;
    parentNode.removeChild(carsToDelete);
}

function drawPlatforms() {
    var line;
    for (var i = 0; i < 5; i++) {
        platforms[i] = [];
        line = "platform" + (i + 1);
        for (var j = 0; j < 3; j++) {
            var id = "p" + i + "-" + j;
            var platform = new Platform();
            platform.createPlatform(line, id);
            platforms[i][j] = platform;
        }
    }
    var platformsToDelete = document.getElementById("platformsToDelete");
    var parentNode = platformsToDelete.parentNode;
    parentNode.removeChild(platformsToDelete);
}

function drawWaterLilies() {
    for (var i = 0; i < 5; i++) {
        var waterlily = new WaterLily();
        waterlily.place(i);
        waterLilies[i] = waterlily;
    }
}

function drawGame() {
    drawCars();
    drawPlatforms();
    drawWaterLilies();
    frog = new Frog();
    gameloop = setInterval(animateGame, 50);
    document.getElementById("life").textContent = "lives:" + lives;
    timer = setInterval(function () {
        document.getElementById("timer").textContent = timeLeft--;
    }, 1000);
    window.addEventListener('keydown', whatKey, true);
}

