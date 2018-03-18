//
//  ViewController.swift
//  chat-mobile
//
//  Created by Piotr Mucha on 18.03.2018.
//  Copyright © 2018 Piotr Mucha. All rights reserved.
//

import UIKit
import StompClientLib

class ViewController: UIViewController,StompClientLibDelegate {
    
    @IBOutlet weak var chatView: UITextView!
    @IBOutlet weak var msgBox: UITextField!
    var socketClient = StompClientLib()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        let url = NSURL(string: "ws://34.248.53.38:8080/register/websocket")
        socketClient.openSocketWithURLRequest(request: NSURLRequest(url: url! as URL), delegate: self as StompClientLibDelegate)
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    @IBAction func sendTouched() {
        let msg = ["content":msgBox.text]
        socketClient.sendJSONForDict(dict: msg as AnyObject, toDestination: "/app/chat")
        chatView.insertText("You:\(String(describing: msgBox.text))")
    }
    
    func stompClientDidConnect(client: StompClientLib!) {
        print("Socket is connected")
        client.subscribe(destination: "/topic/private" )
        let msg = ["name":"muszkin"]
        client.sendJSONForDict(dict: msg as AnyObject, toDestination: "/app/hello")
        let msg2 = ["content":"hello"]
        client.sendJSONForDict(dict: msg2 as AnyObject, toDestination: "/app/chat")
        
        print(client.isConnected())
    }

    func stompClientDidDisconnect(client: StompClientLib!) {
        print("Socket is Disconnected")
    }
    
    func stompClientWillDisconnect(client: StompClientLib!, withError error: NSError) {
        
    }
    
    func stompClient(client: StompClientLib!, didReceiveMessageWithJSONBody jsonBody: AnyObject?, withHeader header: [String : String]?, withDestination destination: String) {
        print("Destination : \(destination)")
        print("JSON Body : \(String(describing: jsonBody))")
        chatView.insertText("Response:\(String(describing: jsonBody))")
    }
    
    func stompClientJSONBody(client: StompClientLib!, didReceiveMessageWithJSONBody jsonBody: String?, withHeader header: [String : String]?, withDestination destination: String) {
        print("DESTINATION : \(destination)")
        print("String JSON BODY : \(String(describing: jsonBody))")
    }
    
    func serverDidSendReceipt(client: StompClientLib!, withReceiptId receiptId: String) {
        print("Receipt : \(receiptId)")
    }
    
    func serverDidSendError(client: StompClientLib!, withErrorMessage description: String, detailedErrorMessage message: String?) {
        print("Error Send : \(String(describing: message))")
    }
    
    func serverDidSendPing() {
        print("Server ping")
    }

}

