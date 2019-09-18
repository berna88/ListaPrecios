var token = '';
    var myIp = Liferay.ThemeDisplay.getPortalURL();
    var body = {
    grant_type : 'client_credentials',
    client_id : 'id-32259466-f158-05e3-62f9-a51bb2ca862',
    client_secret : 'secret-cdf76232-8f8b-e956-b451-bf7ea0be426d',
    };
    $(document).ready(function() {
    $.ajax({
    url : myIp+'/o/oauth2/token',
    type : 'POST',
    dataType : 'json',
    contentType : 'application/x-www-form-urlencoded; charset=UTF-8',
    data : body, 
    complete : function(result) {
    //called when complete
    $.ajax({
    url : myIp+'/o/listaPrecios/obtenerListaDePrecio',
    type : 'GET',
    beforeSend : function(xhr) {
    xhr
    .setRequestHeader('Authorization', 'Bearer '
    + result.responseJSON.access_token);
    },
    data : {},
    success : function(data) {

    },
    error : function(e) {
    console.log(e.message);
    },
    complete: function(res){
    console.log(JSON.parse(res.responseText));
    }
    });
    },
    success : function(result) {
    //called when successful
    },
    error : function(e) {
    //called when there is an error
    console.log(e.message);
    },
    });
    });