<template>
  <div class="hello">
    <p>{{lable}}</p>
    <button @click="switchLang">切换</button>
  </div>
</template>

<script>
  import {mapState} from 'vuex'
export default {
  name:'HelloWorld',
  components:{

  },
  data(){
    return{

    }
  },
  computed:{
    ...mapState(['lang']),
    lable(){
      return this.$t("message.hello")
    }
  },
  methods:{
    switchLang(){
      let lang=''
      this.lang==='en'?lang='cn':lang='en'
      this.$store.commit('switchLang',lang)
    }
  }

}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h3 {
  margin: 40px 0 0;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
</style>
