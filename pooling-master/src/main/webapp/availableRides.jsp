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
    .header-container {
      display: flex;
      justify-content: space-between;
      align-items: center;
      margin-bottom: 1.5rem;
    }
    .button-group {
      display: flex;
      gap: 10px;
    }
  </style>
</head>
<body>

<div class="container">
  <div class="header-container">
    <h1 class="text-primary">Available Rides</h1>
    <div class="button-group">
      <a href="<c:url value='/addRide.jsp'/>" class="btn btn-primary">Create New Ride</a>
      <form action="<c:url value='/logout'/>" method="post">
        <button type="submit" class="btn btn-outline-danger">Logout</button>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
      </form>
    </div>
  </div>

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
        <strong>Driver Contact:</strong> ${ride.driverTelephone}
      </div>
      <div class="ride-detail">
        <strong>Departure:</strong> ${ride.departureTime}
      </div>
      <div class="ride-detail ride-spots">
        Available Spots: ${ride.remainingSpots}
      </div>
      <div class="mt-3">
        <a href="<c:url value='/availableRides/${ride.id}'/>" class="btn btn-outline-primary btn-sm">View Details</a>

        <!-- Only show Book button if user is not already a passenger -->
        <c:if test="${!ride.passengerList.contains(currentUser)}">
          <form action="/availableRides/${ride.id}/passengers" method="POST" style="display: inline;">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <button type="submit" class="btn btn-success btn-sm">Book Ride</button>
          </form>
        </c:if>

        <!-- Only show Unbook button if user is already a passenger -->
        <c:if test="${ride.passengerList.contains(currentUser)}">
          <form action="/availableRides/${ride.id}/passengers" method="POST" style="display: inline;">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <input type="hidden" name="_method" value="DELETE"/>
            <button type="submit" class="btn btn-danger btn-sm">Unbook Ride</button>
          </form>
        </c:if>
      </div>
    </div>
  </c:forEach>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.5/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>