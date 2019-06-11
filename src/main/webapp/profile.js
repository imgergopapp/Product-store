function onProfileClicked() {
    clearMessages();
    showContents(['navigation-bar', 'profile-content']);
    const user = JSON.parse(localStorage.getItem('user'));

    const profileFormEl = document.getElementById('profile-form');

    const nameInputEl = profileFormEl.querySelector('input[name="name"]');
    nameInputEl.value = user.name;

    const emailInputEl = profileFormEl.querySelector('input[name="email"]');
    emailInputEl.value= user.email;

    const countryInputEl = profileFormEl.querySelector('input[name="country"]');
    countryInputEl.value = user.address.country;

    const zipCodeInputEl = profileFormEl.querySelector('input[name="zipCode"]');
    zipCodeInputEl.value = user.address.zip_code;

    const cityInputEl = profileFormEl.querySelector('input[name="city"]');
    cityInputEl.value = user.address.city;

    const streetInputEl = profileFormEl.querySelector('input[name="street"]');
    streetInputEl.value = user.address.street;
}

function onCancelProfileEditClicked(){
    onProfileClicked();
    const profileElementDiv = document.getElementById('profile-content');
    newMessage(profileElementDiv,'info','Removed unsaved changes');
}

function onProfileUpdateClicked(){
    const profileFormEl = document.getElementById('profile-form');

    const nameInputEl = loginFormEl.querySelector('input[name="name"]');
    const emailInputEl = loginFormEl.querySelector('input[name="email"]');
    const countryInputEl = loginFormEl.querySelector('input[name="country"]');
    const zipCodeInputEl = loginFormEl.querySelector('input[name="zipCode"]');
    const cityInputEl = loginFormEl.querySelector('input[name="city"]');
    const streetInputEl = loginFormEl.querySelector('input[name="street"]');

    const params = new URLSearchParams();
    params.append('name', nameInputEl.value);
    params.append('email', emailInputEl.value);
    params.append('country', countryInputEl.value);
    params.append('zipCode', zipCodeInputEl.value);
    params.append('city', cityInputEl.value);
    params.append('street', streetInputEl.value);

    const xhr = new XMLHttpRequest();
    xhr.addEventListener('load', onLoginResponse);
    xhr.addEventListener('error', onNetworkError);
    xhr.open('PUT', 'protected/profile');
    xhr.send(params);
}
