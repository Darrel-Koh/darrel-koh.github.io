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


function getUrlParameter(parameterName) {
  var urlParams = new URLSearchParams(window.location.search);
  return urlParams.get(parameterName);
}

var resultValue = getUrlParameter('result');
console.log(resultValue);


function viewTicket(){
	
     
     $.ajax({
        url: 'SystemViewTicket', // Sending to Ajax Handler 
        method: 'POST',	  // Using POST Method 
        data: {
			ticketId:resultValue
        },
        success: function (resultText) {
	          
          var jsonObject = JSON.parse(resultText);
		  console.log(jsonObject);
		  
          $('#movie-name').html(jsonObject.movieTitle);
          $('#ticket-id').html(jsonObject.ticketNumber);
          $('#date').html(jsonObject.date);
          $('#time').html(jsonObject.time);
          $('#cinema-room').html(jsonObject.cinemaRoom);


          var seatNumberString = jsonObject.seatNumber.join(", ");
          $('#seat').html(seatNumberString);
          
          $('#total-price').html(jsonObject.price);






          
          
        },
        error: function (jqXHR, exception) {
           console.log('Error occured!!');
        }
     });
     

}