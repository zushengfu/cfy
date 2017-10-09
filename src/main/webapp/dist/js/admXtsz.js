/*
* @Author: Marte
* @Date:   2017-08-31 14:40:37
* @Last Modified by:   Marte
* @Last Modified time: 2017-09-29 20:20:31
*/

$(document).ready(function() {
    $.ajax({
        url: '/cfy/properties/getSecurityRank.do',
        type: 'POST',
        contentType:"application/json"
    })
    .done(function(backdata) {
        console.log(backdata);
        var SRKJnFomGrp,SRKJnFomGrp1,SRKJnFomGrp2;
                  $("#admJnSRKFom").empty();
                  SRKJnFomGrp='<div class="form-group"><label class="control-label col-md-2 " style="text-align: left;">选项 </label><label class="control-label col-md-6" style="text-align: center;">名称</label><label class="control-label col-md-3 ">操作</label></div>';
                  $("#admJnSRKFom").append(SRKJnFomGrp);
        for(var i=0;i<backdata.length;i++){
            SRKJnFomGrp1='<div class="form-group"><label class="col-md-3 "><input type="radio"> </label><label class="col-md-4 "><input type="text" class="form-control "  name=';
            SRKJnFomGrp2='></label><label class="control-label col-md-4 col-md-offset-1 "><a href="#">删除</a></label></div>';
            SRKJnFomGrp=SRKJnFomGrp1+backdata[i].skey+SRKJnFomGrp2;
            $("#admJnSRKFom").append(SRKJnFomGrp);
            var admJnSRKquery="[name='"+backdata[i].skey+"']";
            $(admJnSRKquery).val(backdata[i].svalue);
        }
          $('#admJnSRKFomTj').on('click',function(){
        	  console.log('sads');
            SRKJnFomGrp1='<div class="form-group"><label class="col-md-3 "><input type="radio"> </label><label class="col-md-4 "><input type="text" class="form-control "  name="';
            SRKJnFomGrp2='"></label><label class="control-label col-md-4 col-md-offset-1 "><a href="#">删除</a></label> </div>';
            SRKJnFomGrp=SRKJnFomGrp1+'key'+$("#admJnSRKFom").children().length+SRKJnFomGrp2;
            $("#admJnSRKFom").append(SRKJnFomGrp);
        })
        $('#admJnSRKFomBc').on('click',function(){
      	  
      	  var admJnSRKAyy=[];
      	  var admJnSRKObj={};
      	  var admJnSRKAyyName;
      	  for(var i=1;i<$("#admJnSRKFom").children().length;i++){
      		admJnSRKObj={};
      		admJnSRKAyyName="[name="+'key'+i+"]";      		
      		admJnSRKObj.skey='key'+i;
      		admJnSRKObj.svalue=$(admJnSRKAyyName).val();
      		admJnSRKAyy.push(admJnSRKObj);
      		console.log(admJnSRKObj);
      	  };
      	console.log($("#admJnSRKFom").children().length);
      	$.ajax({
            url: '/cfy/properties/setSecurityRank.do',
            type: 'POST',
            contentType:"application/json",
            data:JSON.stringify(admJnSRKAyy)
        })
        .done(function(backdata) {
           console.log(backdata);
        })
        .fail(function() {
            console.log("error");
        })
        .always(function() {
            console.log("complete");
        });
      })
    })
    .fail(function() {
        console.log("error");
    })
    .always(function() {
        console.log("complete");
    });

    $("#admJnSRKFomHf").on('click',function(){

        $.ajax({
            url: '/cfy/properties/restoreSecurityRank.do',
            type: 'POST'
        })
        .done(function() {
            alert("恢复成功！");
            $.ajax({
                url: '/cfy/properties/getSecurityRank.do',
                type: 'POST'

            })
            .done(function() {
                console.log("success");
            })
            .fail(function() {
                console.log("error");
            })
            .always(function() {
                console.log("complete");
            });

    })
    .fail(function() {
        console.log("error");
    })
    .always(function() {
        console.log("complete");
    });
    });
    
    $('#admJnSRKFom').on('click',function(event){
    	
    	console.log($(event.target).parent().parent().find('input')[1].getAttribute('name'));
    	if($(event.target).html()==="删除"){
    		var delSRKObj={};
    		delSRKObj.skey=$(event.target).parent().parent().find('input')[1].getAttribute('name');
    		$.ajax({
      	      url: '/cfy/properties/deleteSecurityRank.do',
      	      type: 'POST',
      	      contentType:"application/json",
      	      data: JSON.stringify(delSRKObj)
      	    })
      	    .done(function() {
      	      console.log("success");
      	    })
      	    .fail(function() {
      	      console.log("error");
      	    })
      	    .always(function() {
      	      console.log("complete");
      	    });
      	    
    	}
    	
    })
    


});