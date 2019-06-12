function onProductsClicked() {
    const params = new URLSearchParams();
    params.append('isOnSale', 'false');

    xhr.addEventListener('load', onProductsResponse);
    xhr.addEventListener('error', onNetworkError);
    xhr.open('GET', 'protected/allproducts?' + params.toString());
    xhr.send();
}

function onProductsResponse() {
    if (this.status === OK) {
        clearMessages();
        showContents(['navigation-bar', 'products-content']);
        showProducts(JSON.parse(this.responseText));
    } else {
        onOtherResponse(productsContentDivEl, this);
    }

}

function showProducts(products) {
    productsTableEl = document.getElementById('products-table');
    removeAllChildren(productsTableEl);

    for (let i = 0; i < products.length; i++) {
        let product = products[i];
        trEl = document.createElement('tr');

        imgTdEl = generateImgTdElement(product);

        tdDetailEl = generateDetailTdElement(product);

        trEl.appendChild(imgTdEl);
        trEl.appendChild(tdDetailEl);

        productsTableEl.appendChild(trEl);
    }
}

function generateImgTdElement(product) {
    tdImgEl = document.createElement('td');
    img = createImgElement(product.pictureUrl, 250, 250)
    img.onclick = function () { showProductPage(product) };
    tdImgEl.appendChild(img);
    return tdImgEl;
}



function generateDetailTdElement(product) {
    tdDetailEl = document.createElement('td');
    h3El = document.createElement('h3');
    h3El.innerHTML = product.productName;

    ulEl = document.createElement('ul');
    ulEl.appendChild(createLiElement(product.category));
    ulEl.appendChild(createLiElement('Price: ' + product.price));
    ulEl.appendChild(createLiElement('In stock :' + product.inStock));

    tdDetailEl.appendChild(h3El);
    tdDetailEl.appendChild(ulEl);
    return tdDetailEl;
}

function showProductPage(product) {
    showContents(['navigation-bar', 'product-page-content']);
    removeAllChildren(productPageContentDivEl);

    buyButtonEl = document.createElement('button');
    buyButtonEl.id = "buy-product-button";
    buyButtonEl.onclick = function () { onBuyProductButtonClicked(product.productId) };
    buyButtonEl.innerHTML = "Buy product!";

    //removeAllChildren(productsTableEl);
    h2El = document.createElement('h2');
    h2El.innerHTML = product.productName;

    imgEl = createImgElement(product.pictureUrl, 500, 500)

    ulEl = document.createElement('ul');
    ulEl.appendChild(imgEl);
    ulEl.appendChild(createLiElement(product.category));
    ulEl.appendChild(createLiElement(product.properties));
    ulEl.appendChild(createLiElement('Price: ' + product.price));
    ulEl.appendChild(createLiElement('In stock :' + product.inStock));
    ulEl.appendChild(createLiElement('On sale % :' + product.inStock));

    productPageContentDivEl.appendChild(h2El);
    productPageContentDivEl.append(ulEl);
    productPageContentDivEl.append(buyButtonEl);
}

function onBuyProductButtonClicked(id) {
    // TODO 
    alert(id);
}