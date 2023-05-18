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

function toggleValueField(checkbox) {
  var quantityField = checkbox.parentNode.querySelector("input[type='number']");
  quantityField.disabled = !checkbox.checked;
}

var namesString; 
var quantitiesString; 

function checkOut(){
	
	var names = $('[name="FoodDrink"]:checked').map(function() {
	  return this.value;
	}).get();
	

	var quantities = $('[name="quantity"]:not(:disabled)').map(function() {
    return this.value;
	}).get();
	
	
	namesString = JSON.stringify(names);
	quantitiesString = JSON.stringify(quantities);



     
     $.ajax({
        url: 'checkOut', // Sending to Ajax Handler 
        method: 'POST',	  // Using POST Method 
        data: {
           names: namesString,
           quantities: quantitiesString,
        },
        success: function (resultText) {
  			const encodedResultText = encodeURIComponent(resultText);
  			window.location.href = `makePayment2.html?result=${encodedResultText}`;
        },
        error: function (jqXHR, exception) {
           console.log('Error occured!!');
        }
     });
     
     
}



function listItem() {

  	
     $.ajax({
        url: 'SystemListOrder', // Sending to Ajax Handler 
        method: 'POST',	  // Using POST Method 
        data: {
			
        },
        success: function (result) {
           var foodDrink = JSON.parse(result); 
           var foodDrinkContainer =$(`#food-list`);
           
           foodDrink.forEach(function(fd){
				
				var div = $('<div class="inputBox"></div)');
				var inputDiv = $('<div class="input"></div>'); 
				var inputDiv2 = $('<div class="input"></div>'); 

        		var checkBox = $('<input type="checkbox" name="FoodDrink" value="'+fd.name+'" onclick="toggleValueField(this)">');
                var label = $('<label for="FoodDrink">'+fd.name+'</label>');
                var quantity = $('<input type="number" name="quantity" min="1" max="10" value="1" disabled>');

				div.append(inputDiv); 
				inputDiv.append(label); 
				

				div.append(checkBox); 
				div.append(quantity); 
				
				
				foodDrinkContainer.append(div); 
                
			
			});
        },
        error: function (jqXHR, exception) {
           console.log('Error occured!!');
        }
     });
  }