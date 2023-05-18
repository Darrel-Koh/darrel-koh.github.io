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

const container = document.querySelector(".container");
const seats = document.querySelectorAll(".row .seat:not(.sold)");
const count = document.getElementById("count");
const total = document.getElementById("total");
const movieSelect = document.getElementById("movie");

populateUI();

let ticketPrice = +movieSelect.value;





// Get data from localstorage and populate UI
function populateUI() {
  const selectedSeats = JSON.parse(localStorage.getItem("selectedSeats"));

  if (selectedSeats !== null && selectedSeats.length > 0) {
    seats.forEach((seat, index) => {
      if (selectedSeats.indexOf(index) > -1) {
        console.log(seat.classList.add("selected"));
      }
    });
  }

  const selectedMovieIndex = localStorage.getItem("selectedMovieIndex");

  if (selectedMovieIndex !== null) {
    movieSelect.selectedIndex = selectedMovieIndex;
    console.log(selectedMovieIndex)
  }
}
console.log(populateUI())
// Movie select event
movieSelect.addEventListener("change", (e) => {
  ticketPrice = +e.target.value;
  setMovieData(e.target.selectedIndex, e.target.value);
  updateSelectedCount();
});

// Seat click event
container.addEventListener("click", (e) => {
  if (
    e.target.classList.contains("seat") &&
    !e.target.classList.contains("sold")
  ) {
    e.target.classList.toggle("selected");

    updateSelectedCount();
  }
});


function continueBooking() {
  const selectedSeat = document.querySelectorAll(".row .seat.selected");
  const selectedSeatIds = Array.from(selectedSeat).map(seat => seat.id);
  const payload = { selectedSeat: selectedSeatIds };
  console.log("Selected Seat IDs:", selectedSeatIds);

  fetch("https://jsonplaceholder.typicode.com/posts", {
    method: "POST",
    headers: {
      "Content-Type": "application/json"
    },
    body: JSON.stringify(payload)
  })
    .then(response => {
      if (response.ok) {
        // Seat booking successful, redirect to another page
        // window.location.href = "makePaymentCust.html$" + selectedSeatIds;
        window.location.href = "makePaymentCust.html?" + selectedSeatIds.join("&")
        console.log("Selected Seat IDs:", selectedSeatIds);
      } else {
        // Seat booking failed, handle the error
      }
    })
    .catch(error => {
      // Handle any errors
      console.log("Error occurred:", error);
    });
}

function getUrlParameter(parameterName) {
  var urlParams = new URLSearchParams(window.location.search);
  return urlParams.get(parameterName);
}



function displayPrice(){
	
	var idValue = getUrlParameter('idvalue');
	console.log("idValue:", idValue);
	
	var selectedSeats = $('.seat.selected');
	
	 
	var price = getUrlParameter('price'); 
	
	var totalPrice = price * (selectedSeats.length - 1); 
	
	document.getElementById('count').textContent = (selectedSeats.length - 1) ;
	document.getElementById('total').textContent = totalPrice ;

		
	
	 
  	
  	
	
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
				        $(this).toggleClass('selected');
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



