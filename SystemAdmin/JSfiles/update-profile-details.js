 // Function to fetch user details based on the userId
 function fetchUserDetails(userId) {
    // Replace this code with your actual logic to fetch the user details
    var users = [
        { id: 1, role: "System Admin" },
        { id: 2, role: "Manager" },
        { id: 3, role: "Staff" },
        { id: 4, role: "Customer" },
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
    document.getElementById('role').value = user.role;
}

