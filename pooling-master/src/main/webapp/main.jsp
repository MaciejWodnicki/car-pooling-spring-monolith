<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>RideShareX - Carpooling Made Simple</title>
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
    </style>
</head>
<body>

<nav class="navbar navbar-expand-lg navbar-light bg-white shadow-sm px-4">
    <div class="container-fluid">
        <a class="navbar-brand" href="#">
            <img src="<c:url value='/resources/images/logo.png'/>" width="100" alt="RideShareX" class="d-inline-block align-text-top">
        </a>
        <div class="navbar-nav">
            <c:choose>
                <c:when test="${pageContext.request.userPrincipal != null}">
                    <div class="button-group">
                        <sec:authorize access="hasAnyRole('MANAGER', 'ADMIN')">
                            <a href="<c:url value='/appUsers'/>" class="btn btn-outline-secondary me-2">Manage Users</a>
                        </sec:authorize>
                        <sec:authorize access="hasRole('ADMIN')">
                            <a href="<c:url value='/managerList'/>" class="btn btn-outline-dark me-2">Manage Managers</a>
                        </sec:authorize>
                        <form action="<c:url value='/logout'/>" method="post">
                            <button type="submit" class="btn btn-danger">Logout</button>
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                        </form>
                    </div>
                </c:when>
                <c:otherwise>
                    <a href="<c:url value='/login.jsp'/>" class="btn btn-primary">Login</a>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</nav>

<!-- Hero Section -->
<section class="hero">
    <div class="container">
        <h1>Welcome to RideShareX</h1>
        <p>Find nearby carpool buddies and make commuting smarter, greener, and cheaper.</p>
        <c:choose>
            <c:when test="${pageContext.request.userPrincipal != null}">
                <a href="<c:url value='/availableRides'/>" class="btn btn-light btn-lg mt-3">View Available Rides</a>
            </c:when>
            <c:otherwise>
                <a href="<c:url value='/login.jsp'/>" class="btn btn-light btn-lg mt-3">Get Started</a>
            </c:otherwise>
        </c:choose>
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