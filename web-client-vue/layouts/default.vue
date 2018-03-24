<template>
  <v-app baseline>

    <v-toolbar color="elevation-0" fixed app>
      <v-btn flat nuxt to="/">
        <v-toolbar-title v-text="title" ></v-toolbar-title>
      </v-btn>
        <v-spacer />  
        <v-btn class="btn-chat" flat nuxt to="/panel">Dashboard</v-btn>
         <v-btn flat nuxt to="/settings">Settings</v-btn>
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
      title: 'Chat Project'
    }
  }
}
</script>