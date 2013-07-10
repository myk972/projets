function Platform() {
    this.speed = 0;
    this.direction = null;
    this.id = null;

    this.movePlatform = function () {
        if (this.direction == "right") {
            if (this.id.x.baseVal.value >= windowBorderRight) {
                this.id.x.baseVal.value = windowBorderLeft - this.id.width.baseVal.value;
            }

        }

        if (this.direction == "left") {
            if (this.id.x.baseVal.value + this.id.width.baseVal.value <= windowBorderLeft) {
                this.id.x.baseVal.value = windowBorderRight;
            }

        }

        this.id.x.baseVal.value += this.speed;

    };

    this.createPlatform = function (line, id) {
        var numLine = id.charAt(1);
        var numPlatform = id.charAt(id.length - 1);
        numLine = parseInt(numLine);
        if (numLine == '0') {
            this.speed = 2;
            this.direction = "right"
        }

        else if (numLine == '2') {
            this.speed = 3;
            this.direction = "right"
        }

        else if (numLine == '4') {
            this.speed = 1;
            this.direction = "right"
        }

        else {
            this.speed = -4;
            this.direction = "left";
        }
        var target = document.getElementById(line);

        this.id = target.cloneNode(true);

        if (numLine == 0) {
            this.id.x.baseVal.value -= (this.id.width.baseVal.value + (this.id.width.baseVal.value * (4 * numPlatform)));
        }

        else if (numLine == 1) {
            this.id.x.baseVal.value += (this.id.width.baseVal.value + (this.id.width.baseVal.value * (3 * numPlatform)));
        }

        else if (numLine == 2) {
            this.id.x.baseVal.value -= (this.id.width.baseVal.value + (this.id.width.baseVal.value * (4 * numPlatform)));
        }

        else if (numLine == 3) {
            this.id.x.baseVal.value += (this.id.width.baseVal.value + (this.id.width.baseVal.value * (4 * numPlatform)));
        }

        else {
            this.id.x.baseVal.value -= (this.id.width.baseVal.value + (this.id.width.baseVal.value * (2 * numPlatform)));
        }
        var contents = document.getElementById('platforms');
        contents.appendChild(this.id);
    }
}

