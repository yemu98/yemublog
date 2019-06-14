
function autosize(text) {
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

function displayitems() {
    var additems=document.getElementById("additems");
    additems.style.display=="block"?additems.style.display="none":additems.style.display="block";

}

function addsection() {
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

function postblog() {
    var blog={};
    var headline = document.getElementById("headline").value;
    blog["headline"]=headline;
    var section = document.getElementsByClassName("section");
    var content = "";
    for (var i = 0; i < section.length; i++) {
        if (section[i].tagName == "TEXTAREA") {
            content += "<p>" + section[i].value + "</p>"
        } else if (section[i].tagName == "IMG") {

            content += "<div class='blogimg'><img src=" + section[i].src + "></div>";
        }
    }
    blog["content"]=content;
    alert(JSON.stringify(blog));
    
}

function addimgbtn() {
    var uploadimgwrap=document.getElementById("uploadimgwrap");
    uploadimgwrap.style.display=="block"?uploadimgwrap.style.display="none":uploadimgwrap.style.display="block";
    event.cancelBubble=true;//阻止父元素事件触发，停止冒泡
    
}
function addimg(uri){
document.getElementById("additems").style.display="none";
    var newnode = document.createElement("img");
    newnode.className = "section";
    newnode.src = uri;
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