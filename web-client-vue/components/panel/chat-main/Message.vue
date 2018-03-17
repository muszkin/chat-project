<template>
  <!-- <div >
        <div class="message">halko</div>
        <div class="message new">halko</div>
        <div class="message checkmark-read">halko</div>
        <div class="checkmark-read">V</div>
        <div class="timestamp">11:12</div>
        <div class="message loading"></div>
        <div class="message message-personal">
            odp
            <div class="avatar">
                <img src="https://avatars2.githubusercontent.com/u/7688113?s=88&v=4" alt="">
            </div>
        </div>
        <div class="message ">
            tete
            <div class="avatar">
                <img src="https://avatars2.githubusercontent.com/u/7688113?s=88&v=4" alt="">
            </div>
        </div>
    </div> -->
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
        <img src="/man.png" alt="">
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