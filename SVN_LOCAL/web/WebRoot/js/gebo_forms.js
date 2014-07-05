/* [ ---- Gebo Admin Panel - extended form elements ---- ] */

	$(document).ready(function() {
		//* masked inputs
		gebo_mask_input.init();
		//* datepicker
		gebo_datepicker.init();
		//* timepicker
		gebo_timepicker.init();
		//* words, characters limit for textarea
		gebo_limiter.init();
		//* autosize for textareas
		gebo_auto_expand.init();
        //* tag handler for inputs
		gebo_tag_handler.init();
        //* input spinners
		gebo_spinners.init();
		//* nice form elements
        gebo_uniform.init();
		//* jQuery UI sliders
		gebo_sliders.init();
		//* animated jQuery UI progressbars
		gebo_progressbars.init();
		//* 2col multiselect
		gebo_multiselect.init();
		//* enhanced select
		gebo_chosen.init();
		//* WYSIWG editor
        gebo_wysiwg.init();
        //* multiupload
        gebo_multiupload.init();
		//* colorpicker
		gebo_colorpicker.init();
		
		$('.open_modal_form').click(function(e) {
			$.colorbox({
				href: '#modal_form',
				inline: true,
				opacity: '0.2',
				fixed: true,
				scrolling: false
			});
			e.preventDefault();
		})
		
		//* password strength checker
		gebo_pass_check.init();
		//* toggle buttons
        gebo_toggle_buttons.init();
	});
	
	//* masked input
	gebo_mask_input = {
		init: function() {
			$("#mask_date").inputmask("99/99/9999",{placeholder:"dd/mm/yyyy"});
			$("#mask_phone").inputmask("(999) 999-9999");
			$("#mask_ssn").inputmask("999-99-9999");
			$("#mask_product").inputmask("AA-999-A999");
		}
	};
	
	//* bootstrap datepicker
	gebo_datepicker = {
		init: function() {
			$('#dp1').datepicker();
			$('#dp2').datepicker();
			
			$('#dp_start').datepicker({format: "mm/dd/yyyy"}).on('changeDate', function(ev){
				var dateText = $(this).data('date');
				
				var endDateTextBox = $('#dp_end input');
				if (endDateTextBox.val() != '') {
					var testStartDate = new Date(dateText);
					var testEndDate = new Date(endDateTextBox.val());
					if (testStartDate > testEndDate) {
						endDateTextBox.val(dateText);
					}
				}
				else {
					endDateTextBox.val(dateText);
				};
				$('#dp_end').datepicker('setStartDate', dateText);
				$('#dp_start').datepicker('hide');
			});
			$('#dp_end').datepicker({format: "mm/dd/yyyy"}).on('changeDate', function(ev){
				var dateText = $(this).data('date');
				var startDateTextBox = $('#dp_start input');
				if (startDateTextBox.val() != '') {
					var testStartDate = new Date(startDateTextBox.val());
					var testEndDate = new Date(dateText);
					if (testStartDate > testEndDate) {
						startDateTextBox.val(dateText);
					}
				}
				else {
					startDateTextBox.val(dateText);
				};
				$('#dp_start').datepicker('setEndDate', dateText);
				$('#dp_end').datepicker('hide');
			});
			$('#dp_modal').datepicker();
		}
	};
	
	//* bootstrap timepicker
	gebo_timepicker = {
		init: function() {
			$('#tp_1').timepicker({
				defaultTime: 'current',
				minuteStep: 10,
				disableFocus: true,
				template: 'modal',
				showMeridian: false
			});
			$('#tp_2').timepicker({
				defaultTime: 'current',
				minuteStep: 1,
				disableFocus: true,
				template: 'dropdown'
			});
			$('#tp_modal').timepicker({
				defaultTime: 'current',
				minuteStep: 1,
				disableFocus: true,
				template: 'dropdown'
			});
		}
	};
	
	//* textarea limiter
	gebo_limiter = {
		init: function(){
			$("#txtarea_limit_chars").counter({
				goal: 120
			});
			$("#txtarea_limit_words").counter({
				goal: 20,
				type: 'word'
			});
		}
	};
	
	//* textarea autosize
	gebo_auto_expand = {
		init: function() {
			$('#auto_expand').autosize();
		}
	};
    
    //* tag handler
	gebo_tag_handler = {
		init: function() {
			$("#array_tag_handler").tagHandler({
				assignedTags: [ 'C', 'Perl', 'PHP' ],
				availableTags: [ 'C', 'C++', 'C#', 'Java', 'Perl', 'PHP', 'Python' ],
				autocomplete: true
			});
			$("#max_tags_tag_handler").tagHandler({
				assignedTags: [ 'Perl' ],
				availableTags: [ 'C', 'C++', 'C#', 'Java', 'Perl', 'PHP', 'Python' ],
				autocomplete: true,
				maxTags:5
			});
		}
	};

	//* spinners
	gebo_spinners = {
		init: function() {
			$("#sp_basic").spinner();
			$("#sp_dec").spinner({
				step: 0.01,
                numberFormat: "n"
			});
			$("#sp_currency").spinner({
				numberFormat: "C",
				max: 20,
				min: 2
			});
            $("#sp_overflow").spinner({
				spin: function( event, ui ) {
                    if ( ui.value > 10 ) {
                        $( this ).spinner( "value", -10 );
                        return false;
                    } else if ( ui.value < -10 ) {
                        $( this ).spinner( "value", 10 );
                        return false;
                    }
                }
			});
		}
	};
    
    //* uniform
    gebo_uniform = {
		init: function() {
            $(".uni_style").uniform();
        }
    };
	
	//* progressbars
	gebo_progressbars = {
		init: function(){
			var iEnd1 = new Date().setTime(new Date().getTime() + 25 * 1000); // now plus 25 secs
			$('#progress1').anim_progressbar({
				finish: iEnd1,
				callback: function() {
					$.sticky("Progressbar no 1 callback", {autoclose : false, position: "top-right", type: "st-info" });
				}
			});
			var iNow = new Date().setTime(new Date().getTime() + 2 * 1000); // now plus 2 secs
			var iEnd2 = new Date().setTime(new Date().getTime() + 10 * 1000); // now plus 10 secs
			$('#progress2').anim_progressbar({
				start: iNow,
				finish: iEnd2,
				interval: 100,
				callback: function() {
					$.sticky("Progressbar no 2 callback", {autoclose : false, position: "top-right", type: "st-success" });
				}
			});
			var iEnd3 = new Date().setTime(new Date().getTime() + 15 * 1000); // now plus 15 secs
			$('#progress3').anim_progressbar({
				interval: 1000,
				finish: iEnd3,
				callback: function() {
					$.sticky("Progressbar no 3 callback", {autoclose : false, position: "top-right", type: "st-error" });
				}
			});
		}
	};

	//* sliders
	gebo_sliders = {
		init: function(){
			//* default slider
			$( ".ui_slider1" ).slider({
				value:100,
				min: 0,
				max: 500,
				step: 50,
				slide: function( event, ui ) {
					$( ".ui_slider1_val" ).text( "$" + ui.value );
					$( "#ui_slider_default_val" ).val( "$" + ui.value );
				}
			});
			$( ".ui_slider1_val" ).text( "$" + $( ".ui_slider1" ).slider( "value" ) );
			$( "#ui_slider_default_val" ).val( "$" + $( ".ui_slider1" ).slider( "value" ) );

			//* range slider
			$( ".ui_slider2" ).slider({
				range: true,
				min: 0,
				max: 500,
				values: [ 75, 300 ],
				slide: function( event, ui ) {
					$( ".ui_slider2_val" ).text( "$" + ui.values[ 0 ] + " - $" + ui.values[ 1 ] );
					$( "#ui_slider_min_val" ).val( "$" + ui.values[ 0 ] );
					$( "#ui_slider_max_val" ).val( "$" + ui.values[ 1 ] );
				}
			});
			$( ".ui_slider2_val" ).text( "$" + $( ".ui_slider2" ).slider( "values", 0 ) + " - $" + $( ".ui_slider2" ).slider( "values", 1 ) );
			$( "#ui_slider_min_val" ).val( "$" + $( ".ui_slider2" ).slider( "values", 0 ) );
			$( "#ui_slider_max_val" ).val( "$" + $( ".ui_slider2" ).slider( "values", 1 ) );
			
			//* slider with select
			var select = $( "#ui_slider3_sel" );
			var slider = $( "<div id='ui_slider3'></div>" ).insertAfter( select ).slider({
				min: 1,
				max: 6,
				range: "min",
				value: select[ 0 ].selectedIndex + 1,
				slide: function( event, ui ) {
					select[ 0 ].selectedIndex = ui.value - 1;
				}
			});
			$( "#ui_slider3_sel" ).change(function() {
				slider.slider( "value", this.selectedIndex + 1 );
			});
		}
	};
	
	//* multiselect
	gebo_multiselect = {
		init: function(){
			
			if($('#public-methods').length) {
                //* public methods
                $('#public-methods').multiSelect();
                $('#select-all').click(function(){
                    $('#public-methods').multiSelect('select_all');
                    return false;
                });
                $('#deselect-all').click(function(){
                    $('#public-methods').multiSelect('deselect_all');
                    return false;
                });
                $('#select-fr').click(function(){
                    $('#public-methods').multiSelect('select', 'fr');
                    return false;
                });
                $('#deselect-fr').click(function(){
                    $('#public-methods').multiSelect('deselect', 'fr');
                    return false;
                });
            }
            if($('#optgroup').length) {
                //* optgroup
                $('#optgroup').multiSelect()
            }
			if($('#custom-headers').length) {
                //* custom headers
                $('#custom-headers').multiSelect({
                    selectableHeader: "<div class='custom-header'>Selectable item</div>",
                    selectionHeader: "<div class='custom-header'>Selected items</div>"
                });
            }
            if($('#searchable').length) {
                //* searchable
                $('#searchable').multiSelect({
                    selectableHeader: '<div class="search-header"><input type="text" class="span12" id="ms-search" autocomplete="off" placeholder="country name"></div>',
                    selectionHeader: "<div class='search-selected'></div>"
                });
            }
            if($('#ms-search').length) {  
                $('#ms-search').quicksearch($('.ms-elem-selectable', '#ms-searchable' )).on('keydown', function(e){
                    if (e.keyCode == 40){
                        $(this).trigger('focusout');
                        $('#ms-searchable').focus();
                        return false;
                    }
                })
            }

		}
	};
	
	//* enhanced select elements
	gebo_chosen = {
		init: function(){
			$(".chzn_a").chosen({
				allow_single_deselect: true
			});
			$(".chzn_b").chosen();
		}
	};
    
    //* TinyMce
	gebo_wysiwg = {
		init: function(){
			// File Browser
            function openKCFinder(field_name, url, type, win) {
                alert("Field_Name: " + field_name + "nURL: " + url + "nType: " + type + "nWin: " + win); // debug/testing
                tinyMCE.activeEditor.windowManager.open({
                    file: '/file-manager/browse.php?opener=tinymce&type=' + type,
                    title: 'KCFinder',
                    width: 700,
                    height: 500,
                    resizable: "yes",
                    inline: true,
                    close_previous: "no",
                    popup_css: false
                }, {
                    window: win,
                    input: field_name
                });
                return false;
            };
            $('textarea#wysiwg_full').tinymce({
                // Location of TinyMCE script
                script_url 							: 'lib/tiny_mce/tiny_mce.js',
                // General options
                theme 								: "advanced",
                plugins 							: "autoresize,style,table,advhr,advimage,advlink,emotions,inlinepopups,preview,media,contextmenu,paste,fullscreen,noneditable,xhtmlxtras,template,advlist",
                // Theme options
                theme_advanced_buttons1 			: "undo,redo,|,bold,italic,underline,strikethrough,|,justifyleft,justifycenter,justifyright,justifyfull,fontselect,fontsizeselect",
                theme_advanced_buttons2 			: "forecolor,backcolor,|,cut,copy,paste,pastetext,|,bullist,numlist,link,image,media,|,code,preview,fullscreen",
                theme_advanced_buttons3 			: "",
                theme_advanced_toolbar_location 	: "top",
                theme_advanced_toolbar_align 		: "left",
                theme_advanced_statusbar_location 	: "bottom",
                theme_advanced_resizing 			: false,
                font_size_style_values 				: "8pt,10px,12pt,14pt,18pt,24pt,36pt",
                init_instance_callback				: function(){
                    function resizeWidth() {
                        document.getElementById(tinyMCE.activeEditor.id+'_tbl').style.width='100%';
                    }
                    resizeWidth();
                    $(window).resize(function() {
                        resizeWidth();
                    })
                },
                // file browser
                file_browser_callback: function openKCFinder(field_name, url, type, win) {
                    tinyMCE.activeEditor.windowManager.open({
                        file: 'file-manager/browse.php?opener=tinymce&type=' + type + '&dir=image/themeforest_assets',
                        title: 'KCFinder',
                        width: 700,
                        height: 500,
                        resizable: "yes",
                        inline: true,
                        close_previous: "no",
                        popup_css: false
                    }, {
                        window: win,
                        input: field_name
                    });
                    return false;
                }
            });
		}
	};
    
	//* drag&drop multi-upload
    gebo_multiupload = {
        init: function() {
            $("#multi_upload").pluploadQueue({
                // General settings
                runtimes : 'html5,flash,silverlight',
                url : 'lib/plupload/examples/upload.php',
                max_file_size : '10mb',
                chunk_size : '1mb',
                unique_names : true,
                browse_button : 'pickfiles',
        
                // Specify what files to browse for
                filters : [
                    {title : "Image files", extensions : "jpg,gif,png"},
                    {title : "Zip files", extensions : "zip"}
                ],
        
                // Flash settings
                flash_swf_url : 'lib/plupload/js/plupload.flash.swf',
        
                // Silverlight settings
                silverlight_xap_url : 'lib/plupload/js/plupload.silverlight.xap'
            });
        }
    };
	
	//* colorpicker
	gebo_colorpicker = {
		init: function(){
			$('#cp1').colorpicker({
				format: 'hex'
			});
			$('#cp2').colorpicker();
			$('#cp3').colorpicker();
			
			$('#cp_modal').colorpicker();
		}
	};
	
	//* password strength checker
	gebo_pass_check = {
		init: function() {
			$("#pass_check").complexify({
					minimumChars: '6',
					strengthScaleFactor: '0.8'
				}, function (valid, complexity) {
				if (!valid) {
					$('#pass_progress .bar').css({'width':complexity + '%'}).parent().removeClass('progress-success').addClass('progress-danger');
				} else {
					$('#pass_progress .bar').css({'width':complexity + '%'}).parent().removeClass('progress-danger').addClass('progress-success');
				}
			});
		}
	};
	
    //* toggle buttons
    gebo_toggle_buttons = {
        init: function() {
            $('#normal-toggle-button').toggleButtons();
            $('#warning-toggle-button').toggleButtons({
                style: {
                    enabled: "warning",
                    disabled: "danger"
                }
            });
            $('#danger-toggle-button').toggleButtons({
                style: {
                    enabled: "danger",
                    disabled: "info"
                }
            });
            $('#info-toggle-button').toggleButtons({
                style: {
                    enabled: "info",
                    disabled: "success"
                }
            });
            $('#success-toggle-button').toggleButtons({
                style: {
                    enabled: "success",
                    disabled: "warning"
                }
            });
            $('#disabled-toggle-button').toggleButtons();
            $('#data-attribute-toggle-button').toggleButtons();
        }
    };
	