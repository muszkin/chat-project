import React from 'react';
import {View,AppRegistry} from "react-native";
import webstomp from 'webstomp-client';
import SockJS from "sockjs-client";


export default class ConnectionManager extends React.Component {

    constructor(props) {
        super(props);
        this.handler = props.handler;
        client = new WebSocket("ws://localhost:8080/register");
        console.log(client);
        client.onopen(function(ev){
            console.log(ev);
            this.websocket = webstomp.over(client);
            try {
                this.websocket.subscribe("/topic/private", function (greeting) {
                    props.handler(JSON.parse(greeting.body).content)
                });
                this.websocket.onmessage((msg) => {
                    this.handler(JSON.parse(msg.body).content);
                } )
            }catch (ex) {
                console.log(ex);
            }
        });
    }

    sendMessage = (msg) => {
        if (this.websocket.connected) {
            try {
                this.websocket.send('/app/chat', msg);
            } catch (ex) {
                console.log(ex);
            }
        }
    };


}

AppRegistry.registerComponent("ConnectionManager", () => ConnectionManager);
