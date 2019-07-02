// 功能js
// ajax（post）
// 时间戳转日期
// 上传文件
// 获取url中所有参数
// 获取cookie中的某个值
// 删除cookie中的某个值
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


function msstodate(inputTime) {//timestamp转日期
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
function upload(url,formData,success,failed,progress){//用formData对象，上传文件
    var xhr=new XMLHttpRequest();
    xhr.open("POST", url,true);
    xhr.upload.onprogress=function(evt){
        progress(evt);
    };
    xhr.send(formData);
    
    xhr.onreadystatechange = function () {
        if (xhr.readyState == 4 && xhr.status == 200) {//成功
            success(xhr.responseText);//调用成功函数
        }
        if(xhr.readyState==4&&xhr.status!=200){//失败服务端发生错误
            failed(xhr.status);//调用失败函数
        }
    }
    
}
function getUrlVars() { //获取url中的参数
    var vars = [],
        hash;
    var hashes = window.location.href.slice(window.location.href.indexOf('?') + 1).split('&');
    for (var i = 0; i < hashes.length; i++) {
        hash = hashes[i].split('=');
        vars.push(hash[0]);
        vars[hash[0]] = hash[1];
    }
    return vars;
}
function getCookie(cname) {//获取cookie值
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


function deleteCookie(cname,path){//删除指定目录下的cookie值
    var date=new Date();
    date.setTime(date.getTime()-10000);
    if (path==null||path==""){
        document.cookie=cname+"=;expires="+date.toGMTString();
    }
    else{
        document.cookie=cname+"=;expires="+date.toGMTString()+";path="+path;
    }
}