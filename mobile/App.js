import React from 'react';
import {StyleSheet, Text, View, Button} from 'react-native';
import ConnectionManager from "./ConnectionManager";

export default class App extends React.Component {

    state = {
        "msg": null
    };

    msgHandler(msg) {
        this.state.msg = msg;
    };

    render() {
    let socketServer = new ConnectionManager({handler: this.msgHandler});
    if (socketServer.connected) {
        socketServer.sendMessage('Hello server');
    }
        let response = this.state.msg;
        return (
          <View style={styles.container}>
            <Text>Msg:{response}</Text>
          </View>
        );
    }
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#fff',
    alignItems: 'center',
    justifyContent: 'center',
  },
});
