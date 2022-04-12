let test = document.querySelector('#test');
let question = document.querySelector('#question1');
let answer = document.querySelector('.vb');

/*document.querySelector('.badd').onclick= function () {
    let clone = question.cloneNode(true);
    test.appendChild(clone);
}*/
function vb(){
    let clone = answer.cloneNode(true);
    question.appendChild(clone);
    document.querySelector('#b1').remove();
}
