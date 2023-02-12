module.exports = {
	runtimeCompiler: true,
	devServer:{
		port: 8098,
		proxy: {
			"/api":{
				target: "http://localhost:8090/",
				changeOrigin: true,
				pathRewrite:{
					'^/api':'/admin'
				}
			}
			
		}
	},
	lintOnSave:false
}