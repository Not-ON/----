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
                <td>小明${i+1}</td>
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

//书籍情况汇报
{
    const good = document.querySelectorAll(".situation-good")
    const bad = document.querySelectorAll(".situation-bad")
    for (let i = 0; i < returnBook.length; i++) {
        good[i].addEventListener("click", function (event) {
            if (confirm("是否确定?")) {
                good[i].style.background = "white"
                bad[i].style.background = "black"
            }
        })
        bad[i].addEventListener("click", function (event) {
            if (confirm("是否确定?")) {
                bad[i].style.background = "white"
                good[i].style.background = "black"
            }
        })
    }
}

//归还审批
 {
    const yes = document.querySelectorAll(".yes")
    const no = document.querySelectorAll(".no")
    for (let i = 0; i < returnBook.length; i++) {
        yes[i].addEventListener("click", function (event) {
            if (confirm("是否确定?")) {
                returnBook[i].remove()
            }
        })
        no[i].addEventListener("click", function (event) {
            if (confirm("是否确定?")) {
                returnBook[i].remove()
            }
        })
    }
}