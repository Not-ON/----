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
    let borrowDetails = []
    let borrowDetailsFinish = []
    for (let i = 0; i < book.length; i++) {
        btnBorrowDetails[i].addEventListener("click", function () {
            btnBorrowDetails[i].disabled = true
            book[i].style.height = "350px"
            borrowDetails[i] = document.createElement("div")
            borrowDetails[i].className = "borrow-details"
            book[i].appendChild(borrowDetails[i])
            //插入可编辑内容
            borrowDetails[i].insertAdjacentHTML("beforeend",
                `
                    <button class="borrow-details-finish">收回详情页</button>
                `
            )
            borrowDetailsFinish[i] = book[i].querySelector(".borrow-details-finish")
            borrowDetailsFinish[i].addEventListener("click", function () {
                book[i].style.height = "45px"
                borrowDetails[i].remove()
                btnBorrowDetails[i].disabled = false
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