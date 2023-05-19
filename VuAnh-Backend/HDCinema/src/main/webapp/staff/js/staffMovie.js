

function viewTicket(){
	
}

// Load the list of movie
function getMovieList() {
			$.ajax({
				url: 'SystemListMovie',
				method: 'POST',
				success: function(result) {
					// Parse the JSON string into a JavaScript object
					var movies = JSON.parse(result);
	                var movieListContainer = $('#movie-list');

	                movies.forEach(function(movie) {
	                	var movieDiv = $('<div class="current-movie"></div>');
	                    var imgBox = $('<div class="cm-img-box"></div>');
	                    var img = $('<img src="' + movie.img + '" alt="">');
	                    var title = $('<h3 class="movie-title">' + movie.title + '</h3>');
	                    var category = $('<p>Categories: ' + movie.category + '</p>');
	                    var content = $('<div class="content"></div>');
	                    var stars = $('<div class="stars">Rating: <i class="fas fa-star">' + movie.rating + '</i></div>');
	                    var booking = $('<div class="booking"></div>');
	                    var price = $('<h2 class="price"> $' + movie.price + '</h2>');
						var buyTickets = $('<a href="staffBuy.html?id=' + movie.id + '&title=' + movie.title + '&price=' + movie.price + '" class="btn">Buy Tickets</a>');

						//var buyTickets = $('<a href="staffBuy.html?id=' + movie.id + '&title=' + movie.title + '" class="btn">Buy Tickets</a>');
	                    // buyTickets.on('click', buyTicket);

	                    
	                    imgBox.append(img);
	                    movieDiv.append(imgBox);
	                    movieDiv.append(title);
	                    movieDiv.append(category);
	                    movieDiv.append(content);
	                    content.append(stars);
	                    movieDiv.append(booking);
	                    booking.append(price);
	                    booking.append(buyTickets);
	                    
	                    movieListContainer.append(movieDiv);
	                    
	                });
					
				},
				error: function(jqXHR, exception) {
					console.log('Error occurred!!');
				}
			});
		}
		
