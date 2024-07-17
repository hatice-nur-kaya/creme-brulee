const {defineConfig} = require('@vue/cli-service')
module.exports = defineConfig({
    transpileDependencies: true,
    configureWebpack: {
        devServer: {
            port: 8081,
            proxy: {
                '^/(login|logout|home|api)': {
                    target: 'http://localhost:8080',
                    ws: true,
                    changeOrigin: true,
                    autoRewrite: true,
                },
            }
        }
    }
})
