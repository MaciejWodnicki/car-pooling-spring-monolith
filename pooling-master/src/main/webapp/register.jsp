<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Register - RideShareX</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.5/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://www.google.com/recaptcha/api.js"></script>
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
<div class="auth-container">
    <div class="auth-logo">
        <img src="<c:url value='/resources/images/logo.png'/>" alt="RideShareX">
    </div>

    <h2 class="auth-title">Create Your Account</h2>

    <c:if test="${not empty error}">
        <div class="alert alert-danger">${error}</div>
    </c:if>
    <c:if test="${not empty msg}">
        <div class="alert alert-success">${msg}</div>
    </c:if>

    <form:form method="post" action="addAppUser" modelAttribute="appUser">
        <div class="row g-3">
            <div class="col-md-6">
                <div class="form-floating mb-3">
                    <input type="text" class="form-control" id="firstName" name="firstName" required>
                    <label for="firstName">First Name</label>
                    <form:errors path="firstName" cssClass="text-danger" />

                </div>
            </div>
            <div class="col-md-6">
                <div class="form-floating mb-3">
                    <input type="text" class="form-control" id="lastName" name="lastName" required>
                    <label for="lastName">Last Name</label>
                    <form:errors path="lastName" cssClass="text-danger" />

                </div>
            </div>
        </div>

        <div class="form-floating mb-3">
            <input type="email" class="form-control" id="email" name="email" required>
            <label for="email">Email Address</label>
            <form:errors path="email" cssClass="text-danger" />

        </div>

        <div class="form-floating mb-3">
            <input type="text" class="form-control" id="username" name="login" required>
            <label for="username">Username</label>

        </div>

        <div class="form-floating mb-3">
            <input type="text" class="form-control" id="telephone" name="telephone" required>
            <label for="telephone">Phone Number</label>
            <form:errors path="telephone" cssClass="text-danger" />
        </div>

        <div class="form-floating mb-3">
            <input type="text" class="form-control" id="pesel" name="pesel.PESEL" required>
            <label for="pesel">PESEL</label>
            <form:errors path="pesel.PESEL" cssClass="text-danger" />
        </div>

        <div class="form-floating mb-3">
            <input type="password" class="form-control" id="password" name="password" required>
            <label for="password">Password</label>
        </div>

        <div class="form-floating mb-3">
            <input type="password" class="form-control" id="confirmPassword" required>
            <label for="confirmPassword">Confirm Password</label>
        </div>

        <h5 class="mb-3 mt-4">Address Information</h5>

        <div class="form-floating mb-3">
            <input type="text" class="form-control" id="street" name="address.street" required>
            <label for="street">Street</label>
            <form:errors path="address.street" cssClass="text-danger" />
        </div>

        <div class="form-floating mb-3">
            <input type="text" class="form-control" id="city" name="address.city" required>
            <label for="city">City</label>
            <form:errors path="address.city" cssClass="text-danger" />

        </div>

        <div class="form-floating mb-3">
            <input type="text" class="form-control" id="state" name="address.state" required>
            <label for="state">State</label>
            <form:errors path="address.state" cssClass="text-danger" />

        </div>

        <div class="form-floating mb-3">
            <input type="text" class="form-control" id="zip" name="address.zip" title="ZIP code must be 6 digits">
            <label for="zip">ZIP Code</label>
            <form:errors path="address.zip" cssClass="text-danger" />
        </div>

        <div class="form-floating mb-3">
            <input type="text" class="form-control" id="country" name="address.country" required>
            <label for="country">Country</label>
            <form:errors path="address.country" cssClass="text-danger" />
        </div>

        <input type="hidden" name="enabled" value="true" />

        <div class="mb-3 form-check">
            <input type="checkbox" class="form-check-input" id="terms" required>
            <label class="form-check-label" for="terms">I agree to the <a href="#">Terms of Service</a> and <a href="#">Privacy Policy</a></label>
        </div>

        <div class="g-recaptcha mb-3" data-sitekey="6Lc0QFwrAAAAANh0Tl05etUi_t2VxgdmF5aV2CoE"></div>

        <button type="submit" class="btn btn-primary w-100 py-2">Create Account</button>

        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
    </form:form>

    <div class="auth-footer">
        <p>Already have an account? <a href="<c:url value='/login'/>">Sign in here</a></p>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.5/dist/js/bootstrap.bundle.min.js"></script>
<script>
    // Client-side password confirmation
    document.querySelector('form').addEventListener('submit', function (e) {
        const password = document.getElementById('password').value;
        const confirmPassword = document.getElementById('confirmPassword').value;

        if (password !== confirmPassword) {
            e.preventDefault();
            alert('Passwords do not match!');
        }
    });
</script>
</body>
</html>
