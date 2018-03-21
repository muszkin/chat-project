<template>
  <section class="container">
    <TheChat />
    <TheBubble />
  </section>
</template>

<script>
  import TheChat from '~/components/TheChat.vue'
  import TheBubble from '~/components/TheBubble.vue'
  export default {
    components: {
      TheChat,
      TheBubble
    }
  }
</script>


<style>
  /*--------------------
  Body
  --------------------*/
  
  *,
  *::before,
  *::after {
    -webkit-box-sizing: border-box;
    box-sizing: border-box;
  }
  
  html,
  body {
    height: 100%;
  }
  
  body {
    background: linear-gradient(135deg, #044f48, #345093);
    background-size: cover;
    background: transparent;
    font-family: 'Avenir', 'Open Sans', sans-serif;
    font-size: 14px;
    line-height: 1.3;
    overflow: hidden;
  }
  
  .bg {
    width: 100%;
    height: 100%;
    top: 0;
    left: 0;
    z-index: 1;
    /* background: url('https://images.unsplash.com/photo-1451186859696-371d9477be93?crop=entropy&fit=crop&fm=jpg&h=975&ixjsv=2.1.0&ixlib=rb-0.3.5&q=80&w=1925') no-repeat 0 0;*/
    -webkit-filter: blur(80px);
    filter: blur(80px);
    -webkit-transform: scale(1.2);
    transform: scale(1.2);
    background: #fff;
  }
  
  .container {
    -webkit-box-shadow: 2px 10px 40px rgba(22, 20, 19, 0.4);
    box-shadow: 2px 10px 40px rgba(22, 20, 19, 0.4);
  }
</style>
