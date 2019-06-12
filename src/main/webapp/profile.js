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
    zipCodeInputEl.value = user.address.zipCode;

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

    const nameInputEl = profileFormEl.querySelector('input[name="name"]');
    const emailInputEl = profileFormEl.querySelector('input[name="email"]');
    const countryInputEl = profileFormEl.querySelector('input[name="country"]');
    const zipCodeInputEl = profileFormEl.querySelector('input[name="zipCode"]');
    const cityInputEl = profileFormEl.querySelector('input[name="city"]');
    const streetInputEl = profileFormEl.querySelector('input[name="street"]');
    

    const name = nameInputEl.value;
    const email = emailInputEl.value;
    const country = countryInputEl.value;
    const zipCode = zipCodeInputEl.value;
    const city = cityInputEl.value;
    const street = streetInputEl.value;


    const params = new URLSearchParams();
    params.append('name', name);
    params.append('email', email);
    params.append('country', country);
    params.append('zipCode', zipCode);
    params.append('city', city);
    params.append('street', street);

    const xhr = new XMLHttpRequest();
    xhr.addEventListener('load', onProfileUpdateResponse);
    xhr.addEventListener('error', onNetworkError);
    xhr.open('PUT', 'protected/profile');
    xhr.send(params);
}
