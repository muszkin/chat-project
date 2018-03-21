<template>
  <div>
    <div v-if="isServer" class="message new">
        <figure class="avatar">
        <img src="https://avatars2.githubusercontent.com/u/7688113?s=88&v=4">
        </figure>{{content}} {{index}}
    </div>
    <div v-else class="message message-personal new"  v-bind:class="{ message_personal: isLastMessage }">{{content}}</div>
  </div>
</template>

<script>
  import { mapGetters } from 'vuex'
  export default {
    props: ['content', 'origin', 'index'],
    computed: {
      ...mapGetters([
        'getMessagesLength'
      ]),
      isServer () {
        return this.origin === 'server'
      },
      isLastMessage () {
        return this.getMessagesLength === this.index + 1
      }
    },
    mounted () {
      console.log('msg len' + this.getMessagesLength)
      console.log('msg id' + this.index)
      console.log(Number(this.getMessagesLength) === Number(this.index))
    }
  }
</script>

<style>
.messages .message.message-personal .avatar{
left: 60px !important;
}
</style>