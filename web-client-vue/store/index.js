export const strict = false
export const state = () => ({
  sidebar: false,
  userList: [
    {
      userName: 'Cool',
      userSurname: 'User',
      messagesCount: 10,
      lastMessage: 'Lorem, ipsum dolor sit amet consectetur adipisicing elit. Recusandae inventore veniam illo veritatis quia.',
      imageSrc: '/man.png',
      lastSeen: '2 hours'
    }
  ],
  messages: [
    {
      content: 'Welcome to our chat',
      origin: 'server'
    },
    {
      content: 'No elo',
      origin: 'self'
    }
  ]
})
export const getters = {
  getUserList (state) {
    return state.userList
  },
  getMessages (state) {
    return state.messages
  },
  getMessagesLength (state) {
    return state.messages.length
  }
}
export const mutations = {
  toggleSidebar (state) {
    state.sidebar = !state.sidebar
  },
  newMessage (state, message) {
    state.messages.push(message)
  }
}
