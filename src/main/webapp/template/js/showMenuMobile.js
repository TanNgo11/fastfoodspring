$(document).ready(function() {
    // Toggle overlay and body class for main menu items without submenus
    $('.menu-mobile > li > a:not(:has(+ ul))').click(function(event) {
        $('.overlay-mobile-menu').slideToggle("slow", toggleBodyClass);
    });

    // Toggle for submenu visibility
    $('.menu-mobile > li:has(> ul) > a').click(function(event) {
        event.preventDefault(); // Prevent default action
        event.stopPropagation(); // Stop the event from bubbling up
        $(this).next('.sub-menu-mobile').slideToggle("slow");
    });

    // Prevent closing overlay when clicking inside submenu
    $('.sub-menu-mobile').click(function(event) {
        event.stopPropagation();
    });

    // Your existing toggle function
    function toggleBodyClass() {
        if ($('.overlay-mobile-menu').is(':visible')) {
            $('body').addClass("fixed-position");
        } else {
            $('body').removeClass("fixed-position");
        }
    }

    // Event handler for the close button and mobile icon remains the same
    $('.menu-mobile-icon, .close-btn').click(function() {
        $('.overlay-mobile-menu').slideToggle("slow", toggleBodyClass);
    });
});
