<template>
    <div v-on:click.prevent="toggleChatOpen" class="bubble">...</div>
</template>

<script>
export default {
    methods: {
        toggleChatOpen() {
            this.$store.commit('toogleChatOpen')
        }
    }
}
</script>

<style scoped>
 div.bubble {
    font-size: 36px;
    font-weight: 800;
    position: fixed;
    bottom: 10px;
    right: 20px;
    z-index: 2147483638;
    width: 62px;
    height: 62px;
    border-radius: 100%;
    cursor: pointer;
    -webkit-transition: 0.3s ease;
    -o-transition: 0.3s ease;
    transition: 0.3s ease;
    color: #fff;
    background-color: #777;
    text-align: center;
 }
</style>
