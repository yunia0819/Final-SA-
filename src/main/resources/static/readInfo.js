// for Info.html
// 當頁面加載時發送請求
let furnitureId;


document.addEventListener("DOMContentLoaded", () => {
  // 獲取商品 ID
  console.log('reading Info');
  furnitureId = getQueryParameter("id");
  console.log("商品 ID:", furnitureId);

  fetchFurnitureData();

  // 綁定加入購物車按鈕
  document.getElementById("addToCart").addEventListener("click", addToCart);
});

// 解析 URL 查詢參數
function getQueryParameter(name) {
  const params = new URLSearchParams(window.location.search);
  return params.get(name);
}

// 發送請求獲取家具數據
function fetchFurnitureData() {
  fetch(`/api/products/${furnitureId}`)
    .then((response) => {
      if (!response.ok) {
        throw new Error("Failed to fetch furniture data");
      }
      return response.json();
    })
    .then((data) => {
      populateFurnitureSection(data);
    })
    .catch((error) => {
      console.error("Error fetching furniture data:", error);
    });
}

// 填充家具數據到頁面
function populateFurnitureSection(data) {
  // 更新家具名稱
  document.querySelector(".furniture-name").textContent = data.name;

  // 更新主圖片
  const mainImage = document.getElementById("mainImage");
  mainImage.src = data.imageLink;

  // 更新價格和評分
  document.querySelector(".furniture-price").textContent = '$' + data.pricePerDay + ' / 天';
  document.querySelector(".furniture-rate").textContent = '★ ' + data.ranks;

  // 更新描述
  document.querySelector(".furniture-info p").textContent = data.description;

  // 更新縮圖區域
  const thumbnailSection = document.querySelector(".thumbnail-section");
  thumbnailSection.innerHTML = ""; // 清空縮圖
  data.thumbnails.forEach((thumbnail) => {
    const thumbnailDiv = document.createElement("div");
    thumbnailDiv.classList.add("thumbnail");
    thumbnailDiv.setAttribute("data-image", thumbnail);

    const img = document.createElement("img");
    img.src = thumbnail;

    thumbnailDiv.appendChild(img);
    thumbnailSection.appendChild(thumbnailDiv);
  });

  // 為縮圖添加點擊事件
  document.querySelectorAll(".thumbnail").forEach((thumbnail) => {
    thumbnail.addEventListener("click", (event) => {
      const imageUrl = thumbnail.getAttribute("data-image");
      mainImage.src = imageUrl; // 更新主圖片
    });
  });
}

// 加入購物車功能
function addToCart() {
  const furnitureId = getQueryParameter("id");

  fetch("/api/cart/add", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify({ id: furnitureId }),
  })
      .then((response) => {
        if (!response.ok) {
          throw new Error("無法加入購物車");
        }
        return response.json();
      })
      .then((data) => {
        alert(data.message || "加入購物車成功！");
        fetchCartData(); // 加入後更新購物車顯示
      })
      .catch((error) => {
        alert("加入購物車失敗");
        console.error("加入購物車失敗:", error);
      });
}