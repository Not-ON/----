let book = []
let stackNumber = []
let borrowNumber = []
const booklist = document.querySelector("tbody")
//图书清单的动态生成
function booklistCreate() {
    for (let i = 0; i < 100; i++) {
        book[i] = document.createElement("tr")
        book[i].className = "book"
        booklist.appendChild(book[i])
        book[i].insertAdjacentHTML("beforeend",
        `
            <td>${i + 1}</td>
            <td class="book-name">三体</td>
            <td>刘慈欣</td>
            <td>科幻</td>
            <td class="stack-number">${i}</td>
            <td class="borrow-number">10</td>
            <td class="handle">
                <button class="btn-borrow-detials">详情</button>
            </td>
        `
        )
        stackNumber[i] = book[i].querySelector(".stack-number")
        borrowNumber[i] = book[i].querySelector(".borrow-number")
    }
}
booklistCreate()

//查看借阅详情操作
function borrowDetailsCreate() {
    const btnBorrowDetails = document.querySelectorAll(".btn-borrow-detials")
    let borrowDetail = {}
    for (let i = 0; i < book.length; i++) {
        btnBorrowDetails[i].addEventListener("click", function () {
            let bookName = book[i].querySelector(".book-name").textContent
            const mask = document.createElement("div")
            document.body.style.overflowY = "hidden"
            document.body.appendChild(mask)
            mask.className = "mask"
            mask.insertAdjacentHTML("beforeend",
                `
                <div class="borrow-detail-page">
                    <h2>《${bookName}》借阅详情</h2>
                    <button class="return-btn">返回</button>
                    <table>
                        <thead>
                            <tr>
                                <th>借阅者</th>
                                <th>邮箱</th>
                                <th>借阅时间</th>
                                <th>借阅时长</th>
                                <th>状态</th>
                                <th class="handle-header"></th>
                            </tr>
                        </thead>
                        <tbody></tbody>
                    </table>
                </div>
            `
            )
            const borrowDetailPage = document.querySelector(".borrow-detail-page")
            const borrowlist = borrowDetailPage.querySelector("tbody")
            for (let i = 0; i < 100; i++){
                borrowlist.insertAdjacentHTML("beforeend",
                    `
                        <tr>
                            <td>王小明</td>
                            <td>1234567890@qq.com</td>
                            <td>2023-1-1 12:00</td>
                            <td>4天16时34分</td>
                            <td>借阅中(未超时)</td>
                            <td class="handle">
                                <button class="ask-return-btn">提醒归还</button>
                            </td>
                        </tr>
                    `
                )
            }
            let returnBtn = document.querySelector(".return-btn")
            returnBtn.addEventListener("click", function () {
                mask.remove()
                document.body.style.overflowY = "auto"
            })
        }
        )
    }
}
borrowDetailsCreate()

//清空图书列表
function booklistClear() {
    for (let i = 0; i < book.length; i++) {
        book[i].remove()
    }
}

//按照有无库存分类
{
    const btnStackStatus = document.getElementById("btn-stack-status")
    const stackStatus = document.getElementById("stack-status")
    const stackNumber = document.querySelectorAll(".stack-number")
    const stackList = stackStatus.querySelectorAll("button")
    const stackAll = document.getElementById("stack-all")
    const stackFull = document.getElementById("stack-full")
    const stackEmpty = document.getElementById("stack-empty")
    document.addEventListener("mouseover", function (event) {
        event.stopPropagation()
        stackStatus.style.display = "none"
    })
    btnStackStatus.addEventListener("mouseover", function (event) {
        event.stopPropagation()
        stackStatus.style.display = "block"
    })
    stackStatus.addEventListener("mouseover", function (event) {
        event.stopPropagation()
        stackStatus.style.display = "block"
    })
    stackStatus.addEventListener("click", function (event) {
        for (let i = 0; i < stackList.length; i++) {
            stackList[i].className = ""
        }
        event.target.className = "status-selected"
    })
    stackAll.addEventListener("click", function (event) {
        booklistClear()
        booklistCreate()
        borrowDetailsCreate()
        stackAll.disabled = true
        stackFull.disabled = false
        stackEmpty.disabled = false
    })
    stackFull.addEventListener("click", function (event) {
        booklistClear()
        booklistCreate()
        borrowDetailsCreate()
        for (let i = 0; i < stackNumber.length; i++) {
            if (stackNumber[i].textContent == "0") {
                book[i].remove()
            }
            // else {
            //     console.log(i + 1 + "库存充足,有" + stackNumber[i].textContent + "本书");
            // }
        }
        stackFull.disabled = true
        stackEmpty.disabled = false
        stackAll.disabled = false
    })
    stackEmpty.addEventListener("click", function (event) {
        booklistClear()
        booklistCreate()
        borrowDetailsCreate()
        for (let i = 0; i < stackNumber.length; i++){
            if (stackNumber[i].textContent != 0 ) {
                book[i].remove();
            }
            // else {
            //     console.log(i + "无库存,有0本书");
            // }
        }
        stackEmpty.disabled = true
        stackFull.disabled = false
        stackAll.disabled = false
    })
}

//循环点击切换排序方法
{
    const sortBox = document.getElementById("sort-box")
    const sortBtn = sortBox.querySelector("button")
    const sort = sortBox.querySelectorAll("p")
    let flag = 1
    let pre = 0
    sortBtn.addEventListener("click", () => {
        sort[pre].style.display = "none";
        sort[flag].style.display = "inline";
        // if (flag == 1) {
        //     for (let i = 0; i < book.length; i++){
        //         for (let j = i + 1; j < book.length; j++) {
        //             if (stackNumber[j].textContent > stackNumber[i].textContent) {
        //                 booklist.insertBefore(book[j], booklist.firstChild);
        //             }
        //         } 
        //     }
        //     console.log(stackNumber.textContent.sort());
        // }
        // if (flag == 2) {
        //     for (let i = 0; i < book.length; i++){
        //         for (let j = i + 1; j < book.length; j++) {
        //             if (borrowNumber[j].textContent > borrowNumber[i].textContent) {
        //                 booklist.insertBefore(book[j], booklist.firstChild);
        //             }
        //         } 
        //     }
        // }
        //下一个点击
        pre = flag;
        if (flag == sort.length - 1) { 
            flag = 0;
        }
        else {
            flag++;
        }
    })
}