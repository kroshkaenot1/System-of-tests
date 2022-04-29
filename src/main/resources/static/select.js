const priv = document.querySelector('#private');
const open = document.querySelector('#open');
const dropdown = document.querySelector('.dropdown');

priv.addEventListener('click',(event)=>{
    if(event.target.checked){
        dropdown.classList.remove('hide');
    }
});
open.addEventListener('click',(event)=>{
    if(event.target.checked){
        dropdown.classList.add('hide');
        $('input:checkbox').removeAttr('checked');
    }
});