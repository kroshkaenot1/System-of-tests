let test = document.querySelector('#test');
let count = document.querySelectorAll('.qu').length;

function createQuestion(buttonAddQ){
    count++;
    let questionDiv = document.createElement('div');
    questionDiv.setAttribute('class','p-3 mb-2 bg-dark text-white qu');

    let childDiv = document.createElement('div');
    childDiv.setAttribute('id','qBody'+count);

    let closeButton = document.createElement('input');
    closeButton.setAttribute('type','button');
    closeButton.setAttribute('class','btn-close btn-close-white');
    closeButton.setAttribute('onclick','deleteQuestion(this)');
    closeButton.setAttribute('name','closeQuest'+count);
    childDiv.appendChild(closeButton);

    questionDiv.appendChild(childDiv);
    let inputQ_t = document.createElement('input');
    inputQ_t.setAttribute('type','text');
    inputQ_t.setAttribute('name','title');
    inputQ_t.setAttribute('placeholder','Введите вопрос');
    inputQ_t.setAttribute('class','form-control mb-2');
    inputQ_t.setAttribute('name','q'+count);
    inputQ_t.required = true;

    let inputImg = document.createElement('input');
    inputImg.setAttribute('type','file');
    inputImg.setAttribute('class','form-control mb-4');
    inputImg.setAttribute('name','files');

    let divR1 = document.createElement('div');
    divR1.setAttribute('class','row g-2 mb-2');

    let divVar1 = document.createElement('div');
    divVar1.setAttribute('class','col-md-8');

    let inputVar1 = document.createElement('input');
    inputVar1.setAttribute('type','text');
    inputVar1.setAttribute('placeholder','Варинт ответа');
    inputVar1.setAttribute('class','form-control');
    inputVar1.setAttribute('name','a'+count+'1');
    inputVar1.required= true;
    let divScore1 = document.createElement('div');
    divScore1.setAttribute('class','col md-4');

    let inputScore1 = document.createElement('input');
    inputScore1.setAttribute('type','number');
    inputScore1.setAttribute('placeholder','Количество баллов');
    inputScore1.setAttribute('class','form-control');
    inputScore1.setAttribute('name','b'+count+'1');
    inputScore1.required= true;

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
    inputVar2.required= true;
    let divScore2 = document.createElement('div');
    divScore2.setAttribute('class','col md-4');

    let inputScore2 = document.createElement('input');
    inputScore2.setAttribute('type','number');
    inputScore2.setAttribute('placeholder','Количество баллов');
    inputScore2.setAttribute('class','form-control');
    inputScore2.setAttribute('name','b'+count+'2');
    inputScore2.required= true;

    divVar2.appendChild(inputVar2);
    divScore2.appendChild(inputScore2);
    divR2.appendChild(divVar2);
    divR2.appendChild(divScore2);

    let buttonAddAnsw = document.createElement('input');
    buttonAddAnsw.setAttribute('type','button');
    buttonAddAnsw.setAttribute('class','btn btn-warning mt-3');
    buttonAddAnsw.setAttribute('value','Добавить вариант ответа');
    buttonAddAnsw.setAttribute('onclick','addAnsw(this)');
    buttonAddAnsw.setAttribute('name','addansw'+count);
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
function redefinition(id){
    let t = 1;
    let array = [];
    for(let i=1;i<=count;i++){
        let k = id+i;
        let temp = document.querySelector(`#test input[name='${k}']`);
        if(temp!=null){
            array.push(temp);
        }
    }
    if(id=="closeQuest"){t++};
    for(let i = 0;i<array.length;i++){
        array[i].setAttribute('name',id+t);
        t++;
    }
}
function createButtonAdd(){
    let mainDiv = document.querySelector('#main');
    let add = document.createElement('input');
    add.setAttribute('type','button');
    add.setAttribute('class','btn btn-dark mt-4');
    add.setAttribute('value','Добавить вопрос');
    add.setAttribute('id','b1');
    add.setAttribute('onclick','createQuestion(this)');
    mainDiv.appendChild(add);
}
function deleteQuestion(button){
    let i;
    let n = button.getAttribute('name');
    let num = Number(n[10]);
    let num10 = Number(n[10]+n[11]);
    let deletes = [];
    let answers = [];
    let score = [];
    let thirds = [];
    let q = [];
    let t = 1;
    if(num10==10){
        num=10;
    }
    for(let i=1;i<=count;i++){
        let th = document.getElementById('third'+i);
        let temp = document.getElementById('qBody'+i);
        let k = "deleteVar"+i;
        let d = document.querySelector(`#test input[name='${k}']`);
        if(d!=null){
            deletes.push(d);
        }
        if(th!=null){
            thirds.push(th);
        }
        if(temp!=null)
        q.push(temp);
    }
    q[num-1].parentNode.remove();
    q.splice(num-1,1);
    for(let i = 0;i<q.length;i++){
        q[i].setAttribute('id','qBody'+t);
        t++;
    }
    t=1;
    redefinition('addansw');
    redefinition('closeQuest');
    redefinition('q');
    deletes.forEach((el)=>{
        let o;
        if(el.name[10]=='0'){
            o = 10;
        }else{
            o = Number(el.name[9]);
        }
        if(o>num){
            o--;
            el.setAttribute('name','deleteVar'+o);
        }
    });
    thirds.forEach((el)=>{
        i = el.parentNode.id;
        el.setAttribute('id','third'+i[5]);
    });
    for(let i =1;i<=count;i++){
        for(let j = 1;j<=3;j++){
            let k = 'a'+i+j;
            let temp = document.querySelector(`#test input[name='${k}']`);
            if(temp!=null){
                answers.push(temp);
            }
        }
    }
    for(let i =1;i<=count;i++){
        for(let j = 1;j<=3;j++){
            let k = 'b'+i+j;
            let temp = document.querySelector(`#test input[name='${k}']`);
            if(temp!=null){
                score.push(temp);
            }
        }
    }
    for(let i = 0;i<answers.length;i++) {
        let t1 = answers[i].getAttribute('name');
        if (i + 1 < answers.length) {
            let t2 = answers[i + 1].getAttribute('name');
            if (i + 2 < answers.length) {
                let t3 = answers[i + 2].getAttribute('name');
                if (t1[1] == t2[1] && t2[1] == t3[1]) {
                    answers[i].setAttribute('name', 'a' + t + '1');
                    answers[i + 1].setAttribute('name', 'a' + t + '2');
                    answers[i + 2].setAttribute('name', 'a' + t + '3');
                    i++;
                    t++;
                    continue;
                }
            }
            if (t1[1] == t2[1]) {
                answers[i].setAttribute('name', 'a' + t + '1');
                answers[i + 1].setAttribute('name', 'a' + t + '2');
                t++;
            }
        }
    }
    t = 1;
    for(let i = 0;i<score.length;i++) {
        let t1 = score[i].getAttribute('name');
        if (i + 1 < score.length) {
            let t2 = score[i + 1].getAttribute('name');
            if (i + 2 < score.length) {
                let t3 = score[i + 2].getAttribute('name');
                if (t1[1] == t2[1] && t2[1] == t3[1]) {
                    score[i].setAttribute('name', 'b' + t + '1');
                    score[i + 1].setAttribute('name', 'b' + t + '2');
                    score[i + 2].setAttribute('name', 'b' + t + '3');
                    i++;
                    t++;
                    continue;
                }
            }
            if (t1[1] == t2[1]) {
                score[i].setAttribute('name', 'b' + t + '1');
                score[i + 1].setAttribute('name', 'b' + t + '2');
                t++;
            }
        }
    }
    count--;
    if(count==9){
        createButtonAdd();
    }
}
