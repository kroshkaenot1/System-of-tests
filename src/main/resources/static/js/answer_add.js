function addAnsw(button){
    let id = button.name;
    let Id;
    if(id[8]=='0'){
        Id=10;
    }else {
        Id = id[7];
    }
    let qBody = document.querySelector('#qBody'+Id);

    let divR = document.createElement('div');
    divR.setAttribute('class','row g-2 mb-2');
    divR.setAttribute('id','third'+Id);

    let divVar = document.createElement('div');
    divVar.setAttribute('class','col-md-8');

    let inputVar = document.createElement('input');
    inputVar.setAttribute('type','text');
    inputVar.setAttribute('placeholder','Варинт ответа');
    inputVar.setAttribute('class','form-control');
    inputVar.setAttribute('name','a'+Id+'3');
    inputVar.required= true;

    let divScore = document.createElement('div');
    divScore.setAttribute('class','col md-4');

    let inputScore = document.createElement('input');
    inputScore.setAttribute('type','number');
    inputScore.setAttribute('placeholder','Количество баллов');
    inputScore.setAttribute('class','form-control');
    inputScore.setAttribute('name','b'+Id+'3');
    inputScore.required = true;
    let divClose = document.createElement('div');
    divClose.setAttribute('class','col');

    let closeButton = document.createElement('input');
    closeButton.setAttribute('type','button');
    closeButton.setAttribute('class','btn-close btn-close-white');
    closeButton.setAttribute('onclick','deleteVar(this)');
    closeButton.setAttribute('name','deleteVar'+Id);

    divClose.appendChild(closeButton);

    divVar.appendChild(inputVar);
    divScore.appendChild(inputScore);
    divR.appendChild(divVar);
    divR.appendChild(divScore);
    divR.appendChild(divClose);
    qBody.appendChild(divR);
    button.remove();
}
function deleteVar(button){
    let id = button.getAttribute("name");
    let Id;
    if(id[10]=='0'){
        Id = 10;
    } else {
        Id = id[9];
    }
    document.querySelector('#third'+Id).remove();
    let buttonAddAnsw = document.createElement('input');
    buttonAddAnsw.setAttribute('type','button');
    buttonAddAnsw.setAttribute('class','btn btn-warning mt-3');
    buttonAddAnsw.setAttribute('value','Добавить вариант ответа');
    buttonAddAnsw.setAttribute('onclick','addAnsw(this)');
    buttonAddAnsw.setAttribute('name','addansw'+Id);
    document.querySelector('#qBody'+Id).parentNode.appendChild(buttonAddAnsw);
}
function deleteImg(button){
    let name = button.id;
    document.querySelector('#file'+name[9]).remove();
}
let fileInputs = document.querySelectorAll("#test input[name=files]");
fileInputs.forEach((el)=>{
    el.addEventListener('change',function (event){
        let i = this.parentNode.id[1];
        if(this.parentNode.id[2]=="0"){
            i=10
        }
        document.querySelector("#file" + i).remove();
    });
});