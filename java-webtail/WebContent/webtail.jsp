<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="org.webtail.tail.Tail"%>
<%@page import="java.io.File"%>
<%@page import="org.webtail.tail.LogFile"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet/less" type="text/css" href="css/style.less">
<script src="js/scroll.js" language="javascript" type="text/javascript"></script>
<script src="js/less.js" type="text/javascript"></script>
<title>Tail Log</title>
</head>
<body onscroll="javascript:currScrollPos();">
    <% 
   String strLineSep = System.getProperty("line.separator");
   if (strLineSep != null) {
      if(strLineSep.equals("\r\n")) {
          strLineSep = "\n";
      }
   }
   String strFileSep = System.getProperty("file.separator");
   String strDirName = "logs";

   
   String strCtxParam = config.getServletContext().getInitParameter("logdir");
   if (strCtxParam != null) {
       if(!strCtxParam.equals("")) {
           strDirName = strCtxParam;
       }
   }
      
   String strLogName = "";
   String strReqLogName = request.getParameter("log");
   File dir = new File(strDirName);
   java.io.FilenameFilter filter = new java.io.FilenameFilter() {
       public boolean accept(File dir, String name) {
           return name.endsWith(".log");
       }
   };
   String[] strLogFiles = dir.list(filter);
   java.util.Arrays.sort(strLogFiles);
   int iLines = 50;
   String strReqLines = request.getParameter("lines");
   if (strReqLines != null) {
       iLines = Integer.parseInt(strReqLines);
   }
   String strChecked = "";
   String strCheckbox = request.getParameter("gotobottom");
   if (strCheckbox != null) {
        strChecked = "checked";
   }
   
   String strPrevScroll = request.getParameter("currScroll");
   if (strPrevScroll == null) {
        strPrevScroll = "0";
   }
   
   if (strReqLogName != null) {
       strLogName = strReqLogName;
   } else if (strLogFiles.length >= 1){
        strLogName = strLogFiles[0];
   }
   
   
   File f = new File(strDirName + strFileSep + strLogName);
   LogFile logFile = new LogFile(f);
   Tail t = new Tail(logFile);
   String strTaillog = "";
   try {
       strTaillog = t.tailLog(iLines);
       strTaillog = strTaillog.replaceAll(strLineSep, "<br>");
   }catch(Exception e1) {
       
   }
%>
    <div class="headerform">
        <form id="headerform" action="webtail.jsp" method="post" name="taillog">
            <select name="log" onchange="this.form.submit();">
                <%for(String strLogFile : strLogFiles)  {
        if(strLogFile.equals(strLogName)) {%>
                <option selected><%=strLogFile %></option>
                <%} else {%>
                <option><%=strLogFile %></option>

                <%}
    }
    %>
            </select> 
            Number of lines: <input type="text" name="lines" value="<%=iLines %>" onchange="this.form.submit();" />
            Goto bottom: <input type="checkbox" id="gotobottom" name="gotobottom" value="gotobottom" <%=strChecked %> />
            <input type="hidden" id="currScroll" name="currScroll" value="" />
            <input type="hidden" id="prevScroll" name="prevScroll" value="<%=strPrevScroll %>" />
            <a href="javascript:pagerefresh()">Refresh</a>
        </form>
        
        <hr>
    </div>
    <p class="content"><%=strTaillog %></p>
</body>
</html>