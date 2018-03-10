

var warningMasterCTRL = ( function() {

    var _callService = function() {
        pageLoader.loadAuthenticated( createLoaderConfig( "rest/tabWarnProducer", "tabs-WRNMSTR" ) );
    };

    var _loadPageInput = function() {
        pageLoader.loadAuthenticated( createLoaderConfig( "rest/warnProducerHtml/warnMaker", "WRNMSTR_body" ) );
    };

    var _loadPageReadList = function() {
        pageLoader.loadAuthenticated( createLoaderConfig( "rest/warnProducerHtml/list", "WRNMSTR_body" ) );
    };

    var _loadPageRead = function() {
        pageLoader.loadAuthenticated( createLoaderConfig( "rest/warnProducerHtmlRead", "WRNMSTR_body" ) );
    };

    // public api
    return {
        load: _callService,
        loadPageInput: _loadPageInput,
        loadPageReadList: _loadPageReadList,
        loadPageRead : _loadPageRead
    }
}());

function listAddItem( btnObj ) {
	dynamicListHandler.addItem( btnObj );
	return true;
}

function listRemoveItem( btnObj ) {
	dynamicListHandler.removeItem( btnObj );
	return true;
}

var tipiProdSelectHandler = ( function() {

    var _srvCallbk = function( loaderConfig, data ) {
        var select = $('<select id="warnTipoProdotto"></select>');
        var option = $('<option></option>');
        option.attr('value', "");
        option.text("");
        select.append(option);

        data.forEach(function( item ) {
            option = $('<option></option>');
            option.attr('value', item.codice);
            option.text( item.descrizione );
            select.append(option);
        });
        $('#warnTipoProdContainer').empty().append(select);
    };

    var _callService = function() {
        pageLoader.callServiceAuthenticated( createLoaderConfig( "rest/tipiProd" ), _srvCallbk );
    };

    // public api
    return {
        createSelect: _callService
    }
}());


var dynamicListHandler = ( function() {

	var templateID = "dynList_";

	var _getInputLi = function( btnObj ) {
		if( btnObj == null ) {
			return null;
		}
		var parentLI = btnObj.parentNode;
		var inputObj = parentLI.getElementsByTagName('input')[0];
		return inputObj;
	}

	var _getMinusBtn = function( btnPiu ) {
		var parentLI = btnPiu.parentNode;
		var btns = parentLI.getElementsByTagName('button');
		var i = 0;
		for( i=0; i < btns.length; i++) {
			if( btns[i].className  == "btnListRemove"){
				return btns[i];
			}
		}
		return null;
	}

    var _getInputLiID = function( inputObj ) {
        var id = null;
		var fullID = inputObj.id;
		if (fullID.indexOf(templateID) == 0) {
			return fullID.substring(templateID.length, fullID.length);
		}
		return id;
    };

	var nextID = function() {
		var idSuffix = 0;
		var liArray = $( "#rootDynamicList li" );
		if( liArray.length > 0 ) {
			var lastLI = $( "#rootDynamicList li" ).last()[0];
			var inputObj = lastLI.getElementsByTagName('input')[0];
			idSuffix = parseInt(_getInputLiID( inputObj ));
			++idSuffix;
		}
		return idSuffix;
	};

	var createInputLI = function( id ) {
		var inputID = templateID + id;
		var x = document.createElement("INPUT");
		x.setAttribute("type", "text");
		x.setAttribute('id', inputID );
		x.setAttribute("class", "dynListElem");
		x.setAttribute("placeholder", "Codice Lotto");
		x.style.width = "200px";
		return x;
	};

	var createDataScadenza = function( id ) {
	    var inputID = "warnDataScadenza_" + id;
	    var x = document.createElement("INPUT");
	    x.setAttribute("type", "text");
        x.setAttribute('id', inputID );
        x.setAttribute("class", "datePicker");
        x.setAttribute("placeholder", "data scadenza/TMC");
        x.style.marginLeft = "15px";
        x.style.marginRight = "15px";
        return x;
	};

	var createBtnAdd = function() {
		var x = document.createElement("button");
		x.setAttribute("onclick", "listAddItem(this)");
		x.setAttribute("class", "btnListAdd");
		return x;
	};

	var createBtnRemove = function() {
		var x = document.createElement("button");
		x.setAttribute("onclick", "listRemoveItem(this)");
		x.setAttribute("class", "btnListRemove");
		x.style.visibility = "hidden";
		return x;
	};

	var _addItem = function( btnObj ) {
        var inputObj = _getInputLi( btnObj );
		if( btnObj != null ) {
			inputObj.disabled= true;
			btnObj.style.display = "none";
			_getMinusBtn( btnObj ).style.visibility = "visible";
		}

        var li = document.createElement("li");
        var nextId = nextID();
        li.appendChild( createInputLI( nextId ) );
        li.appendChild( createDataScadenza( nextId ) );
        li.appendChild( createBtnAdd() );
        li.appendChild( createBtnRemove() );
        $("#rootDynamicList")[0].appendChild(li);
        activateDatePicker();
    }

    var _removeItem = function( btnObj ) {
        var parentLI = btnObj.parentNode;
        parentLI.remove();
    }

    // public api
    return {
        addItem: _addItem,
		removeItem : _removeItem
    }
}());
