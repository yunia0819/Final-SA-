//for Index.html
document.addEventListener("DOMContentLoaded", function () {
    fetchProducts();
    const category = document.querySelectorAll('.category');
    console.log(category);

    category.forEach(item => {
        item.addEventListener('click', fetchCategories);
    });

    // 以種類取得
    function fetchCategories(event) {
        const clickedId = event.target.textContent; // 獲取被點擊的 <li>
        console.log(clickedId);
        fetch(`/api/products/category/${clickedId}`) // 發送 GET 請求到後端 API
            .then(response => response.json())
            .then(data => {
                const productGrid = document.getElementById('product-grid');
                productGrid.innerHTML = '';

                if (data.length === 0) {
                    productGrid.innerHTML = '<p>No products found in this category.</p>';
                    return;
                }

                data.forEach(product => {
                    console.log(product);
                    const productItem = `
                    <div class="product-item">
                        <a href="Info.html?id=${product.productId}">
                            <img src="${product.imageLink}" alt="${product.name}">
                            <div class="product-info">
                                <h3 class="product-name">${product.name}</h3>
                                <p class="product-price">$${product.pricePerDay}</p>
                                <p class="product-rate">★ ${product.ranks}</p>
                            </div>
                        </a>
                    </div>
                `;
                    productGrid.insertAdjacentHTML('beforeend', productItem);
                });
            })
            .catch(error => console.error('Error fetching products:', error));
    }
});

// 預設進入網頁時
function fetchProducts() {
    const categories = ['沙發床類', '辦公桌類', '床架及床邊收納', '其他']; // 定義四個類別
    const query = categories.map(c => `categories=${encodeURIComponent(c)}`).join('&');

    fetch(`/api/products/categories?${query}`)  // 發送 GET 請求到後端 API
        .then(response => response.json()) // 解析後端返回的 JSON 資料
        .then(data => {
            const productGrid = document.getElementById('product-grid');
            productGrid.innerHTML = ''; // 清空現有內容

            if (data.length === 0) {
                productGrid.innerHTML = '<p>No products found.</p>';
                return;
            }

            data.forEach(product => {
                const productItem = `
                    <div class="product-item">
                        <a href="Info.html?id=${product.productId}">
                            <img src="${product.imageLink}" alt="${product.name}">
                            <div class="product-info">
                                <h3 class="product-name">${product.name}</h3>
                                <p class="product-price">$${product.pricePerDay}</p>
                                <p class="product-rate">★ ${product.ranks}</p>
                            </div>
                        </a>
                    </div>
                `;
                productGrid.insertAdjacentHTML('beforeend', productItem);
            });
        })
        .catch(error => console.error('Error fetching products:', error));
}