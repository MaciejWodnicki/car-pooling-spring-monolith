<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Manager List - RideShareX</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.5/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #f8f9fa;
            padding: 2rem;
        }
        .user-card {
            background-color: white;
            border: 1px solid #dee2e6;
            border-radius: 0.75rem;
            padding: 1.5rem;
            margin-bottom: 1.5rem;
            box-shadow: 0 2px 6px rgba(0,0,0,0.05);
        }
        .user-header {
            font-size: 1.25rem;
            font-weight: bold;
            color: #0d6efd;
        }
        .user-detail {
            margin-bottom: 0.5rem;
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
        <h1 class="text-primary">Manager List</h1>
        <div class="button-group">
            <a href="<c:url value='/availableRides'/>" class="btn btn-outline-primary">Available Rides</a>
            <sec:authorize access="hasRole('ADMIN')">
                <a href="<c:url value='/appUsers'/>" class="btn btn-outline-secondary">All Users</a>
            </sec:authorize>
        </div>
    </div>

    <c:if test="${empty managerList}">
        <div class="alert alert-info">No managers found.</div>
    </c:if>

    <c:forEach items="${managerList}" var="manager">
        <div class="user-card">
            <div class="user-header">
                    ${manager.firstName} ${manager.lastName}
                <c:if test="${manager.enabled}">
                    <span class="badge bg-success">Active</span>
                </c:if>
                <c:if test="${not manager.enabled}">
                    <span class="badge bg-secondary">Inactive</span>
                </c:if>
            </div>
            <div class="user-detail">
                <strong>Email:</strong> ${manager.email}
            </div>
            <div class="user-detail">
                <strong>Phone:</strong> ${manager.telephone}
            </div>
            <div class="user-detail">
                <strong>Login:</strong> ${manager.login}
            </div>
            <div class="mt-3">
                <sec:authorize access="hasRole('ADMIN')">
                    <a href="<c:url value='/editUser/${manager.id}'/>" class="btn btn-outline-primary btn-sm">Edit</a>
                    <c:choose>
                        <c:when test="${manager.enabled}">
                            <a href="<c:url value='/toggleUserStatus/${manager.id}'/>" class="btn btn-warning btn-sm">Disable</a>
                        </c:when>
                        <c:otherwise>
                            <a href="<c:url value='/toggleUserStatus/${manager.id}'/>" class="btn btn-success btn-sm">Enable</a>
                        </c:otherwise>
                    </c:choose>
                </sec:authorize>
            </div>
        </div>
    </c:forEach>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.5/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>