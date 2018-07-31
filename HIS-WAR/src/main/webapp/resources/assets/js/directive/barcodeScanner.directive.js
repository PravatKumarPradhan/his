angular.module("myApp")
    .directive('barcodeScanner', function () {
        return {
            restrict: 'A',
            scope: {
                callback: '=barcodeScanner',
            },
            link: function postLink(scope, iElement, iAttrs) {
                // Settings
                var zeroCode = 48;
                var nineCode = 57;
                var enterCode = 13;
                var minLength = 3;
                var delay = 100;

                // Variables
                var pressed = false;
                var chars = [];
                var enterPressedLast = false;
                
                jQuery(document).keypress(function (e) {

                    // Register characters and enter key
                    chars.push(String.fromCharCode(e.which));

                    enterPressedLast = (e.which === enterCode);

                    if (pressed == false) {
                        setTimeout(function () {
                            if (chars.length >= minLength && enterPressedLast) {
                                var barcode = chars.join('');

                                if (angular.isFunction(scope.callback)) {
                                    scope.$apply(function () {
                                        scope.callback(e, barcode);
                                    });
                                }
                            }
                            chars = [];
                            pressed = false;
                        }, delay);
                    }
                    pressed = true;
                });
            }
        };
    });