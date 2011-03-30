jQuery(document).ready(function(){
	$('#header').corner();
	$('.toc').corner();
    $('.details').corner();
    $('.it').corner();
    $('.vci').corner();
    $('.otherinfo').corner();
    $('.menu span').corner();

    
    $('.term h3 span span:eq(0)').click(function() {
        $(this).parent().parent().nextAll('div').children().eq('0').toggle('slow');
        return false;
    });

    $('.term h3 span span:eq(1)').click(function() {
        $(this).parent().parent().nextAll('div').children().eq('1').toggle('slow');
        return false;
    });

    $('.term h3 span span:eq(2)').click(function() {
        $(this).parent().parent().nextAll('div').children().eq('2').toggle('slow');
        return false;
    });

    $('.menu span').click(function () {
        $(this).toggleClass('active no-active');
    });
    
    $("#bottom-bar").jixedbar({
        transparent: true,
        opacity: 0.5,
        slideSpeed: "slow",
        roundedCorners: true,
        roundedButtons: true,
        menuFadeSpeed: "slow",
        tooltipFadeSpeed: "fast",
        tooltipFadeOpacity: 0.5
    });
    
    $("#openCloseAll").toggle(function() {
        $(".term div:hidden").prevAll("h3").click();
        $(".term div div dl:hidden").prevAll("h4").click();
      }, function() {
        $(".term div:visible").prevAll("h3").click();
        $(".term div div dl:visible").prevAll("h4").click();
      });
    
    $.fn.qtip.styles.parrot = { 
            border: {
               width:1,
               radius: 5,
            },
            padding: 10, 
            textAlign: 'center',
    		fontSize: '.9em',
//            width: 300,
            tip: true, // Give it a speech bubble tip with automatic corner detection
            name: 'green' // Style it according to the preset 'cream' style
      }

      $.fn.qtip.defaults.position.corner = {
           tooltip: 'bottomMiddle',
           target: 'topMiddle'
      }

      $.fn.qtip.defaults.hide.when.event = 'mouseout';
      $.fn.qtip.defaults.hide.fixed = true;
    
      $('.icon-info-description').qtip({
          content: 'Add a description using http://purl.org/dc/terms/description',
          style: 'parrot'
      });
      
      $('.datatype-property-icon').qtip({
          content: 'A <a href="http://www.w3.org/TR/owl2-syntax#Data_Properties" target="_blank">data property</a> is used to describe attributes of resources, such as the height of a person or the population of a country.',
          style: 'parrot'
      });
      
      $('.object-property-icon').qtip({
          content: 'An <a href="http://www.w3.org/TR/owl2-syntax#Object_Properties" target="_blank">object property</a> is used to describe relations to other resources, such as the mother of a person or the capital of a country.',
          style: 'parrot'
      });
      
      $('.annotation-property-icon').qtip({
          content: 'An <a href="http://www.w3.org/TR/owl2-syntax#Annotation_Properties" target="_blank">annotation property</a> is used to give more information of resources .',// FIXME complete the description 
          style: 'parrot'
      });
      
      $('.reflexive-property-icon').qtip({
          content: 'A <a href="http://www.w3.org/TR/owl2-syntax/#Reflexive_Object_Properties" target="_blank">reflexive property</a> describes a relation where every resource is related to itself.', 
          style: 'parrot'
      });
      
      $('.irreflexive-property-icon').qtip({
          content: '<a href="http://www.w3.org/TR/owl2-syntax/#Irreflexive_Object_Properties" target="_blank">Irreflexive property</a> describes a relation where none resource is related to itself.',
          style: 'parrot'
      });
      
      $('.symmetric-property-icon').qtip({
          content: '<a href="http://www.w3.org/TR/owl2-syntax/#Symmetric_Object_Properties" target="_blank">Symmetric property</a>', // FIXME complete the description
          style: 'parrot'
      });
      
      $('.asymmetric-property-icon').qtip({
          content: '<a href="http://www.w3.org/TR/owl2-syntax/#Asymmetric_Object_Properties" target="_blank">Asymmetric property</a>',// FIXME complete the description 
          style: 'parrot'
      });

})
