<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Product store</title>

        <c:url value="/style.css" var="styleUrl"/>
        <c:url value="/init.js" var="initScriptUrl"/>
        <c:url value="/register.js" var="registerScriptUrl"/>
        <c:url value="/login.js" var="loginScriptUrl"/>
        <c:url value="/logout.js" var="logoutScriptUrl"/>
        <c:url value="/util.js" var="utilScriptUrl"/>
        <c:url value="/welcome.js" var="welcomeScriptUrl"/>
        <c:url value="/auth.js" var="authScriptUrl"/>
        <c:url value="/navigation.js" var="navigationScriptUrl"/>
        <c:url value="/profile.js" var="profileScriptUrl"/>
        <c:url value="/products.js" var="productsScriptUrl"/>

        <link rel="stylesheet" type="text/css" href="${styleUrl}">
        <script src="${registerScriptUrl}"></script>
        <script src="${loginScriptUrl}"></script>
        <script src="${logoutScriptUrl}"></script>
        <script src="${utilScriptUrl}"></script>
        <script src="${welcomeScriptUrl}"></script>
        <script src="${initScriptUrl}"></script>
        <script src="${navigationScriptUrl}"></script>
        <script src="${profileScriptUrl}"></script>
        <script src="${productsScriptUrl}"></script>
        <script src="${authScriptUrl}"></script>

    </head>
    <body>
        <div id="navigation-bar" class="hidden navbar content">
            <a href="javascript:void(0);" onclick="onWelcomeClicked();">Welcome</a>
            <a href="javascript:void(0);" onclick="onProfileClicked();">My profile</a>
            <a href="javascript:void(0);" onclick="onProductsClicked();">Products</a>
            <a href="javascript:void(0);" onclick="alert('cart');">Cart</a>
            <a href="javascript:void(0);" onclick="alert('orders');">My orders </a>
            <a href="javascript:void(0);" onclick="onLogoutButtonClicked();">Logout</a>
        </div>
        <div id="login-content" class="hidden content">
            <h1>Login</h1>
            <form id="login-form" onsubmit="return false;">
                <input type="text" name="email" placeholder="Email">
                <input type="password" name="password" placeholder="Password">
                <button class="normalBtn" id="login-button">Login</button>
                <button class="normalBtn" id="register-button">Register</button>
            </form>
        </div>
        <div id="register-content" class="hidden content">
            <h1>Register</h1>
            <form id="register-form" onsubmit="return false;">
                <input type="text" name="name" placeholder="Name"><br>
                <input type="text" name="email" placeholder="Email"><br>
                <input type="password" name="password" placeholder="Password"><br>
                <button id="registration-button">Register Account</button>
                <button id="back-to-login-button">Back to login</button><br>
            </form>
        </div>
        <div id="welcome-content" class="hidden content">
            <h1>welcome</h1>
            <h2>Products on sale : </h2>
        <script src="${authScriptUrl}"></script>
        </div>
        <div id = "profile-content" class="hidden content">
            <h2>My Profile</h2>
            <form id="profile-form" onsubmit="return false;">
                <p class="inline">Name </p> <input type="text" name="name" placeholder="Name"><br>
                <p class="inline">Email </p><input type="text" name="email" placeholder="Email" readonly><p class="inline"> (read only)</p><br>
                
                <h2>Address</h2>

                <p class="inline">Country </p><input type="text" name="country"><br>
                <p class="inline">Zip code </p><input type="text" name="zipCode"><br>
                <p class="inline">City </p><input type="text" name="city"><br>
                <p class="inline">Street </p><input type="text" name="street"><br>

                <button id="cancel-profile-edit-button">Cancel</button>
                <button id="update-profile-button">Update</button><br>
            </form>
        </div>
        <div id = "products-content" class="hidden content">
            <h2>Products</h2>
            <table border="1" id  = "products-table">
                <tr>
                    <td>
                        <img src="data:image/jpeg;base64,/9j/4AAQSkZJRgABAQAAAQABAAD/2wCEAAkGBw0NDQ0NDQ0NDQ0NDQ0NDQ0NDQ8IDQ0NFREWFhURFRMYHSggGBolGxMTITEhMSk3Li46Fx8zODM4NygtLisBCgoKDQ0NDw0NDysZFRkrKystLS03Ny03Ky0rKystNzcrKystKysrKysrKysrKysrKysrKysrKysrKysrKysrK//AABEIALUBFwMBIgACEQEDEQH/xAAYAAEBAQEBAAAAAAAAAAAAAAAAAQIHA//EABUQAQEAAAAAAAAAAAAAAAAAAAAB/8QAGQEBAQEBAQEAAAAAAAAAAAAAAAECBAYD/8QAFhEBAQEAAAAAAAAAAAAAAAAAAAER/9oADAMBAAIRAxEAPwDkCg9DjjAFFAXEFBUAABRRBQEFAEVAAEEFQURRMEATFEVEwAGaoioxYoAzYADGK0A7GBQVAUUABBUUAAAUBBQEFAQAEFQBFRFEUQQBFQVGaoAzRAGaoAwNKiupgVFaAAQUAAUABQAEAABUAAFQVEEFQBFRFQVEBFRmqAM1UAZqgDA0oOpgUFBUUQAAUFAAQFAAVREUQQVAEUFRFEERQVEVEBFGVQBlUFRiqAMjYK6WAFVAAFAUAUQAAUBABQABBQGRURRFRFQVAQBKqAMqgDNURRmqgDA9BFdL5ioqgqAKAqCooCoAoAgAoAAAAiKiKIqIoioCAJVQKM1UAZqgDNVAGBtUHSwoCooigogooAiiAKACiCooACAKICCAiKAgoiolAEZqgDNUEGKoAyNgOlhQFQABQFBUUAAQVFAAUAAEBARUFEBAQBUAZqiKiUAGaqAMVQBkbIiulhRBRQBFEAURVFEAUQEUEBQQFEBQEQKCCgIgAiKAMqAjNABiqCDKtgOlhRFVBUFFAAAAVARRAFEAUQBUAAEFARAARRAZABFEBmqIoxQAY1VVB06yoiqgAuiiCoogCiKaKIGiiAKIAAhoogmqAJoAiaACVQBm1RAYtABm1QUYtUAdMYFBYACgAqAAAooACAAAAIAigAADKiAgAJVAGRAGKqgMVQBgf//Z" height="250" width="250">
                    </td>
                    <td>
                        <img src="data:image/jpeg;base64,/9j/4AAQSkZJRgABAQAAAQABAAD/2wCEAAkGBw0NDQ0NDQ0NDQ0NDQ0NDQ0NDQ8IDQ0NFREWFhURFRMYHSggGBolGxMTITEhMSk3Li46Fx8zODM4NygtLisBCgoKDQ0NDw0NDysZFRkrKystLS03Ny03Ky0rKystNzcrKystKysrKysrKysrKysrKysrKysrKysrKysrKysrK//AABEIALUBFwMBIgACEQEDEQH/xAAYAAEBAQEBAAAAAAAAAAAAAAAAAQIHA//EABUQAQEAAAAAAAAAAAAAAAAAAAAB/8QAGQEBAQEBAQEAAAAAAAAAAAAAAAECBAYD/8QAFhEBAQEAAAAAAAAAAAAAAAAAAAER/9oADAMBAAIRAxEAPwDkCg9DjjAFFAXEFBUAABRRBQEFAEVAAEEFQURRMEATFEVEwAGaoioxYoAzYADGK0A7GBQVAUUABBUUAAAUBBQEFAQAEFQBFRFEUQQBFQVGaoAzRAGaoAwNKiupgVFaAAQUAAUABQAEAABUAAFQVEEFQBFRFQVEBFRmqAM1UAZqgDA0oOpgUFBUUQAAUFAAQFAAVREUQQVAEUFRFEERQVEVEBFGVQBlUFRiqAMjYK6WAFVAAFAUAUQAAUBABQABBQGRURRFRFQVAQBKqAMqgDNURRmqgDA9BFdL5ioqgqAKAqCooCoAoAgAoAAAAiKiKIqIoioCAJVQKM1UAZqgDNVAGBtUHSwoCooigogooAiiAKACiCooACAKICCAiKAgoiolAEZqgDNUEGKoAyNgOlhQFQABQFBUUAAQVFAAUAAEBARUFEBAQBUAZqiKiUAGaqAMVQBkbIiulhRBRQBFEAURVFEAUQEUEBQQFEBQEQKCCgIgAiKAMqAjNABiqCDKtgOlhRFVBUFFAAAAVARRAFEAUQBUAAEFARAARRAZABFEBmqIoxQAY1VVB06yoiqgAuiiCoogCiKaKIGiiAKIAAhoogmqAJoAiaACVQBm1RAYtABm1QUYtUAdMYFBYACgAqAAAooACAAAAIAigAADKiAgAJVAGRAGKqgMVQBgf//Z" height="250" width="250">
                    </td>
                </tr>
                <tr>
                    <td>
                        name
                    </td>
                    <td>
                        name
                    </td>
                </tr>
                <tr>
                    <td>
                        category1
                    </td>
                    <td>
                        category2
                    </td>
                </tr>
                <tr>
                    <td>
                        price1
                    </td>
                    <td>
                        price2
                    </td>
                </tr>
            </table>
        </div>
    </body>
</html>
