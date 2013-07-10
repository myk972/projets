function WaterLily() {
    this.id = null;
    this.isOccupied = false;

    this.place = function (num) {
        var target = document.getElementById("waterlily");
        this.id = target.cloneNode(true);
        this.id.x.baseVal.value += (3 * this.id.width.baseVal.value * num);
        var contents = document.getElementById('waterlilies');
        contents.appendChild(this.id);
    }
}
