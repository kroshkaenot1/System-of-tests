let test = document.querySelector('#test');
let count = 1;
let buttonAddQ = document.querySelector('#b1');
buttonAddQ.onclick = function createQuestion(){
    count++;
    let questionDiv = document.createElement('div');
    questionDiv.setAttribute('class','p-3 mb-2 bg-dark text-white');

    let childDiv = document.createElement('div');
    childDiv.setAttribute('id','qBody'+count);
    questionDiv.appendChild(childDiv);
    let inputQ_t = document.createElement('input');
    inputQ_t.setAttribute('type','text');
    inputQ_t.setAttribute('name','title');
    inputQ_t.setAttribute('placeholder','Введите вопрос');
    inputQ_t.setAttribute('class','form-control mb-2');
    inputQ_t.setAttribute('name','q'+count);
    inputQ_t.required;

    let inputImg = document.createElement('input');
    inputImg.setAttribute('type','file');
    inputImg.setAttribute('class','form-control mb-4');
    inputImg.setAttribute('name','img'+count);

    let divR1 = document.createElement('div');
    divR1.setAttribute('class','row g-2 mb-2');

    let divVar1 = document.createElement('div');
    divVar1.setAttribute('class','col-md-8');

    let inputVar1 = document.createElement('input');
    inputVar1.setAttribute('type','text');
    inputVar1.setAttribute('placeholder','Варинт ответа');
    inputVar1.setAttribute('class','form-control');
    inputVar1.setAttribute('name','a'+count+'1');
    inputVar1.required;

    let divScore1 = document.createElement('div');
    divScore1.setAttribute('class','col md-4');

    let inputScore1 = document.createElement('input');
    inputScore1.setAttribute('type','number');
    inputScore1.setAttribute('placeholder','Количество баллов');
    inputScore1.setAttribute('class','form-control');
    inputScore1.setAttribute('name','b'+count+'1');
    inputScore1.required;

    divVar1.appendChild(inputVar1);
    divScore1.appendChild(inputScore1);
    divR1.appendChild(divVar1);
    divR1.appendChild(divScore1);

    let divR2 = document.createElement('div');
    divR2.setAttribute('class','row g-2 mb-2');

    let divVar2 = document.createElement('div');
    divVar2.setAttribute('class','col-md-8');

    let inputVar2 = document.createElement('input');
    inputVar2.setAttribute('type','text');
    inputVar2.setAttribute('placeholder','Варинт ответа');
    inputVar2.setAttribute('class','form-control');
    inputVar2.setAttribute('name','a'+count+'2');
    inputVar2.required;

    let divScore2 = document.createElement('div');
    divScore2.setAttribute('class','col md-4');

    let inputScore2 = document.createElement('input');
    inputScore2.setAttribute('type','number');
    inputScore2.setAttribute('placeholder','Количество баллов');
    inputScore2.setAttribute('class','form-control');
    inputScore2.setAttribute('name','b'+count+'2');
    inputScore2.required;

    divVar2.appendChild(inputVar2);
    divScore2.appendChild(inputScore2);
    divR2.appendChild(divVar2);
    divR2.appendChild(divScore2);

    let buttonAddAnsw = document.createElement('input');
    buttonAddAnsw.setAttribute('type','button');
    buttonAddAnsw.setAttribute('class','btn btn-warning mt-3');
    buttonAddAnsw.setAttribute('value','Добавить вариант ответа');
    buttonAddAnsw.setAttribute('onclick','addAnsw(this)');
    buttonAddAnsw.setAttribute('id','addansw'+count);

    childDiv.appendChild(inputQ_t);
    childDiv.appendChild(inputImg);
    childDiv.appendChild(divR1);
    childDiv.appendChild(divR2);
    questionDiv.appendChild(childDiv);
    questionDiv.appendChild(buttonAddAnsw);
    test.appendChild(questionDiv);
    if(count==10){
        buttonAddQ.remove();
    }
}
