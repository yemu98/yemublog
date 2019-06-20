// 写博客
// 发布博客
function autosize(text) {//textarea自适应
    // var text=document.getElementById("text");
    text.style.height = "auto";
    text.style.height = text.scrollTop + text.scrollHeight + "px";
}

function replace(text) {
    text.value = text.value.replace(/\n/g, ""); //替换换行符
    // console.log(text.value);
}

function newsection(text) {
    var str = text.value;

}

function displayitems() {//显示添加窗口
    var additems=document.getElementById("additems");
    additems.style.display=="block"?additems.style.display="none":additems.style.display="block";

}

function addsection() {//添加新的段落textarea
    var newnode = document.createElement("textarea");
    newnode.className = "section";

    newnode.oninput = function () {
        autosize(this);
        replace(this);
    }
    newnode.placeholder = "段落";
    var parentnode = document.getElementById("sections");
    parentnode.insertBefore(newnode, parentnode.lastElementChild.nextSibling);
}



function addimgbtn() {//显示上传图片窗口
    var uploadimgwrap=document.getElementById("uploadimgwrap");
    uploadimgwrap.style.display=="block"?uploadimgwrap.style.display="none":uploadimgwrap.style.display="block";
    event.cancelBubble=true;//阻止父元素事件触发，停止冒泡
    
}
function addimg(id){//添加完图片显示在页面上
document.getElementById("additems").style.display="none";
    var newnode = document.createElement("img");
    newnode.className = "section";
    newnode.src = "http://blog.yemuc.xyz/img/showImg?id="+id;
    var parentnode = document.getElementById("sections");
    parentnode.insertBefore(newnode, parentnode.lastElementChild.nextSibling);
}

function read_file(files) { //检查图片上传类型
    if (files.length) {
        var file = files[0];
        if (!/image\/\w+/.test(file.type)) {
            alert("文件必须是图片!");
            document.getElementById("uploadimg").value = null;
            return false;
        }
    }
}
function uploadimg() {//上传文件
    var file = document.getElementById("uploadimg").files[0]; //获取文件对象(可能有多个，这里获取第一个)
    var formData = new FormData();
    formData.append("file", file);
    var url = "http://blog.yemuc.xyz/img/upload";
    upload(url,
        formData,
        upimgsuccess,
        upimgfailed,
        progress);
    document.getElementById("additems").style.display = "block";
}

function openwrap() { //保持上传div显示
    event.cancelBubble = true;
}

function progress(evt) {//上传进度条
    var progressBar = document.getElementById("progressbar");
    if (evt.lengthComputable) {
        progressBar.max = evt.total;
        progressBar.value = evt.loaded;

    }
}

function upimgsuccess(text) {//上传图片成功回调
    alert("成功！");
    var img = JSON.parse(text);
    addimg(img.id);
    document.getElementById("uploadimg").value = null;
    var progressBar = document.getElementById("progressbar");
    progressBar.value = 0;
}

function upimgfailed(text) {//上传失败回调
    alert("失败"+text);
}
function postblog() {//发布
    var blog={};
    var headline = document.getElementById("headline").value;
    if (headline.trim()==""||headline==null){
        alert("请填写标题！");
        document.getElementById("headline").focus();
        document.getElementById("headline").select();
        return false;
    }
    var section = document.getElementsByClassName("section");
    var content = "";
    for (var i = 0; i < section.length; i++) {
        if (section[i].tagName == "TEXTAREA") {
            content += "<p>" + section[i].value + "</p>"
        } else if (section[i].tagName == "IMG") {

            content += "<div class='blogimg'><img src=" + section[i].src + "></div>";
        }
    }
    blog["title"]=headline;
    blog["content"]=content;
    blog["author"]=getCookie("name");
    var url="http://blog.yemuc.xyz/post/postBlog"
    post(url,
        "json="+JSON.stringify(blog),
        function success(text){
            // alert(text);

            if (data=="error"){
                alert("发布失败！");
            }
            else{
                var data=JSON.parse(text);
                alert("发布成功！");
                window.location.href="../blog.html?id="+data.id;
            }
        },
        function failed(text){
            alert(text);
        }
    );
    // alert(JSON.stringify(blog));
    
}
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