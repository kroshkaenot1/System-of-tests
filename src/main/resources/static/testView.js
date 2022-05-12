let isLimited = document.getElementById("isLimited");
let form = document.getElementById('test');
if(isLimited.value=="true"){
    let time = document.getElementById('timer');
    timeMinut = 30*60;
    let timer = setInterval(function(){
        minuts = timeMinut/60%60;
        seconds = timeMinut%60;
        if(timeMinut<=0){
            clearInterval(timer);
            alert("Время закончилось");
            form.submit();
        } else {
            let strTimer = `${Math.trunc(minuts)}:${seconds}`;
            time.innerHTML = strTimer;
        }
        timeMinut--;
    },1000);
}