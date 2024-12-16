document.addEventListener("DOMContentLoaded", function () {
    // 獲取所有加入購物車的按鈕
    const buttons = document.querySelectorAll(".add-to-cart-button");

    buttons.forEach(button => {
        button.addEventListener("click", function (event) {
            event.preventDefault(); // 防止默認跳轉行為

            // 獲取商品 ID
            const productId = this.getAttribute("data-id");

            // 發送 POST 請求到後端
            fetch("/furniture/addToCart", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                }
            })
            .then(response => response.json())
            .then(data => {
                if (data.code === 200) {
                    alert("商品已成功加入購物車！");
                    loadCartItems(); // 新增：即時更新購物車內容
                } else {
                    alert("加入購物車失敗，請稍後重試！");
                }
            })
            .catch(error => {
                console.error("發生錯誤:", error);
                alert("加入購物車失敗，請稍後再試！");
            });
        });
    });

    const refButtons = document.querySelectorAll(".add-ref-furniture-button");

    refButtons.forEach(button => {
        button.addEventListener("click", function (event) {
            event.preventDefault(); // 防止默認跳轉行為

            // 獲取商品 ID
            const productId = this.getAttribute("data-id");

            // 發送 POST 請求到後端，生成測試家具
            fetch("/furniture/addTestFurniture", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                },
            })
                .then(response => response.json())
                .then(data => {
                    if (data.code === 200) {
                        alert("商品已成功加入購物車！！");
                        loadCartItems(); // 新增：即時更新購物車內容
                    } else {
                        alert("加入購物車失敗，請稍後重試！");
                    }
                })
                .catch(error => {
                    console.error("發生錯誤:", error);
                    alert("新增測試家具失敗，請稍後再試！");
                });
        });
    });
});

// 動態加載購物車內容
function loadCartItems() {
    fetch("/furniture/cart")
        .then(response => response.json())
        .then(data => {
            const cartContainer = document.querySelector(".all-cart-product");
            const totalItemsElement = document.querySelector(".cart-items span");
            const totalAmountElement = document.querySelector(".cart-totals span");
            const cartIconCount  = document.getElementById("cart-item-count");


            cartContainer.innerHTML = ""; // 清空現有內容

            let totalItems = 0;
            let totalAmount = 0;

            data.data.cartItems.forEach(item => {
                totalItems += item.quantity;
                totalAmount += item.price * item.quantity;

                const cartItem = document.createElement("div");
                cartItem.className = "single-cart clearfix";
                cartItem.innerHTML = `
                    <div class="cart-photo">
                        <a href="#"><img src="${item.imgurl}" alt="${item.furnitureName}"/></a>
                    </div>
                    <div class="cart-info">
                        <h5>${item.furnitureName}</h5>
                        <p>總額: $${item.price * item.quantity}</p>
                        <p>數量: ${item.quantity}</p>
                    </div>
                `;
                cartContainer.appendChild(cartItem);
            });

            // 更新總數量和總金額
            totalItemsElement.textContent = `${totalItems} 件產品`;
            totalAmountElement.textContent = `$${totalAmount}`;
            cartIconCount.textContent = totalItems; // 更新圖示上的數字
        })
        .catch(error => console.error("購物車加載失敗:", error));
}

// 加載購物車內容
document.addEventListener("DOMContentLoaded", loadCartItems);

