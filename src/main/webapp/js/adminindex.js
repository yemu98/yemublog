// 管理首页需要的js
getBlogCount();
        getDiscussCount();
        getRecent();
        function getBlogCount(){
            post("http://localhost:8080/yemublog/blog/getBlogCount",
            "",
            function success(text) {
                document.getElementById("blogcount").innerText = text;
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


        function getRecent() {
            post("http://localhost:8080/yemublog/blog/getRecent",
                "num=100",
                function success(text) {
                    var blogs = {};
                    blogs = JSON.parse(text);
                    updateRencent(blogs);
                },
                function failed(text) {
                    alert(text);
                }
            );
        }

        function updateRencent(blogs) {
            var blogswrap = document.getElementById("blogs");
            for (var i = 0; i < blogs.length; i++) {
                var blog = document.createElement("tr");
                var id = document.createElement("td");
                id.innerText = blogs[i].id;
                blog.appendChild(id);
                var writer = document.createElement("td");
                writer.innerText = blogs[i].author;
                blog.appendChild(writer);
                var title = document.createElement("td");
                var bloglink=document.createElement("a");
                bloglink.className="bloglink";
                bloglink.href="../blog.html?id="+blogs[i].id;
                bloglink.innerText=blogs[i].title;
                title.appendChild(bloglink);
                // title.innerText = blogs[i].title;
                blog.appendChild(title);
                var excerpt = document.createElement("td");
                excerpt.innerText = blogs[i].excerpt;
                blog.appendChild(excerpt);
                var create_time = document.createElement("td");
                create_time.innerText = msstodate(blogs[i].post_time);
                blog.appendChild(create_time);
                var deletebtnwrap = document.createElement("td");
                deletebtnwrap.innerHTML = "<input type='button' value='删除' onclick='deleteBlog(" + blogs[i].id + ")'>";

                blog.appendChild(deletebtnwrap);
                blogswrap.appendChild(blog);
            }
        }

        function deleteBlog(id) {
            post("http://localhost:8080/yemublog/blog/deleteById",
                "id=" + id,
                function success(text) {
                    alert(text);
                    document.getElementById("blogs").innerHTML="";
                    getRecent();
                },
                function failed(text) {
                    alert(text);
                }
            );
        }
