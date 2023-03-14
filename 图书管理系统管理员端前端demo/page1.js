let book = []
const booklist = document.querySelector("tbody")
//图书清单的动态生成
function booklistCreate(){
    for (let i = 0; i < 100; i++) {
        book[i] = document.createElement("tr")
        book[i].className = "book"
        booklist.appendChild(book[i])
        book[i].insertAdjacentHTML("beforeend",
            `
                <td>${i+1}</td>
                <td class="book-name">三体</td>
                <td>刘慈欣</td>
                <td>科幻</td>
                <td>76</td>
                <td class="handle">
                    <button class="edit">编辑</button>
                    <button class="delete">删除</button>
                </td>
            `
        )
    }
}
booklistCreate()

//导出图书清单为excel文件
{
    document.getElementById("booklist-to-xlsx").addEventListener('click', function () {
        let wb = XLSX.utils.table_to_book(document.getElementById("booklist"));
        /* 导出文件下载 */
        XLSX.writeFile(wb, "图书清单.xlsx",);
    });
}

//编辑图书操作
{
    const editBookBtn = document.querySelectorAll(".edit")
    let bookEdit = []
    for (let i = 0; i < book.length; i++) {
        editBookBtn[i].addEventListener("click", function () {
            editBookBtn[i].disabled = true
            book[i].style.height = "500px"
            bookEdit[i] = document.createElement("div")
            bookEdit[i].className = "book-edit"
            book[i].appendChild(bookEdit[i])
            //插入可编辑内容
            bookEdit[i].insertAdjacentHTML("beforeend",
                `
                    <h2>编辑栏</h2>
                    <div class="book-cover-box">
                        <img class="book-cover" src="./photo/OIP-C.jpg" height="400px" width="280px"></img>
                        <button class="book-cover-edit-btn iconfont icon-tianxie"></button>
                    </div>
                    <form>
                        <input type="file" class="book-cover-edit" name="book-cover-file">
                        <div class="input-box">
                            <label>书籍编号：</label>
                            <input type="text" class="input-order" >
                        </div>
                        <div class="input-box">
                            <label>作品名称：</label>
                            <input type="text" class="input-name" >
                        </div>
                        <div class="input-box">
                            <label>作品作者：</label>
                            <input type="text" class="input-author">
                        </div>
                        <div class="input-box">
                            <label>书籍类型：</label>
                            <input type="text" class="input-type">
                        </div>
                        <div class="input-box">
                            <label>出版时间：</label>
                            <input type="text" class="input-time">
                        </div>
                        <div class="input-box">
                            <label>作品简介：</label>
                             <textarea name="" id="" cols="300" rows="100" class="input-introduction"></textarea>
                        </div>
                        <div class="input-box">
                            <label>库存数量：</label>
                            <input type="text" class="input-number">
                        </div>
                        <div class="input-box">
                            <label>图书位置：</label>
                            <input type="text" class="input-location">
                        </div>
                        <div class="book-details-btn-box">
                            <button class="edit-finish">保存更改</button>
                            <button class="edit-cancel">取消</button>
                        </div>
                    </form>
                `
            )
            const inputOrder = bookEdit[i].querySelector(".input-order")
            inputOrder.value = i+1
            const inputName = bookEdit[i].querySelector(".input-name")
            inputName.value = "三体"
            const inputAuthor = bookEdit[i].querySelector(".input-author")
            inputAuthor.value = "刘慈欣"
            const inputType = bookEdit[i].querySelector(".input-type")
            inputType.value = "科幻"
            const inputTime = bookEdit[i].querySelector(".input-time")
            inputTime.value = "2008年1月1日"
            const inputIntroduction = bookEdit[i].querySelector(".input-introduction")
            inputIntroduction.value = "作品讲述了地球人类文明和三体文明的信息交流、生死搏杀及两个文明在宇宙中的兴衰历程。"
            const inputNumber = bookEdit[i].querySelector(".input-number")
            inputNumber.value = 76
            const inputLocation = bookEdit[i].querySelector(".input-location")
            inputLocation.value = "图书馆三层东部书库科幻区"
            //
            const bookCoverEditBtn = bookEdit[i].querySelector(".book-cover-edit-btn")
            const bookCoverEdit = bookEdit[i].querySelector(".book-cover-edit")
            let bookCover = bookEdit[i].querySelector(".book-cover")
            //点击"上传文件"以添加图片文件
            bookCoverEditBtn.onclick = function () {
                bookCoverEdit.click()
            }
            bookCoverEdit.onchange = function () {
                // 创建文件读取对象
                let reader = new FileReader();
                // 用户选择的文件列表
                // console.log(this.files[0]);
                // 读取文件
                reader.readAsDataURL(this.files[0]);
                // 监听onload事件
                reader.onload = function () {
                    // console.log(reader.result);
                    // 将读取的结果显示在页面中
                    bookCover.src = reader.result;
                }
            }
            // 编辑完成与编辑取消
            const editFinish = bookEdit[i].querySelector(".edit-finish")
            const editCancel = bookEdit[i].querySelector(".edit-cancel")
            editFinish.addEventListener("click", function () {
                book[i].style.height = "45px"
                bookEdit[i].remove()
                editBookBtn[i].disabled = false
            })
            editCancel.addEventListener("click", function () {
                book[i].style.height = "45px"
                bookEdit[i].remove()
                editBookBtn[i].disabled = false
            })
        }
        )
    }
}

//删除图书操作
{
    const book = document.querySelectorAll(".book")
    const deleteBook = document.querySelectorAll(".delete")
    for (let i = 0; i < book.length; i++) {
        deleteBook[i].onclick = function () {
            if (confirm("确定要删除这本书吗？")) {
                book[i].remove()
            }
        }
    }
}

//弹窗
{
    const bookIncreaseBtn = document.getElementById("book-increase-btn")
    bookIncreaseBtn.addEventListener("click", () => {
        const mask = document.createElement("div")
        document.body.appendChild(mask)
        mask.className = "mask"
        mask.insertAdjacentHTML("beforeend",
            `
                <div class="book-increase">
                    <h2>添加书籍</h2>
                    <div class="book-cover-box">
                        <img class="book-cover" height="400px" width="280px"></img>
                        <button class="book-cover-upload-btn">上传封面</button>
                    </div>
                    <form>
                        <input type="file" class="book-cover-upload" name="book-cover-file">
                        <div class="input-box">
                            <label>书籍编号</label>
                            <input type="text" name="order class="input-order">
                        </div>
                        <div class="input-box">
                            <label>作品名称</label>
                            <input type="text" name ="book-name" class="input-name">
                        </div>
                        <div class="input-box">
                            <label>作品作者</label>
                            <input type="text" name="author" class="input-author">
                        </div>
                        <div class="input-box">
                            <label>书籍类型</label>
                            <input type="text" ame="type"  class="input-type">
                        </div>
                        <div class="input-box">
                            <label>出版时间</label>
                            <input type="text" name="time" 
                            class="input-time">
                        </div>
                        <div class="input-box">
                            <label>库存数量</label>
                            <input type="text" name="number"  class="input-number">
                        </div>
                        <div class="input-box">
                            <label>作品简介</label>
                            <textarea name="introduction" id="" cols="300" rows="100" class="input-introduction"></textarea>
                        </div>
                        <div class="input-box">
                            <label>图书位置</label>
                            <input type="text" name="location" class="input-location">
                        </div>
                        <div class="modal-btn-box">
                            <input type="submit" name="submit" class="increase-finish" value = "添加"></input>
                            <button class="increase-cancel">取消</button>
                        </div>
                    </form>
                </div>
            `
        )
        const increaseFinish = mask.querySelector(".increase-finish")
        const increaseCancel = mask.querySelector(".increase-cancel")
        const bookCoverUploadBtn = mask.querySelector(".book-cover-upload-btn")
        const bookCoverUpload = mask.querySelector(".book-cover-upload")
        let bookCover = mask.querySelector(".book-cover")
        //点击"上传文件"以添加图片文件
        bookCoverUploadBtn.onclick = function () {
            bookCoverUpload.click()
        }
        bookCoverUpload.onchange = function () {
            bookCoverUploadBtn.style.color = "white"
            let reader = new FileReader();
            reader.readAsDataURL(this.files[0]);
            reader.onload = function () {
                bookCover.src = reader.result;
            }
        }
        // 图书增加表单的提交
        increaseFinish.addEventListener("click", function (){
            mask.remove()
        })
        increaseCancel.addEventListener("click", function (){
            mask.remove()
        })
    })
}