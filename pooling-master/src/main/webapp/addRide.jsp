<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Create New Ride - RideShareX</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.5/dist/css/bootstrap.min.css" rel="stylesheet">
  <style>
    body {
      background-color: #f8f9fa;
      padding: 2rem;
    }
    .ride-form-card {
      background-color: white;
      border: 1px solid #dee2e6;
      border-radius: 0.75rem;
      padding: 1.5rem;
      margin-bottom: 1.5rem;
      box-shadow: 0 2px 6px rgba(0,0,0,0.05);
      max-width: 600px;
      margin: 0 auto;
    }
    .form-header {
      font-size: 1.25rem;
      font-weight: bold;
      color: #0d6efd;
      margin-bottom: 1.5rem;
      text-align: center;
    }
    .form-label {
      font-weight: 500;
    }
    .submit-btn {
      width: 100%;
      margin-top: 1rem;
    }
  </style>
</head>
<body>

<div class="container">
  <h1 class="mb-4 text-primary">Create New Ride</h1>

  <div class="ride-form-card">
    <div class="form-header">Enter Ride Details</div>

    <form action="<c:url value='/availableRides'/>" method="POST">
      <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
      <div class="mb-3">
        <label for="driverName" class="form-label">Your Name</label>
        <input type="text" class="form-control" id="driverName" name="driverName" required>
      </div>

      <div class="mb-3">
        <label for="origin" class="form-label">Origin</label>
        <input type="text" class="form-control" id="origin" name="origin" required>
      </div>

      <div class="mb-3">
        <label for="destination" class="form-label">Destination</label>
        <input type="text" class="form-control" id="destination" name="destination" required>
      </div>

      <div class="mb-3">
        <label for="departureTime" class="form-label">Departure Date & Time</label>
        <input type="datetime-local" class="form-control" id="departureTime" name="departureTime" required>
      </div>

      <div class="mb-3">
        <label for="availableSpots" class="form-label">Available Spots</label>
        <input type="number" class="form-control" id="availableSpots" name="availableSpots" min="1" required>
      </div>

      <button type="submit" class="btn btn-primary submit-btn">Create Ride</button>
    </form>
  </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.5/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>