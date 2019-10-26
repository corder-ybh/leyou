const HtmlWebpackPlugin = require('html-webpack-plugin');

module.exports={
    // 指定打包的入口文件
    entry:'./index.js',
    output : {
        // path: 输出目录
        // __dirname是相对于webpack.config.js配置文件的
        //绝对路径
        path : __dirname + "/dist",
        // 输出的js文件名
        filename:"build.js",
    },
    module: {
        rules : [{
            test : /\.css$/, // 通过正则表达式匹配所以的.css后缀的文件
            use: [ // 要使用的加载器，这两个顺序一定不能乱
                'style-loader',
                'css-loader'
            ]
        }]
    },
    plugins: [
        new HtmlWebpackPlugin ({
            title: '首页', //生成的页面标题<head><title>首页</title></head>
            filename: 'index.html', // dist目录下生成的文件名
            template: './index.html', //原来的index.html，作为模板
        })
    ]
};