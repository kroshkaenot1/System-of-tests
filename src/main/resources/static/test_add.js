let test = document.querySelector('#test');
let question = document.querySelector('#question1');

document.querySelector('#badd').onclick= function () {
    let clone = question.cloneNode(true);
    test.appendChild(clone);
    alert('aaaaaaaaaaaa');
}