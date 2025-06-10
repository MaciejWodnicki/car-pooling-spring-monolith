<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Register - RideShareX</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.5/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #f8f9fa;
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
        }
        .auth-container {
            background: white;
            padding: 2.5rem;
            border-radius: 8px;
            box-shadow: 0 0 15px rgba(0,0,0,0.1);
            width: 100%;
            max-width: 600px;
        }
        .auth-logo {
            text-align: center;
            margin-bottom: 2rem;
        }
        .auth-logo img {
            width: 150px;
        }
        .auth-title {
            color: #0d6efd;
            font-weight: bold;
            margin-bottom: 1.5rem;
            text-align: center;
        }
        .form-control:focus {
            border-color: #0d6efd;
            box-shadow: 0 0 0 0.25rem rgba(13, 110, 253, 0.25);
        }
        .btn-primary {
            background-color: #0d6efd;
            border-color: #0d6efd;
        }
        .auth-footer {
            text-align: center;
            margin-top: 1.5rem;
            padding-top: 1.5rem;
            border-top: 1px solid #eee;
        }
        .password-requirements {
            font-size: 0.9rem;
            color: #6c757d;
            margin-top: -0.5rem;
            margin-bottom: 1rem;
        }
    </style>
</head>
<body>

<div class="container mt-5">
    <h1 class="text-primary">Ride Details</h1>

    <div class="card mt-4">
        <div class="card-body">
            <h5 class="card-title">${ride.origin} → ${ride.destination}</h5>
            <p class="card-text"><strong>Driver:</strong> ${ride.driverName}</p>
            <p class="card-text"><strong>Departure:</strong> ${ride.departureTime}</p>
            <p class="card-text"><strong>Available Spots:</strong> ${ride.availableSpots}</p>
        </div>
    </div>

    <a href="<c:url value='/availableRides'/>" class="btn btn-secondary mt-3">Back to Rides</a>
</div>

</body>
</html>
