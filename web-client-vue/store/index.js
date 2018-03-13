export const state = () => ({
  sidebar: false,
  userList: [
    {
      userName: 'Adam',
      userSurname: 'Małecki',
      messagesCount: 10,
      lastMessage: 'Lorem, ipsum dolor sit amet consectetur adipisicing elit. Recusandae inventore veniam illo veritatis quia.',
      imageSrc: '/man.png'
    },
    {
      userName: 'Adam',
      userSurname: 'Małysz',
      messagesCount: 0,
      lastMessage: 'Lorem, ipsum dolor sit amet consectetur adipisicing elit. Recusandae inventore veniam illo veritatis quia.',
      imageSrc: '/logo.png'
    },
    {
      userName: 'Adam',
      userSurname: 'Małecki',
      messagesCount: 10,
      lastMessage: 'Lorem, ipsum dolor sit amet consectetur adipisicing elit. Recusandae inventore veniam illo veritatis quia.',
      imageSrc: '/man.png'
    },
    {
      userName: 'Adam',
      userSurname: 'Małysz',
      messagesCount: 0,
      lastMessage: 'Lorem, ipsum dolor sit amet consectetur adipisicing elit. Recusandae inventore veniam illo veritatis quia.',
      imageSrc: '/logo.png'
    },
    {
      userName: 'Adam',
      userSurname: 'Małecki',
      messagesCount: 10,
      lastMessage: 'Lorem, ipsum dolor sit amet consectetur adipisicing elit. Recusandae inventore veniam illo veritatis quia.',
      imageSrc: '/man.png'
    },
    {
      userName: 'Adam',
      userSurname: 'Małysz',
      messagesCount: 0,
      lastMessage: 'Lorem, ipsum dolor sit amet consectetur adipisicing elit. Recusandae inventore veniam illo veritatis quia.',
      imageSrc: '/logo.png'
    },
    {
      userName: 'Adam',
      userSurname: 'Małecki',
      messagesCount: 10,
      lastMessage: 'Lorem, ipsum dolor sit amet consectetur adipisicing elit. Recusandae inventore veniam illo veritatis quia.',
      imageSrc: '/man.png'
    },
    {
      userName: 'Adam',
      userSurname: 'Małysz',
      messagesCount: 0,
      lastMessage: 'Lorem, ipsum dolor sit amet consectetur adipisicing elit. Recusandae inventore veniam illo veritatis quia.',
      imageSrc: '/logo.png'
    },
    {
      userName: 'Adam',
      userSurname: 'Małecki',
      messagesCount: 10,
      lastMessage: 'Lorem, ipsum dolor sit amet consectetur adipisicing elit. Recusandae inventore veniam illo veritatis quia.',
      imageSrc: '/man.png'
    },
    {
      userName: 'Adam',
      userSurname: 'Małysz',
      messagesCount: 0,
      lastMessage: 'Lorem, ipsum dolor sit amet consectetur adipisicing elit. Recusandae inventore veniam illo veritatis quia.',
      imageSrc: '/logo.png'
    },
    {
      userName: 'Adam',
      userSurname: 'Małecki',
      messagesCount: 10,
      lastMessage: 'Lorem, ipsum dolor sit amet consectetur adipisicing elit. Recusandae inventore veniam illo veritatis quia.',
      imageSrc: '/man.png'
    },
    {
      userName: 'Adam',
      userSurname: 'Małysz',
      messagesCount: 0,
      lastMessage: 'Lorem, ipsum dolor sit amet consectetur adipisicing elit. Recusandae inventore veniam illo veritatis quia.',
      imageSrc: '/logo.png'
    },
    {
      userName: 'Adam',
      userSurname: 'Małecki',
      messagesCount: 10,
      lastMessage: 'Lorem, ipsum dolor sit amet consectetur adipisicing elit. Recusandae inventore veniam illo veritatis quia.',
      imageSrc: '/man.png'
    },
    {
      userName: 'Adam',
      userSurname: 'Małysz',
      messagesCount: 0,
      lastMessage: 'Lorem, ipsum dolor sit amet consectetur adipisicing elit. Recusandae inventore veniam illo veritatis quia.',
      imageSrc: '/logo.png'
    },
    {
      userName: 'Adam',
      userSurname: 'Małecki',
      messagesCount: 10,
      lastMessage: 'Lorem, ipsum dolor sit amet consectetur adipisicing elit. Recusandae inventore veniam illo veritatis quia.',
      imageSrc: '/man.png'
    },
    {
      userName: 'Adam',
      userSurname: 'Małysz',
      messagesCount: 0,
      lastMessage: 'Lorem, ipsum dolor sit amet consectetur adipisicing elit. Recusandae inventore veniam illo veritatis quia.',
      imageSrc: '/logo.png'
    },
    {
      userName: 'Adam',
      userSurname: 'Małecki',
      messagesCount: 10,
      lastMessage: 'Lorem, ipsum dolor sit amet consectetur adipisicing elit. Recusandae inventore veniam illo veritatis quia.',
      imageSrc: '/man.png'
    },
    {
      userName: 'Adam',
      userSurname: 'Małysz',
      messagesCount: 0,
      lastMessage: 'Lorem, ipsum dolor sit amet consectetur adipisicing elit. Recusandae inventore veniam illo veritatis quia.',
      imageSrc: '/logo.png'
    },
    {
      userName: 'Pan',
      userSurname: 'Kupujący',
      messagesCount: 3,
      lastMessage: 'Lorem, ipsum dolor sit amet consectetur adipisicing elit. Recusandae inventore veniam illo veritatis quia.',
      imageSrc: '/v.png'
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
