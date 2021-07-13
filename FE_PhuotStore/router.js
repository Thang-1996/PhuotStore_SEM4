import Router from 'vue-router'
const subdomains = [
  ...require('./nuxt.config').default.subdomains,
  process.env.ROOT_DOMAIN,
]

export function createRouter(ssrContext, createDefaultRouter, routerOptions) {
  const options = routerOptions || createDefaultRouter(ssrContext).options
  const hostname = ssrContext ? ssrContext.req.headers.host : location.host
  const matcher = hostname.match(
    /^(\w+(-\w+)?)\.(localhost|\w+(-\w+)?)(\.\w+)?/
  ) || [subdomains[subdomains.length - 1]]
  const routesDirectory = matcher[1] || matcher[0]

  function isUnderDirectory(route, directory) {
    let isUnderDir = false
    const path = route.path
    if (typeof directory === 'object') {
      // eslint-disable-next-line array-callback-return
      directory.map((dir) => {
        if (path === '/' + dir || path.startsWith('/' + dir + '/')) {
          isUnderDir = true
        }
      })
      return isUnderDir
    } else {
      return path === '/' + directory || path.startsWith('/' + directory + '/')
    }
  }

  let newRoutes = options.routes
  if (routesDirectory) {
    newRoutes = options.routes
      .filter((route) => {
        // remove routes from other directories
        const toRemove = subdomains.filter((domain) => {
          return domain !== routesDirectory
        })
        return !isUnderDirectory(route, toRemove)
      })
      .map((route) => {
        // remove directory from path and route-name
        if (isUnderDirectory(route, routesDirectory)) {
          return {
            ...route,
            path: route.path.substr(routesDirectory.length + 1) || '/',
            name: route.name.substr(routesDirectory.length + 1) || 'index',
          }
        }
        return route
      })
  }

  return new Router({
    ...options,
    routes: newRoutes,
  })
}
