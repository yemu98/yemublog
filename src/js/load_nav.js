$(document).ready(function () { //html页面加载完执行以下内容
    $.ajax({//获取导航栏项
        url: "../php/get_nav.php",
        type: "get",
        dataType: "json",
        success: function (data) {
            // console.log(data);
            // alert(data[0].nav_item);
            for (var i in data) {
                head.add_item(data[i].nav_item, data[i].link);
            }
            // var str=JSON.stringify(data);
            // alert(str);//转成字符串
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            alert(XMLHttpRequest.status + XMLHttpRequest.readyState + textStatus);
        },
    });
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
});