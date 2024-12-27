document.addEventListener("DOMContentLoaded", function () {
    // 初始化購物車數據
    fetchCartData();

    // 綁定購物車懸停事件
    document.querySelector(".cart").addEventListener("mouseenter", () => {
        const cartPopup = document.querySelector(".cart-popup");
        cartPopup.style.display = "block"; // 顯示購物車內容
        renderCart(cartData); // 渲染購物車內容
    });

    document.querySelector(".cart").addEventListener("mouseleave", () => {
        const cartPopup = document.querySelector(".cart-popup");
        cartPopup.style.display = "none"; // 隱藏購物車內容
    });
});

let cartData = []; // 全局購物車數據

function fetchCartData() {
    fetch("/api/cart/all")
        .then((response) => {
            if (!response.ok) {
                throw new Error("無法獲取購物車數據");
            }
            return response.json();
        })
        .then((data) => {
            console.log("購物車數據:", data);
            cartData = data; // 更新全局購物車數據
            renderCart(cartData); // 渲染購物車
        })
        .catch((error) => {
           // console.error("讀取購物車數據失敗:", error);
            renderCart([]); // 確保在讀取失敗時顯示空購物車
        });
}

function renderCart(cartData) {
    const cartContainer = document.getElementById("cart-container");
    const checkoutButton = document.querySelector(".check-button");

    cartContainer.innerHTML = ""; // 清空舊內容

    if (!cartData || cartData.length === 0) {
        cartContainer.innerHTML = '<p>購物車是空的。</p>';
        if (checkoutButton) {
            checkoutButton.style.display = "none"; // 隱藏按鈕
        }
        return;
    }

    if (checkoutButton) {
        checkoutButton.style.display = "block"; // 顯示按鈕
    }

    cartData.forEach((item) => {
        const cartItem = document.createElement("div");
        cartItem.classList.add("cart-item");
        cartItem.innerHTML = `
            <div class="cart-item-image-container">
                <img src="${item.imgurl}" alt="${item.furnitureName}" class="cart-item-image">
            </div>
            <div class="cart-item-details">
                <h3 class="cart-name">${item.furnitureName}</h3>
                <p class="cart-price">價格: $${item.price.toFixed(2)}</p>
                <div class="cart-cancel">
                    <button class="cancel-button" data-name="${item.furnitureName}">取消</button>
                </div>
            </div>
        `;
        cartContainer.appendChild(cartItem);
    });

    // 動態綁定取消按鈕事件
    document.querySelectorAll(".cancel-button").forEach((button) => {
        button.addEventListener("click", (event) => {
            const furnitureName = event.target.getAttribute("data-name");
            removeCartItem(furnitureName);
        });
    });
}

function removeCartItem(furnitureName) {
    fetch(`/api/cart/remove`, {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify({ name: furnitureName }), // 傳遞商品名稱
    })
        .then((response) => {
            if (!response.ok) {
                throw new Error("刪除商品失敗");
            }
            return response.json();
        })
        .then((data) => {
            console.log("刪除成功:", data.message); // 打印後端返回的成功消息
            fetchCartData(); // 刷新購物車數據
        })
        .catch((error) => {
            console.error("刪除商品錯誤:", error);
        });
}

window.fetchCartData = fetchCartData;
window.renderCart = renderCart;