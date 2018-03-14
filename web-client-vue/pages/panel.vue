<template>
  <v-layout row mt-4> 
    <TheUserList />
    <TheChat />
    <TheUserInfo />
  </v-layout >


</template>


<script>
import TheUserList from '../components/panel/TheUserList'
import TheUserInfo from '../components/panel/TheUserInfo'
import TheChat from '../components/panel/TheChat'
export default {
  components: {
    TheUserList,
    TheUserInfo,
    TheChat
  }
}
</script>

