// confirmRent.js
document.addEventListener('DOMContentLoaded', () => {
    // 1. 解析 URL => 取得日期
    const params = new URLSearchParams(window.location.search);
    const rdate = params.get("date") || ""; // 例如 "2024-12-15~2025-06-15"
    console.log("接收到的參數:", { rdate });
  
    // 2. 計算「租期天數」
    let rentalDays = 1;
    if (rdate) {
      rentalDays = getDaysBetween(rdate);
    }
    console.log("計算出來的租期天數:", rentalDays);
  
    // 3. 抓取 DOM
    const checkoutButton = document.querySelector('.checkout-button');
    const modal = document.getElementById('rent-modal');
    const modalCloseButton = document.getElementById('rent-modal-close-button');
    const modalTotalPrice = document.getElementById('rent-modal-total-price');
    const totalPriceElement = document.getElementById('total-price');
  
    // 初始化隱藏模態
    modal.classList.add('hidden');
  
    // 4. 從後端取得購物車資料 (內含 quantity, price)
    fetchCartData();
  
    // 5. 「確定租借」 => 顯示模態
    checkoutButton.addEventListener('click', () => {
      modalTotalPrice.textContent = totalPriceElement.textContent;
      modal.classList.remove('hidden');
      modal.classList.add('show');
    });
  
    // 6. Modal「完成」 => 提交訂單
    // 當使用者點擊「完成」按鈕 => 送出訂單
modalCloseButton.addEventListener('click', () => {
    const orderData = getOrderData(); // ① 收集畫面中的所有商品資訊
  
    // ② 以 JSON 格式 POST 到後端
    fetch("/api/rentals/add", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(orderData),  
      // orderData 內容形如：
      // {
      //   items: [
      //     { furnitureName: "...", quantity: 1, date: "...", totalPrice: 100 },
      //     { furnitureName: "...", quantity: 2, date: "...", totalPrice: 300 }
      //   ]
      // }
    })
      .then((response) => {
        if (!response.ok) {
          throw new Error("無法建立訂單");
        }
        return response.text();
      })
      .then((data) => {
        console.log("訂單成功提交:", data);
        // 提交後導回首頁，或可改其他頁面
        window.location.href = "Index.html";
      })
      .catch((error) => {
        console.error("提交訂單失敗:", error);
      });
  });
  
  
    // 點擊 Modal 外部 => 隱藏
    window.addEventListener('click', (event) => {
      if (event.target === modal) {
        modal.classList.remove('show');
        modal.classList.add('hidden');
      }
    });
  
    /**
     * ============== 以下是主要邏輯 ==============
     */
  
    // 從後端抓取購物車資料
    function fetchCartData() {
      fetch("/api/cart/all")
        .then((response) => {
          if (!response.ok) {
            throw new Error("無法獲取購物車資料。");
          }
          return response.json();
        })
        .then((cartData) => {
          renderCart(cartData);
        })
        .catch((error) => {
          console.error("獲取購物車資料失敗:", error);
        });
    }
  
    // 將後端的 cartData 渲染到前端
    function renderCart(cartData) {
      const cartItemsContainer = document.querySelector('#bye');
      cartItemsContainer.innerHTML = ''; // 清空
  
      cartData.forEach((item) => {
        // item { id, furnitureName, price, quantity, imgurl, ... }
        if (!item.furnitureName || !item.price || !item.quantity) {
          console.warn("商品資料不完整，跳過：", item);
          return;
        }
  
        const dailyPrice = parseFloat(item.price);
        const itemQuantity = parseInt(item.quantity, 10); 
        // 【重點】：直接使用後端給的 quantity
  
        // 計算：金額 = price × quantity × 租期天數
        const totalItemPrice = dailyPrice * itemQuantity * rentalDays;
  
        // 動態插入畫面
        const cartItemHTML = `
          <div class="cart-item" data-id="${item.id}">
            <div class="cart-image">
              <img src="${item.imgurl}" alt="商品圖片">
            </div>
            <div class="cart-name">${item.furnitureName}</div>
            <div class="list-date">${rdate || "未指定日期"}</div>
            <div class="list-price">$${totalItemPrice.toFixed(2)}</div>
            <div class="cart-quantity">${itemQuantity}</div>
            <div class="cart-cancel">
              <button class="cancel-button">取消</button>
            </div>
          </div>
        `;
        cartItemsContainer.insertAdjacentHTML('beforeend', cartItemHTML);
      });
  
      // 計算整筆訂單的總金額
      updateTotalPrice(cartData);
    }
  
    // 累加所有商品的金額
    function updateTotalPrice(cartData) {
      let total = 0;
      cartData.forEach((item) => {
        if (!item.price || !item.quantity) return;
        const dailyPrice = parseFloat(item.price);
        const itemQuantity = parseInt(item.quantity, 10);
        total += dailyPrice * itemQuantity * rentalDays;
      });
      totalPriceElement.textContent = total.toFixed(2);
    }
  
    // 收集畫面上訂單資料 -> POST 給後端
    function getOrderData() {
        // 找到所有 cart-item
        const items = document.querySelectorAll(".cart-item");
        const cartItems = [];

        
      
        items.forEach((el) => {
          const nameElement = el.querySelector(".cart-name");
          const priceElement = el.querySelector(".list-price");
          const quantityElement = el.querySelector(".cart-quantity");
          const dateElement = el.querySelector(".list-date");

          const name = nameElement?.textContent.trim() || "";
            if (!name) {
            // 代表是標頭列或其他非商品行，不要 push
            return;
            }
      
          // 取得每一欄對應的文字或數字
          const price = parseFloat(priceElement?.textContent.replace("$", "") || "0");
          const quantity = parseInt(quantityElement?.textContent.trim() || "0", 10);
          const date = dateElement?.textContent.trim() || "";
      
          cartItems.push({
            furnitureName: name,
            quantity: quantity,
            totalPrice: price,
            date: date,
          });
        });
      
        console.log("生成的訂單數據:", cartItems);
        // 回傳 { items: [...] } 結構
        return { items: cartItems };
      }
      
    // 工具函式：計算「起迄日」的天數
    function getDaysBetween(dateRangeStr) {
      if (!dateRangeStr.includes("~")) return 1;
      const [start, end] = dateRangeStr.split("~");
      const startDate = new Date(start.trim());
      const endDate = new Date(end.trim());
      if (isNaN(startDate) || isNaN(endDate) || endDate < startDate) {
        return 1;
      }
      const diff = endDate - startDate;
      // +1 代表包含起迄日
      return Math.ceil(diff / (1000 * 60 * 60 * 24)) + 1;
    }
  });

  