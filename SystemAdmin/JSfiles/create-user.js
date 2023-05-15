function validateForm(event) {
    event.preventDefault(); // Prevent default form submission behavior
    var nameInput = document.getElementById("name");
    var roleInput = document.getElementById("role");
    var emailInput = document.getElementById("email");

    if (nameInput.checkValidity() && roleInput.checkValidity() && emailInput.checkValidity()) {
      // Validation successful, navigate to the system admin dashboard page
      window.location.href = "../SystemAdmin/create-confirmation.html";
      return true;
    } else {
      // Validation failed, display error messages or handle as desired
      console.log("Form validation failed. Please fill in all required fields.");
      return false;
    }
  }