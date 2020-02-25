/* Inicializacion del carrusel */
$(document).ready(function(){
  	$('.owl-carousel').owlCarousel({
  	    loop:true,
  	    margin:25,
  	    dots:false,
  	    navText: [ '<i class="fas fa-chevron-left"></i>' , '<i class="fas fa-chevron-right"></i>' ],
  	    responsiveClass:true,
  	    responsive:{
  	        0:{
  	            items:1,
  	            nav:true
  	        },
  	        600:{
  	            items:3,
  	            nav:false
  	        },
  	        1000:{
  	            items:5,
  	            nav:true,
  	            loop:false
  	        }
  	    }
  	})
});

/* Inicializacion de popvers 
$(document).ready(function(){
  $('.info').popover();
});

*/
/* NUESTRAS MARCAS */
$(document).ready(function(){

    /* Obtiene la url actual para definir que opcion del select esta activa*/
    var href = document.location.href;
    var paginaNombre = href.substr(href.lastIndexOf('/') + 1);
    $('#selectMarca option').each(function () {
        var paginaSelect = $(this).val();
        if ( paginaSelect == paginaNombre ) {
            $(this).attr('selected' , 'selected');
        }
    });

    /* Cambia de página de acuerdo a la opción del select */
    $('#selectMarca').on('change', function (e) {
        e.preventDefault();
        var pagina = $(this).val();
        $( location ).attr("href", pagina);
    });

    /* Activa el tab dependiendo de al imagen seleccionada */
    $('#imagen-tabs img').on('click', function (e) {
        var mostrar = $(this).data('target');
        /* Cambia el estado active de als imagenes de acuerdo al que se le da click*/
        $('#imagen-tabs img').removeClass('active');
        $(this).addClass('active');
        /* Oculta tabs que no estan activos */
        $('.tab-pane').removeClass('active show');        
        /* Muestra tab seleccionado*/
        $('#' + mostrar).tab('show');
    })
});

/* Oculta el menu hasta que se abra el dropdown */
$(document).ready(function(){

  $('#collapseMarcas').on('shown.bs.collapse', function () {
    $('.owl-carousel-menu').css('opacity' , '1');
  });

  $(".collapse[data-parent='#menuDesplegable']")
    .on("show.bs.collapse", function(){
      $(".collapse[data-parent='#menuDesplegable']").collapse('hide');
    });
    
});

/* SOMOS JOSE CUERVO */
/* Funcion para activar los tabs por medio de un select */
$(document).ready(function(){

    /* Cambia el tab seleccionado dependiendo de la opción selccionada*/
    $('#selectSeccion').on('change', function (e) {
        e.preventDefault();
        var id = $(this).val();
        $('.tab-pane').removeClass('active show');
        $('#' + id).tab('show');             
    });
});

/* CLASIFICADOS FUNCIONALIDADES*/
$(document).ready(function(){

    $('.info').on('click', function (e) {
      var contenedor = $(this).parents(".clasificado-parent");
      contenedor.find(".aviso-detalles").slideDown();
    })  

    $('.cerrarAviso').on('click', function (e) {
      $(this).parent().slideUp();
    })
    
});

$(document).ready(function(){

    $('.info-usuario').on('click', function (e) {
      var contenedor = $(this).parents(".clasificado-parent");
      contenedor.find(".aviso-detalles-usuario").slideDown();
    })  

    $('.cerrarAviso-usuario').on('click', function (e) {
      $(this).parent().slideUp();
    })
    
});


/* Galeria de productos (Galleriffic) */
jQuery(document).ready(function(e) {
  // We only want these styles applied when javascript is enabled
  $('div.navigation').css({'width' : '300px', 'float' : 'left'});
  $('div.content').css('display', 'block');

  $(".each-gallery").each(function(i){
    // Initialize Advanced Galleriffic Gallery
    var gallery = $('#thumbs'+i).galleriffic({
      delay:                     2500,
      numThumbs:                 15,
      preloadAhead:              10,
      enableTopPager:            true,
      enableBottomPager:         true,
      maxPagesToShow:            7,
      imageContainerSel:         '#slideshow'+ i,
      controlsContainerSel:      '#controls' + i,
      captionContainerSel:       '#caption' + i,
      loadingContainerSel:       '#loading' + i,
      renderSSControls:          true,
      renderNavControls:         true,
      playLinkText:              'Play Slideshow',
      pauseLinkText:             'Pause Slideshow',
      prevLinkText:              '&lsaquo; Previous Photo',
      nextLinkText:              'Next Photo &rsaquo;',
      nextPageLinkText:          'Next &rsaquo;',
      prevPageLinkText:          '&lsaquo; Prev',
      enableHistory:             false,
      autoStart:                 false,
      syncTransitions:           true,
      defaultTransitionDuration: 900,
      onSlideChange:             function(prevIndex, nextIndex) {
        // 'this' refers to the gallery, which is an extension of $('#thumbs')
        this.find('ul.thumbs').children()
          .eq(prevIndex).fadeTo('fast', onMouseOutOpacity).end()
          .eq(nextIndex).fadeTo('fast', 1.0);
      },
      onPageTransitionOut:       function(callback) {
        this.fadeTo('fast', 0.0, callback);
      },
      onPageTransitionIn:        function() {
        this.fadeTo('fast', 1.0);
      }
    });
  });

  // Initially set opacity on thumbs and add
  // additional styling for hover effect on thumbs
  var onMouseOutOpacity = 0.67;
  $('ul.thumbs li').opacityrollover({
    mouseOutOpacity:   onMouseOutOpacity,
    mouseOverOpacity:  1.0,
    fadeSpeed:         'fast',
    exemptionSelector: '.selected'
  });
  
});