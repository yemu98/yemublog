// admin页登录


function getCookie(cname) {
    var name = cname + "=";
    var decodedCookie = decodeURIComponent(document.cookie);
    var ca = decodedCookie.split(';');
    for (var i = 0; i < ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0) == ' ') {
            c = c.substring(1);
        }
        if (c.indexOf(name) == 0) {
            return c.substring(name.length, c.length);
        }
    }
    return "";
}


function login() {
    var account = document.getElementById("account").value;
    var password = document.getElementById("password").value;
    var remember = document.getElementById("remember").checked;
    var url = "http://localhost:8080/yemublog/user/login";
    var data = "account=" + account + "&password=" + password + "&remember=" + remember;
    post(url,
        data,
        function success(text) {
            var data = JSON.parse(text);
            switch (data.return){
                case "success":
                alert("登录成功");
                loginsuccess(data,remember);
                break;
                case "failed":
                alert("密码错误");
                break;
                case "none":
                alert("无此用户");
                break;
                case "error":
                alert("登录失败");
                break;
                default:
                alert("发生错误");
            }

            
            // alert(text);
        },
        function failed(text) {
            alert(text);
        });
    // alert(password);
    return false;
}

function loginsuccess(data,remember) {//登录成功保存到cookie
    if (remember==true){
    var expire = new Date((new Date()).getTime() + 24 * 60 * 60000);//有效期24小时
    document.cookie="account="+data.account+";path=/;expires="+expire;
    document.cookie="name="+data.name+";path=/;expires="+expire;
    window.location.href="index.html";
    }else{
        document.cookie="account="+data.account+";path=/";
        document.cookie="name="+data.name+";path=/";
        window.location.href="index.html";
    }
}