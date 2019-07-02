initPage();

function initPage(){
    getDiscuss();
    getDiscussCount();
    document.getElementById("discusses").innerHTML = "<tr><td>id</td><td>blogid</td>" +
                    "<td>发布人</td><td>内容</td><td>email</td><td>时间</td><td>操作</td></tr>";
}

function getDiscuss() {
    post("http://localhost:8080/yemublog/discuss/getAll",
        "",
        function success(text) {
            var discusses = {};
            discusses = JSON.parse(text);
            updateDiscuss(discusses);
        },
        function failed(text) {
            alert(text);
        }
    );
}

function updateDiscuss(discusses) {
    var discusseswrap = document.getElementById("discusses");
    for (var i = 0; i < discusses.length; i++) {
        var discuss = document.createElement("tr");
        var id = document.createElement("td");
        id.innerText = discusses[i].id;
        discuss.appendChild(id);
        var blogid = document.createElement("td");
        var blog_link = document.createElement("a");
        blog_link.className="blog_link";
        blog_link.href = "../blog.html?id=" + discusses[i].blogid;
        blog_link.innerText = discusses[i].blogid;
        blogid.appendChild(blog_link);
        discuss.appendChild(blogid);
        var writer = document.createElement("td");
        writer.innerText = discusses[i].name;
        discuss.appendChild(writer);
        var content = document.createElement("td");
        content.innerText = discusses[i].content;
        discuss.appendChild(content);
        var email = document.createElement("td");
        email.innerText = discusses[i].email;
        discuss.appendChild(email);
        var create_time = document.createElement("td");
        create_time.innerText = msstodate(discusses[i].create_time);
        discuss.appendChild(create_time);
        var deletebtnwrap = document.createElement("td");
        deletebtnwrap.innerHTML = "<input type='button' value='删除' onclick='deleteDiscuss(" + discusses[i].id + ")'>";
        discuss.appendChild(deletebtnwrap);
        discusseswrap.appendChild(discuss);
    }
}

function deleteDiscuss(id) {
    post("http://localhost:8080/yemublog/discuss/deleteById",
        "id=" + id,
        function success(text) {
            alert(text);
            initPage();
        },
        function failed(text) {
            alert(text);
        }
    );
}
function getDiscussCount() {
    post("http://localhost:8080/yemublog/discuss/getCount",
        "",
        function success(text) {
            document.getElementById("discusscount").innerText = text;
        },
        function failed(text) {
            alert(text);
        }
    );
}