const returnBook = []
//动态生成归还审批清单
{
    const booklist = document.querySelector("tbody")
    for (let i = 0; i < 100; i++) {
        returnBook[i] = document.createElement("tr")
        returnBook[i].className = "returnBook"
        booklist.appendChild(returnBook[i])
        returnBook[i].insertAdjacentHTML("beforeend",
            `
                <td class="return-name">小明${i + 1}</td>
                <td class="book-name">高等数学（第七版上册）</td>
                <td class="borrow-time">2023-1-1 12:00</td>
                <td class="return-time">2023-1-1 18:00</td>
                <td class="timeout">否</td>
                <td class="handle">
                    <button class="situation-good">良好</button>
                    <button class="situation-bad">差</button>
                </td>
                <td class="handle">
                    <button class="yes">是</button>
                    <button class="no">否</button>
                </td>
            `
        )
    }
}
{
    const returnName = document.querySelectorAll(".return-name")
    const bookName = document.querySelectorAll(".book-name")
    //书籍情况汇报
    const good = document.querySelectorAll(".situation-good")
    const bad = document.querySelectorAll(".situation-bad")
    for (let i = 0; i < returnBook.length; i++) {
        good[i].addEventListener("click", function (event) {
            if (confirm(`是否将“${returnName[i].textContent}”所归还《${bookName[i].textContent}》的书籍情况设为"“良好”?`)) {
                good[i].style.background = "#17234b"
                bad[i].style.background = "white"
            }
        })
        bad[i].addEventListener("click", function (event) {
            if (confirm(`是否将“${returnName[i].textContent}”所归还《${bookName[i].textContent}》的书籍情况设为“差”?`)) {
                bad[i].style.background = "#17234b"
                good[i].style.background = "white"
            }
        })
    }
    //归还审批
    const yes = document.querySelectorAll(".yes")
    const no = document.querySelectorAll(".no")
    for (let i = 0; i < returnBook.length; i++) {
        yes[i].addEventListener("click", function (event) {
            if ((good[i].style.background != "rgb(23, 35, 75)") && (bad[i].style.background != "rgb(23, 35, 75)")) {
                alert("尚未设置这本书的书籍情况")
            }
            else {
                if (confirm(`是否接受“${returnName[i].textContent}”的归还?`)) {
                    returnBook[i].remove()
                }
            }
        })
        no[i].addEventListener("click", function (event) {
            if ((good[i].style.background != "rgb(23, 35, 75)") && (bad[i].style.background != "rgb(23, 35, 75)")) {
                alert("尚未设置本书的书籍情况")
            }
            else {
                if (confirm(`是否不接受“${returnName[i].textContent}”的归还?`)) {
                    returnBook[i].remove()
                }
            }
        })
    }
}