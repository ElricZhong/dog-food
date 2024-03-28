package com.dogfood.bowlboot;

/**
 * @author zhongfupeng
 */
public class TomcatWebServer implements WebServer {

    @Override
    public void start() {
        //Tomcat tomcat = new Tomcat();
        //
        //Server server = tomcat.getServer();
        //Service service = server.findService("Tomcat");
        //
        //Connector connector = new Connector();
        //connector.setPort(7001);
        //
        //Engine engine = new StandardEngine();
        //engine.setDefaultHost("localhost");
        //
        //Host host = new StandardHost();
        //host.setName("localhost");
        //
        //String contextPath = "";
        //Context context = new StandardContext();
        //context.setPath(contextPath);
        //context.addLifecycleListener(new Tomcat.FixContextListener());
        //
        //host.addChild(context);
        //engine.addChild(host);
        //
        //service.setContainer(engine);
        //service.addConnector(connector);
        //
        //tomcat.addServlet(contextPath, "dispatcher", new DispatcherServlet(applicationContext));
        //context.addServletMappingDecoded("/*", "dispatcher");
        //
        //try {
        //    tomcat.start();
        //} catch (LifecycleException e) {
        //    e.printStackTrace();
        //}

        System.out.println("启动Tomcat服务器");
    }
}
