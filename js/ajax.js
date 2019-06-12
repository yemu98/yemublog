function post(url,data,success,failed) {//ajax-post方式请求，url：请求地址，data发送的数据，success：成功回调，failed:失败回调
    var xhr = new XMLHttpRequest();
    xhr.open("POST", url);
    xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xhr.send(data);
    xhr.onreadystatechange = function () {
        if (xhr.readyState == 4 && xhr.status == 200) {//成功
            success(xhr.responseText);//调用成功函数
        }
        if(xhr.readyState==4&&xhr.status!=200){//失败服务端发生错误
            failed(xhr.status);//调用失败函数
        }
    }
}
function get() {

}


function msstodate(inputTime) {
    var date = new Date(inputTime);
    var y = date.getFullYear();
    var m = date.getMonth() + 1;
    m = m < 10 ? ('0' + m) : m;
    var d = date.getDate();
    d = d < 10 ? ('0' + d) : d;
    var h = date.getHours();
    h = h < 10 ? ('0' + h) : h;
    var minute = date.getMinutes();
    var second = date.getSeconds();
    minute = minute < 10 ? ('0' + minute) : minute;
    second = second < 10 ? ('0' + second) : second;
    return y + '-' + m + '-' + d + ' ' + '　' + h + ':' + minute + ':' + second;
}