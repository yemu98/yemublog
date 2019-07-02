initPage();

function initPage() { //初始化页面
    post("http://localhost:8080/yemublog/user/getCount",
        "",
        function success(text) {
            document.getElementById("usercount").innerText = text;
        },
        function failed(text) {
            alert(text);
        }
    );
    document.getElementById("users").innerHTML = "<tr><td>id</td><td>账号</td>" +
        "<td>名字</td><td>添加时间</td><td>操作</td></tr>";
    getUsers();
}


function getUsers() { //获取用户信息
    post("http://localhost:8080/yemublog/user/getAll",
        "",
        function success(text) {
            var users = {};
            users = JSON.parse(text);
            updateUsers(users);
        },
        function failed(text) {
            alert(text);
        }
    );

}

function updateUsers(users) { //加载用户信息
    var userswrap = document.getElementById("users");
    for (var i = 0; i < users.length; i++) {
        var user = document.createElement("tr");
        var id = document.createElement("td");
        id.innerText = users[i].id;
        user.appendChild(id);
        var account = document.createElement("td");
        account.innerText = users[i].account;
        user.appendChild(account);
        var name = document.createElement("td");
        name.innerText = users[i].name;
        user.appendChild(name);
        var create_time = document.createElement("td");
        create_time.innerText = msstodate(users[i].create_time);
        user.appendChild(create_time);
        var deletebtnwrap = document.createElement("td");
        deletebtnwrap.innerHTML = "<input type='button' value='删除' onclick='deleteUser(" + users[i].id + ")'>";
        user.appendChild(deletebtnwrap);
        userswrap.appendChild(user);
    }
}

function deleteUser(id) { //根据删除用户
    post("http://localhost:8080/yemublog/user/deleteById",
        "id=" + id,
        function success(text) {
            alert(text);
            initPage();
            // document.getElementById("users").innerHTML = "<tr><td>id</td><td>账号</td>" +
            // "<td>名字</td><td>添加时间</td><td>操作</td></tr>";
            // getUsers();
        },
        function failed(text) {
            alert(text);
        }
    );

}

function check(type) { //检查账号或用户名是否重复
    var temp = document.getElementById("add" + type).value;
    if (temp.trim() == "") {
        document.getElementById("right" + type).innerHTML = "*未填写";
        return false;
    }
    var url = "http://localhost:8080/yemublog/user/getUser";
    var data = type + "=" + temp;
    post(
        url,
        data,
        function success(ajaxText) {
            // alert(ajaxText);
            if (ajaxText != "null" && ajaxText != "") {
                document.getElementById("right" + type).innerHTML = "*已被注册";
                document.getElementById(type + "flag").value = false;
                // document.getElementById("add" + type).select();
                return false;
            } else {
                document.getElementById("right" + type).innerHTML = "可以使用";
                document.getElementById(type + "flag").value = true;
            }
        },
        function failed(ajaxState) {
            console.log(ajaxState);
            document.getElementById(type + "flag").value = false;
            return false;
        });
}

function checkpwd() { //检查密码输入
    var pwd = document.getElementById("addpwd");
    var pwd2 = document.getElementById("addpwd2");
    if (pwd.value == "") {
        document.getElementById("rightpwd").innerHTML = "请输入密码";
        document.getElementById("pwdflag").value = false;
        return false;
    }
    if (pwd.value != pwd2.value) {
        document.getElementById("rightpwd").innerHTML = "两次输入不一致";
        document.getElementById("pwdflag").value = false;
        return false;
    }
    document.getElementById("rightpwd").innerHTML = "输入正确";
    document.getElementById("pwdflag").value = true;
}

function checkinput() { //提交前检查输入
    var accountflag = document.getElementById("accountflag").value;
    var nameflag = document.getElementById("nameflag").value;
    var pwdflag = document.getElementById("pwdflag").value;
    if (accountflag == "true" && nameflag == "true" && pwdflag == "true") {
        addUser();
    } else {
        alert("请检查输入信息");
    }
}

function addUser() { //添加用户
    var account = document.getElementById("addaccount").value;
    var name = document.getElementById("addname").value;
    var password = document.getElementById("addpwd").value;
    var data = "account=" + account + "&name=" + name + "&password=" + password;
    var url = "http://localhost:8080/yemublog/user/addUser";
    post(
        url,
        data,
        function success(ajaxText) {
            alert(ajaxText);
            if (JSON.parse(ajaxText) == "ok") {
                window.location.reload();
            }
        },
        function failed(ajaxState) {
            alert("添加失败 错误码:" + ajaxState);
        });
}

function getById() { //根据id获得用户信息
    var id = document.getElementById("queryid").value;
    var url = "http://localhost:8080/yemublog/user/getUser";
    var data = "id=" + id;
    post(
        url,
        data,
        function success(ajaxText) {
            document.getElementById("userinfo").innerHTML = ajaxText;
        },
        function failed(ajaxState) {
            alert(ajaxState);
        });
}

function getByAccount() { //根据账号获取用户信息
    var account = document.getElementById("queryaccount").value;
    url = "http://localhost:8080/yemublog/user/getUser";
    var data = "account=" + account;
    post(
        url,
        data,
        function success(ajaxText) {
            var user = {};
            user = JSON.parse(ajaxText);
            getUserinfo(user);
            document.getElementById("userinfo").innerHTML = ajaxText;
        },
        function failed(ajaxState) {
            alert(ajaxState);
        });
}

function getByName() { //根据姓名获取用户信息
    var name = document.getElementById("queryname").value;
    url = "http://localhost:8080/yemublog/user/getUser";
    var data = "name=" + name;
    post(
        url,
        data,
        function success(ajaxText) {
            var user = {};
            user = JSON.parse(ajaxText);
            getUserinfo(user);
            document.getElementById("userinfo").innerHTML = ajaxText;
        },
        function failed(ajaxState) {
            alert(ajaxState);
        });
}

function getUserinfo(user) {
    if (user == null || user == "") {
        document.getElementById("userid").innerText = "";
        document.getElementById("useraccount").value = "";
        document.getElementById("username").value = "";
        document.getElementById("user_createtime").innerText = "";
    }
    else{
        document.getElementById("userid").innerText = user.id;
        document.getElementById("useraccount").value = user.account;
        document.getElementById("username").value = user.name;
        document.getElementById("user_createtime").innerText = msstodate(user.create_time);
    }

}
function updateUser() {//修改用户信息
    var id = document.getElementById("userid").innerText;
    var useraccount = document.getElementById("useraccount").value;
    var username = document.getElementById("username").value;
    post("http://localhost:8080/yemublog/user/update",
        "id=" + id + "&account=" + useraccount + "&name=" + username,
        function success(text) {
            if (text == "" || text == 'null') {
                alert("修改失败");
            } else {
                window.location.reload();
            }

        },
        function failed(text) {
            alert(text);
        }
    );
}