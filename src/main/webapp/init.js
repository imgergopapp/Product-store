function onLoad() {
    loginContentDivEl = document.getElementById('login-content');
    registerContentDivEl = document.getElementById('register-content');
    logoutContentDivEl = document.getElementById('logout-content');

    const loginButtonEl = document.getElementById('login-button');
    loginButtonEl.addEventListener('click', onLoginButtonClicked);

    const registerButtonEl = document.getElementById('register-button');
    registerButtonEl.addEventListener('click', onRegisterButtonClicked);

    const registerAccountButtonEl = document.getElementById('registration-button');
    registerAccountButtonEl.addEventListener('click', onRegisterAccountButtonClicked)

    const backToLoginButtonEl = document.getElementById('back-to-login-button');
    backToLoginButtonEl.addEventListener('click', backToLogin);
}

document.addEventListener('DOMContentLoaded', onLoad);