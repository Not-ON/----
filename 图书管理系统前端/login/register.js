//获取元素
var register = document.getElementsByClassName("register-")
var password1 = document.getElementsByClassName("register--")
//邮箱格式错误提示
function registeremail() {
    var str = register[1].value;
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
var result=registeremail()
//发送注册请求
function register0() {
    var name = register[0].value;
    var dir = register[2].value;
    var password = password1[0].value;
    var email = register[1].value;
    var number = register[3].value;
    var code = password1[1].value;
    console.log("hhh");
    if (result == 1)
    {
        axios({
        method: 'post',
        url: 'http://wamjhf.natappfree.cc/Books_Managerment_System_war_exploded/RegisterServlet',
        params: {
            "username": name,
            "password": password,
            "dir": dir,
            "number":number,
            "email": email,
            "invitationCode": code,
        }
        }).then((res) => {
            alert(res)
            console.log(res.data.message)
            if (res.data.message == "注册成功") {
                window.location.href = "./login.html"
            }
        }
    ).catch((e) => { })
    console.log("ha");
    }
    
}
    
