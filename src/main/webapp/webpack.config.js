const path = require('path');
const VueLoaderPlugin = require('vue-loader/lib/plugin')
const ExtractTextPlugin = require('extract-text-webpack-plugin')

const devOptions = process.env.NODE_ENV === 'development' ? 'inline-source-map' : false;

module.exports = {
    entry: './main.ts',
    devtool: devOptions,
    resolve: {
        alias: {
            components: path.resolve(__dirname, 'components')
        },
        extensions: ['.vue', '.js', '.ts']
    },
    module: {
        rules: [{
            test: /\.ts$/,
            loader: 'ts-loader',
            exclude: /node_modules/,
            options: {
                appendTsSuffixTo: [/\.vue$/]
            }
        }, {
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
