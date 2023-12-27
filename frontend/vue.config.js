
const webpack = require('webpack')
module.exports = {
  runtimeCompiler: true,
    configureWebpack: {
      plugins: [
        new webpack.IgnorePlugin(/^\.\/locale$/, /moment$/)
      ],
      optimization: {
        splitChunks: false
      }
    },
    filenameHashing: false,
    outputDir:"dist/static",
    pages: {
      index: {
        entry: 'src/public.js',
        filename: 'index.html'
      },
      intra: {
        entry: 'src/main.js',
        // the source template
        template: 'public/home.html',        
        filename: 'home.html'
      },
      
      /*index: {
        entry: 'src/main.js',
        filename: 'index.html'
      }*/
    }
  }