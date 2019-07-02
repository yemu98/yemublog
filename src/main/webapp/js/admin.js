

window.onload=function(){//检查登录状态
    if (getCookie("account") == "") {
        window.location.href = "login.html";
    }
};