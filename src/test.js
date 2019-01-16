new Vue({
    el: '#app',
    data: {
        test: 'hhhh'
    }
})
Vue.component('yemu-head-nav',{
    props:['list'],
    template: '<p>{{ list.text }}</p>'
})
var header=new Vue({
    //父组件
    el: '#header',
    data: {   
        list: [
          {id: 0,text: 'one'},
          {id: 1,text: 'two'},
          {id: 2,text: 'three'}
      ]
    }
  })