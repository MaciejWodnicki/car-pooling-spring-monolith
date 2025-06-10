<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Registration Successful - RideShareX</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
  <style>
    body {
      background-color: #f8f9fa;
    }
    .hero {
      background-color: #0d6efd;
      color: white;
      padding: 100px 0;
      text-align: center;
    }
    .hero h1 {
      font-size: 3rem;
      font-weight: bold;
    }
    .hero p {
      font-size: 1.25rem;
    }
    .features {
      padding: 60px 0;
    }
    .feature-icon {
      font-size: 2rem;
      margin-bottom: 10px;
      color: #0d6efd;
    }
    .navbar-nav {
      margin-left: auto;
    }
    .button-group {
      display: flex;
      gap: 10px;
    }
    .success-card {
      max-width: 600px;
      margin: 50px auto;
      padding: 30px;
      border-radius: 10px;
      box-shadow: 0 4px 20px rgba(0, 0, 0, 0.1);
      text-align: center;
      background: white;
    }
    .success-icon {
      font-size: 4rem;
      color: #28a745;
      margin-bottom: 20px;
    }
  </style>
</head>
<body>

<nav class="navbar navbar-expand-lg navbar-light bg-white shadow-sm px-4">
  <div class="container-fluid">
    <a class="navbar-brand" href="#">
      <img src="<c:url value='/resources/images/logo.png'/>" width="100" alt="RideShare" class="d-inline-block align-text-top">
    </a>
    <div class="navbar-nav">
      <a href="<c:url value='/login.jsp'/>" class="btn btn-primary">Login</a>
    </div>
  </div>
</nav>

<!-- Success Message Section -->
<section>
  <div class="container">
    <div class="success-card">
      <div class="success-icon">✓</div>
      <h2>Registration Successful!</h2>
      <p class="lead">Thank you for joining RideShareX.</p>

      <div class="alert alert-info mt-4">
        <p>We've sent a confirmation email to <strong>${email}</strong>.</p>
        <p>Please check your inbox and click the activation link to complete your registration.</p>
      </div>

      <div class="d-flex justify-content-center gap-3 mt-4">
        <a href="<c:url value='/login.jsp'/>" class="btn btn-primary">Go to Login</a>
        <a href="<c:url value='/'/>" class="btn btn-outline-secondary">Back to Home</a>
      </div>

      <div class="mt-4 text-muted">
        <p>Didn't receive the email? <a href="#" class="text-decoration-none">Resend confirmation</a></p>
        <p>Check your spam folder if you can't find it in your inbox.</p>
      </div>
    </div>
  </div>
</section>

<!-- Features Section -->
<section class="features text-center">
  <div class="container">
    <h2 class="mb-5">Why Ride with Us?</h2>
    <div class="row g-4">
      <div class="col-md-4">
        <div class="feature-icon">&#128663;</div>
        <h5>Easy to Use</h5>
        <p>Simple interface to quickly find and join carpools.</p>
      </div>
      <div class="col-md-4">
        <div class="feature-icon">&#127757;</div>
        <h5>Eco-Friendly</h5>
        <p>Reduce your carbon footprint by sharing rides.</p>
      </div>
      <div class="col-md-4">
        <div class="feature-icon">&#128176;</div>
        <h5>Save Money</h5>
        <p>Split the cost of gas and tolls with your co-riders.</p>
      </div>
    </div>
  </div>
</section>

<!-- Footer -->
<footer class="text-center py-4 bg-light">
  <p class="mb-0">&copy; 2025 RideShareX. All rights reserved.</p>
</footer>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>