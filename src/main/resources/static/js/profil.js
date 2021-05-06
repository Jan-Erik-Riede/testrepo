function myFunction() {
    const x = document.getElementsByClassName("change-input");
    const y = document.getElementsByClassName("change-div");
    const z = document.getElementById("empty-block");
    const a = document.getElementById("button-block");
    for (var i = 0; i < 6; i++)
        if (x[i].style.display === "none") {
            x[i].style.display = "block";
            y[i].style.display = "none";
            z.style.display = "none";
            a.style.display = "block";
        } else {
            x[i].style.display = "none";
            y[i].style.display = "block";
            z.style.display = "block";
            a.style.display = "none";
        }


}