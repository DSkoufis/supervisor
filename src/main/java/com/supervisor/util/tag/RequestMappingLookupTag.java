package com.supervisor.util.tag;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RequestMappingLookupTag extends CustomAbstractTag {

    private Class<?> controller;
    private String action;
    private Long id;
    private Map<String, ?> params;
    private Map<String, ?> args;

    public void setController(Class<?> controller) {
        this.controller = controller;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setParams(Map<String, ?> params) {
        this.params = params;
    }

    public void setArgs(Map<String, ?> args) {
        this.args = args;
    }

    @Override
    public void doTag() throws IOException {
        String controllerPath = getControllerPath();
        String actionPath = getActionPath();

        String uri = this.replaceArgs(this.buildPath(controllerPath, actionPath));
        String params = this.buildParams();

        uri += params.trim().equals("") ? "" : "?" + params;
        printOut(uri);
    }

    private String buildPath(String controller, String action) {
        String results = "";
        if (!controller.equals("")) {
            results = "/" + controller + "/";
        }

        if (!action.equals("")) {
            results += action;
        }
        return results;
    }

    private String replaceArgs(String uri) {
        if (id != null) {
            uri = uri.replace("{id}", id.toString());
        }

        if (this.args == null) {
            return uri;
        }

        for (Map.Entry<String, ?> entry : this.args.entrySet()) {
            uri = uri.replace("{" + entry.getKey() + "}", entry.getValue().toString());
        }
        return uri;
    }

    private String buildParams() {
        if (this.params == null) {
            return "";
        }

        Map<String, String> clearedMap = new HashMap<>();
        for (Map.Entry<String, ?> entry : this.params.entrySet()) {
            if (entry.getValue() instanceof List) {
                clearedMap.putAll(sanitizeParamsList(entry.getKey(), (List) entry.getValue()));
            } else {
                clearedMap.put(entry.getKey(), entry.getValue().toString());
            }
        }
        return clearedMap.entrySet().stream()
                .map(entry -> entry.getKey() + "=" + entry.getValue())
                .collect(Collectors.joining("&"));
    }

    private Map<String, String> sanitizeParamsList(String key, List<?> paramList) {
        Map<String, String> resultMap = new HashMap<>();
        for (int i = 0; i < paramList.size(); i++) {
            String keyWithIndex = key + "[" + i + "]";
            resultMap.put(keyWithIndex, paramList.get(i).toString());
        }
        return resultMap;
    }

    private String getControllerPath() {
        RequestMapping controllerMapping = controller.getAnnotation(RequestMapping.class);
        return this.getValue(controllerMapping.value());
    }

    private String getActionPath() {
        for (Method method : controller.getMethods()) {
            if (method.getName().equalsIgnoreCase(action)) {
                if (method.getAnnotation(RequestMapping.class) != null) {
                    return this.getValue(method.getAnnotation(RequestMapping.class).value());
                } else if (method.getAnnotation(GetMapping.class) != null) {
                    return this.getValue(method.getAnnotation(GetMapping.class).value());
                } else if (method.getAnnotation(PostMapping.class) != null) {
                    return this.getValue(method.getAnnotation(PostMapping.class).value());
                } else if (method.getAnnotation(PutMapping.class) != null) {
                    return this.getValue(method.getAnnotation(PutMapping.class).value());
                } else if (method.getAnnotation(DeleteMapping.class) != null) {
                    return this.getValue(method.getAnnotation(DeleteMapping.class).value());
                } else if (method.getAnnotation(PatchMapping.class) != null) {
                    return this.getValue(method.getAnnotation(PatchMapping.class).value());
                }
            }
        }
        return "";
    }

    private String getValue(String[] values) {
        return values == null || values.length == 0 ? "" : this.sanitizePath(values[0]);
    }

    private String sanitizePath(String path) {
        path = path.trim();
        return path.equals("") || path.equals("/") ? path : this.removeSlashFromEnd(this.removeSlashFromStart(path));
    }

    private String removeSlashFromStart(String path) {
        return path.length() > 1 && path.startsWith("/") ? path.substring(1) : path;
    }

    private String removeSlashFromEnd(String path) {
        return path.length() > 1 && path.endsWith("/") ? path.substring(0, path.length() - 1) : path;
    }
}
