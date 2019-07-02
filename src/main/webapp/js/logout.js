// 退出admin

function logout(){
    deleteCookie("account","/");
    deleteCookie("name","/");
    window.location.reload();
}
