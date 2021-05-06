function cancelFunction() {
    const x = document.getElementsByClassName("cancel-box")[0];
    const y = document.getElementsByClassName("text-danger");
    x.style.display = "block";
    let i;
    for(i = 0; i < y.length; i++) {
        y[i].style.opacity = "0";
    }

}
function cancel_cancelFunction() {
    const x = document.getElementsByClassName("cancel-box")[0];
    const y = document.getElementsByClassName("text-danger");
    x.style.display = "none";
    let i;
    for(i = 0; i < y.length; i++) {
        y[i].style.opacity = "1";
    }
}