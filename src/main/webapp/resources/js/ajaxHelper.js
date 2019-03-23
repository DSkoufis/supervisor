SUP = SUP || {};

SUP.ajaxHelper = (function () {
    let extractFromRequest = function (jqxhr) {
        return {
            originalRequest: jqxhr,
            responseText: jqxhr.responseText,
            status: jqxhr.status,
            getHeader: jqxhr.statusCode().getResponseHeader,
            statusText: jqxhr.statusText
        }
    };

    return {
        extractRequest: extractFromRequest
    };
})();