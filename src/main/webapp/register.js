function onRegisterResponse() {
    if (this.status === OK) {
        backToLogin();
    } else {
         onOtherResponse(registerContentDivEl,this);
    }
}

function backToLogin() {
    clearMessages();
    showContents(['login-content']);
}

function onRegisterButtonClicked(){
    showContents(['register-content']);
}

function onRegisterAccountButtonClicked() {
    const loginFormEl = document.forms['register-form'];
    
    const nameInputEl = loginFormEl.querySelector('input[name="name"]');
    const emailInputEl = loginFormEl.querySelector('input[name="email"]');
    const passwordInputEl = loginFormEl.querySelector('input[name="password"]');
 
    const name = nameInputEl.value;
    const email = emailInputEl.value;
    const password = passwordInputEl.value;

    const params = new URLSearchParams();
    params.append('name', name);
    params.append('email', email);
    params.append('password', password);

    const xhr = new XMLHttpRequest();
    xhr.addEventListener('load', onRegisterResponse);
    xhr.addEventListener('error', onNetworkError);
    xhr.open('POST', 'register');
    xhr.send(params);
}
