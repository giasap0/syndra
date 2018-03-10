
var pageLoader = ( function() {

    var _setHtmlContent = function( loaderConfig, htmlContent ) {
        var htmlContainerOBJ = $('#'+loaderConfig.htmlContainerID );
        htmlContainerOBJ.html( htmlContent );
    };

    var _callServiceWelcome = function( loaderConfig ) {
        _callService( loaderConfig, _setHtmlContent  );
    };

    var _callService = function( loaderConfig, fnCallback ) {
        $.get( loaderConfig.serviceName, function(data, status) { fnCallback(loaderConfig, data); } );
    };

    // public api
    return {
        setHtmlContent: _setHtmlContent,
        load: _callServiceWelcome,
        callService: _callService
    }
}());

$( document ).ready(function() {
    pageLoader.load( createLoaderConfig( "rest/welcome", "application-container" ) );
});

function createLoaderConfig( srvName, htmlID ) {
    var loaderConfig = {
        serviceName: srvName,
        htmlContainerID: htmlID
    };
    return loaderConfig;
}
