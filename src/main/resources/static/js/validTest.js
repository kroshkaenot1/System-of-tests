let form = document.querySelector('#test'),
    title = document.querySelector('input[name=title]'),
    descr = document.querySelector('textarea[name=description]');

form.onsubmit = function (){
    let formInputs = document.querySelectorAll('#test input');
    let i = 0;
    formInputs.forEach((el)=> {
        if (el.name != "files" && el.type!="button" && el.type!="hidden") {
            el.addEventListener('input',function (event){
                if (el.value.length > 50 || el.value === "") {
                    el.classList.add('error');
                    el.classList.remove('valid');
                } else {
                    el.classList.remove('error');
                    el.classList.add('valid');
                }
            });
        if (el.value.length > 50 || el.value === "") {
            el.classList.add('error');
            el.classList.remove('valid');
            i++;
        } else {
            el.classList.remove('error');
            el.classList.add('valid');
        }
    }
    });
    if(descr.value.length>100 || descr.value===""){
        descr.classList.add('error');
        descr.classList.remove('valid');
        i++;
    } else {
        descr.classList.remove('error');
        descr.classList.add('valid');
    }
    descr.addEventListener('input',function (event) {
        if(descr.value.length>100 || descr.value===""){
            descr.classList.add('error');
            descr.classList.remove('valid');
            i++;
        } else {
            descr.classList.remove('error');
            descr.classList.add('valid');
        }
    });
    if(i!=0){
        return false;
    }
}