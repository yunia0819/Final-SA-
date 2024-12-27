// cartDate.js
document.addEventListener("DOMContentLoaded", function () {
    // 取得按鈕和 Modal 的元素
    const openModalButton = document.getElementById("open-modal");
    const closeModalButton = document.getElementById("close-modal");
    const confirmButton = document.getElementById("confirm-date");
    const cancelButton = document.getElementById("cancel-date");
    const dateModal = document.getElementById("date-modal");
    const startDatePicker = document.getElementById("start-date");
    const endDatePicker = document.getElementById("end-date");
    const selectedDateInfo = document.getElementById("selected-date");
  
    let dateRange = "";  // 只紀錄日期範圍
  
    // 打開 Modal
    openModalButton.addEventListener("click", () => {
      dateModal.style.display = "block";
    });
  
    // 關閉 Modal
    closeModalButton.addEventListener("click", () => {
      dateModal.style.display = "none";
    });
    cancelButton.addEventListener("click", () => {
      dateModal.style.display = "none";
    });
  
    // 即時更新選擇的日期
    startDatePicker.addEventListener("input", updateSelectedDate);
    endDatePicker.addEventListener("input", updateSelectedDate);
  
    function updateSelectedDate() {
      const startDate = startDatePicker.value;
      const endDate = endDatePicker.value;
      if (startDate && endDate) {
        if (endDate <= startDate) {
          selectedDateInfo.textContent = "結束日期必須比開始日期晚";
          endDatePicker.setCustomValidity("結束日期必須比開始日期晚");
        } else {
          dateRange = `${startDate}~${endDate}`;
          selectedDateInfo.textContent = `租借日期：${dateRange}`;
          endDatePicker.setCustomValidity("");
        }
      } else {
        selectedDateInfo.textContent = "尚未選擇完整日期";
      }
    }
  
    // 點擊「確認日期」 => 帶參數到 List.html
    confirmButton.addEventListener("click", () => {
      const startDate = startDatePicker.value;
      const endDate = endDatePicker.value;
      if (!startDate || !endDate || endDate <= startDate) {
        selectedDateInfo.textContent = "請選擇正確的開始與結束日期";
        return;
      }
      // 只帶 date 參數，示例: List.html?date=2024-12-15~2025-06-15
      const queryString = `?date=${encodeURIComponent(dateRange)}`;
      window.location.href = `List.html${queryString}`;
      dateModal.style.display = "none";
    });
  
    // 點擊視窗外部區域 => 關閉 Modal
    window.addEventListener("click", (event) => {
      if (event.target === dateModal) {
        dateModal.style.display = "none";
      }
    });
  });
  