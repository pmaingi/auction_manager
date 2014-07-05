/* [ ---- Gebo Admin Panel - editable_elements ---- ] */

    $(function(){
        
        //ajax mocks
        $.mockjaxSettings.responseTime = 500; 
        
        $.mockjax({
            url: '/post',
            response: function(settings) {
                log(settings, this);
            }
        });
    
        $.mockjax({
            url: '/error',
            status: 400,
            statusText: 'Bad Request',
            response: function(settings) {
                this.responseText = 'Please input correct value'; 
                log(settings, this);
            }        
        });
        
        $.mockjax({
            url: '/status',
            status: 500,
            response: function(settings) {
                this.responseText = 'Internal Server Error';
                log(settings, this);
            }        
        });
      
        $.mockjax({
            url: '/groups',
            response: function(settings) {
                this.responseText = [ 
                 {value: 0, text: 'Guest'},
                 {value: 1, text: 'Service'},
                 {value: 2, text: 'Customer'},
                 {value: 3, text: 'Operator'},
                 {value: 4, text: 'Support'},
                 {value: 5, text: 'Admin'}
               ];
               log(settings, this);
            }        
        });
        
        function log(settings, response) {
                var s = [], str;
                s.push(settings.type.toUpperCase() + ' url = "' + settings.url + '"');
                for(var a in settings.data) {
                    if(settings.data[a] && typeof settings.data[a] === 'object') {
                        str = [];
                        for(var j in settings.data[a]) {str.push(j+': "'+settings.data[a][j]+'"');}
                        str = '{ '+str.join(', ')+' }';
                    } else {
                        str = '"'+settings.data[a]+'"';
                    }
                    s.push(a + ' = ' + str);
                }
                s.push('RESPONSE: status = ' + response.status);
    
                if(response.responseText) {
                    if($.isArray(response.responseText)) {
                        s.push('[');
                        $.each(response.responseText, function(i, v){
                           s.push('{value: ' + v.value+', text: "'+v.text+'"}');
                        }); 
                        s.push(']');
                    } else {
                       s.push($.trim(response.responseText));
                    }
                }
                s.push('--------------------------------------\n');
                $('#console').val(s.join('\n') + $('#console').val());
        }
        
        //defaults
        $.fn.editable.defaults.url = '/post'; 
        
         //enable / disable
        $('#enable').click(function() {
            console.log('fd');
            $('#user .editable').editable('toggleDisabled');
        });    
         
         //editables 
        $('#username').editable({
            url: '/post',
            type: 'text',
            pk: 1,
            name: 'username',
            title: 'Enter username'
        });
         
        $('#firstname').editable({
            validate: function(value) {
               if($.trim(value) == '') return 'This field is required';
            }
        });
         
         $('#sex').editable({
            prepend: "not selected",
            source: [
                {value: 1, text: 'Male'},
                {value: 2, text: 'Female'}
            ],
            display: function(value, sourceData) {
                var colors = {"": "gray", 1: "green", 2: "blue"},
                    elem = $.grep(sourceData, function(o){return o.value == value;});
                if(elem.length) {    
                    $(this).text(elem[0].text).css("color", colors[value]); 
                } else {
                    $(this).empty(); 
                }
            }   
         });    
         
         $('#status').editable();   
         
         $('#group').editable({
            showbuttons: false 
         });   
        
         $('#vacation').editable();  
             
         $('#dob').editable();
               
         $('#event').editable({
            placement: 'right',
            combodate: {
                firstItem: 'name'
            }
         });      
         
         $('#comments').editable({
            showbuttons: true
         }); 
         
         $('#note').editable(); 
         
         $('#pencil').click(function(e) {
            e.stopPropagation();
            e.preventDefault();
            $('#note').editable('toggle');
        });   
        
        $('#fruits').editable({
            pk: 1,
            limit: 3,
            source: [
                {value: 1, text: 'banana'},
                {value: 2, text: 'peach'},
                {value: 3, text: 'apple'},
                {value: 4, text: 'watermelon'},
                {value: 5, text: 'orange'}
            ]
         }); 
         
         $('#address').editable({
            url: '/post',
            value: {
               city: "Moscow", 
               street: "Lenina", 
               building: "12"
            },
            validate: function(value) {
               if(value.city == '') return 'city is required!'; 
            },
            display: function(value) {
                if(!value) {
                    $(this).empty();
                    return; 
                }
                var html = '<b>' + $('<div>').text(value.city).html() + '</b>, ' + $('<div>').text(value.street).html() + ' st., bld. ' + $('<div>').text(value.building).html();
                $(this).html(html); 
            }         
        });              
              
        $('#user .editable').on('hidden', function(e, reason){
            if(reason === 'save' || reason === 'nochange') {
                var $next = $(this).closest('tr').next().find('.editable');
                if($('#autoopen').is(':checked')) {
                    setTimeout(function() {
                        $next.editable('show');
                    }, 300); 
                } else {
                    $next.focus();
                } 
            }
        });
        
    });