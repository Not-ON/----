// axios({
//     method: "GET",
//     url: "http://pmwiz6.natappfree.cc/Books_Managerment_System_war_exploded/borrowerServlet",
//     headers: { 'Content-Type': 'application/x-www-form-urlencoded`' },
//     // data: {
//     //     "img": "https://xirealm.oss-cn-beijing.aliyuncs.com/xi/clip_image006.gif" ,
//     //     "number": "12",
//     //     "bookName": "体",
//     //     "classify": "歌",
//     //     "author": "刘欣",
//     //     "publisher": "重庆版社",
//     //     "bookLocation": "三层",
//     //     "content": "baal",
//     //     "count": "3",
//     //     "bookName": "三体",
//     // }
// })
//     .then(function (response) {
//         console.log("注册报文", response.data);
//     })
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
                <td class="stack-number">12</td>
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