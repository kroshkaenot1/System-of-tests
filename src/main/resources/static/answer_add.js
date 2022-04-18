function addAnsw(button){
    let id = button.name;
    let qBody = document.querySelector('#qBody'+id[7]);

    let divR = document.createElement('div');
    divR.setAttribute('class','row g-2 mb-2');
    divR.setAttribute('id','third'+id[7]);

    let divVar = document.createElement('div');
    divVar.setAttribute('class','col-md-8');

    let inputVar = document.createElement('input');
    inputVar.setAttribute('type','text');
    inputVar.setAttribute('placeholder','Варинт ответа');
    inputVar.setAttribute('class','form-control');
    inputVar.setAttribute('name','a'+id[7]+'3');
    inputVar.required;

    let divScore = document.createElement('div');
    divScore.setAttribute('class','col md-4');

    let inputScore = document.createElement('input');
    inputScore.setAttribute('type','number');
    inputScore.setAttribute('placeholder','Количество баллов');
    inputScore.setAttribute('class','form-control');
    inputScore.setAttribute('name','b'+id[7]+'3');
    inputScore.setAttribute('value','0');
    inputScore.required;
    let divClose = document.createElement('div');
    divClose.setAttribute('class','col');

    let closeButton = document.createElement('input');
    closeButton.setAttribute('type','button');
    closeButton.setAttribute('class','btn-close btn-close-white');
    closeButton.setAttribute('onclick','deleteVar(this)');
    closeButton.setAttribute('name','deleteVar'+id[7]);

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
    document.querySelector('#third'+id[9]).remove();
    let buttonAddAnsw = document.createElement('input');
    buttonAddAnsw.setAttribute('type','button');
    buttonAddAnsw.setAttribute('class','btn btn-warning mt-3');
    buttonAddAnsw.setAttribute('value','Добавить вариант ответа');
    buttonAddAnsw.setAttribute('onclick','addAnsw(this)');
    buttonAddAnsw.setAttribute('name','addansw'+id[9]);
    document.querySelector('#qBody'+id[9]).parentNode.appendChild(buttonAddAnsw);
}