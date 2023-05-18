let menu = document.querySelector('#menu-bars');
let navbar = document.querySelector('.navbar');

menu.onclick = () =>{
    menu.classList.toggle('fa-times');
    navbar.classList.toggle('active');
}
window.onscroll = () =>{
    menu.classList.remove('fa-times');
    navbar.classList.remove('active');
}
document.querySelector('#search-icon').onclick = () =>{
    document.querySelector('#search-form').classList.toggle('active');
}
document.querySelector('#close').onclick = () =>{
    document.querySelector('#search-form').classList.remove('active');
}
function cardspace(){
    var cardDigit = document.getElementById('cardnum').value;
    if(cardDigit.length == 16){
        document.getElementById('cardnum').value = cardDigit+" ";
        document.getElementById('cardnum').max = 1;
    }
}
function addSlashes(){
    var expireDate = document.getElementById('validtill').value;
    if(expireDate.length == 2){
        document.getElementById('validtill').value = expireDate+" ";
        document.getElementById('validtill').max = 1;
    }
}


function viewOrder(){
	
	const urlParams = new URLSearchParams(window.location.search);
	
	// Get the value of the 'result' query parameter
	const result = urlParams.get('result');
		
	const encodedResultText = encodeURIComponent(result);
  	window.location.href = `viewOrder.html?result=${encodedResultText}`;


}