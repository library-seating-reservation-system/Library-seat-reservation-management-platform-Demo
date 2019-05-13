<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
    <title>Library seat reservation management platform</title>
    <link href="lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />  
    <link rel="stylesheet" type="text/css" id="mylink"/>   
    <script src="lib/jquery/jquery-1.9.0.min.js" type="text/javascript"></script>    
    <script src="lib/ligerUI/js/ligerui.all.js" type="text/javascript"></script>  
    <script src="lib/jquery.cookie.js"></script>
    <script src="lib/json2.js"></script>
    <script src="indexdata.js" type="text/javascript"></script>
        <script type="text/javascript">
 
            var tab = null;
            var accordion = null;
            var tree = null;
            var tabItems = [];
            $(function ()
            {

                //布局
                $("#layout1").ligerLayout({
                    leftWidth: 190,
                    height: '100%',
                    heightDiff: -34,
                    space: 4,
                    onHeightChanged: f_heightChanged,
                    onLeftToggle: function ()
                    {
                        tab && tab.trigger('sysWidthChange');
                    },
                    onRightToggle: function ()
                    {
                        tab && tab.trigger('sysWidthChange');
                    }
                });

                var height = $(".l-layout-center").height();

                //Tab
                tab = $("#framecenter").ligerTab({
                    height: height,
                    showSwitchInTab : true,
                    showSwitch: true,
                    onAfterAddTabItem: function (tabdata)
                    {
                        tabItems.push(tabdata);
                        saveTabStatus();
                    },
                    onAfterRemoveTabItem: function (tabid)
                    { 
                        for (var i = 0; i < tabItems.length; i++)
                        {
                            var o = tabItems[i];
                            if (o.tabid == tabid)
                            {
                                tabItems.splice(i, 1);
                                saveTabStatus();
                                break;
                            }
                        }
                    },
                    onReload: function (tabdata)
                    {
                        var tabid = tabdata.tabid;
                        addFrameSkinLink(tabid);
                    }
                });

                //面板
                $("#accordion1").ligerAccordion({
                    height: height - 24, speed: null
                });

                $(".l-link").hover(function ()
                {
                    $(this).addClass("l-link-over");
                }, function ()
                {
                    $(this).removeClass("l-link-over");
                });
                //树
                $("#tree1").ligerTree({
                    data : indexdata,
                    checkbox: false,
                    slide: false,
                    nodeWidth: 120,
                    attribute: ['nodename', 'url'],
                    render : function(a){
                        if (!a.isnew) return a.text;
                        return '<a href="' + a.url + '" target="_blank">' + a.text + '</a>';
                    },
                    onSelect: function (node)
                    {
                        if (!node.data.url) return;
                        if (node.data.isnew)
                        { 
                            return;
                        }
                        var tabid = $(node.target).attr("tabid");
                        if (!tabid)
                        {
                            tabid = new Date().getTime();
                            $(node.target).attr("tabid", tabid)
                        } 
                        f_addTab(tabid, node.data.text, node.data.url);
                    }
                });

                function openNew(url)
                { 
                    var jform = $('#opennew_form');
                    if (jform.length == 0)
                    {
                        jform = $('<form method="post" />').attr('id', 'opennew_form').hide().appendTo('body');
                    } else
                    {
                        jform.empty();
                    } 
                    jform.attr('action', url);
                    jform.attr('target', '_blank'); 
                    jform.trigger('submit');
                };


                tab = liger.get("framecenter");
                accordion = liger.get("accordion1");
                tree = liger.get("tree1");
                $("#pageloading").hide();

                css_init();
                pages_init();
            });
            function f_heightChanged(options)
            {  
                if (tab)
                    tab.addHeight(options.diff);
                if (accordion && options.middleHeight - 24 > 0)
                    accordion.setHeight(options.middleHeight - 24);
            }
            function f_addTab(tabid, text, url)
            {
                tab.addTabItem({
                    tabid: tabid,
                    text: text,
                    url: url,
                    callback: function ()
                    {
               //         addShowCodeBtn(tabid); 
                        addFrameSkinLink(tabid); 
                    }
                });
            }
            
            function addFrameSkinLink(tabid)
            {
                var prevHref = getLinkPrevHref(tabid) || "";
                var skin = getQueryString("skin");
                if (!skin) return;
                skin = skin.toLowerCase();
                attachLinkToFrame(tabid, prevHref + skin_links[skin]);
            }
            var skin_links = {
                "aqua": "lib/ligerUI/skins/Aqua/css/ligerui-all.css",
                "gray": "lib/ligerUI/skins/Gray/css/all.css",
                "silvery": "lib/ligerUI/skins/Silvery/css/style.css",
                "gray2014": "lib/ligerUI/skins/gray2014/css/all.css"
            };
            function pages_init()
            {
                var tabJson = $.cookie('liger-home-tab'); 
                if (tabJson)
                { 
                    var tabitems = JSON2.parse(tabJson);
                    for (var i = 0; tabitems && tabitems[i];i++)
                    { 
                        f_addTab(tabitems[i].tabid, tabitems[i].text, tabitems[i].url);
                    } 
                }
            }
            function saveTabStatus()
            { 
                $.cookie('liger-home-tab', JSON2.stringify(tabItems));
            }
            function css_init()
            {
                var css = $("#mylink").get(0), skin = getQueryString("skin");
                $("#skinSelect").val(skin);
                $("#skinSelect").change(function ()
                { 
                    if (this.value)
                    {
                        location.href = "indexmanager.jsp?skin=" + this.value;
                    } else
                    {
                        location.href = "indexmanager.jsp";
                    }
                });

               
                if (!css || !skin) return;
                skin = skin.toLowerCase();
                $('body').addClass("body-" + skin); 
                $(css).attr("href", skin_links[skin]); 
            }
            function getQueryString(name)
            {
                var now_url = document.location.search.slice(1), q_array = now_url.split('&');
                for (var i = 0; i < q_array.length; i++)
                {
                    var v_array = q_array[i].split('=');
                    if (v_array[0] == name)
                    {
                        return v_array[1];
                    }
                }
                return false;
            }
            function attachLinkToFrame(iframeId, filename)
            { 
                if(!window.frames[iframeId]) return;
                var head = window.frames[iframeId].document.getElementsByTagName('head').item(0);
                var fileref = window.frames[iframeId].document.createElement("link");
                if (!fileref) return;
                fileref.setAttribute("rel", "stylesheet");
                fileref.setAttribute("type", "text/css");
                fileref.setAttribute("href", filename);
                head.appendChild(fileref);
            }
            function getLinkPrevHref(iframeId)
            {
                if (!window.frames[iframeId]) return;
                var head = window.frames[iframeId].document.getElementsByTagName('head').item(0);
                var links = $("link:first", head);
                for (var i = 0; links[i]; i++)
                {
                    var href = $(links[i]).attr("href");
                    if (href && href.toLowerCase().indexOf("ligerui") > 0)
                    {
                        return href.substring(0, href.toLowerCase().indexOf("lib") );
                    }
                }
            }
     </script> 
<style type="text/css"> 
    body,html{height:100%;}
    body{ padding:0px; margin:0;  overflow:hidden;}  
    .l-link{ display:block; height:26px; line-height:26px; padding-left:10px; text-decoration:underline; color:#333;}
    .l-link2{text-decoration:underline; color:white; margin-left:2px;margin-right:2px;}
    .l-layout-top{background:#102A49; color:White;}
    .l-layout-bottom{ background:#E5EDEF; text-align:center;}
    #pageloading{position:absolute; left:0px; top:0px; background:white url('loading.gif') no-repeat center; width:100%; height:100%;z-index:99999;}
    .l-link{ background:#0678c3;color:#fff;text-decoration:none;display:block; line-height:30px; height:30px; padding-left:16px;border:1px solid #ccc; margin-top:0px;}
    .l-link-over{ background:#FFEEAC; border:1px solid #ccc;color:#000} 
    .l-winbar{ background:#2B5A76; height:30px; position:absolute; left:0px; bottom:0px; width:100%; z-index:99999;}
    .space{ color:#E7E7E7;}
    /* 顶部 */ 
    .l-topmenu{ margin:0; padding:0; height:50px; line-height:50px; background:url('img/title2.jpg') repeat-x bottom;  position:relative;  }
    .l-topmenu-logo{ color:#fff;font-weight:bold;font-size: 24px; padding-left:5px; line-height:50px;background-size: 50px auto;}
    .l-topmenu-welcome{  position:absolute; height:50px; line-height:50px;  right:150px; top:0px;color:#070A0C;}
    .l-topmenu-admin{ position:absolute; height:24px; line-height:24px;  right:9px; top:1px;color:#fff;}
    .l-topmenu-admin a{ color:#E7E7E7; text-decoration:none}
    .l-topmenu-admin p{ text-decoration:none}
    .body-gray2014 #framecenter{
        margin-top:3px;
    }
    .viewsourcelink {
         background:#B3D9F7;  display:block; position:absolute; right:10px; top:3px; padding:6px 4px; color:#333; text-decoration:underline;
    }
    .viewsourcelink-over {
        background:#81C0F2;
    }
    .l-topmenu-welcome label {color:white;
    }
    #skinSelect {
        margin-right: 6px;
    }
 </style>
</head>
<body style="padding:0px;background:#6b9ccc;">  
 
<div id="topmenu" class="l-topmenu">
    <div class="l-topmenu-logo">Library seat reservation management platform</div>
    <div class="l-topmenu-admin">
        	<p class="l-link2"><strong title="2 unread messages" style="cursor: pointer;">Super administrator</strong>,Welcome!</p>
        	<p><a href="#" class="l-link2" target="_blank">Help center</a>|<a href="log/logchoose.jsp" class="l-link2">Retreat safely</a></p>
        </div>
</div>
  <div id="layout1" style="width:99.2%; margin:0 auto; margin-top:4px; "> 
        <div position="left"  title="Main menu" id="accordion1">
                     <div title="Seat management" >
                        <a class="l-link" href="javascript:f_addTab('manager','Seat management','manager/manager.jsp')">Seat management</a>  
                    </div>
                    <div title="Personnel management">
                   
						<a class="l-link" href="javascript:f_addTab('delete','Delete users','manager/delete.jsp')">Delete users</a>
                    </div>
                    <div title="Repair information view">
                        <a class="l-link" href="javascript:f_addTab('Repair','Repair information view','Repair/Repair.jsp')">Repair information view</a>   
                    </div>
        </div>
        <div position="center" id="framecenter"> 
            <div tabid="home" title="my home page" style="height:300px" >
                <iframe frameborder="0" name="home" id="home" src="welcome.jsp"></iframe>
            </div> 
        </div>         
    </div>
    <div  style="height:32px; line-height:32px; text-align:center;">
            Copyright © 2019-2020
    </div>
    <div style="display:none"></div>
</body>
</html>