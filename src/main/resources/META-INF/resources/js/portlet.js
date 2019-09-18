function callServeResource(){
        AUI().use('aui-io-request', function(A){
            A.io.request('${resourceUrl1}', {
                   method: 'post',
                   data: {
                	   <portlet:namespace />param2: 'value2',
                   },
                   on: {
                       	success: function() {
                       		alert(this.get('responseData'));
                       }
                  }
            });
     
        });
    }