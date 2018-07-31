angular
    .module("myApp")
    .filter('sumByColumn', function () {
        return function (collection, column) {
            var total = 0;

            if(collection)
            collection.forEach(function (item) {
                total += parseFloat(item[column]);
            });

            return total;
        };
    });

    angular
    .module("myApp")
    .filter('sumByColumnSubColumn', function () {
        
        return function (collection, column) {
            var total = 0;
            if(collection)
            collection.forEach(function (item) {
                total += parseFloat(item[column]);

                if(item.selectedItems)
                item.selectedItems.forEach(function (subitem) {
                    total += parseFloat(subitem[column]);
                });
            });

            return total;
        };
    });