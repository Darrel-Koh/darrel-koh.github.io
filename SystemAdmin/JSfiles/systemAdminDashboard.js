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
  { id: 1, name: "Alfreds Futterkiste", role: "System Admin", email: "alfred@example.com" },
  { id: 2, name: "Berglunds snabbkop", role: "Manager", email: "berglunds@example.com" },
  { id: 3, name: "Jack neo", role: "Staff", email: "jack@example.com" },
  { id: 4, name: "James Hemsworth", role: "Customer", email: "handsome@example.com" },
  // Add more user objects as needed
  ];

  // Function to generate the table rows dynamically
  function generateTable() {
  var tableBody = document.getElementById("tableBody");
  tableBody.innerHTML = ""; // Clear previous table rows

  users.forEach(function (user) {
      var row = document.createElement("tr");
      var nameCell = document.createElement("td");
      var nameLink = document.createElement("a");
      nameLink.href = "./view-user-details.html?userId=" + user.id; // Modify the href value with the appropriate link destination

      nameLink.textContent = user.name; // Set the text content of the link

      // Attach event listener to handle click event
      nameLink.addEventListener("click", function (event) {
      event.preventDefault(); // Prevent the default hyperlink behavior
      var userId = user.id; // Get the user ID from the user object
      // Call a function or perform any desired action with the user ID
      displayUserDetails(userId);
      });

      nameCell.appendChild(nameLink);
      row.appendChild(nameCell);
      row.innerHTML += `
      <td>${user.role}</td>
      <td>${user.email}</td>
      `;
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