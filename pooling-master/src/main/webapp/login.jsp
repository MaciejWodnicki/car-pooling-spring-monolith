<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Login - RideShareX</title>
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
      max-width: 450px;
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
    .error-message {
      color: #dc3545;
      margin-bottom: 1rem;
    }
    .success-message {
      color: #198754;
      margin-bottom: 1rem;
    }
  </style>
</head>
<body>
<div class="auth-container">
  <div class="auth-logo">
    <img src="<c:url value='/resources/images/logo.png'/>" alt="RideShareX">
  </div>

  <h2 class="auth-title">Login to RideShareX</h2>

  <c:if test="${not empty error}">
    <div class="alert alert-danger error-message">${error}</div>
  </c:if>

  <c:if test="${not empty msg}">
    <div class="alert alert-success success-message">${msg}</div>
  </c:if>

  <form name="loginForm" action="<c:url value='/login'/>" method="POST">
    <div class="mb-3">
      <label for="login" class="form-label">Username</label>
      <input type="text" class="form-control" id="login" name="login" required autofocus>
    </div>

    <div class="mb-3">
      <label for="password" class="form-label">Password</label>
      <input type="password" class="form-control" id="password" name="password" required>
    </div>

    <div class="mb-3 form-check">
      <input type="checkbox" class="form-check-input" id="rememberMe" name="remember-me">
      <label class="form-check-label" for="rememberMe">Remember me</label>
    </div>

    <button type="submit" class="btn btn-primary w-100 py-2 mb-3">Login</button>

    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
  </form>

  <div class="auth-footer">
    <p class="mb-2">Don't have an account? <a href="<c:url value='/register.jsp'/>">Register here</a></p>
    <p><a>Forgot your password?</a></p>
  </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.5/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>