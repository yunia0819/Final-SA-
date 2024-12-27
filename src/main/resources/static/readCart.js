let cartData = []; // 存儲購物車數據

document.addEventListener("DOMContentLoaded", () => {
    fetchCartData();
});

// 從後端讀取購物車數據
function fetchCartData() {
    fetch("/api/cart/all")
        .then((response) => {
            if (!response.ok) {
                throw new Error("無法獲取購物車數據");
            }
            return response.json();
        })
        .then((data) => {
            cartData = data; // 更新全局購物車數據
            renderCart(cartData); // 重新渲染購物車
        })
        .catch((error) => {
            console.error("讀取購物車數據失敗:", error);
        });
}

// 渲染購物車內容
function renderCart(cartData) {
    const cartContainer = document.getElementById("cart-insert");
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
              <div class="cart-image">
                <img src="${item.imgurl}" alt="${item.furnitureName}">
              </div>
              <div class="cart-name">${item.furnitureName}</div>
              <div class="cart-price">價格: $${item.price.toFixed(2)}</div>
              <div class="cart-quantity">數量: ${item.quantity}</div>
              <div class="cart-cancel">
                <button class="cancel-button" data-name="${item.furnitureName}">取消</button>
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

// 刪除購物車商品
function removeCartItem(furnitureName) {
    fetch("/api/cart/remove", {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify({ name: furnitureName }), // 傳遞要移除的商品名稱
    })
        .then((response) => {
            if (!response.ok) {
                throw new Error("無法移除購物車商品");
            }
            return response.json();
        })
        .then(() => {
            fetchCartData(); // 更新購物車數據
        })
        .catch((error) => {
            console.error("移除購物車商品失敗:", error);
        });
}
