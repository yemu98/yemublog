// 博客详情页js
// 初始化页面
// 加载blog
// 加载评论
// 发表评论
// 
var id = getUrlVars()["id"]; //从url获取id
id = parseInt(id); //转为int型
if (isNaN(id)) { //判断id是否合法
    alert("无此页面，或已被删除！");
    window.location.href = "../html/index.html"; //id不是数字跳转到首页
} else {
    sessionStorage.setItem("blogid", id); //存到session里便于取用
}

initpage();

window.onload = function () {
    var discusstext = document.getElementById("discusstext");
    var discussname = document.getElementById("discussname");
    var discussemail = document.getElementById("discussemail");
    var discussbtn=document.getElementById("discussbtn");
    discussname.addEventListener("keyup", function (event) {
        event.preventDefault();
        if (event.keyCode === 13) {
            discussbtn.click();
        }
    });
};

function initpage() { //初始化页面
    var id = sessionStorage.getItem("blogid");
    post("http://localhost:8080/yemublog/blog/getById",
        "id=" + id,
        function success(text) {
            var blog = JSON.parse(text);
            if (blog == null) {
                alert("无此页面，或已被删除！");
                window.location.href = "../html/index.html"; //id不是数字跳转到首页
                return false;
            }
            blog.post_time = msstodate(blog.post_time);
            updatepage(blog); //加载博文
            getdiscuss(); //加载评论区
        },
        function failed(text) {
            alert(text);
        }
    );
}


function updatepage(blog) { //加载blog
    var blogheadline = document.getElementById("blogheadline");
    blogheadline.innerText = blog.title;
    var writer = document.getElementById("writer");
    writer.innerText = blog.author;
    var posttime = document.getElementById("posttime");
    posttime.innerText = blog.post_time;
    document.title = blog.title;
    var blogcontent = document.getElementById("blogcontent");
    blogcontent.innerHTML = blog.content;
}

function adddiscuss() { //发布评论
    var discusstext = document.getElementById("discusstext");
    var discussname = document.getElementById("discussname");
    var discussemail = document.getElementById("discussemail");
    var blogid = sessionStorage.getItem("blogid");
    if (discusstext.value.trim() == "" || discusstext.value.trim() == null) {
        alert("请填写评论");
        discusstext.focus();
        return false;
    }
    if (discussname.value.trim() == "" || discusstext.name.trim() == null) {
        alert("请填写姓名");
        discussname.focus();
        return false;
    }

    var url = "http://localhost:8080/yemublog/discuss/addDiscuss";
    var discuss = {};
    discuss["blogid"] = blogid;
    discuss["content"] = discusstext.value;
    discuss["name"] = discussname.value;
    discuss["email"] = discussemail.value;
    post(url,
        "discuss=" + JSON.stringify(discuss),
        function suc(text) { //评论完成重新加载评论区
            discusstext.value = ""; //清空输入
            discussname.value = "";
            discussemail.value = "";
            document.getElementById("discussitems").innerHTML = ""; //清空评论区
            getdiscuss(); //重新加载
        },
        function failed(text) {
            alert(text);
        }
    );
    return false;
}

function getdiscuss() { //加载评论区
    var blogid = sessionStorage.getItem("blogid");
    blogid = parseInt(blogid);
    var url = "http://localhost:8080/yemublog/discuss/getById";
    if (!isNaN(blogid)) {
        post(url,
            "blogid=" + blogid,
            function success(text) {
                var discusses = JSON.parse(text);
                for (var i = 0; i < discusses.length; i++) {
                    updatediscuss(discusses[i]);
                }
            },
            function failed(text) {
                alert(text);
            })
    }
}

function updatediscuss(discuss) { //加载评论
    var discussitem = document.createElement("div");
    discussitem.className = "discussitem";
    var discusswriter = document.createElement("div");
    discusswriter.className = "discusswriter";
    discusswriter.innerText = discuss.name;
    discussitem.appendChild(discusswriter);
    var discusscontent = document.createElement("div");
    discusscontent.className = "discusscontent";
    discusscontent.innerText = discuss.content;
    discussitem.appendChild(discusscontent);
    var discussitems = document.getElementById("discussitems");
    discussitems.appendChild(discussitem);
}