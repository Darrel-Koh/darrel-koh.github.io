function myFunction() {
    // Declare variables
    var input, filter, table, tr, td, i, txtValue;
    input = document.getElementById("myInput");
    filter = input.value.toUpperCase();
    table = document.getElementById("myTable");
    tr = table.getElementsByTagName("tr");
  
    // Loop through all table rows, and hide those who don't match the search query
    for (i = 0; i < tr.length; i++) {
      td = tr[i].getElementsByTagName("td")[0];
      if (td) {
        txtValue = td.textContent || td.innerText;
        if (txtValue.toUpperCase().indexOf(filter) > -1) {
          tr[i].style.display = "";
        } else {
          tr[i].style.display = "none";
        }
      }
    }
  }

// Example data of user objects
var users = [
    { id: 1, role: "System Admin" },
    { id: 2, role: "Manager" },
    { id: 3, role: "Staff" },
    { id: 4, role: "Customer" },
    // Add more user objects as needed
  ];
  
  // Function to generate the table rows dynamically
  function generateTable() {
    var tableBody = document.getElementById("tableBody");
    tableBody.innerHTML = ""; // Clear previous table rows
  
    users.forEach(function (user) {
      var row = document.createElement("tr");
  
      // Create a hidden input field for user ID
      var userIdInput = document.createElement("input");
      userIdInput.type = "hidden";
      userIdInput.value = user.id;
  
      // Create the role cell with hyperlink
      var roleCell = document.createElement("td");
      var roleLink = document.createElement("a");
      roleLink.href = "./view-profile-details.html?userId=" + user.id; // Modify the href value with the appropriate link destination
      roleLink.textContent = user.role; // Set the text content of the link
  
      roleCell.appendChild(roleLink);
      row.appendChild(userIdInput);
      row.appendChild(roleCell);
      tableBody.appendChild(row);
    });
  }
  
  generateTable(); // Call the function to generate the table initially


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