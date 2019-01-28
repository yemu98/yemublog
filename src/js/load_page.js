$(document).ready(function () { //html页面加载完执行以下内容
    $.ajax({
        url: "../php/get_nav.php",
        type: "get",
        dataType: "json",
        success: function (data) {
            for (var i in data) {
                head.add_item(data[i].nav_item, data[i].link);
            }
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            alert(XMLHttpRequest.status + XMLHttpRequest.readyState + textStatus);
        },
    });
    $.ajax({
        url: "../php/get_post_info.php",
        type: "get",
        dataType: "json",
        success: function (data) {
            for (var i in data) {
                if (i<10){
                    main_post.add_post(data[i]);//写入页面
                }
                else{
                    break;
                }
            }
            window.onscroll=function(){
                if (i>=data.length){
                    // alert('没有了');
                    window.onscroll="";
                }
                else if ($(document).height()-$(document).scrollTop()-$(window).height()<=50){
                main_post.add_post(data[i]);//写入页面
                i++;
                }
            }
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            alert(XMLHttpRequest.status + XMLHttpRequest.readyState + textStatus);
        },
    })
    var head = new Vue({ //加载导航栏
        el: '#header_nav',
        data() {
            return {
                nav_list: []
            }
        },
        methods: {
            add_item: function (head, link) {
                // console.log(this.nav_list.length);
                this.nav_list.push({
                    id: this.nav_list.length,
                    item: head,
                    link: link
                })
            }
        },
    })
    var main_post = new Vue({ //加载post
        el: '#main_post',
        data() {
            return {
                post_infos: []
            }
        },
        methods: {
            add_post: function (post_info) {
                this.post_infos.push(post_info)
            }
        },
    })
});