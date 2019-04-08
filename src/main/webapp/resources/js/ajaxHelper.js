SUP = SUP || {};

SUP.ajaxHelper = (function () {
    let extractJsonResponse = function (jqxhr) {
        var responseText = jqxhr.responseText;
        return {
            originalRequest: jqxhr,
            responseText: responseText,
            response: JSON.parse(responseText),
            status: jqxhr.status,
            getHeader: jqxhr.statusCode().getResponseHeader,
            statusText: jqxhr.statusText
        }
    };

    return {
        extractJson: extractJsonResponse
    };
})();