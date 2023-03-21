//获取元素
var login = document.getElementsByClassName("login--")
//验证邮箱格式
function loginemail() {
    var str = login[0].value;
    var reg = /^[A-Za-z0-9\u4e00-\u9fa5]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;
    var res = reg.test(str);
    var error1 = document.getElementById("error1");
    console.log(error1);
    console.log(res);
    if (res ===true||str==="admin") {
        error1.style.display = "none";
        return 0;
    } else {
        error1.style.display = "block";
        return 1;
    }
}
//发送登录请求
function login0() {
     var username = login[0].value;
    var password = login[1].value;
    console.log(username)
    console.log(password)
    axios({
        method: 'post',
        url: 'http://wamjhf.natappfree.cc/Books_Managerment_System_war_exploded/LoginServlet',
        data: {
            "username" : username,
            "password" : password,
        }
    }).then((res) => {
        alert(res.data.message)
        console.log(res);
        if(res.data.message=="欢迎来到管理员端")
        {
            window.location.href="../管理员端/index.html"
        } else if (res.data.message == "欢迎来到书库") {
            window.location.href="../用户端/index.html"
        }
    }).catch((e) => { })
    console.log("hahaha");
}
//跳转到注册页面
function jump() {
    window.location.href = "./register.html";
}


