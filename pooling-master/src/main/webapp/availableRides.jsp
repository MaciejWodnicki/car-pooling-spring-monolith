<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Available Rides - RideShareX</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.5/dist/css/bootstrap.min.css" rel="stylesheet">
  <style>
    body {
      background-color: #f8f9fa;
      padding: 2rem;
    }
    .ride-card {
      background-color: white;
      border: 1px solid #dee2e6;
      border-radius: 0.75rem;
      padding: 1.5rem;
      margin-bottom: 1.5rem;
      box-shadow: 0 2px 6px rgba(0,0,0,0.05);
    }
    .ride-header {
      font-size: 1.25rem;
      font-weight: bold;
      color: #0d6efd;
    }
    .ride-detail {
      margin-bottom: 0.5rem;
    }
    .ride-spots {
      font-weight: bold;
      color: #198754;
    }
  </style>
</head>
<body>

<div class="container">
  <h1 class="mb-4 text-primary">Available Rides</h1>

  <c:if test="${empty ridesList}">
    <div class="alert alert-info">No rides are currently available.</div>
  </c:if>

  <c:forEach items="${ridesList}" var="ride">
    <div class="ride-card">
      <div class="ride-header">
          ${ride.origin} → ${ride.destination}
      </div>
      <div class="ride-detail">
        <strong>Driver:</strong> ${ride.driverName}
      </div>
      <div class="ride-detail">
        <strong>Departure:</strong> ${ride.departureTime}
      </div>
      <div class="ride-detail ride-spots">
        Available Spots: ${ride.availableSpots}
      </div>
      <div class="mt-3">
        <a href="rideDetails?id=${ride.id}" class="btn btn-outline-primary btn-sm">View Details</a>
        <a href="bookRide?id=${ride.id}" class="btn btn-success btn-sm">Book Ride</a>
      </div>
    </div>
  </c:forEach>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.5/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
