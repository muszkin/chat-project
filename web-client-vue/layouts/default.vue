<template>
  <v-app baseline>

    <v-toolbar color="elevation-0" fixed app>
      <v-btn flat nuxt to="/">
        <v-toolbar-title v-text="title" ></v-toolbar-title>
      </v-btn>
        <v-spacer />  
        <!-- <v-btn flat color="primary" nuxt to="/signUp">SIGN UP</v-btn> -->
        <!-- <v-btn flat color="primary" nuxt to="/pricing">PRICING</v-btn> -->
        <!-- <v-btn flat color="primary" nuxt to="/about">ABOUT</v-btn> -->
        <!-- <v-btn flat color="primary" nuxt to="/login">LOGIN</v-btn> -->
        <v-btn flat color="primary" nuxt to="/panel">Dashboard</v-btn>
         <v-btn flat color="primary" nuxt to="/settings">Settings</v-btn>
    </v-toolbar>
    <v-content>
      <v-container>
        <nuxt />
      </v-container>
    </v-content>
  </v-app>
</template>

<script>
export default {
  data () {
    return {
      title: 'czat'
    }
  }
}
</script>
