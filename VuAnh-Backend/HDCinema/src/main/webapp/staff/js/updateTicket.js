
// Get passed value on URL
function getUrlParameter(parameterName) {
  var urlParams = new URLSearchParams(window.location.search);
  return urlParams.get(parameterName);
}

var idValue = getUrlParameter('result');




function updateTicket(){
	var cinemaRoom = document.getElementById("s_roomNum").value;
	var seat = document.getElementById("s_seat").value;
	var date = document.getElementById("s_date").value;
	var time = document.getElementById("s_time").value;
	
	var encodedOrderId = encodeURIComponent(idValue);

     
     $.ajax({
        url: 'SystemUpdateTicket', // Sending to Ajax Handler 
        method: 'POST',	  // Using POST Method 
        data: {
           idValue: idValue,
           cinemaRoom: cinemaRoom,
           seat: seat,
           date: date, 
           time: time
        },
        success: function (resultText) {
           console.log(resultText); 
           window.location.href = `staffTicket.html?result=${encodedOrderId}`;

        },
        error: function (jqXHR, exception) {
           console.log('Error occured!!');
        }
     });
	
}