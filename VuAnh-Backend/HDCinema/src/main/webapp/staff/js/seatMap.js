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


var idValue = getUrlParameter('idvalue');
var moviePrice = getUrlParameter('price'); 
var movieDate = getUrlParameter('date');
var guestName = getUrlParameter('guestName');
var roomNumber = getUrlParameter('roomNumber');
var movieTime = getUrlParameter('time');
var movieName = getUrlParameter('title');



function continueBooking(){
	
	var totalPrice = displayPrice(); 
	var selectedElements = document.getElementsByClassName('selected');
	var selectedSeat = [];
	
	for (var i = 0; i < selectedElements.length; i++) {
		selectedSeat.push(selectedElements[i].id);
	}
	
	selectedSeat.shift();
	
	var selectedSeatJson = JSON.stringify(selectedSeat);
	

     
     $.ajax({
        url: 'SystemCreateTicket', // Sending to Ajax Handler 
        method: 'POST',	  // Using POST Method 
        data: {
			movieTitle: movieName,
			date: movieDate,
			time: movieTime, 
			movieId: idValue,
			totalPrice: totalPrice,
			seat: selectedSeatJson,
			cinemaRoom: roomNumber
        },
        success: function (resultText) {
          const encodedResultText = encodeURIComponent(resultText);
  			window.location.href = `makePayment.html?result=${encodedResultText}`;
        },
        error: function (jqXHR, exception) {
           console.log('Error occured!!');
        }
     });
	
}



// Get passed value on URL
function getUrlParameter(parameterName) {
  var urlParams = new URLSearchParams(window.location.search);
  return urlParams.get(parameterName);
}



function displayPrice(){
	
	console.log("idValue:", idValue);
	
	var selectedSeats = $('.seat.selected');
	
	var totalPrice = moviePrice * (selectedSeats.length - 1); 
	
	document.getElementById('count').textContent = (selectedSeats.length - 1) ;
	document.getElementById('total').textContent = totalPrice ;
	
	return totalPrice; 
 	
}

function toggleSelectedClass(element) {
	// Only allow select the chair that is not selected 
    if (!element.hasClass('sold')) {
        element.toggleClass('selected');
    }
}

function showMovieName(){
		
	var movieName = getUrlParameter('title');
	document.getElementById('movie-name').textContext = movieName; 
	console.log("SHOW MOVIE NAME")
}


function showSeat(){
	
	$.ajax({
		url: 'SystemShowSeat',
		method: 'POST',
		success: function(result) {
			console.log(result); 
			
			var seatArr = JSON.parse(result);
			var seatContainer = $('#seat-container');
			
			
			var div = $('<div></div>');
			var status = "seat";
			var id = 0;
			
			// Access each value in the 2D array
			for (var i = 0; i < seatArr.length; i++) {
			    var row = $('<div class="row"></div>');
			    
			    for (var j = 0; j < seatArr[i].length; j++) {
			        var value = seatArr[i][j];
			        
			        if (value == 1) {
			            status = "seat sold";
			        } else {
			            status = "seat";
			        }
			        
			        // var seatDiv = $('<div>').addClass(status).attr('id', id++);
			        var seatDiv = $('<div>')
				    .addClass(status)
				    .attr('id', id++)
				    .click(function() {
				        toggleSelectedClass($(this));
				        displayPrice(); // Call the displayPrice() function
				    });
			        row.append(seatDiv);
			        
			        console.log(value);
			        // Perform any desired operations with the value
			    }
			    
			    div.append(row);
			}
			
			seatContainer.append(div);
			
			
		},
		error: function(jqXHR, exception) {
			console.log('Error occurred!!');
		}
	});
	
}



