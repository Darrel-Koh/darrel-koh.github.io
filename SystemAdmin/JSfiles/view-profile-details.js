
 // Function to fetch user details based on the userId
 function fetchUserDetails(userId) {
    // Replace this code with your actual logic to fetch the user details
    var users = [
        { id: 1, name: "Alfreds Futterkiste", role: "System Admin", email: "alfred@example.com" },
        { id: 2, name: "Berglunds snabbkop", role: "Manager", email: "berglunds@example.com" },
        { id: 3, name: "Jack neo", role: "Staff", email: "jack@example.com" },
        { id: 4, name: "James Hemsworth", role: "Customer", email: "handsome@example.com" },
        // Add more user objects as needed
    ];

    // Find the user with the matching userId
    var user = users.find(function (user) {
        return user.id == userId;
    });

    return user;
}

// Retrieve user details from URL parameters
const urlParams = new URLSearchParams(window.location.search);
const userId = urlParams.get('userId');

// Fetch user details based on the userId
const user = fetchUserDetails(userId);

// Populate the form-like view with the user details
if (user) {
    document.getElementById('name').value = user.name;
    document.getElementById('role').value = user.role;
    document.getElementById('email').value = user.email;
}


document.addEventListener("DOMContentLoaded", function () {
    var userForm = document.getElementById("userForm");
    var updateLink = document.getElementById("updateLink");

    // Retrieve the user details from the query parameters
    var urlParams = new URLSearchParams(window.location.search);
    var userId = urlParams.get("userId");
    var user = fetchUserDetails(userId); // Replace this with your own function to fetch user details

    if (user) {
      // Populate the form fields with the user details
      document.getElementById("name").value = user.name;
      document.getElementById("role").value = user.role;
      document.getElementById("email").value = user.email;

      // Update the "Update" button link with the user ID
      updateLink.href = "../SystemAdmin/update-user-details.html?userId=" + user.id;
    }
  }
)










// for buttons
    const updateButton = document.getElementById('updateButton');
  const suspendButton = document.getElementById('suspendButton');
  const nameInput = document.getElementById('name');
  const roleInput = document.getElementById('role');
  const emailInput = document.getElementById('email');

  // updateButton.addEventListener('click', function() {
  //   nameInput.readOnly = false;
  //   roleInput.readOnly = false;
  //   emailInput.readOnly = false;
  // });

  suspendButton.addEventListener('click', function() {
    const confirmation = confirm('Are you sure you want to suspend the user?');
    if (confirmation) {
      // Perform suspend action
      // You can add your own logic here
    }
  });



// for NavBar

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
// Initialize Swiper 
var swiper = new Swiper(".home-slider", {
  spaceBetween: 30,
  centeredSlides: true,
  autoplay: {
    delay: 7500,
    disableOnInteraction: false,
  },
  pagination: {
    el: ".swiper-pagination",
    clickable: true,
  },
  loop:true,
});