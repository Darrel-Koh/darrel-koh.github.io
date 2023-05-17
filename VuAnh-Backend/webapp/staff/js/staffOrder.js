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
				
        		var fdDiv = $('<div class="product-box"></div>');
                var img = $('<img src="' + fd.img + '" alt="" class="product-img">');
                var title = $('<h2 class="product-title">' + fd.name + '</h3>');
                var price = $('<span class="price"> $' + fd.price + '</span>');
                var buyNow = $('<a href="staffBuy2.html" class="btn">Buy Now</a>');

                fdDiv.append(img); 
                fdDiv.append(title); 
                fdDiv.append(price); 
                fdDiv.append(buyNow); 
                
                foodDrinkContainer.append(fdDiv); 
			
			});
        },
        error: function (jqXHR, exception) {
           console.log('Error occured!!');
        }
     });
  }