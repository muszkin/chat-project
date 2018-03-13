<template>
  <v-app dark>

    <v-toolbar color="elevation-0" fixed app>
      <v-btn flat nuxt to="/">
        <v-toolbar-title v-text="title" ></v-toolbar-title>
      </v-btn>
        <v-spacer />  
        <v-btn flat color="primary" nuxt to="/signUp">SIGN UP</v-btn>
        <v-btn flat color="primary" nuxt to="/pricing">PRICING</v-btn>
        <v-btn flat color="primary" nuxt to="/about">ABOUT</v-btn>
        <v-btn flat color="primary" nuxt to="/login">LOGIN</v-btn>
        <v-btn flat color="primary" nuxt to="/panel">PANEL</v-btn>
    </v-toolbar>
    <v-content>
      <v-container>
        <nuxt />
      </v-container>
    </v-content>

    <v-footer :fixed="fixed" app>
      <span>&copy; 2018</span>
    </v-footer>
  </v-app>
</template>

<script>
export default {
  data () {
    return {
      title: '🐦'
    }
  }
}
</script>
