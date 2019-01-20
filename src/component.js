Vue.component('yemu-header-nav-item', {
    props: ['nav_item'],
    template: '<div class=header_nav_item><a href="#">{{ nav_item.item }}</a></div>'
})
Vue.component('yemu-post',{
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