const priv = document.querySelector('#private');
const open = document.querySelector('#open');
const dropdown = document.querySelector('.dropdown');

priv.addEventListener('click',(event)=>{
        dropdown.style.display="";
});
open.addEventListener('click',(event)=>{
        dropdown.style.display="none";
        var checks = document.getElementsByTagName('input');
        for(let i = 0; i< checks.length;i++){
            if(checks[i].type=='checkbox'){
                checks[i].checked=false;
            }
        }
});