// https://v3.nuxtjs.org/api/configuration/nuxt.config
export default defineNuxtConfig({
    static: {
        prefix: false
    },
    meta: {
        link: [
            { rel: 'stylesheet', href: '/css/owl.carousel.min.css' },
            { rel: 'stylesheet', href: '/css/bootstrap.css' },
            { rel: 'stylesheet', href: '/fonts/ionicons/css/ionicons.min.css' },
            { rel: 'stylesheet', href: '/fonts/fontawesome/css/font-awesome.min.css' },
            { rel: 'stylesheet', href: '/fonts/flaticon/font/flaticon.css' },
            { rel: 'stylesheet', href: '/css/style.css' },
        ],
        script: [
            {type: 'text/javascript', src: '/js/jquery-3.2.1.min.js'},
            {type: 'text/javascript', src: '/js/jquery-migrate-3.0.0.js'},
            {type: 'text/javascript', src: '/js/popper.min.js'},
            {type: 'text/javascript', src: '/js/bootstrap.min.js'},
            {type: 'text/javascript', src: '/js/owl.carousel.min.js'},
            {type: 'text/javascript', src: '/js/jquery.waypoints.min.js'},
            {type: 'text/javascript', src: '/js/jquery.stellar.min.js'},
            {type: 'text/javascript', src: '/js/main.js'},
        ]
    }

})
