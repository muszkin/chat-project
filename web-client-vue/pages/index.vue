<template>
  <v-layout column justify-center align-center justify-space-around>
    <v-flex xs12 sm8 md6>
     
      <v-card class="card-centered">
         <div class="text-xs-center">
            <img src="/golab.png" alt="Vuetify.js" class="mt-3 mb-1 logo" />
         </div>
        <v-card-title class="headline text-xs-center">ePidgeon</v-card-title>
        <v-card-text>
          <p>Odwiedzający stronę czekają na Twoje wsparcie</p>
          <p>Czat to najszybsza forma komunikacji z klientem - jest znacznie szybszy niż e-mail i dużo bardziej efektywy niż telefon. Nie każ swoim klientom czekać!</p>
          <h3 class="mt-3">Ten czat to sekret mojego sukcesu</h3>
          <div class="text-xs-right">
            <em><small>&mdash; Mr. Succes</small></em>
          </div>
        </v-card-text>
        <v-card-actions >
          <v-flex class="text-xs-center">
            <v-btn color="primary" flat nuxt to="/signUp" class="menu-btns">Zacznij</v-btn>
            <v-btn color="primary" flat nuxt to="/about" class="menu-btns">Info</v-btn>
          </v-flex>
        </v-card-actions>
      </v-card>
    </v-flex>
  </v-layout>
</template>

<style scoped>
  .card-centered{
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    max-width: 600px;
    min-width: 500px;
   }

   .logo {
     max-width: 200px;
   }

   .headline {
     display: block;
   }

   .menu-btns{
     border: 2px solid;
     margin: 20px 20px;
     font-size: 20px;
     padding: 20px 20px;
   }
</style>

