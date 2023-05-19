

// Get passed value on URL
function getUrlParameter(parameterName) {
  var urlParams = new URLSearchParams(window.location.search);
  return urlParams.get(parameterName);
}




function updateStatus(){
	
	var selectElement = document.getElementById("order-status");
	var orderStatus = selectElement.value;
	var orderId = getUrlParameter('result');
	var encodedOrderId = encodeURIComponent(orderId);
	console.log(orderId);

	$.ajax({
        url: 'SystemUpdateOrderStatus', // Sending to Ajax Handler 
        method: 'POST',	  // Using POST Method 
        data: {
           orderId: orderId,
           orderStatus: orderStatus
        },
        success: function (resultText) {
			window.location.href = `viewOrder.html?result=${encodedOrderId}`;
        },
        error: function (jqXHR, exception) {
           console.log('Error occured!!');
        }
     });
	
}