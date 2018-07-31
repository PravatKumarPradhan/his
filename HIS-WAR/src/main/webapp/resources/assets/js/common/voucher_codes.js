/**@author  Ganesh Chaudhari */

;(function() {
    "use strict";

    var root = this;

    function randomInt(min, max) {
        return Math.floor(Math.random() * (max - min + 1)) + min;
    }

    function randomElem(arr) {
        return arr[randomInt(0, arr.length - 1)];
    }

    function charset(name) {
        var charsets = {
            numbers: "0123456789",
            alphabetic: "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ",
            alphanumeric: "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"
        };
        return charsets[name];
    }

    function repeat(str, count)
    {
        var res = "";
        for (var i = 0; i < count; i++) {
            res += str;
        }
        return res;
    }
    
    function convertIntToString(number)
    {
       return number+"";
    }
    
    function generateNumber(str, length) 
    {
        var number = "";
        var numberLength = str.length;
        try{
        if(str!=null)
        {
          for (var i = 0; i < (length-numberLength); i++) 
          {
        	number += "0";
          }
          number+=str;
        }
        else{
        	number = 0;
        }
        }
        catch(e)
        {
        	console.log(e.message)
        }
        return number;
    }

    function Config(config) 
    {
        config = config || {};
        this.count = config.count || 1;
        this.length = config.length || 8;
        this.prefix = config.prefix || "";
        this.postfix = config.postfix || "";
        this.pattern = config.pattern || repeat("#", this.length);
        this.inputNumber = convertIntToString(config.inputNumber)||'1';
    }

    function generateOne(config) {
        var code = generateNumber(config.inputNumber,config.length);
        return config.prefix + code + config.postfix;
    }

    function isFeasible(pattern, count) {
        return Math.pow((pattern.match(/#/g) || []).length) >= count;
    }

    function generate(config) {
        config = new Config(config);
        var count = config.count;

        var codes = {};
        while (count > 0)
        {
            var code = generateOne(config);
            if (codes[code] === undefined) {
                codes[code] = true;
                count--;
            }
        }
        return Object.keys(codes);
    }

    var voucher_codes = {
        generate: generate,
        charset: charset
    };

    if (typeof exports !== 'undefined') {
        if (typeof module !== 'undefined' && module.exports) 
        {
            exports = module.exports = voucher_codes;
        }
        exports = voucher_codes;
    } else {
        root.voucher_codes = voucher_codes;
    }
}).call(this);