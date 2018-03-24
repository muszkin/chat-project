<template>
  <div style="position:relative">
    <div v-if="isServer" class="message" v-bind:class="{ new: isLastMessage }">
      {{content}} {{index}}
      <div class="avatar">
        <img src="/aws.png" alt="">
      </div>
    </div>
    <div v-else class="message message-personal"  v-bind:class="{ new: isLastMessage }">
      {{content}}
      <div class="avatar">
        <img :src="getActiveUser.avatar" alt="">
      </div>
    </div>
  </div>
</template>

<script>
  import { mapGetters } from 'vuex'
  export default {
    props: ['content', 'origin', 'index'],
    computed: {
      ...mapGetters([
        'getMessagesLength',
        'getActiveUser'
      ]),
      isServer () {
        return this.origin === 'server'
      },
      isLastMessage () {
        return this.getMessagesLength === this.index + 1
      }
    },
    mounted () {
    }
  }
</script>

<style>
.messages .message.message-personal .avatar{
left: 60px !important;
}
</style>