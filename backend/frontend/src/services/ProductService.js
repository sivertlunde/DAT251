function jsonProductsToDropdownList(products) {
    let productOptions = [];
    products.forEach(product => {
        console.log(product)
        let productOption = {};
        productOption.label = product['name']+"\tkr "+product['storePrice']['kolonialno'];
        productOption.value = product['name'];
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
                    if (products) {
                        resolve(jsonProductsToDropdownList(products));
                    }
                })
        )
    }

    // getProducts(input) {
    //     new Promise(resolve =>
    //         this.getProductsFetcher(input)
    //             .then(products => {
    //                 let productOptions = [];
    //                 products.forEach(product => {
    //                     let productOption = {};
    //                     productOption.label = product['name'];
    //                     productOption.value = product['name'];
    //                     productOptions.push(productOption);
    //                 });
    //                 resolve(productOptions)
    //             })
    //     )
    // }
}

export default new ProductService();