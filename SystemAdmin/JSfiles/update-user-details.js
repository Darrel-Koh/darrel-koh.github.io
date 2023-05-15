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