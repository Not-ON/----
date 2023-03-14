const borrow = []
//借阅审批清单的动态生成
{
    const booklist = document.querySelector("tbody")
    for (let i = 0; i < 100; i++) {
        borrow[i] = document.createElement("tr")
        borrow[i].className = "borrow"
        booklist.appendChild(borrow[i])
        borrow[i].insertAdjacentHTML("beforeend",
            `
                <td>小明${i+1}</td>
                <td class="book-name">高等数学（第七版上册）</td>
                <td class="stack-number">2000</td>
                <td class="borrow-time">2023-1-1 12:00</td>
                <td class="handle">
                    <button class="yes">是</button>
                    <button class="no">否</button>
                </td>
            `
        )
    }
}

{
    const yes = document.querySelectorAll(".yes")
    const no = document.querySelectorAll(".no")
    for (let i = 0; i < borrow.length; i++){
        yes[i].addEventListener("click", function (event) {
            if (confirm("是否确定?")) {
                borrow[i].remove()
            }
        })
        no[i].addEventListener("click", function (event) {
            if (confirm("是否确定?")) {
                borrow[i].remove()
            }
        })
    }
}