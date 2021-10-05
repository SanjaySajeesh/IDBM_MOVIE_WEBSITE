package app.controller;

import java.util.Map;

import app.controller.paths.Template;
import app.controller.utils.ViewUtil;
import io.javalin.http.Handler;

public class AdminController {
	
    public static Handler serveAdminPage = ctx -> {
        Map<String, Object> model = ViewUtil.baseModel(ctx);
        ctx.render(Template.ADMIN, model);
    };
}
