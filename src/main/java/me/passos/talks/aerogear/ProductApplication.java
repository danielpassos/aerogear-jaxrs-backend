package me.passos.talks.aerogear;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/rest")
public class ProductApplication extends Application {

    public static final String AG_PUSH_URL = "http://localhost:8080/ag-push";

}
