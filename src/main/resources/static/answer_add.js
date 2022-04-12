function addAnsw(button){
    let id = button.id;
    let qBody = document.querySelector('#qBody'+id[7]);

    let divR = document.createElement('div');
    divR.setAttribute('class','row g-2 mb-2');

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
    inputScore.setAttribute('type','text');
    inputScore.setAttribute('placeholder','Количество баллов');
    inputScore.setAttribute('class','form-control');
    inputScore.setAttribute('name','b'+id[7]+'3');
    inputScore.required;

    divVar.appendChild(inputVar);
    divScore.appendChild(inputScore);
    divR.appendChild(divVar);
    divR.appendChild(divScore);
    qBody.appendChild(divR);
    button.remove();
}