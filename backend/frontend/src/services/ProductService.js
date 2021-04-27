function jsonProductsToDropdownList(products) {
    let productOptions = [];
    products.forEach(product => {
        let productOption = {};
        productOption.label = product['name'];//+"\tkr "+product['storePrice']['kolonialno'];
        productOption.value = product['name'];
        product.qty = 1;
        productOption.product = product;
        productOptions.push(productOption);
    });
    return productOptions
}


class ProductService {

    getProducts(input) {
        return new Promise(resolve =>
            fetch('/api/products/' + input)
                .then(response => {
                    return response.json()
                })
                .then(products => {
                    try {
                        resolve(jsonProductsToDropdownList(products));
                    }
                    catch (e) {
                        resolve([]);
                    }
                })
        )
    }
}

export default new ProductService();