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
  <div>
    <div v-if="isServer" class="message ">
      {{content}}
      <div class="avatar">
        <img src="/aws.png" alt="">
      </div>
    </div>
    <div v-else class="message message-personal">
      {{content}}
      <div class="avatar">
        <img src="/man.png" alt="">
      </div>
    </div>
  </div>
</template>

<script>
  export default {
    props: ['content', 'origin'],
    computed: {
      isServer () {
        return this.origin === 'server'
      }
    }
  }
</script>

<style scoped>
.messages .message.message-personal .avatar{
left: 60px;
}
</style>