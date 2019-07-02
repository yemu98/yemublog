// 首页js
// 加载页面
// 加载blog列表
// 加载分页


    var nowpage = getUrlVars()["page"]; //从url获取nowpage
    nowpage = parseInt(nowpage);
    getPageCount(); //获取总页码数
    var pagecount = sessionStorage.getItem("pagecount");
    pagecount = parseInt(pagecount);
    if (!isNaN(pagecount) && nowpage > pagecount) {
        nowpage = sessionStorage.getItem("pagecount");
    }
    if (isNaN(nowpage) || nowpage <= 0) { //检查nowpage合法性
        nowpage = 1;
    }
    nowpage = parseInt(nowpage);
    sessionStorage.setItem("nowpage", nowpage); //将本页码存到session里便于取用
    initPage(nowpage);


function initPage(nowpage) { //请求page信息
    post("http://localhost:8080/yemublog/blog/getPage",
        "pagenum=" + nowpage + "&blognum=10",
        function suc(text) {
            var bloglist = JSON.parse(text);
            for (var i = 0; i < bloglist.length; i++) {
                var blog = bloglist[i];
                blog.post_time = msstodate(blog.post_time);
                addblog(blog); //加载blog信息
            }
            //加载完blog后，加载页码，确保页码在最下面
            addpagination(sessionStorage.getItem("pagecount"));
        },
        function failed(text) {
            alert("加载失败" + text);
        }
    );
}

function getPageCount() { //获取总页码数并存到session里
    post("http://localhost:8080/yemublog/blog/getPageCount",
        "blognum=10",
        function suc(text) {
            pagecount = parseInt(text);
            if (isNaN(pagecount)) {
                sessionStorage.setItem("pagecount", 1); //获取不正常设为1
            } else {
                sessionStorage.setItem("pagecount", pagecount); //存到session里方便取用
            }
        },
        function failed(text) {
            sessionStorage.setItem("pagecount", 1); //获取失败设为1
        }
    );
}

function addpagination(pagecount) {
    var nowpage = sessionStorage.getItem("nowpage");
    var pagination = document.createElement("ul");
    pagination.className = "pagination";
    for (var i = 1; i <= pagecount; i++) {
        var li = document.createElement("li");
        var a = document.createElement("a");
        if (i == nowpage) {
            a.className = "active";
        }
        a.innerText = i;
        a.href = "?page=" + i;
        li.appendChild(a);
        pagination.appendChild(li);
    }
    document.getElementById("bloglist").appendChild(pagination);
}


function addblog(blog) { //加载blog
    var blogwrap = document.createElement("div");
    blogwrap.className = "blog";
    var bloginfo = document.createElement("div");
    bloginfo.className = "bloginfo";
    blogwrap.appendChild(bloginfo);
    var writer = document.createElement("div");
    writer.className = "writer";
    writer.innerHTML = blog.author;
    bloginfo.appendChild(writer);
    var writericon = document.createElement("img");
    writericon.className = "bloginfoicon";
    writericon.src = "../res/作者.png";
    writer.appendChild(writericon);
    var posttime = document.createElement("div");
    posttime.className = "posttime";
    posttime.innerHTML = blog.post_time;
    bloginfo.appendChild(posttime);
    var posttimeicon = document.createElement("img");
    posttimeicon.className = "bloginfoicon";
    posttimeicon.src = "../res/时间.png";
    posttime.appendChild(posttimeicon);
    var blogheadline = document.createElement("div");
    blogheadline.className = "blogheadline";
    blogwrap.appendChild(blogheadline);
    var blogheadlinelink = document.createElement("a");
    blogheadlinelink.href = "http://localhost:8080/yemublog/html/blog.html?id=" + blog.id;
    blogheadlinelink.innerText = blog.title;
    blogheadline.appendChild(blogheadlinelink);
    var bloglist = document.getElementById("bloglist");
    var blogpart = document.createElement("div");
    blogpart.className = "blogpart";
    blogpart.innerHTML = blog.excerpt;
    blogwrap.appendChild(blogpart);
    bloglist.appendChild(blogwrap);

    var blogoperation = document.createElement("div");
    blogoperation.className = "blogoperation";
    blogwrap.appendChild(blogoperation);
    var readmorebtn = document.createElement("a");
    readmorebtn.className = "readmorebtn";
    readmorebtn.href = "http://localhost:8080/yemublog/html/blog.html?id=" + blog.id;
    readmorebtn.innerHTML = "<img class='readmoreicon' src='../res/阅读.png'>阅读";
    blogoperation.appendChild(readmorebtn);
    var discussbtn = document.createElement("a");
    discussbtn.className = "discussbtn";
    discussbtn.href = "http://localhost:8080/yemublog/html/blog.html?id=" + blog.id + "#discusstext";
    discussbtn.innerHTML = "<img class='discussicon' src='../res/评论.png'>发布评论";
    blogoperation.appendChild(discussbtn);
}