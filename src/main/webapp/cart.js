function onCartClicked() {
    const xhr = new XMLHttpRequest();

    xhr.addEventListener('load', onCartResponse);
    xhr.addEventListener('error', onNetworkError);
    xhr.open('GET', 'protected/cart');
    xhr.send();
}

function onCartResponse() {
    if (this.status === OK) {
        clearMessages();
        showContents(['navigation-bar', 'cart-content']);
        showCart(JSON.parse(this.responseText));
    } else {
        onOtherResponse(cartContentDivEl, this);
    }
}

function showCart(cartDto) {
    cartTableEl = document.getElementById('cart-table');
    cartTableBodyEl = cartTableEl.querySelector('tbody');
    const cartItems = cartDto.cartItems;
    const totalPrice = cartDto.totalPrice;

    removeAllChildren(cartTableBodyEl);
    document.getElementById("order-button").disabled = true;
    let message = 'The cart is empty !';
    if (cartItems.length > 0) {
        for (let i = 0; i < cartItems.length; i++) {
            let cartItem = cartItems[i];
            trEl = document.createElement('tr');

            tdNameEl = document.createElement('td');
            tdNameEl.textContent = cartItem.productName;

            tdQantityEl = document.createElement('td');
            tdQantityEl.textContent = cartItem.quantity;

            tdPriceEl = document.createElement('td');
            tdPriceEl.textContent = cartItem.price;

            trEl.appendChild(tdNameEl);
            trEl.appendChild(tdQantityEl);
            trEl.appendChild(tdPriceEl);

            cartTableBodyEl.appendChild(trEl);
        }
        message = 'Total price is ' + totalPrice;
        document.getElementById("order-button").disabled = false;
    }
    newInfo(cartContentDivEl, message);
}
