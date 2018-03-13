<template>
  <v-layout row wrap>
    <v-flex xs12>
      <v-card class="user-searchbar">
        <v-container fluid grid-list-lg>
            <v-layout row>
              <v-flex xs12>
               <div class="element-search">
                   <input placeholder="Search users by name..." type="text">
                   <span><v-icon>search</v-icon></span>
                </div>
              </v-flex>
          </v-layout>
        </v-container>
      </v-card>
    </v-flex>
  </v-layout>
  </template>

  <style scoped>
   .user-searchbar{
         color: #0e0e0e;
    background-color: #CFD8DC;
    border: 1px solid #eeeeee;
    position: relative;
   }

   .element-search input {
       background: #f6f7f8;
       padding: 2px 15px;
       border-radius: 50px;
   }
   .element-search i {
       position: absolute;
       right: 20px;
       font-size: 25px;
       cursor: pointer;
       transition: 0.2s all ease-in;
   }

    .element-search i:hover {
        transform: scale(1.5)
    }
   
  </style>
  