

var loginHandler = ( function() {

    var KEY_service_key = "service_key";
    var KEY_auth_token = "auth_token";

    var getHtmlContainer = function() {
        return $('#application-container');
    };

    var _setServiceKey = function(serviceKey ) {
        document.cookie = KEY_service_key+"="+serviceKey;
    };

    var _setAuthToken = function(authTkn ) {
        document.cookie = KEY_auth_token+"="+authTkn;
    };

    var getCookieByName = function(cname) {
        var name = cname + "=";
        var decodedCookie = decodeURIComponent(document.cookie);
        var ca = decodedCookie.split(';');
        for(var i = 0; i <ca.length; i++) {
            var c = ca[i];
            while (c.charAt(0) == ' ') {
                c = c.substring(1);
            }
            if (c.indexOf(name) == 0) {
                return c.substring(name.length, c.length);
            }
        }
        return "";
    }

    var _getServiceKey = function() {
        return getCookieByName( KEY_service_key );
    };

    var _getAuthToken = function() {
        return getCookieByName( KEY_auth_token );
    };

    // public api
    return {
        setServiceKey: _setServiceKey,
        getServiceKey : _getServiceKey,
        setAuthToken: _setAuthToken,
        getAuthToken: _getAuthToken
    }
}());

//extend pageLoader object
pageLoader.callServiceAuthenticated = function( loaderConfig, fnCallback ) {
    var jsonHeaders = {
        'service_key': loginHandler.getServiceKey(),
        'auth_token': loginHandler.getAuthToken()
    };

    $.ajax({
        url: loaderConfig.serviceName,
        contentType: 'application/json; charset=utf-8',
        headers: jsonHeaders,
        type: "GET",
        success: function(data, status) { if( fnCallback!= undefined){fnCallback(loaderConfig,data);}  },
        error: function() {
            var loaderCfg = {
                serviceName : "rest/welcome",
                htmlContainerID : "application-container"
            };
            pageLoader.load(loaderCfg);
        }
    });
};

pageLoader.loadAuthenticated = function( loaderConfig ) {
    pageLoader.callServiceAuthenticated( loaderConfig, pageLoader.setHtmlContent );
};

function onLogin() {
    var url = "rest/login";
    $.post( url, { username: $("#login_usr").val(), password: $("#login_psw").val() } )
        .done(  function(data) { loginCallback(data); } );
    return true;
}

function loginCallback( data ) {
    loginHandler.setServiceKey( data.service_key );
    loginHandler.setAuthToken( data.auth_token );

    pageLoader.loadAuthenticated( createLoaderConfig( "rest/home", "application-container" ) );
    return true;
}

function headerUsrLogout() {
    pageLoader.callServiceAuthenticated( createLoaderConfig( "rest/userInfo/logoff") );
    pageLoader.load( createLoaderConfig( "rest/welcome", "application-container" ) );
}

