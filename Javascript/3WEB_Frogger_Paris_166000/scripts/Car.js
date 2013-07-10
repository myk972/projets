function Car() {
    this.speed = 0;
    this.direction = null;
    this.id = null;

    this.moveCar = function () {
        if (this.direction == "right") {
            if (this.id.x.baseVal.value >= windowBorderRight) {
                this.id.x.baseVal.value = windowBorderLeft - this.id.width.baseVal.value;
            }

            this.id.x.baseVal.value += this.speed;
        }

        if (this.direction == "left") {
            if (this.id.x.baseVal.value <= windowBorderLeft) {
                this.id.x.baseVal.value = windowBorderRight;
            }

            this.id.x.baseVal.value -= this.speed;
        }
    };

    this.createCar = function (line, id) {
        var numLine = id.charAt(0);
        var numCar = id.charAt(id.length - 1);
        numLine = parseInt(numLine);
        if (numLine == '0') {
            this.speed = 2;
            this.direction = "right"
        }

        else if (numLine == '2' || numLine == '4') {
            this.speed = 3;
            this.direction = "right"
        }
        else {
            this.speed = 4;
            this.direction = "left";
        }


        var target = document.getElementById(line);
        this.id = target.cloneNode(true);
        if (numLine == 0) {
            this.id.x.baseVal.value -= (45 + (45 * (5 * numCar)));
        }

        else if (numLine == 1) {
            this.id.x.baseVal.value += (45 + (45 * (4 * numCar)));
        }

        else if (numLine == 2) {
            this.id.x.baseVal.value += (45 - (45 * (5 * numCar)));
        }

        else if (numLine == 3) {
            this.id.x.baseVal.value += (45 + (45 * (4 * numCar)));
        }

        else {
            this.id.x.baseVal.value += (45 - (45 * (6 * numCar)));
        }

        var contents = document.getElementById('cars');
        contents.appendChild(this.id);
    }
}
