Vue.component('yemu-header-nav-item', {//导航栏
    props: ['nav_item'],
    template: '<div class=header_nav_item><a :href="nav_item.link">{{ nav_item.item }}</a></div>'
})
Vue.component('yemu-post-info',{//首页的post简介
    props:['post_info'],
    template: ` 
    <div class="post">
       <div class="post_title"><a :href="post_info.link">{{ post_info.title }}</a></div>
       <div class="post_date">{{ post_info.date}}</div>
<div class="post_brief">{{ post_info.brief }}</div>
<div class="post_all"><a :href="post_info.link">阅读全文...</a></div>
</div>
`//模板字符串使用反引号 (` `) 来代替普通字符串中的用双引号和单引号。
})
Vue.component('yemu_post',{//详情页的post正文
    props:['post_date','headline','main_text'],
    template: `<div><div class="post_date">{{ post_date }}</div>
        <div class="post_headline">{{ headline }}</div>
        <yemu-post-para v-for="(para, index) in text" :key="index" :para="para"></yemu-post-para></div>`,
        computed: {
        text: function () {//对文本处理，遇到换行符\n，生成一段
            var strs=new Array(), //定义一数组
            strs=this.main_text.split("\n") //字符分割 
            return strs;
        }
    },
} )
Vue.component('yemu-post-para',{//post段落
    props:['para'],
    template :'<div class="post_main_text"><p>{{ para }}</p></div>'
})