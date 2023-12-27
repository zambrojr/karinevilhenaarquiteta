/* eslint-disable */


/**
 * We'll load jQuery and the Bootstrap jQuery plugin which provides support
 * for JavaScript based Bootstrap features such as modals and tabs. This
 * code may be modified to fit the specific needs of your application.
 */

try {
    window.$ = window.jQuery = require('jquery');

    require('bootstrap');
} catch (e) {}


window.Popper = require('popper.js').default;
require("./lib/jquery.fancybox");
require("./lib/owl");
import WOW from 'wow.js/dist/wow.js';
global.WOW = WOW;
require('./lib/appear');
require("./lib/scrollbar")(window.$);
require('./lib/script');