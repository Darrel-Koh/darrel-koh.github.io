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


// Function to get the value of a URL parameter by name
function getURLParameter(name) {
    const urlParams = new URLSearchParams(window.location.search);
    return urlParams.get(name);
}





const continueButton = document.getElementById('continueBtn');
  continueButton.addEventListener('click', function(event) {
    event.preventDefault();
    
	// This will be the id collected
	const idValue = getURLParameter('id');
	const title = getURLParameter('title'); 
	const price = getURLParameter('price');
	
    // Retrieve the input values
    const guestName = document.getElementById('s_guestName').value;
    const roomNumber = document.getElementById('s_roomNumber').value;
    const date = document.getElementById('s_date').value;
    const time = document.getElementById('s_time').value;

   // Build the query string with the input values
    const queryString = `?guestName=${guestName}&roomNumber=${roomNumber}&date=${date}&time=${time}&idvalue=${idValue}&title=${title}&price=${price}`;

    // Redirect to the next page with the query string
    window.location.href = `seatMap.html${queryString}`;
  });
