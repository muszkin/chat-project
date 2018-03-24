<template>
 <v-card height="60px" flat class="navigation">
  
    <!-- <v-bottom-nav absolute :value="true" :active.sync="e1" color="transparent">
      <v-btn flat color="#fff" value="chat">
        <span>Chats</span>
        <v-icon>chat</v-icon>
      </v-btn>
      <v-btn flat color="#fff" value="contacrs">
        <span>Broadcast</span>
        <v-icon>radio</v-icon>
      </v-btn>
      <v-btn flat color="#fff" value="channels">
        <span>Channels</span>
         <v-icon>reorder</v-icon>
      </v-btn>
      <v-btn flat color="#fff" value="favourites">
        <span>Favourites</span>
        <v-icon>star</v-icon>
      </v-btn>
    </v-bottom-nav> -->
  </v-card>
</template>

<script>
  export default {
    data () {
      return {
        e1: 'chat'
      }
    }
  }
</script>

<style>
.navigation {
    color: #ffffff;
  background-color: #424242;
  border-top: 1px solid rgb(133, 133, 133);
  border-radius: 5px;
  margin: 0px 1px;
}
</style>
