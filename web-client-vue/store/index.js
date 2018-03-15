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
    },
    {
      userName: 'Super',
      userSurname: 'User',
      messagesCount: 3,
      lastMessage: 'Lorem, ipsum dolor sit amet consectetur adipisicing elit. Recusandae inventore veniam illo veritatis quia.',
      imageSrc: '/man.png',
      lastSeen: '15 min'
    },
    {
      userName: 'That',
      userSurname: 'User',
      messagesCount: 1,
      lastMessage: 'Lorem, ipsum dolor sit amet consectetur adipisicing elit. Recusandae inventore veniam illo veritatis quia.',
      imageSrc: '/man.png',
      lastSeen: 'Less than 1 min'
    },
    {
      userName: 'Cool',
      userSurname: 'User',
      messagesCount: 10,
      lastMessage: 'Lorem, ipsum dolor sit amet consectetur adipisicing elit. Recusandae inventore veniam illo veritatis quia.',
      imageSrc: '/man.png',
      lastSeen: '2 hours'
    },
    {
      userName: 'Super',
      userSurname: 'User',
      messagesCount: 3,
      lastMessage: 'Lorem, ipsum dolor sit amet consectetur adipisicing elit. Recusandae inventore veniam illo veritatis quia.',
      imageSrc: '/man.png',
      lastSeen: '15 min'
    },
    {
      userName: 'That',
      userSurname: 'User',
      messagesCount: 1,
      lastMessage: 'Lorem, ipsum dolor sit amet consectetur adipisicing elit. Recusandae inventore veniam illo veritatis quia.',
      imageSrc: '/man.png',
      lastSeen: 'Less than 1 min'
    },
    {
      userName: 'Cool',
      userSurname: 'User',
      messagesCount: 10,
      lastMessage: 'Lorem, ipsum dolor sit amet consectetur adipisicing elit. Recusandae inventore veniam illo veritatis quia.',
      imageSrc: '/man.png',
      lastSeen: '2 hours'
    },
    {
      userName: 'Super',
      userSurname: 'User',
      messagesCount: 3,
      lastMessage: 'Lorem, ipsum dolor sit amet consectetur adipisicing elit. Recusandae inventore veniam illo veritatis quia.',
      imageSrc: '/man.png',
      lastSeen: '15 min'
    },
    {
      userName: 'That',
      userSurname: 'User',
      messagesCount: 1,
      lastMessage: 'Lorem, ipsum dolor sit amet consectetur adipisicing elit. Recusandae inventore veniam illo veritatis quia.',
      imageSrc: '/man.png',
      lastSeen: 'Less than 1 min'
    }
  ]
})
export const getters = {
  getUserList (state) {
    return state.userList
  }
}
export const mutations = {
  toggleSidebar (state) {
    state.sidebar = !state.sidebar
  }
}
