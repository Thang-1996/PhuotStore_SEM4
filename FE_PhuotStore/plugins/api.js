export default function ({ $axios, redirect }, inject) {
  const axios = $axios.create({
    headers: {
      common: {
        Accept: 'application/json',
      },
    },
  })
  axios.setBaseURL(process.env.baseUrl)
  axios.onRequest((config) => {
    // eslint-disable-next-line no-console
    console.log('Making request to ' + config.url)
  })

  axios.onError((error) => {
    const code = parseInt(error.response && error.response.status)
    if (code === 400) {
      redirect('/400')
    }
  })
  const api = {
    login: (data) => axios.$post('/api/v1/auth/signin', data),
    register: (data) => axios.$post('/api/v1/auth/signup', data),
    categoryList: (config) => axios.$get('api/v1/categories', config),
    addCategory: (data, config) =>
      axios.$post('api/v1/categories/add', data, config),
    getCategory: (id, config) => axios.$get(`api/v1/categories/${id}`, config),
    updateCategory: (id, data, config) =>
      axios.$put(`api/v1/categories/update/${id}`, data, config),
    deleteCategory: (id, config) =>
      axios.$delete(`api/v1/categories/delete/${id}`, config),
    brandList: (config) => axios.$get('api/v1/brands', config),
    addBrand: (data, config) => axios.$post('api/v1/brands/add', data, config),
    getBrand: (id, config) => axios.$get(`api/v1/brands/${id}`, config),
    updateBrand: (id, data, config) =>
      axios.$put(`api/v1/brands/update/${id}`, data, config),
    deleteBrand: (id, config) =>
      axios.$delete(`api/v1/brands/delete/${id}`, config),
  }
  inject('api', api)
}
