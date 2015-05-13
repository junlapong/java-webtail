# Introduction #

  * copy the [war](http://code.google.com/p/java-webtail/downloads/list) file to your j2ee container
  * in most cases it should be automatically deployed
  * with http://yourhost/java-webtail/webtail.jsp you could display the webtail main page


## Change the Logs directory ##

  * go to your j2ee container directory (where your war file is)
  * go to the java-webtail/web-inf directory
  * Edit the web.xml file
  * there should be a block like
```
  <context-param>
    <description>path to log folder</description>
    <param-name>logdir</param-name>
    <param-value>logs</param-value>
  </context-param>
```
> > Change the param-value according to your needs.
  * You have to restart/reload the server/webapp


