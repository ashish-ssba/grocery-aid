const path = require('path');
const VueLoaderPlugin = require('vue-loader/lib/plugin')
const ExtractTextPlugin = require('extract-text-webpack-plugin')

const devOptions = process.env.NODE_ENV === 'development' ? 'inline-source-map' : false;

module.exports = {
    entry: './main.js',
    devtool: devOptions,
    resolve: {
        alias: {
            components: path.resolve(__dirname, 'components')
        },
        extensions: ['.vue', '.js']
    },
    module: {
        rules: [{
            test: /\.vue$/,
            loader: 'vue-loader'
        }, {
            test: /\.scss$/,
            use: ExtractTextPlugin.extract({
                fallback: 'style-loader',
                use: [
                    'css-loader',
                    'sass-loader'
                ]
            })
        }, {
            test: /\.css$/,
            loader: 'css-loader'
        }]
    },
    plugins: [
        new VueLoaderPlugin(),
        new ExtractTextPlugin('../css/app.css')
    ],
    output: {
        filename: 'app.js',
        path: path.resolve(__dirname, '../resources/public/js')
    }
};
