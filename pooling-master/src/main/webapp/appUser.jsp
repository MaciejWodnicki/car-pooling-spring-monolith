<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
    <title>All Users - RideShareX</title>
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
        .role-badge {
            font-size: 0.8rem;
            margin-right: 0.3rem;
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
        <h1 class="text-primary">All Users</h1>
        <div class="button-group">
            <a href="<c:url value='/availableRides'/>" class="btn btn-outline-primary">Available Rides</a>
        </div>
    </div>

    <c:if test="${empty appUserList}">
        <div class="alert alert-info">No users found.</div>
    </c:if>

    <c:forEach items="${appUserList}" var="user">
        <div class="user-card">
            <div class="user-header">
                    ${user.firstName} ${user.lastName}
                <c:if test="${user.enabled}">
                    <span class="badge bg-success">Active</span>
                </c:if>
                <c:if test="${not user.enabled}">
                    <span class="badge bg-secondary">Inactive</span>
                </c:if>
            </div>
            <div class="user-detail">
                <strong>Email:</strong> ${user.email}
            </div>
            <div class="user-detail">
                <strong>Phone:</strong> ${user.telephone}
            </div>
            <div class="user-detail">
                <strong>Login:</strong> ${user.login}
            </div>
            <div class="user-detail">
                <strong>Roles:</strong>
                <c:forEach items="${user.appUserRole}" var="role">
                    <span class="badge bg-info text-dark role-badge">${role.role}</span>
                </c:forEach>
            </div>
            <div class="mt-3">
                <a href="<c:url value='/users/${user.id}'/>" class="btn btn-outline-primary btn-sm">View</a>
                <sec:authorize access="hasAnyRole('ADMIN', 'MANAGER')">
                    <a href="<c:url value='/users/${user.id}/edit'/>" class="btn btn-outline-secondary btn-sm">Edit</a>
                </sec:authorize>
                <sec:authorize access="hasAnyRole('ADMIN', 'MANAGER')">
                    <c:choose>
                        <c:when test="${user.enabled}">
                            <a href="<c:url value='/users/${user.id}/disable'/>" class="btn btn-warning btn-sm">Disable</a>
                        </c:when>
                        <c:otherwise>
                            <a href="<c:url value='/users/${user.id}/enable'/>" class="btn btn-success btn-sm">Enable</a>
                        </c:otherwise>
                    </c:choose>
                    <form action="/appUsers/${user.id}" method="POST" class="btn btn-danger btn-sm" style="display: inline;" onclick="return confirm('Are you sure you want to delete this user?')">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                        <input type="hidden" name="_method" value="DELETE"/>
                        <button type="submit" class="btn btn-danger btn-sm">Delete user</button>
                    </form>
                </sec:authorize>
            </div>
        </div>
    </c:forEach>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.5/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>