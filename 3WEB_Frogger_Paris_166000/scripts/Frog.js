function Frog() {
    this.id = document.getElementById("frog");
    this.initialX = this.id.x.baseVal.value;
    this.initialY = this.id.y.baseVal.value;
    this.isOnPlatform = false;
    this.step = 18;
    this.img = document.getElementById("frog_img");


    this.move = function (key) {
        switch (key) {
            //left arrow
            case 37:
                if ((this.id.x.baseVal.value - this.step) > windowBorderLeft) {
                    this.id.x.baseVal.value -= this.step;
                    this.img.setAttribute("xlink:href", "images/frogLeft.gif");
                }
                break;

            //right arrow
            case 39:
                if ((this.id.x.baseVal.value + this.id.width.baseVal.value + this.step) < windowBorderRight) {
                    this.id.x.baseVal.value += this.step;
                    this.img.setAttribute("xlink:href", "images/frogRight.gif");
                }
                break;

            //up arrow
            case 38:
                if ((this.id.y.baseVal.value - this.step) > windowHigh || this.stepOnFreeWaterLily()) {
                    this.id.y.baseVal.value -= this.step;
                    this.img.setAttribute("xlink:href", "images/frogUp.gif");
                }
                break;

            //down arrow
            case 40:
                if ((this.id.y.baseVal.value + this.id.height.baseVal.value + this.step) < windowBottom) {
                    this.id.y.baseVal.value += this.step;
                    this.img.setAttribute("xlink:href", "images/frogDown.gif");
                }
                break;
        }
    };

    this.stepOnFreeWaterLily = function () {
        var test = false;
        for (var i = 0; i < waterLilies.length; i++) {
            if (this.id.x.baseVal.value > waterLilies[i].id.x.baseVal.value &&
                this.id.x.baseVal.value < (waterLilies[i].id.x.baseVal.value + waterLilies[i].id.width.baseVal.value) &&
                (this.id.y.baseVal.value - this.step) < waterLilies[i].id.y.baseVal.value + waterLilies[i].id.height.baseVal.value) {
                if (!waterLilies[i].isOccupied) {
                    this.img.setAttribute("xlink:href", "images/frogUp.gif");
                    this.duplicateFrog();
                    this.reinitializePosition();
                    waterLilies[i].isOccupied = true;
                    isComplete();
                }
                else {
                    isOver();
                }
            }
        }
        timeLeft = 60;
        return test;
    };


    this.reinitializePosition = function () {
        this.id.x.baseVal.value = this.initialX;
        this.id.y.baseVal.value = this.initialY;
        this.img.setAttribute("xlink:href", "images/frogUp.gif");
    };

    this.isDiving = function () {
        if (this.id.y.baseVal.value < riverYPosition && (this.isOnPlatform == false)) {
            isOver();
        }
    };

    this.stayOnScreen = function () {
        if (this.id.x.baseVal.value < windowBorderLeft || (this.id.x.baseVal.value + this.id.width.baseVal.value ) > windowBorderRight) {
            isOver();
        }
    };

    this.isHit = function () {
        for (var i = 0; i < 5; i++) {
            for (var j = 0; j < 3; j++) {
                if (((this.id.x.baseVal.value + this.id.width.baseVal.value >= cars[i][j].id.x.baseVal.value && this.id.x.baseVal.value + this.id.width.baseVal.value <= cars[i][j].id.x.baseVal.value + cars[i][j].id.width.baseVal.value) ||
                    (this.id.x.baseVal.value >= cars[i][j].id.x.baseVal.value &&
                        this.id.x.baseVal.value <= cars[i][j].id.x.baseVal.value + cars[i][j].id.width.baseVal.value)) &&
                    ((this.id.y.baseVal.value <= cars[i][j].id.y.baseVal.value + cars[i][j].id.height.baseVal.value) &&
                        (this.id.y.baseVal.value >= cars[i][j].id.y.baseVal.value) ||
                        (this.id.y.baseVal.value + this.id.height.baseVal.value <= cars[i][j].id.y.baseVal.value + cars[i][j].id.height.baseVal.value) &&
                            (this.id.y.baseVal.value + this.id.height.baseVal.value >= cars[i][j].id.y.baseVal.value))) {
                    isOver();
                }
            }
        }
    };

    this.istransported = function () {
        var test = false;
        for (var i = 0; i < 5; i++) {
            for (var j = 0; j < 3; j++) {
                if (((this.id.x.baseVal.value + (this.id.width.baseVal.value / 2) >= platforms[i][j].id.x.baseVal.value
                    && this.id.x.baseVal.value + (this.id.width.baseVal.value / 2) <= platforms[i][j].id.x.baseVal.value + platforms[i][j].id.width.baseVal.value) &&
                    (this.id.y.baseVal.value <= platforms[i][j].id.y.baseVal.value + platforms[i][j].id.height.baseVal.value) &&
                    (this.id.y.baseVal.value >= platforms[i][j].id.y.baseVal.value))) {
                    test = true;
                    this.id.x.baseVal.value += platforms[i][j].speed;
                }
            }
        }
        this.isOnPlatform = test;
    };

    this.verify = function () {
        this.istransported();
        this.isDiving();
        this.isHit();
        this.stayOnScreen();
    };

    this.duplicateFrog = function () {
        var newnode = this.id.cloneNode(true);
        newnode.y.baseVal.value = this.id.y.baseVal.value - this.step;
        var contents = document.getElementById('frogs');
        contents.appendChild(newnode)
    };
}


