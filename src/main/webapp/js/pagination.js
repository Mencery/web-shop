var paginationHandler = function(){
    // store pagination container so we only select it once
    var $paginationContainer = $(".pagination-container"),
        $pagination = $paginationContainer.find('.pagination ul');
    // click event
    $pagination.find("li a").on('click.pageChange',function(e){
        e.preventDefault();

        // get parent li's data-page attribute and current page
        var parentLiPage = $(this).parent('li').data("page");
        var currentPage = parseInt( $(".pagination-container div[data-page]:visible").data('page'));
        var numPages = $paginationContainer.find("div[data-page]").length;

        $('li[data-page*=' + currentPage + ']').removeClass('active');

        // make sure they aren't clicking the current page
        if ( parseInt(parentLiPage) !== parseInt(currentPage) ) {
            // hide the current page
           $paginationContainer.find("div[data-page]").hide();

            if ( parentLiPage === '+' ) {
                // next page
                $paginationContainer.find("div[data-page="+( currentPage+1>numPages ? numPages : currentPage+1 )+"]").show();
            } else if ( parentLiPage === '-' ) {
                // previous page
                $paginationContainer.find("div[data-page="+( currentPage-1<1 ? 1 : currentPage-1 )+"]").show();
            } else {
                // specific page
                $paginationContainer.find("div[data-page="+parseInt(parentLiPage)+"]").show();
            }
             $('li[data-page*=' + $(".pagination-container div[data-page]:visible").data('page') + ']').addClass('active');
        }
    });
};



$( document ).ready( paginationHandler );
