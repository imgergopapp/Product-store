function onLoad() {
    loginContentDivEl = document.getElementById('login-content');
    registerContentDivEl = document.getElementById('register-content');
    logoutContentDivEl = document.getElementById('logout-content');
    welcomeContentDivEl = document.getElementById('welcome-content');
    profileContentDivEl = document.getElementById('profile-content');
    productsContentDivEl = document.getElementById('products-content');
    productPageContentDivEl = document.getElementById('product-page-content');
    cartContentDivEl = document.getElementById('cart-content');

    // Login
    const loginButtonEl = document.getElementById('login-button');
    loginButtonEl.addEventListener('click', onLoginButtonClicked);

    // Registration
    const registerButtonEl = document.getElementById('register-button');
    registerButtonEl.addEventListener('click', onRegisterButtonClicked);

    const registerAccountButtonEl = document.getElementById('registration-button');
    registerAccountButtonEl.addEventListener('click', onRegisterAccountButtonClicked)

    const backToLoginButtonEl = document.getElementById('back-to-login-button');
    backToLoginButtonEl.addEventListener('click', backToLogin);

    // Profile
    const cancelProfileEditButtonEl = document.getElementById('cancel-profile-edit-button');
    cancelProfileEditButtonEl.addEventListener('click', onCancelProfileEditClicked);

    const onProfileUpdateButtonEl = document.getElementById('update-profile-button');
    onProfileUpdateButtonEl.addEventListener('click', onProfileUpdateClicked);

    //Cart
    const orderButtonEl = document.getElementById('order-button');
    orderButtonEl.addEventListener('click', onOrderClicked);
}

document.addEventListener('DOMContentLoaded', onLoad);