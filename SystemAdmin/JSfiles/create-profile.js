function validateForm(event) {
    event.preventDefault(); // Prevent default form submission behavior
    var roleInput = document.getElementById("role");


    if (roleInput.checkValidity()) {
      // Validation successful, navigate to the system admin dashboard page
      window.location.href = "../SystemAdmin/create-profile-confirmation.html";
      return true;
    } else {
      // Validation failed, display error messages or handle as desired
      console.log("Form validation failed. Please fill in all required fields.");
      return false;
    }
  }