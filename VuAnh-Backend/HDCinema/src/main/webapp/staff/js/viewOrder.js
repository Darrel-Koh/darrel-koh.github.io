
// View order page just need to receive the id via URI -> will conduct search in database by itself 

function loadOrder(){
	const urlParams = new URLSearchParams(window.location.search);
	
	// Get the value of the 'result' query parameter
	const id = urlParams.get('result');
		
	console.log(id); 
	
	 $.ajax({
        url: 'viewOrder', // Sending to Ajax Handler 
        method: 'POST',	  // Using POST Method 
        data: {
           result:id
        },
        success: function (resultText) {
           $('#order-id').html(id);
           console.log(resultText); 
           
           var result = JSON.parse(resultText);
           
			var itemList = result.itemList;
			var itemListHTML = '';
			
			for (var i = 0; i < itemList.length; i++) {
		    var item = itemList[i];
		    var itemName = item.name;
		    var itemQuantity = item.quantity;
		    var itemPrice = item.price;
		
		    // Create the HTML for the item
		    var itemHTML = '<li>' +
		      '<span class="item-name">' + itemName + '</span>' +
		      '<span class="item-quantity"> - QTY: ' + itemQuantity + '</span>' +
		      '</li>';
		
		    itemListHTML += itemHTML;
		  }
		  
         $('#order_items').html(itemListHTML);
         $('#order-date').html(result.orderDate);
         $('#order-time').html(result.orderTime);
         $('#final-price').html(result.orderPrice);
         $('#order-status').html(result.status);


		  console.log(itemListHTML);


        },
        error: function (jqXHR, exception) {
           console.log('Error occured!!');
        }
     });
	
	
	
}