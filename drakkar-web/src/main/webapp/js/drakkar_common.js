Element.prototype.remove = function() {
    this.parentElement.removeChild(this);
}

NodeList.prototype.remove = HTMLCollection.prototype.remove = function() {
    for(var i = this.length - 1; i >= 0; i--) {
        if(this[i] && this[i].parentElement) {
            this[i].parentElement.removeChild(this[i]);
        }
    }
}

function activateDatePicker() {
    $(".datePicker").each(function(i, obj) {
        $(this).datepicker( { dateFormat: 'dd/mm/yy' } );
    });
}

var headerHandler = ( function() {

    var _srvCallbk = function( loaderConfig, data ) {
        $('#usernameContainer').html( data.username );
    };

    var _callService = function() {
        pageLoader.callServiceAuthenticated( createLoaderConfig( "rest/userInfo/username" ), _srvCallbk );
    };

    // public api
    return {
        setUsername: _callService
    }
}());
