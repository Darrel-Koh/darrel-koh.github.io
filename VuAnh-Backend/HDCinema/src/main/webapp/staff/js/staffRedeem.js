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
// starter js end

function listOffer(){
	 
     
     $.ajax({
        url: 'StaffRedeem', // Sending to Ajax Handler 
        method: 'POST',	  // Using POST Method 
        data: {
           
        },
        success: function (resultText) {
         	console.log(resultText);
         	
         	var jsonObj = JSON.parse(resultText); 
         	
            var movieListContainer = $('#reward-list');

            jsonObj.forEach(function(rw) {

         	
         	var mainDiv = $('<div class="product-box"></div>');
         	var img =$('<img src="' + rw.img +'" class="product-img">');
         	var title = $('<h2 class="product-title">'+rw.name + '</h2>');
         	var point = $('<span class="price">'+rw.point + '</span>');
			var button = $('<a href="#" class="btn" data-point="'+ rw.point +'" onclick="redeemReward(event)">'+ "Redeem Now" + '</a>');
         	
         	mainDiv.append(img); 
         	mainDiv.append(title); 
         	mainDiv.append(point); 
         	mainDiv.append(button); 
         	
         	movieListContainer.append(mainDiv); 
         	
         	});
         	
        },
        error: function (jqXHR, exception) {
           console.log('Error occured!!');
        }
     });
}

var inputElement = document.getElementById("order-id");
inputElement.addEventListener("input", function() {
  var enteredValue = inputElement.value;
  // Call your function here with the enteredValue
  checkBalance(enteredValue); 
});



function checkBalance(enteredValue){
     
     $.ajax({
        url: 'SystemCheckPoint', // Sending to Ajax Handler 
        method: 'POST',	  // Using POST Method 
        data: {
           custId: enteredValue
        },
        success: function (resultText) {
           console.log(resultText); 
          $('#total-point').html(resultText);

        },
        error: function (jqXHR, exception) {
           console.log('Error occured!!');
        }
     });
	
}

function popUp(){
	alert("Done"); 
}


function redeemReward(){
	
	// Retrieve the ID 
	var inputElement = document.getElementById("order-id");
	var enteredValue = inputElement.value;
	
	var button = event.target;
	
	// This is the point selected
	var point = button.dataset.point;
	//console.log("Point:", point);
	console.log("Placeholder content:", enteredValue);


	 $.ajax({
        url: 'SystemRedeemReward', // Sending to Ajax Handler 
        method: 'POST',	  // Using POST Method 
        data: {
           custId: enteredValue,
           rwPoint: point
        },
        success: function (resultText) {
           console.log(resultText); 
           popUp(); 

        },
        error: function (jqXHR, exception) {
           console.log('Error occured!!');
        }
     });
  
  
}